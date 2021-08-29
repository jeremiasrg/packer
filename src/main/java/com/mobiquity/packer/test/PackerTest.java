package com.mobiquity.packer.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

public class PackerTest {

	@Test
	public void testAPIException() {
		try {
			Packer.pack("\\WRONG\\PATH");
		} catch (Exception e) {
			assertEquals("com.mobiquity.exception.APIException", e.getClass().getName());
		}
	}

	@Test
	public void testResult() throws APIException {
		Path relative = Paths.get("");
		String s = relative.toAbsolutePath().toString();
		String r = Packer.pack(s + "\\src\\main\\test\\resources\\example_input");
		String[] result = r.split("\n");

		assertEquals("4", result[0]);
		assertEquals("-", result[1]);
		assertEquals("2,7", result[2]);
		assertEquals("8,9", result[3]);
	}

}
