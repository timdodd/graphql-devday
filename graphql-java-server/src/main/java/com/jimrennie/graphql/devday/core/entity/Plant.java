package com.jimrennie.graphql.devday.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "PLANT")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
public class Plant {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity = 0;

}
