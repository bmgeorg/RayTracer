package scene;

//Immutable
final public class HitpointData {
	private boolean hit;
	private Vector3 position, uNormal;
	private double distance;
	private Lighting lighting;
	private Color color;
	
	private static HitpointData noHit;
	public static HitpointData getNoHit() {
		if(noHit == null) {
			noHit = new HitpointData();
		}
		return noHit;
	}

	//no hit constructor
	private HitpointData() {
		this.hit = false;
		this.position = null;
		this.uNormal = null;
	}
	
	/*Pre:
	 * 1. uNormal is unit
	 */
	public HitpointData(Vector3 position, Vector3 uNormal, double distance, Color color, Lighting lighting) {
		assert uNormal.isUnit();
		
		this.hit = true;
		this.position = position;
		this.uNormal = uNormal;
		this.distance = distance;
		this.color = color;
		this.lighting = lighting;
	}
	
	public boolean isHit() {
		return hit;
	}
	public Vector3 getPosition() {
		return position;
	}
	public Vector3 getuNormal() {
		return uNormal;
	}
	public double getDistance() {
		return distance;
	}
	public Lighting getLighting() {
		return lighting;
	}
	public Color getColor() {
		return color;
	}
	
	/* overridden methods */
	public String toString() {
		String result;
		result = "HitpointData" +
				" position: " + position.toString() +
				" uNormal: " + uNormal.toString() +
				" distance: " + String.valueOf(distance) +
				" lighting: " + lighting.toString() + 
				" color: " + color.toString();
		return result;
	}
	
}
