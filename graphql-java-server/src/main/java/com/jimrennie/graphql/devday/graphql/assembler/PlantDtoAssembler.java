package com.jimrennie.graphql.devday.graphql.assembler;

import com.jimrennie.graphql.devday.core.entity.Garden;
import com.jimrennie.graphql.devday.core.entity.Plant;
import com.jimrennie.graphql.devday.graphql.api.GardenDto;
import com.jimrennie.graphql.devday.graphql.api.PlantDto;
import org.springframework.stereotype.Component;

@Component
public class PlantDtoAssembler {

	public PlantDto assemble(Plant plant) {
		return new PlantDto()
				.setId(plant.getId())
				.setPlantType(plant.getPlantType())
				.setQuantity(plant.getQuantity());
	}

}
