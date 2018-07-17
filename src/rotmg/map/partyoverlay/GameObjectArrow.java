package rotmg.map.partyoverlay;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Vector;
import flash.airglobal.Graphics;
import flash.airglobal.Shape;
import flash.display.DisplayObjectContainer;
import flash.display.Sprite;
import flash.events.MouseEvent;
import flash.geom.Point;
import flash.geom.Rectangle;
import rotmg.map.Camera;
import rotmg.objects.GameObject;
import rotmg.ui.menu.Menu;
import rotmg.ui.tooltip.ToolTip;
import rotmg.util.RectangleUtil;
import rotmg.util.Trig;
import spark.filters.DropShadowFilter;

public class GameObjectArrow extends Sprite {

	public static final int SMALL_SIZE = 8;

	public static final int BIG_SIZE = 11;

	public static final int DIST = 3;

	private static Menu menu = null;

	public DisplayObjectContainer menuLayer;

	public int lineColor;

	public int fillColor;

	public GameObject go = null;

	public Vector<GameObject> extraGOs;

	public boolean mouseOver = false;
	protected ToolTip tooltip = null;
	private boolean big;
	private Shape arrow;
	private Point tempPoint;

	public GameObjectArrow(int param1, int param2, boolean param3) {
		super();
		this.extraGOs = new Vector<GameObject>();
		this.arrow = new Shape();
		this.tempPoint = new Point();
		this.lineColor = param1;
		this.fillColor = param2;
		this.big = param3;
		addChild(this.arrow);
		this.drawArrow();
		addEventListener(MouseEvent.MOUSE_OVER, new EventConsumer<>(this::onMouseOver));
		addEventListener(MouseEvent.MOUSE_OUT, new EventConsumer<>(this::onMouseOut));
		addEventListener(MouseEvent.MOUSE_DOWN, new EventConsumer<>(this::onMouseDown));
		filters = new Vector<>(new DropShadowFilter(0, 0, 0, 1, 8, 8));
		visible = false;
	}

	public static void removeMenu() {
		if (menu != null) {
			if (menu.parent != null) {
				menu.parent.removeChild(menu);
			}
			menu = null;
		}
	}

	protected void onMouseOver(MouseEvent param1) {
		this.mouseOver = true;
		this.drawArrow();
	}

	protected void onMouseOut(MouseEvent param1) {
		this.mouseOver = false;
		this.drawArrow();
	}

	protected void onMouseDown(MouseEvent param1) {
		param1.stopImmediatePropagation();
	}

	protected void setToolTip(ToolTip param1) {
		this.removeTooltip();
		this.tooltip = param1;
		if (this.tooltip != null) {
			addChild(this.tooltip);
			this.positionTooltip(this.tooltip);
		}
	}

	protected void removeTooltip() {
		if (this.tooltip != null) {
			if (this.tooltip.parent != null) {
				this.tooltip.parent.removeChild(this.tooltip);
			}
			this.tooltip = null;
		}
	}

	protected void setMenu(Menu param1) {
		this.removeTooltip();
		menu = param1;
		this.menuLayer.addChild(menu);
	}

	public void setGameObject(GameObject param1) {
		if (this.go != param1) {
			this.go = param1;
		}
		this.extraGOs.length = 0;
		if (this.go == null) {
			visible = false;
		}
	}

	public void addGameObject(GameObject param1) {
		this.extraGOs.add(param1);
	}

	public void draw(int param1, Camera param2) {
		Rectangle loc3 = null;
		double loc4 = 0;
		double loc5 = 0;
		if (this.go == null) {
			visible = false;
			return;
		}
		this.go.computeSortVal(param2);
		loc3 = param2.clipRect;
		loc4 = this.go.posS.get(0);
		loc5 = this.go.posS.get(1);
		if (!RectangleUtil.lineSegmentIntersectXY(param2.clipRect, 0, 0, loc4, loc5, this.tempPoint)) {
			this.go = null;
			visible = false;
			return;
		}
		x = this.tempPoint.x;
		y = this.tempPoint.y;
		double loc6 = Trig.boundTo180(270 - Trig.toDegrees * Math.atan2(loc4, loc5));
		if (this.tempPoint.x < loc3.left + 5) {
			if (loc6 > 45) {
				loc6 = 45;
			}
			if (loc6 < -45) {
				loc6 = -45;
			}
		} else if (this.tempPoint.x > loc3.right - 5) {
			if (loc6 > 0) {
				if (loc6 < 135) {
					loc6 = 135;
				}
			} else if (loc6 > -135) {
				loc6 = -135;
			}
		}
		if (this.tempPoint.y < loc3.top + 5) {
			if (loc6 < 45) {
				loc6 = 45;
			}
			if (loc6 > 135) {
				loc6 = 135;
			}
		} else if (this.tempPoint.y > loc3.bottom - 5) {
			if (loc6 > -45) {
				loc6 = -45;
			}
			if (loc6 < -135) {
				loc6 = -135;
			}
		}
		this.arrow.rotation = loc6;
		if (this.tooltip != null) {
			this.positionTooltip(this.tooltip);
		}
		visible = true;
	}

	private void positionTooltip(ToolTip param1) {
		double loc5 = 0;
		double loc8 = 0;
		double loc9 = 0;
		double loc2 = this.arrow.rotation;
		int loc3 = DIST + BIG_SIZE + 12;
		double loc4 = loc3 * Math.cos(loc2 * Trig.toRadians);
		loc5 = loc3 * Math.sin(loc2 * Trig.toRadians);
		double loc6 = param1.contentWidth;
		double loc7 = param1.contentHeight;
		if (loc2 >= 45 && loc2 <= 135) {
			loc8 = loc4 + loc6 / Math.tan(loc2 * Trig.toRadians);
			param1.x = (loc4 + loc8) / 2 - loc6 / 2;
			param1.y = loc5;
		} else if (loc2 <= -45 && loc2 >= -135) {
			loc8 = loc4 - loc6 / Math.tan(loc2 * Trig.toRadians);
			param1.x = (loc4 + loc8) / 2 - loc6 / 2;
			param1.y = loc5 - loc7;
		} else if (loc2 < 45 && loc2 > -45) {
			param1.x = loc4;
			loc9 = loc5 + loc7 * Math.tan(loc2 * Trig.toRadians);
			param1.y = (loc5 + loc9) / 2 - loc7 / 2;
		} else {
			param1.x = loc4 - loc6;
			loc9 = loc5 - loc7 * Math.tan(loc2 * Trig.toRadians);
			param1.y = (loc5 + loc9) / 2 - loc7 / 2;
		}
	}

	private void drawArrow() {
		Graphics loc1 = this.arrow.graphics;
		loc1.clear();
		int loc2 = this.big || this.mouseOver ? BIG_SIZE : SMALL_SIZE;
		loc1.lineStyle(1, this.lineColor);
		loc1.beginFill(this.fillColor);
		loc1.moveTo(DIST, 0);
		loc1.lineTo(loc2 + DIST, loc2);
		loc1.lineTo(loc2 + DIST, -loc2);
		loc1.lineTo(DIST, 0);
		loc1.endFill();
		loc1.lineStyle();
	}
}

