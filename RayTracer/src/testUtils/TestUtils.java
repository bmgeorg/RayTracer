package testUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

/*
 * Pre:
 * 1. path1 and path2 are existent files
 */
public class TestUtils {
	
	public static boolean filesEqual(String path1, String path2) {
		File img1 = new File(path1);
		File img2 = new File(path2);
		
		if(!img1.isFile()) {
			System.err.println("File " + path1 + " is not a file");
			assert false;
		}
		if(!img2.isFile()) {
			System.err.println("File " + path2 + " is not a file");
			assert false;
		}

		boolean equals = false;
		try {
			equals = FileUtils.contentEquals(img1, img2);
		} catch(IOException e) {
			System.err.println("IOException when trying to compare files " + path1 + " and " + path2); 
			e.printStackTrace();
		}
		return equals;
	}
	
	public static void writeStringToFile(String filePath, String content) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filePath, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.err.println("Could not create file at path: " + filePath);
			e.printStackTrace();
		}
		writer.print(content);
		writer.close();
	}
	
	public static void removeFile(String filePath) {
		try {
			Files.deleteIfExists(Paths.get(filePath + ".test"));
		} catch (IOException e) {
			System.err.println("Could not delete test file: " + filePath + ".test");
			e.printStackTrace();
		}
	}
	
	public static boolean compareStringWithFile(String filePath, String content) {
		TestUtils.writeStringToFile(filePath + ".test", content);
		boolean result = filesEqual(filePath, filePath + ".test");
		removeFile(filePath + ".test");
		return result;
	}
}
