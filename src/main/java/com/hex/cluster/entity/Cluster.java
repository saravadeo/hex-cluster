package com.hex.cluster.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.hex.cluster.exception.RestApiException;

public class Cluster {
	private final Map<String, HexNode> mapOfHexNode;
	private final Map<Coordinate, HexNode> mapOfCoordinateNode;

	public Cluster() {
		mapOfHexNode = new HashMap<String, HexNode>();
		mapOfCoordinateNode = new HashMap<Coordinate, HexNode>();
		this.addNode("ax", null, -1);
	}

	public Collection<HexNode> getAllNodes() {
		return this.mapOfHexNode.values();
	}

	public HexNode addNode(final String name, final String existingNodeName, final int border) {
		int x = 0, y = 0, colEvenOrOrdd = 0;
		if (this.mapOfHexNode.containsKey(name)) {
			throw new RestApiException(400, "Add Node", "Node with name already exists.");
		}
		if (border != -1) {
			HexNode existingNode = mapOfHexNode.get(existingNodeName);
			if (existingNode == null) {
				throw new RestApiException(400, "Add Node", "Existing node not in cluster.");
			}
			if (existingNode.getColEvenOrOrdd() == 0) {
				colEvenOrOrdd = (border % 3 == 0) ? 0 : 1;
			} else {
				colEvenOrOrdd = (border % 3 == 0) ? 1 : 0;
			}

			int[] coordinateSpecification = BorderNodes.values[existingNode.getColEvenOrOrdd()][border];
			x = existingNode.x + coordinateSpecification[0];
			y = existingNode.y + coordinateSpecification[1];
		}
		HexNode node = new HexNode(name, x, y, colEvenOrOrdd);
		mapOfHexNode.put(name, node);
		mapOfCoordinateNode.put(new Coordinate(x, y), node);
		return node;
	}

	public List<HexNode> getNeighbours(final String name) {
		HexNode node = mapOfHexNode.get(name);
		if (node == null) {
			throw new RestApiException(400, "Neighbours", "Node not in cluster.");
		}
		int currentX = node.x;
		int currentY = node.y;
		int colEvenOrOrdd = node.getColEvenOrOrdd();
		List<HexNode> neighbours = new LinkedList<HexNode>();
		for (int i = 0; i < BorderNodes.values[colEvenOrOrdd].length; i++) {
			Coordinate coordinate = new Coordinate(currentX + BorderNodes.values[colEvenOrOrdd][i][0],
					currentY + BorderNodes.values[colEvenOrOrdd][i][1]);
			HexNode neighbourNode = mapOfCoordinateNode.get(coordinate);
			if (neighbourNode != null) {
				neighbours.add(neighbourNode);
			}
		}
		return neighbours;
	}

	private boolean bfsToDeleteNode(final HexNode startNode, final String skipNode,
			final Set<String> needToCoverNodes) {
		Queue<String> queue = new LinkedList<String>();
		queue.add(startNode.getName());
		Set<String> alreadyConvered = new HashSet<String>();
		while (!queue.isEmpty()) {
			final String nameOfNode = queue.remove();
			HexNode currentNode = this.mapOfHexNode.get(nameOfNode);
			alreadyConvered.add(nameOfNode);
			int currentX = currentNode.x;
			int currentY = currentNode.y;
			int colEvenOrOrdd = currentNode.getColEvenOrOrdd();
			for (int i = 0; i < BorderNodes.values[colEvenOrOrdd].length; i++) {
				Coordinate coordinate = new Coordinate(currentX + BorderNodes.values[colEvenOrOrdd][i][0],
						currentY + BorderNodes.values[colEvenOrOrdd][i][1]);
				HexNode neighbourNode = mapOfCoordinateNode.get(coordinate);
				if (neighbourNode != null && !neighbourNode.getName().equals(skipNode)
						&& !alreadyConvered.contains(neighbourNode.getName())) {
					if (needToCoverNodes.contains(neighbourNode.getName())) {
						needToCoverNodes.remove(neighbourNode.getName());
					}
					queue.add(neighbourNode.getName());
				}
			}
		}
		if (needToCoverNodes.isEmpty()) {
			HexNode removedNode = this.mapOfHexNode.remove(skipNode);
			this.mapOfCoordinateNode.remove(Coordinate.build(removedNode));
			return true;
		} else {
			throw new RestApiException(400, "Delete Node", "Node can't be removed.");
		}
	}

	public boolean deleteNode(final String name) {
		HexNode currentNode = mapOfHexNode.get(name);
		int currentX = currentNode.x;
		int currentY = currentNode.y;
		int colEvenOrOrdd = currentNode.getColEvenOrOrdd();
		Set<String> neighbourNodes = new HashSet<String>();
		String lastNode = null;
		for (int i = 0; i < BorderNodes.values[colEvenOrOrdd].length; i++) {
			Coordinate coordinate = new Coordinate(currentX + BorderNodes.values[colEvenOrOrdd][i][0],
					currentY + BorderNodes.values[colEvenOrOrdd][i][1]);
			HexNode neighbourNode = mapOfCoordinateNode.get(coordinate);
			if (neighbourNode != null) {
				neighbourNodes.add(neighbourNode.getName());
				lastNode = neighbourNode.getName();
			}
		}
		if (lastNode != null) {
			neighbourNodes.remove(lastNode);
			return this.bfsToDeleteNode(this.mapOfHexNode.get(lastNode), currentNode.getName(), neighbourNodes);
		} else {
			throw new RestApiException(400, "Delete node", "Root node can't be removed.");
		}
	}

	public boolean resetCluster() {
		this.mapOfCoordinateNode.clear();
		this.mapOfHexNode.clear();
		this.addNode("ax", null, -1);
		return true;
	}
}
