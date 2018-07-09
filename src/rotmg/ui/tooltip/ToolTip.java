package rotmg.ui.tooltip;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import flash.display.*;
import flash.events.Event;
import flash.events.MouseEvent;
import rotmg.ui.view.SignalWaiter;
import rotmg.util.GraphicsUtil;
import spark.filters.DropShadowFilter;

public class ToolTip extends Sprite {

	protected final SignalWaiter waiter = new SignalWaiter();
	public int contentWidth;
	public int contentHeight;
	private int background;
	private double backgroundAlpha;
	private int outline;
	private double outlineAlpha;
	private boolean _followMouse;
	private boolean forcePositionLeft = false;
	private boolean forcePositionRight = false;
	private DisplayObject targetObj;
	private GraphicsSolidFill backgroundFill;
	private GraphicsSolidFill outlineFill;
	private GraphicsStroke lineStyle;
	private GraphicsPath path;
	private Vector<IGraphicsData> graphicsData;

	public ToolTip(int param1, double param2, int param3, double param4, boolean param5) {
		super();
		this.backgroundFill = new GraphicsSolidFill(0, 1);
		this.outlineFill = new GraphicsSolidFill(16777215, 1);
		this.lineStyle = new GraphicsStroke(1, false, LineScaleMode.NORMAL, CapsStyle.NONE, JointStyle.ROUND, 3, this.outlineFill);
		this.path = new GraphicsPath(new Vector<Integer>(), new Vector<Double>());

		graphicsData = new Vector<IGraphicsData>(this.lineStyle, this.backgroundFill, this.path, GraphicsUtil.END_FILL, GraphicsUtil.END_STROKE);
		this.background = param1;
		this.backgroundAlpha = param2;
		this.outline = param3;
		this.outlineAlpha = param4;
		this._followMouse = param5;
		mouseEnabled = false;
		mouseChildren = false;
		filters = new Vector<>(new DropShadowFilter(0, 0, 0, 1, 16, 16));
		addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
		addEventListener(Event.REMOVED_FROM_STAGE, new EventConsumer<>(this::onRemovedFromStage));
		this.waiter.complete.add(new SignalConsumer<>(this::alignUIAndDraw));
	}

	public void alignUIAndDraw() {
		this.alignUI();
		this.draw();
		this.position();
	}

	protected void alignUI() {
	}

	public void attachToTarget(DisplayObject param1) {
		if (param1 != null) {
			this.targetObj = param1;
			this.targetObj.addEventListener(MouseEvent.ROLL_OUT, new EventConsumer<>(this::onLeaveTarget));
		}
	}

	public void detachFromTarget() {
		if (this.targetObj != null) {
			this.targetObj.removeEventListener(MouseEvent.ROLL_OUT, new EventConsumer<>(this::onLeaveTarget));
			if (parent != null) {
				parent.removeChild(this);
			}
			this.targetObj = null;
		}
	}

	public void forcePostionLeft() {
		this.forcePositionLeft = true;
		this.forcePositionRight = false;
	}

	public void forcePostionRight() {
		this.forcePositionRight = true;
		this.forcePositionLeft = false;
	}

	private void onLeaveTarget(MouseEvent param1) {
		this.detachFromTarget();
	}

	private void onAddedToStage(Event param1) {
		if (this.waiter.isEmpty()) {
			this.draw();
		}
		if (this._followMouse) {
			this.position();
			addEventListener(Event.ENTER_FRAME, new EventConsumer<>(this::onEnterFrame));
		}
	}

	private void onRemovedFromStage(Event param1) {
		if (this._followMouse) {
			removeEventListener(Event.ENTER_FRAME, new EventConsumer<>(this::onEnterFrame));
		}
	}

	private void onEnterFrame(Event param1) {
		this.position();
	}

	protected void position() {
		if (stage == null) {
			return;
		}
		if (!this.forcePositionLeft && stage.mouseX < stage.stageWidth / 2 || this.forcePositionRight) {
			x = stage.mouseX + 12;
		} else {
			x = stage.mouseX - width - 1;
		}
		if (x < 12) {
			x = 12;
		}
		if (!this.forcePositionLeft && stage.mouseY < stage.stageHeight / 3 || this.forcePositionRight) {
			y = stage.mouseY + 12;
		} else {
			y = stage.mouseY - height - 1;
		}
		if (y < 12) {
			y = 12;
		}
	}

	public void draw() {
		this.backgroundFill.color = this.background;
		this.backgroundFill.alpha = this.backgroundAlpha;
		this.outlineFill.color = this.outline;
		this.outlineFill.alpha = this.outlineAlpha;
		graphics.clear();
		this.contentWidth = (int) width;
		this.contentHeight = (int) height;
		GraphicsUtil.clearPath(this.path);
		GraphicsUtil.drawCutEdgeRect(-6, -6, this.contentWidth + 12, this.contentHeight + 12, 4, new Vector<Integer>(1, 1, 1, 1), this.path);
		graphics.drawGraphicsData(this.graphicsData);
	}


}
