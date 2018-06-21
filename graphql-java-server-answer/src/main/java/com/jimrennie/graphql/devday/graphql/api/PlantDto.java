package com.jimrennie.graphql.devday.graphql.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlantDto {

	private Long id;
	private String plantType;
	private Integer quantity;

}
