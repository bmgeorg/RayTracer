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
	public void specularSpheres() {
		Scene s = new Scene();
		s.setBackgroundColor(new Pixel(Color.black));
		s.setViewpoint(Viewpoint.getDefaultViewpoint());
		s.addLight(new Light(new Vector3(5, 5, 5), 1));
		Vector3 c1 = new Vector3(-2, -1, -6);
		Vector3 c2 = new Vector3(2, 1, -6);
		Sphere s1 = new Sphere(c1, 1.5, new Shading(new Color(1, 0, 0), new Color(.1, 0, 0), Color.black));
		Sphere s2 = new Sphere(c2, 1.5, new Shading(new Color(0, 1, 1), new Color(0, .1, .1), new Color(.5, .5, .5)));
		s.addSceneObject(s1);
		s.addSceneObject(s2);
		Pixel image[][] = s.render();
		PPMLibrary.writePPMImage(testFilesDir + "specularSpheresTest.ppm", image);
		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "specularSpheres.ppm", testFilesDir + "specularSpheresTest.ppm"));
		TestUtils.removeFile(testFilesDir + "specularSpheresTest.ppm");
	}
	
	@Test
	public void twoLightsTwoSpheres() {
		Scene s = new Scene();
		s.setBackgroundColor(new Pixel(Color.black));
		s.setViewpoint(Viewpoint.getDefaultViewpoint());
		s.addLight(new Light(new Vector3(5, 5, 5), .8));
		s.addLight(new Light(new Vector3(-5, -5, -2), .3));
		Vector3 c1 = new Vector3(-2, -1, -6);
		Vector3 c2 = new Vector3(2, 1, -4);
		s.addSceneObject(new Sphere(c1, 3, new Shading(new Color(0, 1, 0), new Color(0, .3, .3), Color.black)));
		s.addSceneObject(new Sphere(c2, 2, new Shading(new Color(1, 0, 0), new Color(.1, 0, 0), Color.black)));
		Pixel image[][] = s.render();
		PPMLibrary.writePPMImage(testFilesDir + "twoLightsTwoSpheresTest.ppm", image);
		Assert.assertTrue(TestUtils.filesEqual(testFilesDir + "twoLightsTwoSpheres.ppm", testFilesDir + "twoLightsTwoSpheresTest.ppm"));
		TestUtils.removeFile(testFilesDir + "twoLightsTwoSpheresTest.ppm");
	}
	
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
		s.addSceneObject(new Sphere(c, radius, new Shading(Color.black, Color.green, Color.black)));
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