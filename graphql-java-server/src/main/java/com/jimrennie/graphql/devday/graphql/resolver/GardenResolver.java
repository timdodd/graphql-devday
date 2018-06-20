package com.jimrennie.graphql.devday.graphql.resolver;

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
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

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

		BatchLoader<GardenDtoType, ZombieDto> batchLoader = new BatchLoader<GardenDtoType, ZombieDto>() {
			@Override
			public CompletionStage<List<ZombieDto>> load(List<GardenDtoType> gardenDtoType) {
				return CompletableFuture.supplyAsync(() -> {
					return gardenDtoType.stream()
							.flatMap(gdt -> {
								return Optional.ofNullable(gdt.getType())
										.map(type -> zombieService.findZombiesByGardenIdAndZombieType(gdt.garden.getId(), gdt.getType()))
										.orElseGet(() -> zombieService.findZombiesByGardenId(gdt.garden.getId()))
										.stream()
										.map(zombieDtoAssembler::assemble);
							})
							.collect(Collectors.toList());
				});
			}
		};
		DataLoader<GardenDtoType, ZombieDto> zombieLoder = new DataLoader<GardenDtoType, ZombieDto>(batchLoader);
		zombieLoder.load(new GardenDtoType().setGarden(gardenDto).setType(zombieType));
		return zombieLoder.dispatchAndJoin();
	}

	@Getter
	@Setter
	@Accessors(chain = true)
	class GardenDtoType {
		GardenDto garden;
		String type;
	}
}
