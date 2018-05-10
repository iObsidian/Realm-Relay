package realmrelay.game.engine3d;

import javafx.scene.Camera;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import realmrelay.packets.data.unused.BitmapData;

public class Point3D {

    private static final List<int> commands = new <int>[GraphicsPathCommand.MOVE_TO, GraphicsPathCommand.LINE_TO,GraphicsPathCommand.LINE_TO,GraphicsPathCommand.LINE_TO];

    private static final GraphicsEndFill END_FILL = new GraphicsEndFill();
    private final float[] data = new float[0];
    private final GraphicsBitmapFill bitmapFill = new GraphicsBitmapFill(null, new Matrix(), false, false);
    private final GraphicsSolidFill solidFill = new GraphicsSolidFill(0, 1);

    public Point3D(float param1) {
        path = new GraphicsPath(commands, this.data);
        this.size = param1;
    }

    public Number size;
    public Vector3D posS;
    private GraphicsPath path;

    public void setSize(float param1) {
        this.size = param1;
    }


    public void draw() {

    }

    public void draw(List<IGraphicsData> graphicsData, Vector3D posL, float angle, Matrix3D lToS, Camera camera, BitmapData bitmapData, int color) {
        this.posS = Utils3D.projectVector(lToS, posL);
        if (this.posS.w < 0) {
            return;
        }
        float o = this.posS.w * Math.sin(camera.pp.fieldOfView / 2 * Trig.toRadians);
        float s = this.size / o;
        this.data.length = 0;
        if (angle == 0) {
            this.data.add(this.posS.x - s, this.posS.y - s, this.posS.x + s, this.posS_.y - s, this.posS_.x + s, this.posS_.y + s, this.posS_.x - s, this.posS_.y + s);
        } else {
            double ca = Math.cos(angle);
            double sa = Math.sin(angle);
            this.data.add(this.posS_.x + (ca * -s + sa * -s), this.posS_.y + (sa * -s - ca * -s), this.posS_.x + (ca * s + sa * -s), this.posS_.y + (sa * s - ca * -s), this.posS_.x + (ca * s + sa * s), this.posS_.y + (sa * s - ca * s), this.posS_.x + (ca * -s + sa * s), this.posS_.y + (sa * -s - ca * s));
        }
        if (bitmapData != null) {
            this.bitmapFill_.bitmapData = bitmapData;
            Matrix m = this.bitmapFill_.matrix;
            m.identity();
            m.scale(2 * s / bitmapData.width, 2 * s / bitmapData.height);
            m.translate(-s, -s);
            m.rotate(angle);
            m.translate(this.posS_.x, this.posS_.y);
            graphicsData.add(this.bitmapFill_);
        } else {
            this.solidFill_.color = color;
            graphicsData.add(this.solidFill_);
        }
        graphicsData.add(this.path_);
        graphicsData.add(END_FILL);
    }


}