package com.jimrennie.graphql.devday.core.service;

import com.jimrennie.graphql.devday.core.entity.Zombie;
import com.jimrennie.graphql.devday.core.repository.ZombieRepository;
import graphql.execution.batched.Batched;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ZombieService {

	@Autowired
	private ZombieRepository zombieRepository;

	public List<Zombie> findAllZombies() {
		return zombieRepository.findAll();
	}

	public List<Zombie> findZombiesByZombieType(String zombieType) {
		return zombieRepository.findAllByZombieTypeIgnoreCase(zombieType);
	}

	public List<Zombie> findZombiesByGardenId(Long gardenId) {
		try {
			Thread.sleep(2000);
			log.info("Aaaaaaargggggghhhh!!!!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return zombieRepository.findZombiesByGardenId(gardenId);
	}

	public List<Zombie> findZombiesByGardenIdAndZombieType(Long gardenId, String zombieType) {
		return zombieRepository.findZombiesByGardenIdAndZombieTypeIgnoreCase(gardenId, zombieType);
	}

	public Optional<Zombie> getZombieById(Long id) {
		return zombieRepository.findById(id);
	}

	public Zombie manifestZombie(Zombie zombie) {
		return zombieRepository.save(zombie);
	}

	public void buryZombie(Long id) {
		zombieRepository.deleteById(id);
	}

}
