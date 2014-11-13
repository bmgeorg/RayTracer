package sceneObjects;

import scene.HitpointData;
import scene.Vector3;

//Immutable
final public class Sphere extends SceneObject {
	private double radius;
	private double NO_HIT = -1;
	
	public Sphere(Vector3 position, double radius) {
		super(position);
		this.radius = radius;
	}
	
	
	/*Pre:
	 * 1. base != null
	 * 2. uDir != null
	 * 3. uDir is unit
	 */
	//returns NO_HIT if there is no hit point, or if hit point is behind base.
	private double distanceToHitpoint(Vector3 base, Vector3 uDir) {
		assert base != null;
		assert uDir != null;
		assert uDir.isUnit();

		/* Let
		 * t = distance to hitpoint
		 * r = radius
		 * b = base
		 * c = position (center of sphere)
		 * d = uDir
		 * (t^2)(d dot d) + 2t(d dot(b-c)) + (b-c)dot(b-c) = r^2
		 * => t^2 + 2t(d dot (b-c)) + (b-c)dot(b-c)-r^2 = 0
		 * 
		 * Use quadratic formula to solve for t
		 */
		double a = 1;
		double b = 2*uDir.dot(base.sub(position));
		double c = base.sub(position).dot(base.sub(position)) - radius*radius;
		
		double discriminant = b*b - 4*a*c;
		//imaginary hit point
		if(discriminant < 0)
			return NO_HIT;
		double t1 = (-b - Math.sqrt(discriminant))/(2*a);
		double t2 = (-b + Math.sqrt(discriminant))/(2*a);
		if(t1 >= 0)
			return t1;
		else
			return t2;
	}
	
	/*Pre:
	 * 1. base != null
	 * 2. uDir != null
	 * 3. uDir is unit
	 */
	@Override
	public HitpointData hit(Vector3 base, Vector3 uDir) {
		assert base != null;
		assert uDir != null;
		assert uDir.isUnit();
		
		double distance = distanceToHitpoint(base, uDir);
		Vector3 hitPoint = base.add(uDir.scale(distance));
		Vector3 normal = hitPoint.sub(position).unit();
		HitpointData result = new HitpointData(hitPoint, normal, distance);
		
		return result;
	}
}
