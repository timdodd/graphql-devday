package com.jimrennie.graphql.devday.graphql.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlantInputDto {

	private String plantType;
	private Integer quantity;

}
