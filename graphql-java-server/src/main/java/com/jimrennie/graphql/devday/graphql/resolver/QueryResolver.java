package com.jimrennie.graphql.devday.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jimrennie.graphql.devday.core.entity.Garden;
import com.jimrennie.graphql.devday.core.entity.Plant;
import com.jimrennie.graphql.devday.core.service.GardenService;
import com.jimrennie.graphql.devday.core.service.PlantService;
import com.jimrennie.graphql.devday.graphql.api.GardenDto;
import com.jimrennie.graphql.devday.graphql.api.PlantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryResolver implements GraphQLQueryResolver {

	@Autowired
	private GardenService gardenService;
	@Autowired
	private PlantService plantService;

	public List<GardenDto> getGardens() {
		return gardenService.findAllGardens()
				.stream()
				.map(this::toGardenDto)
				.collect(Collectors.toList());
	}

	public List<PlantDto> getPlants(String plantType) {
		return plantService.findPlantsByPlantType(plantType)
				.stream()
				.map(this::toPlantDto)
				.collect(Collectors.toList());
	}

	private GardenDto toGardenDto(Garden garden) {
		return new GardenDto()
				.setId(garden.getId())
				.setTitle(garden.getTitle())
				.setDescription(garden.getDescription());
	}

	private PlantDto toPlantDto(Plant plant) {
		return new PlantDto()
				.setId(plant.getId())
				.setPlantType(plant.getPlantType())
				.setQuantity(plant.getQuantity());
	}

}
