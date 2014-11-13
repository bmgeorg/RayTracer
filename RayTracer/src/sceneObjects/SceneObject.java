package sceneObjects;

import scene.HitpointData;
import scene.Vector3;

public abstract class SceneObject {
	protected Vector3 position;
	
	/*Pre:
	 * 1. position != null
	 */
	public SceneObject(Vector3 position) {
		assert position != null;
		this.position = position;
	}

	public Vector3 getPosition() {
		return position;
	}

	/*Pre:
	 * 1. position != null
	 */
	public void setPosition(Vector3 position) {
		assert position != null;
		this.position = position;
	}
	
	/*Pre:
	 * 1. base != null
	 * 2. uDir != null
	 * 3. uDir is unit
	 */
	public abstract HitpointData hit(Vector3 base, Vector3 uDir);
}
