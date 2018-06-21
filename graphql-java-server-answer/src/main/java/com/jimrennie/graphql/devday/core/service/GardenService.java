package com.jimrennie.graphql.devday.core.service;

import com.jimrennie.graphql.devday.core.entity.Garden;
import com.jimrennie.graphql.devday.core.repository.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GardenService {

	@Autowired
	private GardenRepository gardenRepository;

	public List<Garden> findAllGardens() {
		return gardenRepository.findAll();
	}

	public Optional<Garden> getGardenById(Long id) {
		return gardenRepository.findById(id);
	}

	public Garden saveGarden(Garden garden) {
		return gardenRepository.save(garden);
	}

	public void deleteGarden(Long id) {
		gardenRepository.deleteById(id);
	}
}
