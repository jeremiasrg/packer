# Packer implementation

## Introduction

This page is the Packer's documentation. Below is possible to see more about this project.

## Design Pattern

I choose MVC as Design Pattern because I believe that is a good strategy looking to the necessity and it will simplify the maintainability.

### Model

- <a href="https://github.com/jeremiasrg/packer/blob/main/src/main/java/com/mobiquity/packer/model/Package.java">Package.java</a>

- <a href="https://github.com/jeremiasrg/packer/blob/main/src/main/java/com/mobiquity/packer/model/PackageItem.java">PackageItem.java</a>

### View

- <a href="https://github.com/jeremiasrg/packer/blob/main/src/main/java/com/mobiquity/packer/Packer.java">Packer.java</a>

### Controller

- <a href="https://github.com/jeremiasrg/packer/blob/main/src/main/java/com/mobiquity/packer/controller/PackerController.java">PackerController.java</a>

## Adopted Process

### TDD

As suggested in the requirements, I adopted TDD. Click on PackerTest.java to see the class.

- <a href="https://github.com/jeremiasrg/packer/blob/main/src/main/java/com/mobiquity/packer/test/PackerTest.java">PackerTest.java</a>

## Documented code

The code was documented with JavaDoc and comments.

example:

```java
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
	...
	}
```

## Conclusion

Thank you for the opportunity to participate in this process. Any feedback is welcome and if I am chosen, I will be ready to humbly collaborate and learn from everyone.

Regards.
