package ppm;

import scene.Color;


//Immutable
public class Pixel {
	//clamped to range [PPMConstants.MIN_COLOR_VALUE, PPMConstants.MAX_COLOR_VALUE]
	private int r, g, b;
	
	public Pixel() {
		r = g = b = PPMConstants.MIN_COLOR_VALUE;
	}
	
	/*Post:
	 * 1. clamps r to [PPMConstants.MIN_COLOR_VALUE, PPMConstants.MAX_COLOR_VALUE]
	 * 2. clamps g to [PPMConstants.MIN_COLOR_VALUE, PPMConstants.MAX_COLOR_VALUE]
	 * 3. clamps b to [PPMConstants.MIN_COLOR_VALUE, PPMConstants.MAX_COLOR_VALUE]
	 */
	public Pixel(int r, int g, int b) {
		this.r = clamp(r);
		this.g = clamp(g);
		this.b = clamp(b);
	}
	
	/*Pre:
	 * 1. color != null
	 */
	public Pixel(Color color) {
		this(	(int)(color.getR()*PPMConstants.MAX_COLOR_VALUE),
				(int)(color.getG()*PPMConstants.MAX_COLOR_VALUE),
				(int)(color.getB()*PPMConstants.MAX_COLOR_VALUE));
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}
	
	private int clamp(int x) {
		if(x < PPMConstants.MIN_COLOR_VALUE)
			x = PPMConstants.MIN_COLOR_VALUE;
		if(x > PPMConstants.MAX_COLOR_VALUE)
			x = PPMConstants.MAX_COLOR_VALUE;
		return x;
	}
	
	@Override
	public String toString() {
		return String.format("r: %d g: %d b: %d", r, g, b);
	}
}
