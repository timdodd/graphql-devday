package com.jimrennie.graphql.devday.graphql.api;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GardenDto {

	private Long id;
	private String title;
	private String description;

}
