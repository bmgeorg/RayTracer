package ppmTest;

import org.junit.Assert;
import org.junit.Test;

import ppm.PPMConstants;
import ppm.PPMLibrary;
import ppm.Pixel;
import scene.Color;
import testUtils.TestUtils;

public class PPMLibraryTest {
	protected static String testFilesDir = "testFiles/PPMLibrary/";

	private Pixel[][] createLightbeamImage(int width, int height) {
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
	public void lightbeam() {
		final int imageWidth = 500;
		final int imageHeight = 500;

		Pixel image[][] = createLightbeamImage(imageWidth, imageHeight);

		PPMLibrary.writePPMImage(testFilesDir + "grayscaleLightbeamTest.ppm", image);

		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "grayscaleLightbeamTest.ppm", testFilesDir + "grayscaleLightbeam.ppm"));
		TestUtils.removeFile(testFilesDir + "grayscaleLightbeamTest.ppm");
	}

	private Pixel[][] createTopLineImage(int width, int height) {
		int STRIP_WIDTH = 20;
		Color LEFT_STRIP_COLOR = Color.black;
		Color TOP_STRIP_COLOR = Color.blue;
		Color TOP_LEFT_BOX_COLOR = Color.red;
		Color BACKGROUND_COLOR = Color.white;
		assert width > STRIP_WIDTH;

		Pixel image[][] = new Pixel[height][width];

		//background
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				image[i][j] = new Pixel(BACKGROUND_COLOR);
			}
		}
		
		//top strip
		for(int i = 0; i < STRIP_WIDTH; i++) {
			for(int j = 0; j < width; j++)
				image[i][j] = new Pixel(TOP_STRIP_COLOR);
		}
		
		//left strip
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < STRIP_WIDTH; j++)
				image[i][j] = new Pixel(LEFT_STRIP_COLOR);
		}
		
		//top left box
		for(int i = 0; i < STRIP_WIDTH; i++) {
			for(int j = 0; j < STRIP_WIDTH; j++)
				image[i][j] = new Pixel(TOP_LEFT_BOX_COLOR);
		}

		return image;
	}

	@Test
	public void testOrientation() {
		final int imageWidth = 500;
		final int imageHeight = 200;

		Pixel image[][] = createTopLineImage(imageWidth, imageHeight);

		PPMLibrary.writePPMImage(testFilesDir + "orientationTest.ppm", image);

		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "orientation.ppm", testFilesDir + "orientationTest.ppm"));
		TestUtils.removeFile(testFilesDir + "orientationTest.ppm");
	}

}
