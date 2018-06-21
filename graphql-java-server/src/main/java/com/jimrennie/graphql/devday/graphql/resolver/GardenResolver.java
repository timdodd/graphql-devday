package com.jimrennie.graphql.devday.graphql.resolver;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

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

import lombok.Data;
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

	public CompletionStage<List<ZombieDto>> getZombies(GardenDto gardenDto, String zombieType) {
		return CompletableFuture.supplyAsync(()->findZombies(new GardenDtoType().setGarden(gardenDto).setType(zombieType)));
	}
	
	private List<ZombieDto> findZombies(GardenDtoType gardenDtoType) {
		final long startTime = System.currentTimeMillis();
		return Optional.ofNullable(gardenDtoType.getType())
				.map(type -> zombieService.findZombiesByGardenIdAndZombieType(gardenDtoType.garden.getId(), type))
				.orElseGet(() -> zombieService.findZombiesByGardenId(gardenDtoType.garden.getId()))
				.stream()
				.peek(t -> System.out.println("Time taken for = "+gardenDtoType+" was - "+(System.currentTimeMillis()-startTime)))
				.map(zombieDtoAssembler::assemble)
				.collect(Collectors.toList());
	}

	@Data
	@Accessors(chain = true)
	class GardenDtoType {
		GardenDto garden;
		String type;
	}
}
