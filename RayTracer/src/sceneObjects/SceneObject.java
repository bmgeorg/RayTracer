package sceneObjects;


import scene.Color;
import scene.HitpointData;
import scene.Shading;
import scene.Vector3;

public abstract class SceneObject {
	protected Vector3 position;
	protected Shading baseShading;

	/*Pre:
	 * 1. position != null
	 */
	public SceneObject(Vector3 position) {
		this(position, new Shading(Color.black, Color.black, Color.black));
	}
	
	/*Pre:
	 * 1. position != null
	 * 3. baseShading != null
	 */
	public SceneObject(Vector3 position, Shading baseShading) {
		assert position != null;
		assert baseShading != null;

		this.position = position;
		this.baseShading = baseShading;
	}

	public Vector3 getPosition() {
		return position;
	}
	public Shading getBaseShading() {
		return baseShading;
	}
	public void setBaseShading(Shading baseShading) {
		this.baseShading = baseShading;
	}
	
	/*Pre:
	 * 1. base != null
	 * 2. uDir != null
	 * 3. uDir is unit
	 */
	public abstract HitpointData hit(Vector3 base, Vector3 uDir);
}
