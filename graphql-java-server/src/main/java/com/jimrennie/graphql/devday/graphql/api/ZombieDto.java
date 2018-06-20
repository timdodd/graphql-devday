package com.jimrennie.graphql.devday.graphql.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ZombieDto {

	private Long id;
	private String zombieType;
	private Integer hitPoints;

}
