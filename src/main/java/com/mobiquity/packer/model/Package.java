package com.mobiquity.packer.model;

import java.io.Serializable;
import java.util.LinkedList;

public class Package implements Serializable {

	private static final long serialVersionUID = 1L;
	private Double weightLimit;
	private LinkedList<PackageItem> packageItems;
	private LinkedList<LinkedList<PackageItem>> comparations;

	public Package(Double weightLimit, LinkedList<PackageItem> packageItems) {
		super();
		this.weightLimit = weightLimit;
		this.packageItems = packageItems;
	}

	public Double getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(Double limit) {
		this.weightLimit = limit;
	}

	public LinkedList<PackageItem> getPackageItems() {
		return packageItems;
	}

	public void setPackageItems(LinkedList<PackageItem> packageItems) {
		this.packageItems = packageItems;
	}

	public LinkedList<LinkedList<PackageItem>> getComparations() {
		return comparations;
	}

	public void setComparations(LinkedList<LinkedList<PackageItem>> comparations) {
		this.comparations = comparations;
	}

}
