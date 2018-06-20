package com.jimrennie.graphql.devday.core.repository;

import com.jimrennie.graphql.devday.core.entity.Zombie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZombieRepository extends JpaRepository<Zombie, Long> {

	List<Zombie> findZombiesByGardenId(Long gardenId);
	List<Zombie> findZombiesByGardenIdAndZombieTypeIgnoreCase(Long gardenId, String zombieType);
	List<Zombie> findAllByZombieTypeIgnoreCase(String zombieType);

}
