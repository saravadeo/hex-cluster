package com.hex.cluster.entity;

public class HexNode extends Coordinate {
	private String name;
	private int colEvenOrOrdd;

	public HexNode(final String name, final int x, final int y, final int colEvenOrOrdd) {
		super(x, y);
		this.setName(name);
		this.setColEvenOrOrdd(colEvenOrOrdd);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColEvenOrOrdd() {
		return colEvenOrOrdd;
	}

	public void setColEvenOrOrdd(int colEvenOrOrdd) {
		this.colEvenOrOrdd = colEvenOrOrdd;
	}

}
