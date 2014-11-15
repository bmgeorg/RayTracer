package sceneTest;

import org.junit.Assert;
import org.junit.Test;

import scene.Color;
import scene.HitpointData;
import scene.Shading;
import scene.Vector3;
import sceneObjects.Sphere;

public class HitpointDataTest {

	@Test
	public void testConstructors() {
		HitpointData a = HitpointData.getNoHit();
		Assert.assertFalse(a.isHit());
		Assert.assertNull(a.getPosition());
		Assert.assertNull(a.getuNormal());

		Vector3 p = new Vector3(3, 3, 3);
		Vector3 n = new Vector3(0, 0, 1);
		Vector3 i = new Vector3(0, 1, 0);
		Sphere hitObject = new Sphere(new Vector3(0, 0, 0), 1, new Shading(Color.black, Color.black, Color.black));
		HitpointData b = new HitpointData(p, n, i, 5, new Shading(Color.black, Color.black, Color.black), hitObject);
		Assert.assertTrue(b.isHit());
		Assert.assertEquals(p, b.getPosition());
		Assert.assertEquals(n,  b.getuNormal());
		Assert.assertTrue(b.getuNormal().isUnit());
	}

}
