package scene;

//Immutable
public class Viewpoint {
	private Vector3 position, uFrontOrientation, uDownOrientation, uRightOrientation, frameCenter;
	private double frameWidth, frameHeight, frameDepth;
	private int imageWidth, imageHeight;
	
	/* constructors */
	
	/*Pre:
	 * 1. uFrontOrientation != null
	 * 2. uDownOrientation != null
	 * 3. uFrontOrientation is unit
	 * 4. uDownOrientation is unit
	 * 5. uFrontOrientation dot uDownOrientation = 0
	 * 6. frameWidth > 0
	 * 7. frameHeight > 0
	 * 8. frameDepth > 0
	 * 9. imageWidth > 0
	 * 10. imageHeight > 0
	 * 11. position != null
	 */
	public Viewpoint(Vector3 position, Vector3 uFrontOrientation, Vector3 uDownOrientation,
			double frameWidth, double frameHeight, double frameDepth,
			int imageWidth, int imageHeight) {
		assert position != null;
		assert uFrontOrientation != null;
		assert uFrontOrientation.isUnit();
		assert uDownOrientation != null;
		assert uDownOrientation.isUnit();
		assert Math.abs(uFrontOrientation.dot(uDownOrientation)) < Vector3.ABSOLUTE_TOLERANCE;
		assert frameWidth > 0;
		assert frameHeight > 0;
		assert frameDepth > 0;
		assert imageWidth > 0;
		assert imageHeight > 0;
		
		this.position = position;
		this.uDownOrientation = uDownOrientation;
		this.uFrontOrientation = uFrontOrientation;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.frameDepth = frameDepth;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		
		this.uRightOrientation = uDownOrientation.cross(uFrontOrientation).unit();
		this.frameCenter = position.add(uFrontOrientation.scale(frameDepth));
	}

	/* getters */

	public Vector3 getPosition() {
		return position;
	}
	
	public Vector3 getuFrontOrientation() {
		return uFrontOrientation;
	}

	public Vector3 getuDownOrientation() {
		return uDownOrientation;
	}

	public Vector3 getuRightOrientation() {
		return uRightOrientation;
	}

	public Vector3 getFrameCenter() {
		return frameCenter;
	}

	public static Vector3 getOrientation() {
		return FRONT_ORIENTATION;
	}

	public double getFrameWidth() {
		return frameWidth;
	}

	public double getFrameHeight() {
		return frameHeight;
	}

	public double getFrameDepth() {
		return frameDepth;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}
	
	/* action methods */
	
	/*
	 * Pre:
	 * 1. 0 <= y < imageHeight
	 * 2. 0 <= x < imageWidth
	 */
	public Vector3 fireRay(int x, int y) {
		assert y >= 0;
		assert y < imageHeight;
		assert x >= 0;
		assert x < imageWidth;

		//find frame coords of x, y
		double fy, fx;
		if(imageHeight == 0)
			fy = 0;
		else
			fy = (y * frameHeight)/(imageHeight-1);
		if(imageWidth == 0)
			fx = 0;
		else
			fx = (x * frameWidth)/(imageWidth-1);
		
		//find frame coords relative center of frame
		//positive y-axis down
		//positive x-axis right
		double cfy = fy - frameHeight/2;
		double cfx = fx - frameWidth/2;
		
		Vector3 result = frameCenter.add(uDownOrientation.scale(cfy)).add(uRightOrientation.scale(cfx));
		
		return result;
	}
	
	/* for debugging */
	final static double FRAME_WIDTH = 10;
	final static double FRAME_HEIGHT = 10;
	final static double FRAME_DEPTH = 10;
	final static int IMAGE_WIDTH = 500;
	final static int IMAGE_HEIGHT = 500;
	final static Vector3 POSITION = new Vector3();
	final static Vector3 FRONT_ORIENTATION = new Vector3(0, 0, 1);
	final static Vector3 DOWN_ORIENTATION = new Vector3(0, -1, 0);
	
	static Viewpoint defaultViewpoint = null;
	public static Viewpoint getDefaultViewpoint() {
		if(defaultViewpoint == null) {
			defaultViewpoint = new Viewpoint(POSITION, FRONT_ORIENTATION, DOWN_ORIENTATION, FRAME_WIDTH, FRAME_HEIGHT, FRAME_DEPTH, IMAGE_WIDTH, IMAGE_HEIGHT);
		}
		return defaultViewpoint;
	}
}
