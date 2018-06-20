package com.jimrennie.graphql.devday.graphql.resolver;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.jimrennie.graphql.devday.core.service.PlantService;
import com.jimrennie.graphql.devday.core.service.ZombieService;
import com.jimrennie.graphql.devday.graphql.api.GardenDto;
import com.jimrennie.graphql.devday.graphql.api.PlantDto;
import com.jimrennie.graphql.devday.graphql.api.ZombieDto;
import com.jimrennie.graphql.devday.graphql.assembler.PlantDtoAssembler;
import com.jimrennie.graphql.devday.graphql.assembler.ZombieDtoAssembler;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Component
public class GardenResolver implements GraphQLResolver<GardenDto> {

	@Autowired
	private PlantService plantService;
	@Autowired
	private PlantDtoAssembler plantDtoAssembler;
	@Autowired
	private ZombieService zombieService;
	@Autowired
	private ZombieDtoAssembler zombieDtoAssembler;

	public List<PlantDto> getPlants(GardenDto gardenDto, String plantType) {
		return Optional.ofNullable(plantType)
				.map(type -> plantService.findPlantsByGardenIdAndPlantType(gardenDto.getId(), type))
				.orElseGet(() -> plantService.findPlantsByGardenId(gardenDto.getId()))
				.stream()
				.map(plantDtoAssembler::assemble)
				.collect(Collectors.toList());
	}

	public List<ZombieDto> getZombies(GardenDto gardenDto, String zombieType) {
		BatchLoader<GardenDtoType, ZombieDto> batchLoader = this::loadZombies;
		DataLoader<GardenDtoType, ZombieDto> zombieLoder = new DataLoader<GardenDtoType, ZombieDto>(batchLoader);
		zombieLoder.load(new GardenDtoType().setGarden(gardenDto).setType(zombieType));
		return zombieLoder.dispatchAndJoin();
	}
	
	private CompletableFuture<List<ZombieDto>> loadZombies(List<GardenDtoType> gardenDto) {
		return gardenDto
				   .stream()
				   .map(this::supplyZombies)
				   .map(CompletableFuture::supplyAsync)
				   .collect(Collectors.collectingAndThen(Collectors.toList(), List::stream))
				   .flatMap(CompletableFuture::join)
				   .collect(Collectors.collectingAndThen(Collectors.toList(), CompletableFuture::completedFuture));
	}
	
	private Supplier<Stream<ZombieDto>> supplyZombies(GardenDtoType gardenDtoType) {
		return ()->findZombies(gardenDtoType);
	}
	
	private Stream<ZombieDto> findZombies(GardenDtoType gardenDtoType) {
		return Optional.ofNullable(gardenDtoType.getType())
				.map(type -> zombieService.findZombiesByGardenIdAndZombieType(gardenDtoType.garden.getId(), type))
				.orElseGet(() -> zombieService.findZombiesByGardenId(gardenDtoType.garden.getId()))
				.stream()
				.map(zombieDtoAssembler::assemble);
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	class GardenDtoType {
		GardenDto garden;
		String type;
	}
}
