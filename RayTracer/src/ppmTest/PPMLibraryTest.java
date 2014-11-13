package ppmTest;

import org.junit.Assert;
import org.junit.Test;

import ppm.PPMConstants;
import ppm.PPMLibrary;
import ppm.Pixel;
import testUtils.TestUtils;

public class PPMLibraryTest {
	protected static String testFilesDir = "testFiles/PPMLibrary/";
	
	private static Pixel[][] createLightbeamImage(int width, int height) {
		Pixel image[][] = new Pixel[height][width];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				short v = (short) ((double) i/(double)j * PPMConstants.MAX_COLOR_VALUE);
				if(v >= PPMConstants.MAX_COLOR_VALUE)
					v = (short) ((double) j/(double)i * PPMConstants.MAX_COLOR_VALUE);
				image[i][j] = new Pixel(v, v, v);
			}
		}

		return image;
	}

	@Test
	public void test() {
		final int imageWidth = 500;
		final int imageHeight = 500;
		
		Pixel image[][] = createLightbeamImage(imageWidth, imageHeight);
		
		PPMLibrary.writePPMImage(testFilesDir + "grayscaleLightbeamTest.ppm", image);
		
		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "grayscaleLightbeamTest.ppm", testFilesDir + "grayscaleLightbeam.ppm"));
		TestUtils.removeFile(testFilesDir + "grayscaleLightbeamTest.ppm");
	}

}
