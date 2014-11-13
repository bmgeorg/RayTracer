package scene;

//Immutable
final public class Vector3 {
	private double x, y, z;
	public static final double ABSOLUTE_TOLERANCE = 11.92e-08; //Inclusive tolerance
	
	/* constructors */
	
	/*Post:
	 * 1. Sets x, y, z to 0
	 */
	public Vector3() {
		this(0, 0, 0);
	}
	
	//default constructor
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/* getters */
	
	public double get(int index) {
		switch(index) {
		case 0:
			return x;
		case 1:
			return y;
		case 2:
			return z;
		default:
			System.err.println("index to vector out of bounds: " + String.valueOf(index));
			assert false;
			return -1;
		}
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	/* operations */
	
	public Vector3 add(Vector3 v) {
		return new Vector3(x + v.x, y + v.y, z + v.z);
	}
	
	public Vector3 sub(Vector3 v) {
		return new Vector3(x - v.x, y - v.y, z - v.z);
	}
	
	public Vector3 unit() {
		double length = this.length();
		if(this.isZero()) {
			return new Vector3();
		} else {
			Vector3 result = new Vector3(x/length, y/length, z/length);
			assert result.isUnit();
			return result;
		}
	}
	
	public double dot(Vector3 v) {
		return x*v.x + y*v.y + z*v.z;
	}
	
	public Vector3 cross(Vector3 v) {
		Vector3 result = new Vector3();

		result.x = (y*v.z - z*v.y);
		result.y = (z*v.x - x*v.z);
		result.z = (x*v.y - y*v.x);

		return result;
	}
	
	public Vector3 scale(double factor) {
		return new Vector3(x*factor, y*factor, z*factor);
	}
	
	/* information */
	
	public boolean isZero() {
		return Math.abs(length()) <= ABSOLUTE_TOLERANCE;
	}
	
	public boolean isUnit() {
		return Math.abs(1-length()) <= ABSOLUTE_TOLERANCE;
	}
	
	public double length() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public boolean equalsWithTol(Vector3 other) {
		if(Math.abs(x-other.x) > ABSOLUTE_TOLERANCE)
			return false;
		if(Math.abs(y-other.y) > ABSOLUTE_TOLERANCE)
			return false;
		if(Math.abs(z-other.z) > ABSOLUTE_TOLERANCE)
			return false;
		return true;
	}
	
	/* overriden methods */
	
	@Override
	public String toString() {
		return String.format("(%.10f %.10f %.10f)", x, y, z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector3))
			return false;
		Vector3 other = (Vector3) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
}
