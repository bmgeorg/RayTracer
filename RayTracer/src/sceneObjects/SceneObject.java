package sceneObjects;


import scene.Color;
import scene.HitpointData;
import scene.Lighting;
import scene.Vector3;

public abstract class SceneObject {
	protected Vector3 position;
	protected Color baseColor;
	protected Lighting baseLighting;

	/*Pre:
	 * 1. position != null
	 */
	public SceneObject(Vector3 position) {
		this(position, new Color(0, 0, 0), new Lighting(0, 0, 0));
	}
	
	/*Pre:
	 * 1. position != null
	 * 2. baseColor != null
	 * 3. baseLighting != null
	 */
	public SceneObject(Vector3 position, Color baseColor, Lighting baseLighting) {
		assert position != null;
		assert baseColor != null;
		assert baseLighting != null;

		this.position = position;
		this.baseColor = baseColor;
		this.baseLighting = baseLighting;
	}

	public Vector3 getPosition() {
		return position;
	}
	public Lighting getBaseLighting() {
		return baseLighting;
	}
	public void setBaseLighting(Lighting baseLighting) {
		this.baseLighting = baseLighting;
	}
	
	/*Pre:
	 * 1. base != null
	 * 2. uDir != null
	 * 3. uDir is unit
	 */
	public abstract HitpointData hit(Vector3 base, Vector3 uDir);
}
