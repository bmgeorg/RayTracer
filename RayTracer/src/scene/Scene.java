package scene;

import java.util.ArrayList;

import ppm.Pixel;
import sceneObjects.SceneObject;

//mutable
public class Scene {
	private ArrayList<SceneObject> sceneObjects;
	private Viewpoint viewpoint;
	private Pixel backgroundColor;
	
	public Scene() {
		sceneObjects = new ArrayList<SceneObject>();
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
				Vector3 uRay = viewpoint.fireRay(row, col);
				
				HitpointData closestHit = getClosestHit(viewpoint.getPosition(), uRay);
				if(closestHit != null) {
					image[row][col] = makePixel(closestHit);
					
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
	
	private Pixel makePixel(HitpointData hitpoint) {
		assert hitpoint.isHit();
		Color finalColor = new Color(0, 0, 0);
		
		Color ambient = hitpoint.getShading().getAmbient();
		Color diffuse = hitpoint.getShading().getDiffuse();
		Color specular = hitpoint.getShading().getSpecular();
		
		//ambient
		finalColor = finalColor.add(calcAmbient(hitpoint, ambient));
		finalColor = finalColor.add(calcDiffuse(hitpoint, diffuse));
		finalColor = finalColor.add(calcSpecular(hitpoint, specular));
		
		return new Pixel(finalColor);
	}
	
	private Color calcAmbient(HitpointData hitpoint, Color ambient) {
		return ambient;
	}
	
	private Color calcDiffuse(HitpointData hitpoint, Color diffuse) {
		double cosOfAngle = -hitpoint.getuIncoming().dot(hitpoint.getuNormal());
		assert cosOfAngle > 0;
		
		return diffuse.scale(cosOfAngle);
	}
	
	private Color calcSpecular(HitpointData hitpoint, Color diffuse) {
		
		return Color.black;
	}
	
	private HitpointData getClosestHit(Vector3 base, Vector3 ray) {
		double closestHitDist = Double.MAX_VALUE;
		HitpointData closestHit = null;
		for(SceneObject so: sceneObjects) {
			HitpointData hit = so.hit(viewpoint.getPosition(), ray);
			if(hit.isHit()) {
				assert hit.getDistance() >= 0;
				if(hit.getDistance() >= viewpoint.getFrameDepth() && 
						hit.getDistance() < closestHitDist) {
					closestHit = hit;
				}
			}
		}
		return closestHit;
	}
}
