package com.jimrennie.graphql.devday.core.service;

import com.jimrennie.graphql.devday.core.entity.Plant;
import com.jimrennie.graphql.devday.core.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

	public Optional<Plant> getPlantById(Long id) {
		return plantRepository.findById(id);
	}

	public Plant savePlant(Plant plant) {
		return plantRepository.save(plant);
	}

	public void deletePlant(Long id) {
		plantRepository.deleteById(id);
	}
}
