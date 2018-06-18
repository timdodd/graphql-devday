package com.jimrennie.graphql.devday.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.jimrennie.graphql.devday.core.service.PlantService;
import com.jimrennie.graphql.devday.graphql.api.GardenDto;
import com.jimrennie.graphql.devday.graphql.api.PlantDto;
import com.jimrennie.graphql.devday.graphql.assembler.PlantDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GardenResolver implements GraphQLResolver<GardenDto> {

	@Autowired
	private PlantService plantService;
	@Autowired
	private PlantDtoAssembler plantDtoAssembler;

	public List<PlantDto> getPlants(GardenDto gardenDto, String plantType) {
		return Optional.ofNullable(plantType)
				.map(type -> plantService.findPlantsByGardenIdAndPlantType(gardenDto.getId(), type))
				.orElseGet(() -> plantService.findPlantsByGardenId(gardenDto.getId()))
				.stream()
				.map(plantDtoAssembler::assemble)
				.collect(Collectors.toList());
	}
}
