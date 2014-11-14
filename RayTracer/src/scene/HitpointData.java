package scene;

//Immutable
final public class HitpointData {
	private boolean hit;
	private Vector3 position, uNormal, uIncoming;
	private double distance;
	private Shading shading;
	
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
	 * 2. uIncoming is unit
	 */
	public HitpointData(Vector3 position, Vector3 uNormal, Vector3 uIncoming, double distance, Shading shading) {
		assert uNormal.isUnit();
		assert uIncoming.isUnit();
		
		this.hit = true;
		this.position = position;
		this.uNormal = uNormal;
		this.uIncoming = uIncoming;
		this.distance = distance;
		this.shading = shading;
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
	public Vector3 getuIncoming() {
		return uIncoming;
	}
	public double getDistance() {
		return distance;
	}
	public Shading getShading() {
		return shading;
	}
	
	/* overridden methods */
	public String toString() {
		String result;
		result = "HitpointData" +
				" position: " + position.toString() +
				" uNormal: " + uNormal.toString() +
				" uIncoming: " + uIncoming.toString() +
				" distance: " + String.valueOf(distance) +
				" shading: " + shading.toString();
		return result;
	}
	
}
