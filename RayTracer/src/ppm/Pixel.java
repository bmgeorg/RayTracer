package ppm;

//Immutable
public class Pixel {
	//clamped to range [PPMConstants.MIN_COLOR_VALUE, PPMConstants.MAX_COLOR_VALUE]
	private int r, g, b;
	
	public Pixel() {
		r = g = b = PPMConstants.MIN_COLOR_VALUE;
	}
	
	public Pixel(int r, int g, int b) {
		this.r = clamp(r);
		this.g = clamp(g);
		this.b = clamp(b);
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
}
