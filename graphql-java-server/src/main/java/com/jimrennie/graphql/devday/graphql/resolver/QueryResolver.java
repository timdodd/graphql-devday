package com.jimrennie.graphql.devday.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.jimrennie.graphql.devday.core.entity.Garden;
import com.jimrennie.graphql.devday.core.service.GardenService;
import com.jimrennie.graphql.devday.graphql.api.GardenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryResolver implements GraphQLQueryResolver {

	@Autowired
	private GardenService gardenService;

	public List<GardenDto> getGardens() {
		return gardenService.getGardens()
				.stream()
				.map(this::toGardenDto)
				.collect(Collectors.toList());
	}

	private GardenDto toGardenDto(Garden garden) {
		return new GardenDto()
				.setId(garden.getId())
				.setTitle(garden.getTitle())
				.setDescription(garden.getDescription());
	}

}
