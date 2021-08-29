package com.mobiquity.packer;

import java.io.IOException;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.controller.PackerController;

public class Packer {

	private Packer() {
	}

	/**
	 * 
	 * @param filePath Text file with packages.
	 * @return String that will contain what packages are better, following the rule:</br> 
	 * Total weight is less than or equal to the package limit and the total cost is as large as possible.
	 * @throws APIException
	 */
	public static String pack(String filePath) throws APIException {
		try {
			PackerController pc = new PackerController();
			String result = pc.startFileProcessing(filePath);
			return result;
		} catch (IOException e) {
			throw new APIException("The file processing return an error.", e);
		}

	}
}
