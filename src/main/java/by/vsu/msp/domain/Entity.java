package by.vsu.msp.domain;

import java.io.Serializable;

abstract public class Entity<PK extends Serializable> implements Serializable {
	private PK id;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}
}
