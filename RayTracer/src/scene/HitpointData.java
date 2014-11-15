package scene;

import sceneObjects.SceneObject;

//Immutable
final public class HitpointData {
	private boolean hit;
	private Vector3 position, uNormal;
	private double distance;
	private Shading shading;
	private SceneObject hitObject;
	
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
	 * 2. parameter != null for all parameters
	 * 3. distance >= 0
	 */
	public HitpointData(Vector3 position, Vector3 uNormal, double distance, Shading shading, SceneObject hitObject) {
		assert uNormal != null;
		assert position != null;
		assert distance >= 0;
		assert shading != null;
		assert hitObject != null;
		
		this.hit = true;
		this.position = position;
		this.uNormal = uNormal;
		this.distance = distance;
		this.shading = shading;
		this.hitObject = hitObject;
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
	public Shading getShading() {
		return shading;
	}
	public SceneObject getHitObject() {
		return hitObject;
	}
	
	/* overridden methods */
	public String toString() {
		String result;
		if(this == noHit) {
			result = "HitpointData noHit";
		} else {
			result = "HitpointData" +
					" position: " + position.toString() +
					" uNormal: " + uNormal.toString() +
					" distance: " + String.valueOf(distance) +
					" shading: " + shading.toString() +
					" hit object: " + hitObject.toString();
		}
		return result;
	}
	
}
