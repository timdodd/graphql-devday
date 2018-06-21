package com.jimrennie.graphql.devday.core.repository;

import com.jimrennie.graphql.devday.core.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

	List<Plant> findPlantsByGardenId(Long gardenId);

	List<Plant> findPlantsByGardenIdAndPlantTypeIgnoreCase(Long gardenId, String plantType);

	List<Plant> findAllByPlantTypeIgnoreCase(String plantType);

	@Modifying(clearAutomatically = true)
	@Query("UPDATE Plant p SET p.quantity = p.quantity + 1 WHERE p.id = :id")
	void incrementQuantity(@Param("id") Long id);

}
