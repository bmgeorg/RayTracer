package sceneTest;


import org.junit.Assert;
import org.junit.Test;

import ppm.PPMLibrary;
import ppm.Pixel;
import scene.Color;
import scene.Light;
import scene.Scene;
import scene.Shading;
import scene.Vector3;
import scene.Viewpoint;
import sceneObjects.Sphere;
import testUtils.TestUtils;

public class SceneTest {
	protected static String testFilesDir = "testFiles/Scenes/";
	
	@Test
	public void oneLightOneSphere() {
		Scene s = new Scene();
		s.setBackgroundColor(new Pixel(Color.black));
		s.setViewpoint(Viewpoint.getDefaultViewpoint());
		s.addLight(new Light(new Vector3(5, 5, 5), 1));
		Vector3 c = new Vector3(2, 0, -5);
		double radius = 3;
		s.addSceneObject(new Sphere(c, radius, new Shading(Color.white, Color.green, Color.black)));
		Pixel image[][] = s.render();
		PPMLibrary.writePPMImage(testFilesDir + "oneLightOneSphereTest.ppm", image);
		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "oneLightOneSphere.ppm", testFilesDir + "oneLightOneSphereTest.ppm"));
		TestUtils.removeFile(testFilesDir + "oneLightOneSphereTest.ppm");
	}
	
	@Test
	public void flatSphere() {
		Scene s = new Scene();
		s.setBackgroundColor(new Pixel(Color.black));
		s.setViewpoint(Viewpoint.getDefaultViewpoint());
		Vector3 c = new Vector3(0, 0, -5);
		double radius = 3;
		s.addSceneObject(new Sphere(c, radius, new Shading(Color.green, Color.green, Color.green)));
		Pixel image[][] = s.render();
		PPMLibrary.writePPMImage(testFilesDir + "flatSphereTest.ppm", image);
		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "flatSphere.ppm", testFilesDir + "flatSphereTest.ppm"));
		TestUtils.removeFile(testFilesDir + "flatSphereTest.ppm");
	}
	
	@Test
	public void blank() {
		Scene s = new Scene();
		s.setBackgroundColor(new Pixel(Color.cyan));
		s.setViewpoint(Viewpoint.getDefaultViewpoint());
		Pixel image[][] = s.render();
		PPMLibrary.writePPMImage(testFilesDir + "blankTest.ppm", image);
		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "blank.ppm", testFilesDir + "blankTest.ppm"));
		TestUtils.removeFile(testFilesDir + "blankTest.ppm");
	}
}