package com.jimrennie.graphql.devday.graphql.assembler;

import com.jimrennie.graphql.devday.core.entity.Zombie;
import com.jimrennie.graphql.devday.graphql.api.ZombieDto;
import org.springframework.stereotype.Component;

@Component
public class ZombieDtoAssembler {

	public ZombieDto assemble(Zombie zombie) {
		return new ZombieDto()
				.setId(zombie.getId())
				.setZombieType(zombie.getZombieType())
				.setHitPoints(zombie.getHitPoints());
	}

	public Zombie disassemble(ZombieDto zombie) {
		return new Zombie()
				.setId(zombie.getId())
				.setZombieType(zombie.getZombieType())
				.setHitPoints(zombie.getHitPoints());
	}

}
