package com.jimrennie.graphql.devday;

import com.jimrennie.graphql.devday.core.entity.Garden;
import com.jimrennie.graphql.devday.core.entity.Plant;
import com.jimrennie.graphql.devday.core.service.GardenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

@Slf4j
@SpringBootApplication
public class GraphQLApplication {

	@Autowired
	private GardenService gardenService;

	public static void main(String[] args) {
		SpringApplication.run(GraphQLApplication.class, args);
	}

	@PostConstruct
	public void createSeedData() {

		log.info("--- Creating Seed data Start ---");

		log.info("Garden [{}]", gardenService.saveGarden(new Garden()
				.setTitle("My First Garden")
				.setDescription("This garden is full of hope, but also full of weeds.")
				.addPlants(asList(
						new Plant()
								.setPlantType("Potato")
								.setQuantity(5),
						new Plant()
								.setPlantType("Dandelion")
								.setQuantity(100),
						new Plant()
								.setPlantType("Tomato")
								.setQuantity(8)))));

		log.info("Garden [{}]", gardenService.saveGarden(new Garden()
				.setTitle("Herb Garden")
				.setDescription("Parsley sage rosemary and thyme... and basil and dill")
				.addPlants(asList(
						new Plant()
								.setPlantType("Parsley")
								.setQuantity(1),
						new Plant()
								.setPlantType("Sage")
								.setQuantity(3),
						new Plant()
								.setPlantType("Rosemary")
								.setQuantity(5),
						new Plant()
								.setPlantType("Basil")
								.setQuantity(4),
						new Plant()
								.setPlantType("Dill")
								.setQuantity(2)))));

		log.info("--- Creating Seed Data Complete ---");
	}

}
