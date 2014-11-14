package ppm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PPMLibrary {
	/*Pre:
	 * 1. pixels are in row major order
	 * 2. pixels.length > 0
	 * 3. pixels[0].length > 0
	 * 4. all pixels[i].length are equal
	 */
	public static void writePPMImage(String imagePath, Pixel pixels[][]) {
		assert pixels.length > 0 && pixels[0].length > 0;
		
		//Open 
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(imagePath, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.err.println("Could not create ppm image at path: " + imagePath);
			e.printStackTrace();
		}
 		
		int height = pixels.length;
		int width = pixels[0].length;
		
		//header
		writePPMHeader(writer, width, height);
		
		//body
		for(int row = 0; row < height; row++) {
			assert pixels[row].length == width;
			for(int col = 0; col < width; col++) {
				Pixel p = pixels[row][col];
				writer.format("%d %d %d\n", p.getR(), p.getG(), p.getB());
			}
		}
		
		writer.close();
	}
	
	private static void writePPMHeader(PrintWriter writer, int width, int height) {
		writer.format("P3\n %d %d %d\n", width, height, PPMConstants.MAX_COLOR_VALUE);
	}
	
	private PPMLibrary() {
		//No instantiation allowed
		assert false;
	}

}
