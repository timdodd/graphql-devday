package com.jimrennie.graphql.devday.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.jimrennie.graphql.devday.core.entity.Plant;
import com.jimrennie.graphql.devday.core.service.PlantService;
import com.jimrennie.graphql.devday.core.service.ZombieService;
import com.jimrennie.graphql.devday.graphql.api.PlantDto;
import com.jimrennie.graphql.devday.graphql.api.ZombieDto;
import com.jimrennie.graphql.devday.graphql.assembler.PlantDtoAssembler;
import com.jimrennie.graphql.devday.graphql.assembler.ZombieDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

	@Autowired
	private PlantService plantService;
	@Autowired
	private PlantDtoAssembler plantDtoAssembler;
	@Autowired
	private ZombieService zombieService;
	@Autowired
	private ZombieDtoAssembler zombieDtoAssembler;

	public PlantDto addPlant(Long gardenId, String plantType, Integer quantity) {
		return plantDtoAssembler.assemble(plantService.savePlant(new Plant().setPlantType(plantType).setQuantity(quantity).setGardenId(gardenId)));
	}

	public ZombieDto plantHitZombie(Long zombieId, Integer hitPoints) {
		return zombieDtoAssembler.assemble(zombieService.injureZombie(zombieId, hitPoints));
	}
}
