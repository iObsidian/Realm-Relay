package rotmg.map;

import flash.geom.Matrix3D;
import flash.geom.PerspectiveProjection;
import flash.geom.Rectangle;
import flash.geom.Vector3D;
import rotmg.objects.GameObject;
import rotmg.parameters.Parameters;
import rotmg.util.RandomUtil;

public class Camera {

	public static final Vector3D lN = new Vector3D(0, 0, 1);

	private static final Rectangle CENTERSCREENRECT = new Rectangle(-300, -325, 600, 600);

	private static final Rectangle OFFSETSCREENRECT = new Rectangle(-300, -450, 600, 600);

	private static final Rectangle SCREENSHOTSCREENRECT = new Rectangle(-400, -325, 800, 600);

	private static final Rectangle SLIMSCREENSHOTSCREENRECT = new Rectangle(-400, -275, 800, 500);
	private final double MAXJITTER = 0.5;
	private final int JITTERBUILDUPMS = 10000;
	public double x;
	public double y;
	public double z;
	public double angleRad;
	public Rectangle clipRect;
	public PerspectiveProjection pp;
	public double maxDist;
	public double maxDistSq;
	public boolean isHallucinating = false;
	public Matrix3D wToS;
	public Matrix3D wToV;
	public Matrix3D vToS;
	public Matrix3D ppMatrix;
	private Matrix3D nonPPMatrix;
	private Vector3D p;
	private Vector3D f;
	private Vector3D u;
	private Vector3D r;
	private boolean isJittering = false;
	private double jitter = 0;
	private double[] rd;

	public Camera() {
		super();
		this.pp = new PerspectiveProjection();
		this.wToS = new Matrix3D();
		this.wToV = new Matrix3D();
		this.vToS = new Matrix3D();
		this.nonPPMatrix = new Matrix3D();
		this.p = new Vector3D();
		this.f = new Vector3D();
		this.u = new Vector3D();
		this.r = new Vector3D();
		this.rd = new double[16];
		this.pp.focalLength = 3;
		this.pp.fieldOfView = 48;
		this.ppMatrix = this.pp.toMatrix3D();
		this.nonPPMatrix.appendScale(50, 50, 50);
		this.f.x = 0;
		this.f.y = 0;
		this.f.z = -1;
	}

	public void configureCamera(GameObject object, boolean isHallucinating) {
		Rectangle screenRect = Parameters.Data.centerOnPlayer ? CENTERSCREENRECT : OFFSETSCREENRECT;
		if (Parameters.screenShotMode) {
			if (!Parameters.screenShotSlimMode) {
				screenRect = SCREENSHOTSCREENRECT;
			} else {
				screenRect = SLIMSCREENSHOTSCREENRECT;
			}
		}
		double cameraAngle = Parameters.Data.cameraAngle;
		this.configure(object.x, object.y, 12, cameraAngle, screenRect, false);
		this.isHallucinating = isHallucinating;
	}

	public void startJitter() {
		this.isJittering = true;
		this.jitter = 0;
	}

	public void update(Number dt) {
	}

	public void configure(double x, double y, double z, double angleRad, Rectangle clipRect, boolean usePP) {
		double o = 0;
		double w = 0;
		double h = 0;
		if (this.isJittering) {
			x = x + RandomUtil.plusMinus(this.jitter);
			y = y + RandomUtil.plusMinus(this.jitter);
		}
		this.x = x;
		this.y = y;
		this.z = z;
		this.angleRad = angleRad;
		this.clipRect = clipRect;
		this.p.x = x;
		this.p.y = y;
		this.p.z = z;
		this.r.x = Math.cos(this.angleRad);
		this.r.y = Math.sin(this.angleRad);
		this.r.z = 0;
		this.u.x = Math.cos(this.angleRad + Math.PI / 2);
		this.u.y = Math.sin(this.angleRad + Math.PI / 2);
		this.u.z = 0;
		this.rd[0] = this.r.x;
		this.rd[1] = this.u.x;
		this.rd[2] = this.f.x;
		this.rd[3] = 0;
		this.rd[4] = this.r.y;
		this.rd[5] = this.u.y;
		this.rd[6] = this.f.y;
		this.rd[7] = 0;
		this.rd[8] = this.r.z;
		this.rd[9] = !!usePP ? this.u.z : -1;
		this.rd[10] = this.f.z;
		this.rd[11] = 0;
		this.rd[12] = -this.p.dotProduct(this.r);
		this.rd[13] = -this.p.dotProduct(this.u);
		this.rd[14] = -this.p.dotProduct(this.f);
		this.rd[15] = 1;
		this.wToV.rawData = this.rd;
		if (usePP) {
			this.vToS = this.ppMatrix;
		} else {
			this.vToS = this.nonPPMatrix;
		}
		this.wToS.identity();
		this.wToS.append(this.wToV);
		this.wToS.append(this.vToS);
		if (usePP) {
			/*o = this.z * Math.tan(this.pp.fieldOfView / 2 * Trig.toRadians);
			this.maxDist = Math.SQRT2 * o * 1.4;*/
		} else {
			w = this.clipRect.width / (2 * 50);
			h = this.clipRect.height / (2 * 50);
			this.maxDist = Math.sqrt(w * w + h * h) + 1;
		}
		this.maxDistSq = this.maxDist * this.maxDist;
	}
}
