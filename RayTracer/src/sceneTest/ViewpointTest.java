package sceneTest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import scene.Vector3;
import scene.Viewpoint;
import testUtils.TestUtils;


public class ViewpointTest {
	protected static String testFilesDir = "testFiles/Viewpoint/";

	@Test
	public void testFireRay() throws IOException {
		final double FRAME_WIDTH = 1;
		final double FRAME_HEIGHT = 1;
		final double FRAME_DEPTH = 1;
		final int IMAGE_WIDTH = 10;
		final int IMAGE_HEIGHT = 10;
		final Vector3 POSITION = new Vector3(0, 0, 1);
		final Vector3 FRONT_ORIENTATION = new Vector3(0, 0, -1);
		final Vector3 DOWN_ORIENTATION = new Vector3(0, -1, 0);

		Viewpoint vp = new Viewpoint(POSITION, FRONT_ORIENTATION, DOWN_ORIENTATION, FRAME_WIDTH, FRAME_HEIGHT, FRAME_DEPTH, IMAGE_WIDTH, IMAGE_HEIGHT);

		String content = "";
		for(int i = 0; i < IMAGE_HEIGHT; i++) {
			for(int j = 0; j < IMAGE_WIDTH; j++) {
				Vector3 ray = vp.fireRay(j, i);
				Assert.assertTrue(ray.isUnit());
				content += String.format("%.10f %.10f %.10f ", ray.getX(), ray.getY(), ray.getZ());
			}
			content += "\n";
		}
		
		Assert.assertTrue(TestUtils.compareStringWithFile(testFilesDir + "centered10x10rays", content));
	}

}
