package rotmg.ui.menu;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Vector;
import flash.display.*;
import flash.events.Event;
import flash.events.MouseEvent;
import flash.geom.Rectangle;
import rotmg.ui.view.UnFocusAble;
import rotmg.util.GraphicsUtil;
import rotmg.util.RectangleUtil;
import spark.filters.DropShadowFilter;

public class Menu extends Sprite implements UnFocusAble {

	protected int yOffset;
	private GraphicsSolidFill backgroundFill;
	private GraphicsSolidFill outlineFill;
	private GraphicsStroke lineStyle;
	private GraphicsPath path;
	private Vector<IGraphicsData> graphicsData;
	private int background;
	private int outline;

	public Menu(int param1, int param2) {
		super();
		this.backgroundFill = new GraphicsSolidFill(0, 1);
		this.outlineFill = new GraphicsSolidFill(0, 1);
		this.lineStyle = new GraphicsStroke(1, false, LineScaleMode.NORMAL, CapsStyle.NONE, JointStyle.ROUND, 3, this.outlineFill);
		this.path = new GraphicsPath(new Vector<Integer>(), new Vector<Double>());
		this.graphicsData = new Vector<IGraphicsData>(this.lineStyle, this.backgroundFill, this.path, GraphicsUtil.END_FILL, GraphicsUtil.END_STROKE);
		this.background = param1;
		this.outline = param2;
		this.yOffset = 40;
		filters = new Vector<>(new DropShadowFilter(0, 0, 0, 1, 16, 16));
		addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
		addEventListener(Event.REMOVED_FROM_STAGE, new EventConsumer<>(this::onRemovedFromStage));
	}

	protected void addOption(MenuOption param1) {
		param1.x = 8;
		param1.y = this.yOffset;
		addChild(param1);
		this.yOffset = this.yOffset + 28;
	}

	protected void onAddedToStage(Event param1) {
		this.draw();
		this.position();
		addEventListener(Event.ENTER_FRAME, new EventConsumer<>(this::onEnterFrame));
		addEventListener(MouseEvent.ROLL_OUT, new EventConsumer<>(this::onRollOut));
	}

	protected void onRemovedFromStage(Event param1) {
		removeEventListener(Event.ENTER_FRAME, new EventConsumer<>(this::onEnterFrame));
		removeEventListener(MouseEvent.ROLL_OUT, new EventConsumer<>(this::onRollOut));
	}

	protected void onEnterFrame(Event param1) {
		if (stage == null) {
			return;
		}
		Rectangle loc2 = getRect(stage);
		double loc3 = RectangleUtil.pointDist(loc2, stage.mouseX, stage.mouseY);
		if (loc3 > 40) {
			this.remove();
		}
	}


	private void position() {
		if (stage == null) {
			return;
		}
		if (stage.mouseX < stage.stageWidth / 2) {
			x = stage.mouseX + 12;
		} else {
			x = stage.mouseX - width - 1;
		}
		if (x < 12) {
			x = 12;
		}
		if (stage.mouseY < stage.stageHeight / 3) {
			y = stage.mouseY + 12;
		} else {
			y = stage.mouseY - height - 1;
		}
		if (y < 12) {
			y = 12;
		}
	}

	protected void onRollOut(Event param1) {
		this.remove();
	}

	public void remove() {
		if (parent != null) {
			parent.removeChild(this);
		}
	}

	protected void draw() {
		this.backgroundFill.color = this.background;
		this.outlineFill.color = this.outline;
		graphics.clear();
		GraphicsUtil.clearPath(this.path);
		GraphicsUtil.drawCutEdgeRect(-6, -6, (int) Math.max(154, width + 12), (int) height + 12, 4, new Vector<>(1, 1, 1, 1), this.path);
		graphics.drawGraphicsData(this.graphicsData);
	}
}
