package sceneTest;

import org.junit.Assert;
import org.junit.Test;

import scene.Vector3;

public class VectorTest {

	@Test
	public void testCrossProduct() {
		Vector3 a = new Vector3(2, 8.8, 13);
		Vector3 b = new Vector3(9, 32, 1.8);
		Vector3 result = a.cross(b);
		
		Vector3 goal = new Vector3(-400.16, 113.4, -15.2);
		
		Assert.assertEquals(result.get(0), -400.16, Vector3.ABSOLUTE_TOLERANCE);
		Assert.assertEquals(result.get(1), 113.4, Vector3.ABSOLUTE_TOLERANCE);
		Assert.assertEquals(result.get(2), -15.2, Vector3.ABSOLUTE_TOLERANCE);
		Assert.assertTrue(result.equalsWithTol(goal));
		
		final Vector3 x = new Vector3(1, 0, 0);
		final Vector3 y = new Vector3(0, 1, 0);
		Vector3 z = x.cross(y);
		Assert.assertTrue(z.equalsWithTol(new Vector3(0, 0, 1)));
		
		final Vector3 FRONT_ORIENTATION = new Vector3(0, 0, 1);
		final Vector3 DOWN_ORIENTATION = new Vector3(0, 1, 0);
		Vector3 RIGHT_ORIENTATION = DOWN_ORIENTATION.cross(FRONT_ORIENTATION);
		Assert.assertTrue(RIGHT_ORIENTATION.equalsWithTol(new Vector3(1, 0, 0)));
	}
	
	@Test
	public void testEquals() {
		Vector3 a = new Vector3(2, 8.8, 13);
		Vector3 b = new Vector3(9, 32, 1.8);
		
		Assert.assertFalse(a.equals(b));
		
		b = new Vector3(2 + Vector3.ABSOLUTE_TOLERANCE/2, 8.8, 13);
		Assert.assertFalse(a.equals(b));
		Assert.assertTrue(a.equalsWithTol(b));
	}
	
	@Test
	public void testUnitize() {
		Vector3 a = null;
		
		final double bounds = 10;
		final double inc = .1;
		for(double i = -bounds; i < bounds; i += inc) {
			for(double j = -bounds; j < bounds; j += inc) {
				for(double k = -bounds; k < bounds; k += inc) {
					a = new Vector3(i, j, k);
					if(a.isZero()) {
						a = a.unit();
						Assert.assertTrue(a.isZero());
					} else {
						a = a.unit();
						Assert.assertTrue(a.isUnit());
					}
				}
			}
		}
		
		a = new Vector3(3, 1, 4);
		Assert.assertFalse(a.isUnit());
	}

}
