package scene;

import java.util.ArrayList;

import ppm.Pixel;
import sceneObjects.SceneObject;

//mutable
public class Scene {
	private static final int NUM_SPECULAR_REFLECTIONS = 1; // [0, inf]
	private static final double MIN_DISTANCE_OF_REFLECTION = .01;
	
	private ArrayList<SceneObject> sceneObjects;
	private ArrayList<Light> lights;
	private Viewpoint viewpoint;
	private Pixel backgroundColor;

	public Scene() {
		sceneObjects = new ArrayList<SceneObject>();
		lights = new ArrayList<Light>();
		viewpoint = null;
		backgroundColor = new Pixel(Color.black);
	}

	public Viewpoint getViewpoint() {
		return viewpoint;
	}

	public void setViewpoint(Viewpoint viewpoint) {
		this.viewpoint = viewpoint;
	}

	public ArrayList<SceneObject> getSceneObjects() {
		return sceneObjects;
	}

	public void addSceneObject(SceneObject sceneObject) {
		sceneObjects.add(sceneObject);
	}

	public ArrayList<Light> getLights() {
		return lights;
	}

	public void addLight(Light light) {
		lights.add(light);
	}

	public Pixel getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Pixel backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Pixel[][] render() {
		assert viewpoint != null;
		Pixel[][] image = new Pixel[viewpoint.getImageHeight()][viewpoint.getImageWidth()];

		for(int row = 0; row < viewpoint.getImageHeight(); row++) {
			for(int col = 0; col < viewpoint.getImageWidth(); col++) {
				Vector3 uRay = viewpoint.fireRay(col, row);

				if(row == 328 && col == 98) {
					System.err.println();
				}
				//TODO: min distance forms a bowl currently, instead of the plane of the screen
				HitpointData closestHit = getClosestHit(viewpoint.getPosition(), uRay, viewpoint.getFrameDepth());
				if(closestHit != HitpointData.getNoHit()) {
					image[row][col] = new Pixel(makeColor(closestHit, NUM_SPECULAR_REFLECTIONS));

					//Debugging Start
					if(Math.abs(closestHit.getDistance() - viewpoint.getFrameDepth()) < Vector3.ABSOLUTE_TOLERANCE)
						System.err.println("hit object very close to screen: " + closestHit.toString());
					//Debugging end
				} else {
					image[row][col] = backgroundColor;
				}
			}
		}
		return image;
	}

	/*Pre:
	 * 1. hitpoint != null
	 * 2. hitpoint.isHit() = true
	 * 2. numReflectionsLeft >= 0
	 */
	private Color makeColor(HitpointData hitpoint, int numReflectionsLeft) {
		assert hitpoint != null;
		assert hitpoint.isHit();
		assert numReflectionsLeft >= 0;

		Color finalColor = new Color(0, 0, 0);

		Color ambient = hitpoint.getShading().getAmbient();
		Color diffuse = hitpoint.getShading().getDiffuse();
		Color specular = hitpoint.getShading().getSpecular();

		//ambient
		finalColor = finalColor.add(calcAmbient(hitpoint, ambient));
		finalColor = finalColor.add(calcDiffuse(hitpoint, diffuse));
		finalColor = finalColor.add(calcSpecular(hitpoint, specular, numReflectionsLeft));

		return finalColor;
	}

	private Color calcAmbient(HitpointData hitpoint, Color ambient) {
		return ambient;
	}

	private Color calcDiffuse(HitpointData hitpoint, Color diffuse) {
		double cumulativeIntensity = 0;
		for(Light light : lights) {
			Vector3 uRay = hitpoint.getPosition().sub(light.getPosition()).unit();
			//TODO: Optimization: never calculate closestHit for any given light more than once
			HitpointData hitPointOfLightRay = getClosestHit(light.getPosition(), uRay, 0);
			//If light hits point
			if(		hitPointOfLightRay != HitpointData.getNoHit() &&
					hitPointOfLightRay.getHitObject() == hitpoint.getHitObject() &
					hitPointOfLightRay.getPosition().equalsWithTol(hitpoint.getPosition())) {
				Vector3 uIncidentRay = uRay.scale(-1).unit();
				double cosOfAngle = uIncidentRay.dot(hitpoint.getuNormal());
				if(cosOfAngle > 0) {
					cumulativeIntensity += cosOfAngle*light.getBrightness();
				}
			}
		}

		return diffuse.scale(cumulativeIntensity);
	}

	private Color calcSpecular(HitpointData hitpoint, Color diffuse, int numReflectionsLeft) {
		if(numReflectionsLeft <= 0)
			return Color.black;
		if(diffuse.getR() == 0 && diffuse.getB() == 0 && diffuse.getB() == 0)
			return Color.black;
		Vector3 newBase = hitpoint.getPosition();
		Vector3 projectionOfIncoming = hitpoint.getuIncoming().projectTo(hitpoint.getuNormal());
		Vector3 uReflectedRay = hitpoint.getuIncoming().add(projectionOfIncoming.scale(-2)).unit();
		HitpointData newHitpoint = getClosestHit(newBase, uReflectedRay, MIN_DISTANCE_OF_REFLECTION);
		if(!newHitpoint.isHit())
			return Color.black;
		Color reflectionColor = makeColor(newHitpoint, numReflectionsLeft-1);
		//scale reflectionColor by diffuse
		Color result = new Color(diffuse.getR()*reflectionColor.getR(), diffuse.getG()*reflectionColor.getG(), diffuse.getB()*reflectionColor.getB());
		
		return result;
	}

	private HitpointData getClosestHit(Vector3 base, Vector3 uRay, double minDistance) {
		assert uRay.isUnit();
		double closestHitDist = Double.MAX_VALUE;
		HitpointData closestHit = HitpointData.getNoHit();
		for(SceneObject so: sceneObjects) {
			HitpointData hit = so.hit(base, uRay);
			if(hit.isHit()) {
				assert hit.getDistance() >= 0;
				if(hit.getDistance() >= minDistance && 
						hit.getDistance() < closestHitDist) {
					closestHit = hit;
					closestHitDist = hit.getDistance();
				}
			}
		}
		return closestHit;
	}
}
