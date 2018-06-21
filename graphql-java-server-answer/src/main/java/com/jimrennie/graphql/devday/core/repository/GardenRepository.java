package com.jimrennie.graphql.devday.core.repository;

import com.jimrennie.graphql.devday.core.entity.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {
}
