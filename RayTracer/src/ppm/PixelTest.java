package ppm;


import org.junit.Assert;
import org.junit.Test;

import scene.Color;

public class PixelTest {

	@Test
	public void testFromColor() {
		Color c = new Color(0, 0, 0);
		Pixel p = new Pixel(c);
		Assert.assertEquals(0, p.getR());
		Assert.assertEquals(0, p.getG());
		Assert.assertEquals(0, p.getB());
		
		c = new Color(1, 1, 1);
		p = new Pixel(c);
		Assert.assertEquals(PPMConstants.MAX_COLOR_VALUE, p.getR());
		Assert.assertEquals(PPMConstants.MAX_COLOR_VALUE, p.getG());
		Assert.assertEquals(PPMConstants.MAX_COLOR_VALUE, p.getB());
	}

}
