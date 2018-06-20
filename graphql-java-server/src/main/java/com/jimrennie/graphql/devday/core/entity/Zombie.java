package com.jimrennie.graphql.devday.core.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "ZOMBIE")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@ToString
public class Zombie {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "ZOMBIE_TYPE", nullable = false)
	private String zombieType;

	@Column(name = "HITPOINTS", nullable = false)
	private Integer hitPoints = 0;

	@Column(name = "GARDEN_ID")
	private Long gardenId;

}
