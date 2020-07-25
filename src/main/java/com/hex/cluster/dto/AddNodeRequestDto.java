package com.hex.cluster.dto;

import java.io.Serializable;

public class AddNodeRequestDto implements Serializable {
	private static final long serialVersionUID = -5314303219857520816L;
	private String name;
	private String existingNodeName;
	private int border;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExistingNodeName() {
		return existingNodeName;
	}

	public void setExistingNodeName(String existingNodeName) {
		this.existingNodeName = existingNodeName;
	}

	public int getBorder() {
		return border;
	}

	public void setBorder(int border) {
		this.border = border;
	}

}
