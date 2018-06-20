package com.jimrennie.graphql.devday.core.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GARDEN")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Accessors(chain = true)
@ToString
public class Garden {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION", length = 1000)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "GARDEN_ID", referencedColumnName = "ID")
	@Setter(AccessLevel.NONE)
	private List<Plant> plants = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "GARDEN_ID", referencedColumnName = "ID")
	@Setter(AccessLevel.NONE)
	private List<Zombie> zombies = new ArrayList<>();


	@Transient
	public Garden addPlants(List<Plant> plants) {
		this.plants.addAll(plants);
		return this;
	}

	@Transient
	public Garden addZombies(List<Zombie> zombies) {
		this.zombies.addAll(zombies);
		return this;
	}
}
