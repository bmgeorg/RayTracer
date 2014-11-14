package scene;


//immutable
public class Color {
	private double r, g, b; //in range [0, 1]
	
	public final static Color white     = new Color(1, 1, 1);
    public final static Color lightGray = new Color(.75, .75, .75);
    public final static Color gray      = new Color(.55, .55, .55);
    public final static Color darkGray  = new Color(.4, .4, .4);
    public final static Color black     = new Color(0, 0, 0);
    public final static Color red       = new Color(1, 0, 0);
    public final static Color pink      = new Color(1, .6, .6);
    public final static Color orange    = new Color(1, .8, 0);
    public final static Color yellow    = new Color(1, 1, 0);
    public final static Color green     = new Color(0, 1, 0);
    public final static Color magenta   = new Color(1, 0, 1);
    public final static Color cyan      = new Color(0, 1, 1);
    public final static Color blue      = new Color(0, 0, 1);

    /* constructors */
	/*Post:
	 * 1. clamps r to [0, 1]
	 * 2. clamps g to [0, 1]
	 * 3. clamps b to [0, 1]
	 */
	public Color(double r, double g, double b) {
		
		this.r = clamp(r);
		this.g = clamp(g);
		this.b = clamp(b);
	}

	/* getters */
	public double getR() {
		return r;
	}

	public double getG() {
		return g;
	}

	public double getB() {
		return b;
	}
	
	/* operations */
	public Color scale(double factor) {
		return new Color(r*factor, g*factor, b*factor);
	}
	
	public Color add(Color other) {
		return new Color(r+other.r, g+other.g, b+other.b);
	}
	
	/* private */
	private double clamp(double x) {
		if(x < 0)
			x = 0;
		if(x > 1)
			x = 1;
		return x;
	}
	
	/* overridden */
	@Override
	public String toString() {
		return String.format("r: %f g: %f b: %f", r, g, b);
	}
}
