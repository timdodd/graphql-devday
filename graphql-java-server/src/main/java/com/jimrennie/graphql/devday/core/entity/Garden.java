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
public class Garden {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "GARDEN_ID", referencedColumnName = "ID")
	@Setter(AccessLevel.NONE)
	private List<Plant> plants = new ArrayList<>();
}
