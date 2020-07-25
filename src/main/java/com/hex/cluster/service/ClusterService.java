package com.hex.cluster.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hex.cluster.entity.Cluster;
import com.hex.cluster.entity.HexNode;

@Service
public class ClusterService {
	private final Cluster cluster;

	public ClusterService() {
		this.cluster = new Cluster();
	}

	public HexNode addNode(final String name, final String existingNodeName, final int sharedBorder) {
		return this.cluster.addNode(name, existingNodeName, sharedBorder);
	}

	public boolean deleteNode(final String name) {
		return this.cluster.deleteNode(name);
	}

	public List<HexNode> getNeighbours(final String name) {
		return this.cluster.getNeighbours(name);
	}

	public Collection<HexNode> getAllNodes() {
		return this.cluster.getAllNodes();
	}

	public boolean resetCluster() {
		return this.cluster.resetCluster();
	}

}
