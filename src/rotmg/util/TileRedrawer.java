package rotmg.util;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.geom.Point;
import flash.geom.Rectangle;
import flash.utils.Dictionary;
import rotmg.map.GroundLibrary;
import rotmg.map.Map;
import rotmg.objects.Square;
import rotmg.parameters.Parameters;

public class TileRedrawer {

	private static final Rectangle rect0 = new Rectangle(0, 0, 4, 4);

	private static final Point p0 = new Point(0, 0);

	private static final Rectangle rect1 = new Rectangle(4, 0, 4, 4);

	private static final Point p1 = new Point(4, 0);

	private static final Rectangle rect2 = new Rectangle(0, 4, 4, 4);

	private static final Point p2 = new Point(0, 4);

	private static final Rectangle rect3 = new Rectangle(4, 4, 4, 4);

	private static final Point p3 = new Point(4, 4);

	private static final int INNER = 0;

	private static final int SIDE0 = 1;

	private static final int SIDE1 = 2;

	private static final int OUTER = 3;

	private static final int INNERP1 = 4;

	private static final int INNERP2 = 5;

	private static final Vector<Vector<ImageSet>> mlist = getMasks();
	private static final Rectangle RECT01 = new Rectangle(0, 0, 8, 4);
	private static final Rectangle RECT13 = new Rectangle(4, 0, 4, 8);
	private static final Rectangle RECT23 = new Rectangle(0, 4, 8, 4);
	private static final Rectangle RECT02 = new Rectangle(0, 0, 4, 8);
	private static final Rectangle RECT0 = new Rectangle(0, 0, 4, 4);
	private static final Rectangle RECT1 = new Rectangle(4, 0, 4, 4);
	private static final Rectangle RECT2 = new Rectangle(0, 4, 4, 4);
	private static final Rectangle RECT3 = new Rectangle(4, 4, 4, 4);
	private static final Point POINT0 = new Point(0, 0);
	private static final Point POINT1 = new Point(4, 0);
	private static final Point POINT2 = new Point(0, 4);
	private static final Point POINT3 = new Point(4, 4);
	private static Vector<Dictionary<Vector<Integer>, BitmapData>> cache = new Vector<>();


	public TileRedrawer() {
		super();
	}

	public static BitmapData redraw(Square param1, boolean param2) {
		Vector<Integer> loc3 = null;
		BitmapData loc5 = null;
		if (Parameters.blendType == 0) {
			return null;
		}
		if (param1.tileType == 253) {
			loc3 = getCompositeSig(param1);
		} else if (param1.props.hasEdge) {
			loc3 = getEdgeSig(param1);
		} else {
			loc3 = getSig(param1);
		}
		if (loc3 == null) {
			return null;
		}
		Dictionary<Vector<Integer>, BitmapData> loc4 = cache.get(Parameters.blendType);
		if (loc4.contains(loc3)) {
			return loc4.get(loc3);
		}
		if (param1.tileType == 253) {
			loc5 = buildComposite(loc3);
			loc4.put(loc3, loc5);
			return loc5;
		}
		if (param1.props.hasEdge) {
			loc5 = drawEdges(loc3);
			loc4.put(loc3, loc5);
			return loc5;
		}
		boolean loc6 = false;
		boolean loc7 = false;
		boolean loc8 = false;
		boolean loc9 = false;
		if (loc3.get(1) != loc3.get(4)) {
			loc6 = true;
			loc7 = true;
		}
		if (loc3.get(3) != loc3.get(4)) {
			loc6 = true;
			loc8 = true;
		}
		if (loc3.get(5) != loc3.get(4)) {
			loc7 = true;
			loc9 = true;
		}
		if (loc3.get(7) != loc3.get(4)) {
			loc8 = true;
			loc9 = true;
		}
		if (!loc6 && loc3.get(0) != loc3.get(4)) {
			loc6 = true;
		}
		if (!loc7 && loc3.get(2) != loc3.get(4)) {
			loc7 = true;
		}
		if (!loc8 && loc3.get(6) != loc3.get(4)) {
			loc8 = true;
		}
		if (!loc9 && loc3.get(8) != loc3.get(4)) {
			loc9 = true;
		}
		if (!loc6 && !loc7 && !loc8 && !loc9) {
			loc4.put(loc3, null);
			return null;
		}
		BitmapData loc10 = GroundLibrary.getBitmapData(param1.tileType);
		if (param2) {
			loc5 = loc10.clone();
		} else {
			loc5 = new BitmapDataSpy(loc10.width, loc10.height, true, 0);
		}
		if (loc6) {
			redrawRect(loc5, rect0, p0, mlist.get(0), loc3.get(4), loc3.get(3), loc3.get(0), loc3.get(1));
		}
		if (loc7) {
			redrawRect(loc5, rect1, p1, mlist.get(1), loc3.get(4), loc3.get(1), loc3.get(2), loc3.get(5));
		}
		if (loc8) {
			redrawRect(loc5, rect2, p2, mlist.get(2), loc3.get(4), loc3.get(7), loc3.get(6), loc3.get(3));
		}
		if (loc9) {
			redrawRect(loc5, rect3, p3, mlist.get(3), loc3.get(4), loc3.get(5), loc3.get(8), loc3.get(7));
		}
		loc4.put(loc3, loc5);
		return loc5;
	}

	private static void redrawRect(BitmapData param1, Rectangle param2, Point param3, Vector<ImageSet> param4, int param5, int param6, int param7, int param8) {
		BitmapData loc9 = null;
		BitmapData loc10 = null;
		if (param5 == param6 && param5 == param8) {
			loc10 = param4.get(OUTER).random();
			loc9 = GroundLibrary.getBitmapData(param7);
		} else if (param5 != param6 && param5 != param8) {
			if (param6 != param8) {
				param1.copyPixels(GroundLibrary.getBitmapData(param6), param2, param3, param4.get(INNERP1).random(), p0, true);
				param1.copyPixels(GroundLibrary.getBitmapData(param8), param2, param3, param4.get(INNERP2).random(), p0, true);
				return;
			}
			loc10 = param4.get(INNER).random();
			loc9 = GroundLibrary.getBitmapData(param6);
		} else if (param5 != param6) {
			loc10 = param4.get(SIDE0).random();
			loc9 = GroundLibrary.getBitmapData(param6);
		} else {
			loc10 = param4.get(SIDE1).random();
			loc9 = GroundLibrary.getBitmapData(param8);
		}
		param1.copyPixels(loc9, param2, param3, loc10, p0, true);
	}

	private static Vector getSig(Square param1) {
		int loc6 = 0;
		Square loc7 = null;
		Vector loc2 = new Vector();
		Map loc3 = param1.map;
		int loc4 = param1.tileType;
		int loc5 = (int) (param1.y - 1);
		while (loc5 <= param1.y + 1) {
			loc6 = (int) (param1.x - 1);
			while (loc6 <= param1.x + 1) {
				if (loc6 < 0 || loc6 >= loc3.width || loc5 < 0 || loc5 >= loc3.height || loc6 == param1.x && loc5 == param1.y) {
					loc2.add(loc4);
				} else {
					loc7 = loc3.squares.get(loc6 + loc5 * loc3.width);
					if (loc7 == null || loc7.props.blendPriority <= param1.props.blendPriority) {
						loc2.add(loc4);
					} else {
						loc2.add(loc7.tileType);
					}
				}
				loc6++;
			}
			loc5++;
		}
		return loc2;
	}

	private static Vector<Vector<ImageSet>> getMasks() {
		Vector<Vector<ImageSet>> loc1 = new Vector<>();
		addMasks(loc1, AssetLibrary.getImageSet("inner_mask"), AssetLibrary.getImageSet("sides_mask"), AssetLibrary.getImageSet("outer_mask"), AssetLibrary.getImageSet("innerP1_mask"), AssetLibrary.getImageSet("innerP2_mask"));
		return loc1;
	}

	private static void addMasks(Vector<Vector<ImageSet>> param1, ImageSet param2, ImageSet param3, ImageSet param4, ImageSet param5, ImageSet param6) {

		int[] i7 = new int[]{-1, 0, 2, 1};

		for (int loc7 = 0; loc7 < i7.length; loc7++) {
			param1.add(new Vector<>(rotateImageSet(param2, loc7), rotateImageSet(param3, loc7 - 1), rotateImageSet(param3, loc7), rotateImageSet(param4, loc7), rotateImageSet(param5, loc7), rotateImageSet(param6, loc7)));
		}
	}

	private static ImageSet rotateImageSet(ImageSet param1, int param2) {
		ImageSet loc3 = new ImageSet();
		for (BitmapData loc4 : param1.images) {
			loc3.add(BitmapUtil.rotateBitmapData(loc4, param2));
		}
		return loc3;
	}

	private static Vector getCompositeSig(Square param1) {
		Square loc14 = null;
		Square loc15 = null;
		Square loc16 = null;
		Square loc17 = null;
		Vector<Integer> loc2 = new Vector<>();
		loc2.length = 4;
		Map loc3 = param1.map;
		int loc4 = (int) param1.x;
		int loc5 = (int) param1.y;
		Square loc6 = loc3.lookupSquare(loc4, loc5 - 1);
		Square loc7 = loc3.lookupSquare(loc4 - 1, loc5);
		Square loc8 = loc3.lookupSquare(loc4 + 1, loc5);
		Square loc9 = loc3.lookupSquare(loc4, loc5 + 1);
		int loc10 = loc6 != null ? loc6.props.compositePriority : -1;
		int loc11 = loc7 != null ? loc7.props.compositePriority : -1;
		int loc12 = loc8 != null ? loc8.props.compositePriority : -1;
		int loc13 = loc9 != null ? loc9.props.compositePriority : -1;
		if (loc10 < 0 && loc11 < 0) {
			loc14 = loc3.lookupSquare(loc4 - 1, loc5 - 1);
			loc2.put(0, loc14 == null || loc14.props.compositePriority < 0 ? 255 : loc14.tileType);
		} else if (loc10 < loc11) {
			loc2.put(0, loc7.tileType);
		} else {
			loc2.put(0, loc6.tileType);
		}
		if (loc10 < 0 && loc12 < 0) {
			loc15 = loc3.lookupSquare(loc4 + 1, loc5 - 1);
			loc2.put(1, loc15 == null || loc15.props.compositePriority < 0 ? 255 : loc15.tileType);
		} else if (loc10 < loc12) {
			loc2.put(1, loc8.tileType);
		} else {
			loc2.put(1, loc6.tileType);
		}
		if (loc11 < 0 && loc13 < 0) {
			loc16 = loc3.lookupSquare(loc4 - 1, loc5 + 1);
			loc2.put(2, loc16 == null || loc16.props.compositePriority < 0 ? 255 : loc16.tileType);
		} else if (loc11 < loc13) {
			loc2.put(2, loc9.tileType);
		} else {
			loc2.put(2, loc7.tileType);
		}
		if (loc12 < 0 && loc13 < 0) {
			loc17 = loc3.lookupSquare(loc4 + 1, loc5 + 1);
			loc2.put(3, loc17 == null || loc17.props.compositePriority < 0 ? 255 : loc17.tileType);
		} else if (loc12 < loc13) {
			loc2.put(3, loc9.tileType);
		} else {
			loc2.put(3, loc8.tileType);
		}
		return loc2;
	}

	private static BitmapData buildComposite(Vector<Integer> param1) {
		BitmapData loc3 = null;
		BitmapData loc2 = new BitmapDataSpy(8, 8, false, 0);
		if (param1.get(0) != 255) {
			loc3 = GroundLibrary.getBitmapData(param1.get(0));
			loc2.copyPixels(loc3, RECT0, POINT0);
		}
		if (param1.get(1) != 255) {
			loc3 = GroundLibrary.getBitmapData(param1.get(1));
			loc2.copyPixels(loc3, RECT1, POINT1);
		}
		if (param1.get(2) != 255) {
			loc3 = GroundLibrary.getBitmapData(param1.get(2));
			loc2.copyPixels(loc3, RECT2, POINT2);
		}
		if (param1.get(3) != 255) {
			loc3 = GroundLibrary.getBitmapData(param1.get(3));
			loc2.copyPixels(loc3, RECT3, POINT3);
		}
		return loc2;
	}

	private static Vector getEdgeSig(Square param1) {
		int loc7 = 0;
		Square loc8 = null;
		boolean loc9 = false;
		Vector loc2 = new Vector();
		Map loc3 = param1.map;
		boolean loc4 = false;
		boolean loc5 = param1.props.sameTypeEdgeMode;
		int loc6 = (int) (param1.y - 1);
		while (loc6 <= param1.y + 1) {
			loc7 = (int) (param1.x - 1);
			while (loc7 <= param1.x + 1) {
				loc8 = loc3.lookupSquare(loc7, loc6);
				if (loc7 == param1.x && loc6 == param1.y) {
					loc2.add(loc8.tileType);
				} else {
					if (loc5) {
						loc9 = loc8 == null || loc8.tileType == param1.tileType;
					} else {
						loc9 = loc8 == null || loc8.tileType != 255;
					}
					loc2.add(loc9);
					loc4 = loc4 || !loc9;
				}
				loc7++;
			}
			loc6++;
		}
		return loc4 ? loc2 : null;
	}

	// TODO implement this
	private static BitmapData drawEdges(Vector<Integer> param1) {
		BitmapData loc2 = GroundLibrary.getBitmapData(param1.get(4));
		BitmapData loc3 = loc2.clone();
		return loc3;
	}


}
