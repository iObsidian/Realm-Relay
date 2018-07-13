package rotmg.stage3D;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.display.GraphicsBitmapFill;
import flash.display.GraphicsSolidFill;
import flash.display3D.Context3DVertexBufferFormat;
import flash.display3D.VertexBuffer3D;
import flash.geom.ColorTransform;
import flash.utils.Dictionary;
import rotmg.parameters.Parameters;

public class GraphicsFillExtra {

	private static final Vector<Double> DEFAULT_OFFSET = new Vector<Double>(0.0, 0.0, 0.0, 0.0);
	private static Dictionary<GraphicsBitmapFill, Vector<Double>> textureOffsets = new Dictionary<>();
	private static int textureOffsetsSize = 0;
	private static Dictionary<GraphicsBitmapFill, Double> waterSinks = new Dictionary<>();
	private static int waterSinksSize = 0;
	private static Dictionary<BitmapData, ColorTransform> colorTransforms = new Dictionary<>();
	private static int colorTransformsSize = 0;
	private static Dictionary<GraphicsBitmapFill, VertexBuffer3D> vertexBuffers = new Dictionary<>();
	private static int vertexBuffersSize = 0;
	private static Dictionary<GraphicsBitmapFill, Boolean> softwareDraw = new Dictionary<>();
	private static int softwareDrawSize = 0;
	private static Dictionary<GraphicsSolidFill, Boolean> softwareDrawSolid = new Dictionary<>();
	private static int softwareDrawSolidSize = 0;
	private static int lastChecked = 0;

	public GraphicsFillExtra() {
		super();
	}

	public static void setColorTransform(BitmapData param1, ColorTransform param2) {
		if (!Parameters.isGpuRender()) {
			return;
		}
		if (colorTransforms.get(param1) == null) {
			colorTransformsSize++;
		}
		colorTransforms.put(param1, param2);
	}

	public static ColorTransform getColorTransform(BitmapData param1) {
		ColorTransform loc2 = null;
		if (colorTransforms.contains(param1)) {
			loc2 = colorTransforms.get(param1);
			colorTransforms.put(param1, new ColorTransform());
		} else {
			loc2 = new ColorTransform();
			colorTransforms.put(param1, loc2);
			colorTransformsSize++;
		}
		return loc2;
	}

	public static void setOffsetUV(GraphicsBitmapFill param1, double param2, double param3) {
		if (!Parameters.isGpuRender()) {
			return;
		}
		testOffsetUV(param1);
		textureOffsets.get(param1).put(0, param2);
		textureOffsets.get(param1).put(1, param3);
	}

	public static Vector<Double> getOffsetUV(GraphicsBitmapFill param1) {
		if (textureOffsets.get(param1) != null) {
			return textureOffsets.get(param1);
		}
		return DEFAULT_OFFSET;
	}

	private static void testOffsetUV(GraphicsBitmapFill param1) {
		if (!Parameters.isGpuRender()) {
			return;
		}
		if (textureOffsets.get(param1) == null) {
			textureOffsetsSize++;
			textureOffsets.put(param1, new Vector<Double>(0.0, 0.0, 0.0, 0.0));
		}
	}

	public static void setSinkLevel(GraphicsBitmapFill param1, double param2) {
		if (!Parameters.isGpuRender()) {
			return;
		}
		if (waterSinks.get(param1) == null) {
			waterSinksSize++;
		}
		waterSinks.put(param1, param2);
	}

	public static double getSinkLevel(GraphicsBitmapFill param1) {
		if (waterSinks.get(param1) != null && waterSinks.get(param1) instanceof Double) {
			return waterSinks.get(param1);
		}
		return 0;
	}

	public static void setVertexBuffer(GraphicsBitmapFill param1, Vector<Double> param2) {
		if (!Parameters.isGpuRender()) {
			return;
		}
		Context3DProxy loc3 = Context3DProxy.getInstance();
		VertexBuffer3D loc4 = loc3.GetContext3D().createVertexBuffer(4, 5);
		loc4.uploadFromVector(param2, 0, 4);
		loc3.GetContext3D().setVertexBufferAt(0, loc4, 0, Context3DVertexBufferFormat.FLOAT_3);
		loc3.GetContext3D().setVertexBufferAt(1, loc4, 3, Context3DVertexBufferFormat.FLOAT_2);
		if (vertexBuffers.get(param1) == null) {
			vertexBuffersSize++;
		}
		vertexBuffers.put(param1, loc4);
	}

	public static VertexBuffer3D getVertexBuffer(GraphicsBitmapFill param1) {
		if (vertexBuffers.get(param1) != null && vertexBuffers.get(param1) instanceof VertexBuffer3D) {
			return vertexBuffers.get(param1);
		}
		return null;
	}

	public static void clearSink(GraphicsBitmapFill param1) {
		if (!Parameters.isGpuRender()) {
			return;
		}
		if (waterSinks.get(param1) != null) {
			waterSinksSize--;
			waterSinks.remove(param1);
		}
	}

	public static void setSoftwareDraw(GraphicsBitmapFill param1, boolean param2) {
		if (!Parameters.isGpuRender()) {
			return;
		}
		if (softwareDraw.get(param1) == null) {
			softwareDrawSize++;
		}
		softwareDraw.put(param1, param2);
	}

	public static boolean isSoftwareDraw(GraphicsBitmapFill param1) {
		if (softwareDraw.get(param1) != null && softwareDraw.get(param1) instanceof Boolean) {
			return softwareDraw.get(param1);
		}
		return false;
	}

	public static void setSoftwareDrawSolid(GraphicsSolidFill param1, boolean param2) {
		if (!Parameters.isGpuRender()) {
			return;
		}
		if (softwareDrawSolid.get(param1) == null) {
			softwareDrawSolidSize++;
		}
		softwareDrawSolid.put(param1, param2);
	}

	public static boolean isSoftwareDrawSolid(GraphicsSolidFill param1) {
		if (softwareDrawSolid.get(param1) != null && softwareDrawSolid.get(param1) != null) {
			return softwareDrawSolid.get(param1);
		}
		return false;
	}

	public static void dispose() {
		textureOffsets = new Dictionary<>();
		waterSinks = new Dictionary<>();
		colorTransforms = new Dictionary<>();
		disposeVertexBuffers();
		softwareDraw = new Dictionary<>();
		softwareDrawSolid = new Dictionary<>();
		textureOffsetsSize = 0;
		waterSinksSize = 0;
		colorTransformsSize = 0;
		vertexBuffersSize = 0;
		softwareDrawSize = 0;
		softwareDrawSolidSize = 0;
	}

	public static void disposeVertexBuffers() {
		for (VertexBuffer3D loc1 : vertexBuffers) {
			loc1.dispose();
		}
		vertexBuffers = new Dictionary<>();
	}

	public static void manageSize() {
		if (colorTransformsSize > 2000) {
			colorTransforms = new Dictionary<>();
			colorTransformsSize = 0;
		}
		if (textureOffsetsSize > 2000) {
			textureOffsets = new Dictionary<>();
			textureOffsetsSize = 0;
		}
		if (waterSinksSize > 2000) {
			waterSinks = new Dictionary<>();
			waterSinksSize = 0;
		}
		if (vertexBuffersSize > 1000) {
			disposeVertexBuffers();
			vertexBuffersSize = 0;
		}
		if (softwareDrawSize > 2000) {
			softwareDraw = new Dictionary<>();
			softwareDrawSize = 0;
		}
		if (softwareDrawSolidSize > 2000) {
			softwareDrawSolid = new Dictionary<>();
			softwareDrawSolidSize = 0;
		}
	}
}
