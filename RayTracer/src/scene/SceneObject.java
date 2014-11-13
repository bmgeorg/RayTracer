package scene;

public abstract class SceneObject {
	private Vector3 position;
	
	public SceneObject() {
		this.position = new Vector3();
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public abstract HitpointData hit(Vector3 base, Vector3 uDir);
}
