package com.jimrennie.graphql.devday.graphql.assembler;

import com.jimrennie.graphql.devday.core.entity.Garden;
import com.jimrennie.graphql.devday.graphql.api.GardenDto;
import org.springframework.stereotype.Component;

@Component
public class GardenDtoAssembler {

	public GardenDto assemble(Garden garden) {
		return new GardenDto()
				.setId(garden.getId())
				.setTitle(garden.getTitle())
				.setDescription(garden.getDescription());
	}

}
