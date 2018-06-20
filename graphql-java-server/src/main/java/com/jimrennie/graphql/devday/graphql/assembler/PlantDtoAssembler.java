package com.jimrennie.graphql.devday.graphql.assembler;

import com.jimrennie.graphql.devday.core.entity.Plant;
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

	public Plant disassemble(PlantDto plant) {
		return new Plant()
				.setId(plant.getId())
				.setPlantType(plant.getPlantType())
				.setQuantity(plant.getQuantity());
	}

}
