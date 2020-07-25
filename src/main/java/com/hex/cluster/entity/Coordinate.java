package com.hex.cluster.entity;

public class Coordinate {
	public int x;
	public int y;

	@Override
	public boolean equals(final Object obj) {
		if (this.x == ((Coordinate) obj).x && this.y == ((Coordinate) obj).y) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int tmp = (this.y + ((this.x + 1) / 2));
		return this.x + (tmp * tmp);
	}

	public Coordinate(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public static Coordinate build(final int x, final int y) {
		return new Coordinate(x, y);
	}

	public static Coordinate build(final HexNode node) {
		return new Coordinate(node.x, node.y);
	}
}
