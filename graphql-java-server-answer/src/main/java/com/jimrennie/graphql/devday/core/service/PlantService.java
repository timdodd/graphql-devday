package com.jimrennie.graphql.devday.core.service;

import com.jimrennie.graphql.devday.core.entity.Plant;
import com.jimrennie.graphql.devday.core.repository.PlantRepository;
import com.jimrennie.graphql.devday.graphql.api.PlantDto;
import graphql.execution.batched.Batched;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlantService {

	@Autowired
	private PlantRepository plantRepository;

	public List<Plant> findAllPlants() {
		return plantRepository.findAll();
	}

	public List<Plant> findPlantsByPlantType(String plantType) {
		return plantRepository.findAllByPlantTypeIgnoreCase(plantType);
	}

	public List<Plant> findPlantsByGardenId(Long gardenId) {
		return plantRepository.findPlantsByGardenId(gardenId);
	}

	public List<Plant> findPlantsByGardenIdAndPlantType(Long gardenId, String plantType) {
		return plantRepository.findPlantsByGardenIdAndPlantTypeIgnoreCase(gardenId, plantType);
	}

	public Plant getPlantById(Long id) {
		return plantRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Plant id [" + id + "] not found"));
	}

	public Plant savePlant(Plant plant) {
		return plantRepository.save(plant);
	}

	public void deletePlant(Long id) {
		plantRepository.deleteById(id);
	}

	public void incrementPlantQuantity(Long id) {
		plantRepository.incrementQuantity(id);
	}

}
