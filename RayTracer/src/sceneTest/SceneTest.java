package sceneTest;

import org.junit.Assert;
import org.junit.Test;

import ppm.PPMLibrary;
import ppm.Pixel;
import scene.Scene;
import scene.Viewpoint;
import testUtils.TestUtils;

public class SceneTest {
	protected static String testFilesDir = "testFiles/SceneTest/";
	
	@Test
	public void blankScene() {
		Scene s = new Scene();
		s.setBackgroundColor(new Pixel(40, 20, 200));
		s.setViewpoint(Viewpoint.getDefaultViewpoint());
		Pixel image[][] = s.render();
		PPMLibrary.writePPMImage(testFilesDir + "blankTest.ppm", image);
		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "blank.ppm", testFilesDir + "blank.ppm"));
		TestUtils.removeFile(testFilesDir + "blankTest.ppm");
	}
}