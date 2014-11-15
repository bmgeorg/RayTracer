package scene;

//Immutable
public class Light {
	private Vector3 position;
	private double brightness;
	
	/* constructors */
	/*Pre:
	 * 1. position != null
	 * 2. 0 <= brightness <= 1
	 */
	public Light(Vector3 position, double brightness) {
		assert position != null;
		assert brightness >= 0;
		assert brightness <= 1;
		
		this.position = position;
		this.brightness = brightness;
	}

	public Vector3 getPosition() {
		return position;
	}

	public double getBrightness() {
		return brightness;
	}
	
	
}
