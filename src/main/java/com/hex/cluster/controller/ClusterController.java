package com.hex.cluster.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hex.cluster.dto.AddNodeRequestDto;
import com.hex.cluster.entity.HexNode;
import com.hex.cluster.service.ClusterService;

@RestController
@RequestMapping("/cluster")
public class ClusterController {

	private final ClusterService clusterService;

	public ClusterController(final ClusterService clusterService) {
		this.clusterService = clusterService;
	}

	@GetMapping
	public Collection<HexNode> getAllNodes() {
		return this.clusterService.getAllNodes();
	}

	@PostMapping("/node")
	public HexNode addNode(@RequestBody AddNodeRequestDto addNodeRequestDto) {
		return this.clusterService.addNode(addNodeRequestDto.getName(), addNodeRequestDto.getExistingNodeName(),
				addNodeRequestDto.getBorder());
	}

	@DeleteMapping("/node")
	public boolean deleteNode(@RequestParam("name") final String name) {
		return this.clusterService.deleteNode(name);
	}

	@GetMapping("/node/neighbours")
	public List<HexNode> getNeighbours(@RequestParam("name") final String name) {
		return this.clusterService.getNeighbours(name);
	}
	
	@DeleteMapping
	public boolean resetCluster() {
		return this.clusterService.resetCluster();
	}

}
