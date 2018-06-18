package com.jimrennie.graphql.devday.core.repository;

import com.jimrennie.graphql.devday.core.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

	List<Plant> findPlantsByGardenId(Long gardenId);
	List<Plant> findPlantsByGardenIdAndPlantTypeIgnoreCase(Long gardenId, String plantType);
	List<Plant> findAllByPlantTypeIgnoreCase(String plantType);

}
