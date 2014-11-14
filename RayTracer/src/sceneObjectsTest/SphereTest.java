package sceneObjectsTest;

import org.junit.Assert;
import org.junit.Test;

import scene.Color;
import scene.HitpointData;
import scene.Lighting;
import scene.Vector3;
import sceneObjects.Sphere;


public class SphereTest {

	@Test
	public void testHitpoint() {
		Sphere a = new Sphere(new Vector3(0, 0, -1), 1, Color.black, new Lighting(0, 0, 0));
		Vector3 base = new Vector3(0, 0, 1);
		Vector3 dir = new Vector3(0, 0, -1);
		HitpointData hit = a.hit(base, dir);
		Assert.assertTrue(hit.isHit());
		Assert.assertTrue(hit.getPosition().equalsWithTol(new Vector3(0, 0, 0)));
		Assert.assertEquals(1, hit.getDistance(), Vector3.ABSOLUTE_TOLERANCE);
		Assert.assertTrue(hit.getuNormal().equalsWithTol(new Vector3(0, 0, 1)));
	}

}
