package com.gmail.gm.jcant;

public class BBTPers {
	private String name;
	private int generation = 0;

	public BBTPers() {
		super();
	}

	public BBTPers(String name) {
		super();
		this.generation = 0;
		this.name = name;
	}

	public BBTPers(String name, int generation) {
		super();
		this.generation = generation;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGeneration() {
		return generation;
	}

	@Override
	public String toString() {
		return "\t" + name + " generation=" + generation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + generation;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BBTPers other = (BBTPers) obj;
		if (generation != other.generation)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	protected BBTPers clone() {
		BBTPers newPers = new BBTPers(this.name, this.generation + 1);
		return newPers;
	}

}
