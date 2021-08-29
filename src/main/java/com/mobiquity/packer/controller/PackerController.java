package com.mobiquity.packer.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.stream.Collectors;

import com.mobiquity.packer.model.Package;
import com.mobiquity.packer.model.PackageItem;

public class PackerController {

	private final int MAX_WEIGHT = 100;

	/**
	 * Starts the processing of the text file with packages.
	 * 
	 * @param filePath Text file with packages.
	 * @return String that will contain what packages are better, following the
	 *         rule:</br>
	 *         Total weight is less than or equal to the package limit and the total
	 *         cost is as large as possible.
	 * @throws IOException
	 */
	public String startFileProcessing(String filePath) throws IOException {
		File file = new File(filePath);
		FileReader fileReader = new FileReader(file);
		BufferedReader brFile = new BufferedReader(fileReader);
		String result = separateValuesFromFile(brFile);
		brFile.close();
		return result;
	}

	/**
	 * Creates values separation from the text file.
	 * 
	 * @param bufferedReaderFile Text file as BufferedReader
	 * @return String that will contain what packages are better, following the
	 *         rule:</br>
	 *         Total weight is less than or equal to the package limit and the total
	 *         cost is as large as possible.
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	private String separateValuesFromFile(BufferedReader bufferedReaderFile) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		//Iterates all text lines.
		while ((line = bufferedReaderFile.readLine()) != null) {
			LinkedList<PackageItem> packageItems = new LinkedList<PackageItem>();
			//Separates total weight and package items.
			String[] arrayOfLines = line.split(":");
			//get weightLimit.
			Double weightLimit = Double.parseDouble(arrayOfLines[0].trim());
			//get items.
			String[] packItems = arrayOfLines[1].trim().split(" ");
			//iterates String items.
			for (String packItem : packItems) {
				//iterate String items.
				String[] packInfo = packItem.split(",");
				int index = Integer.parseInt(packInfo[0].substring(1));
				double weight = Double.parseDouble(packInfo[1]);
				double price = Double.parseDouble(packInfo[2].substring(3, packInfo[2].length() - 1));
				//Creates PackageItem with values separated.
				PackageItem pi = new PackageItem(index, weight, price);
				packageItems.add(pi);
			}
			//Creates Package with values separated.
			Package pac = new Package(weightLimit, packageItems);
			//Sends package to analyze process.
			sb.append(analyzePackage(pac) + "\n");
		}
		return sb.toString();

	}

	/**
	 * Analyzes all the items inside the package.
	 * 
	 * @param p An instance of Package with values from a text file, but now,
	 *          separated as an object.
	 * @return String that will contain what packages are better, following the
	 *         rule:</br>
	 *         Total weight is less than or equal to the package limit and the total
	 *         cost is as large as possible.
	 */
	private String analyzePackage(Package p) {
		LinkedList<PackageItem> itensFiltered = filterItemsWithLimit(p.getPackageItems(), p.getWeightLimit());
		LinkedList<LinkedList<PackageItem>> combinations = createItemsCombinations(itensFiltered);
		// check if combinations is empty
		if (combinations.size() == 0) {
			return "-";
		} else {
			// sends combinations to find best combination
			LinkedList<PackageItem> bPackageItems = findBestPackage(combinations, p.getWeightLimit());
			return prepareResult(bPackageItems);
		}
	}

	/**
	 * It Prepares the text processing result.
	 * 
	 * @param packageItems
	 * @return String that will contain what packages are better, following the
	 *         rule:</br>
	 *         Total weight is less than or equal to the package limit and the total
	 *         cost is as large as possible.
	 */
	private String prepareResult(LinkedList<PackageItem> packageItems) {
		String r = packageItems.stream().map(o -> String.valueOf(o.getId())).collect(Collectors.joining(","));
		return r;
	}

	/**
	 * Choose the best package following the rule:</br>
	 * Total weight is less than or equal to the package limit and the total cost is
	 * as large as possible.
	 * 
	 * @param combinations PackageItems combinations to found the best item.
	 * @param weightLimit  calculated weight limit.
	 * @return
	 */
	private LinkedList<PackageItem> findBestPackage(LinkedList<LinkedList<PackageItem>> combinations,
			Double weightLimit) {
		LinkedList<PackageItem> bCombination = new LinkedList<PackageItem>();
		double bCost = 0;
		double bWeight = MAX_WEIGHT;
		for (LinkedList<PackageItem> combination : combinations) {
			double combWeight = calcTotalWeight(combination);
			if (combWeight > weightLimit) {
				continue;
			} else {
				double combinationPrice = calcTotalPrice(combination);
				if (combinationPrice > bCost) {
					bCost = combinationPrice;
					bCombination = combination;
					bWeight = combWeight;
				} else if (combinationPrice == bCost) {
					if (combWeight < bWeight) {
						bCost = combinationPrice;
						bCombination = combination;
						bWeight = combWeight;
					}
				}
			}
		}
		return bCombination;
	}

	/**
	 * Summarize items' weight.
	 * 
	 * @param packageItems
	 * @return sum items' weight.
	 */
	private double calcTotalWeight(LinkedList<PackageItem> packageItems) {
		return packageItems.stream().map(i -> i.getWeight()).reduce(0.0, (a, b) -> Double.sum(a, b));
	}

	/**
	 * Summarize price's weight.
	 * 
	 * @param packageItems
	 * @return sum price's weight.
	 */
	private double calcTotalPrice(LinkedList<PackageItem> packageItems) {
		return packageItems.stream().map(v -> v.getPrice()).reduce(0.0, (a, b) -> Double.sum(a, b));
	}

	/**
	 * It Iterates items and creates combinations to check the best items.
	 * 
	 * @param packageItems
	 * @return Items combined
	 */
	private LinkedList<LinkedList<PackageItem>> createItemsCombinations(LinkedList<PackageItem> packageItems) {
		LinkedList<LinkedList<PackageItem>> compItems = new LinkedList<LinkedList<PackageItem>>();
		
		for (int x = 0; x < packageItems.size(); x++) {
			PackageItem c = packageItems.get(x);
			int combSize = compItems.size();
			
			for (int y = 0; y < combSize; y++) {
				LinkedList<PackageItem> comb = compItems.get(y);
				
				LinkedList<PackageItem> newComb = new LinkedList<PackageItem>(comb);
				newComb.add(c);
				compItems.add(newComb);
			}
			
			LinkedList<PackageItem> current = new LinkedList<PackageItem>();
			current.add(c);
			compItems.add(current);
		}

		return compItems;
	}

	/**
	 * remove items that are overweight.
	 * 
	 * @param packageItems
	 * @param weightLimit
	 * @return Only items with the allowed weight.
	 */
	private LinkedList<PackageItem> filterItemsWithLimit(LinkedList<PackageItem> packageItems, Double weightLimit) {
		return new LinkedList<PackageItem>(
				packageItems.stream().filter(o -> o.getWeight() <= weightLimit).collect(Collectors.toList()));
	}

}
