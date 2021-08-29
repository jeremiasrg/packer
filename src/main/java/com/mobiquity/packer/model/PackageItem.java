package com.mobiquity.packer.model;

import java.io.Serializable;

public class PackageItem implements Serializable, Comparable<PackageItem>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Double weight;
	private Double price;


	public PackageItem(Integer id, Double weight, Double price) {
		super();
		this.id = id;
		this.weight = weight;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int compareTo(PackageItem o) {
		return this.id - o.id;
	}

}