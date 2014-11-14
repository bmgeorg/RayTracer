package scene;

//immutable
public class Lighting {
	private double diffuse, ambient, spectral;

	/*Pre:
	 * 1. 0 <= diffuse <= 1
	 * 2. 0 <= ambient <= 1
	 * 3. 0 <= spectral <= 1
	 */
	public Lighting(double diffuse, double ambient, double spectral) {
		assert diffuse >= 0;
		assert diffuse <= 1;
		assert ambient >= 0;
		assert ambient <= 1;
		assert spectral >= 0;
		assert spectral <= 1;
		
		this.diffuse = diffuse;
		this.ambient = ambient;
		this.spectral = spectral;
	}
	
	public double getDiffuse() {
		return diffuse;
	}

	public double getAmbient() {
		return ambient;
	}

	public double getSpectral() {
		return spectral;
	}
	
	@Override
	public String toString() {
		return String.format("Diffuse: %f Ambient: %f Spectral: %f", diffuse, ambient, spectral);
	}
}
