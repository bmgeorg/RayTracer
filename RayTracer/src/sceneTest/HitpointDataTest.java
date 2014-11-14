package sceneTest;

import org.junit.Assert;
import org.junit.Test;

import scene.Color;
import scene.HitpointData;
import scene.Lighting;
import scene.Vector3;

public class HitpointDataTest {

	@Test
	public void testConstructors() {
		HitpointData a = HitpointData.getNoHit();
		Assert.assertFalse(a.isHit());
		Assert.assertNull(a.getPosition());
		Assert.assertNull(a.getuNormal());

		Vector3 p = new Vector3(3, 3, 3);
		Vector3 n = new Vector3(0, 0, 1);
		HitpointData b = new HitpointData(p, n, 5, Color.black, new Lighting(0, 0, 0));
		Assert.assertTrue(b.isHit());
		Assert.assertEquals(p, b.getPosition());
		Assert.assertEquals(n,  b.getuNormal());
		Assert.assertTrue(b.getuNormal().isUnit());
	}

}
