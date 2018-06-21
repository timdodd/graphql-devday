package com.jimrennie.graphql.devday;

import com.jimrennie.graphql.devday.core.entity.Garden;
import com.jimrennie.graphql.devday.core.entity.Plant;
import com.jimrennie.graphql.devday.core.entity.Zombie;
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
								.setPlantType("Basil")
								.setQuantity(11),
						new Plant()
								.setPlantType("Tomato")
								.setQuantity(8)))
				.addZombies(asList(
						new Zombie()
								.setZombieType("Classic Stumble")
								.setHitPoints(10),
						new Zombie()
								.setZombieType("Fast and Weak")
								.setHitPoints(2),
						new Zombie()
								.setZombieType("Mummy")
								.setHitPoints(15),
						new Zombie()
								.setZombieType("Michael Jackson Zombie")
								.setHitPoints(15),
						new Zombie()
								.setZombieType("Evil Dead Zombie")))));

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
								.setQuantity(2)))
				.addZombies(asList(
						new Zombie()
								.setZombieType("Classic Stumble")
								.setHitPoints(10),
						new Zombie()
								.setZombieType("Fast and Weak")
								.setHitPoints(2),
						new Zombie()
								.setZombieType("Mummy")
								.setHitPoints(15),
						new Zombie()
								.setZombieType("Michael Jackson Zombie")
								.setHitPoints(15),
						new Zombie()
								.setZombieType("Evil Dead Zombie")
								.setHitPoints(30)))));

		log.info("Garden [{}]", gardenService.saveGarden(new Garden()
				.setTitle("Zombie Patch")
				.setDescription("Darkness falls across the land " +
						"The midnight hour is close at hand " +
						"Creatures crawl in search of blood " +
						"To terrorize y'all's neighborhood " +
						"And whosoever shall be found " +
						"Without the soul for getting down " +
						"Must stand and face the hounds of hell " +
						"And rot inside a corpse's shell")
				.addZombies(asList(
						new Zombie()
								.setZombieType("Classic Stumble")
								.setHitPoints(10),
						new Zombie()
								.setZombieType("Fast and Weak")
								.setHitPoints(2),
						new Zombie()
								.setZombieType("Mummy")
								.setHitPoints(15),
						new Zombie()
								.setZombieType("Michael Jackson Zombie")
								.setHitPoints(15),
						new Zombie()
								.setZombieType("Evil Dead Zombie")
								.setHitPoints(30)))));



		log.info("--- Creating Seed Data Complete ---");
	}

}
