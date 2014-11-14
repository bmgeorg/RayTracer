package scene;

//immutable
public class Shading {
	private Color diffuse, ambient, specular;

	/*Pre:
	 * 1. diffuse != null
	 * 2. ambient != null
	 * 3. specular != null
	 */
	public Shading(Color diffuse, Color ambient, Color specular) {
		assert diffuse != null;
		assert ambient != null;
		assert specular != null;
		
		this.diffuse = diffuse;
		this.ambient = ambient;
		this.specular = specular;
	}
	
	public Color getDiffuse() {
		return diffuse;
	}

	public Color getAmbient() {
		return ambient;
	}

	public Color getSpecular() {
		return specular;
	}
	
	@Override
	public String toString() {
		return String.format("Diffuse - %s Ambient - %s Spectral - %s", diffuse.toString(), ambient.toString(), specular.toString());
	}
}
