package rotmg.map.mapoverlay;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Vector;
import flash.display.*;
import flash.events.MouseEvent;
import flash.geom.Point;
import flash.text.TextField;
import flash.text.TextFieldAutoSize;
import flash.text.TextFormat;
import rotmg.map.Camera;
import rotmg.objects.GameObject;
import rotmg.parameters.Parameters;
import rotmg.util.GraphicsUtil;
import spark.filters.DropShadowFilter;

public class SpeechBalloon extends Sprite implements IMapOverlayElement {

	public GameObject go;

	public int lifetime;

	public boolean hideable;

	public Point offset;

	public TextField text;

	private GraphicsSolidFill backgroundFill;

	private GraphicsSolidFill outlineFill;

	private GraphicsStroke lineStyle;

	private GraphicsPath path;

	private Vector<IGraphicsData> graphicsData;
	private String senderName;

	private boolean isTrade;

	private boolean isGuild;

	private int startTime = 0;

	public SpeechBalloon(GameObject param1, String param2, String param3, boolean param4, boolean param5, int param6, double param7, int param8, double param9, int param10, int param11, boolean param12, boolean param13) {
		super();

		this.offset = new Point();
		this.backgroundFill = new GraphicsSolidFill(0, 1);
		this.outlineFill = new GraphicsSolidFill(16777215, 1);
		this.lineStyle = new GraphicsStroke(2, false, LineScaleMode.NORMAL, CapsStyle.NONE, JointStyle.ROUND, 3, this.outlineFill);
		this.path = new GraphicsPath(new Vector<Integer>(), new Vector<Double>());

		graphicsData = new Vector<IGraphicsData>(this.lineStyle, this.backgroundFill, this.path, GraphicsUtil.END_FILL, GraphicsUtil.END_STROKE);

		this.go = param1;
		this.senderName = param3;
		this.isTrade = param4;
		this.isGuild = param5;
		this.lifetime = param11 * 1000;
		this.hideable = param13;
		this.text = new TextField();
		this.text.autoSize = TextFieldAutoSize.LEFT;
		this.text.embedFonts = true;
		this.text.width = 150;
		TextFormat loc14 = new TextFormat();
		loc14.font = "Myriad Pro";
		loc14.size = 14;
		loc14.bold = param12;
		loc14.color = param10;
		this.text.defaultTextFormat = loc14;
		this.text.selectable = false;
		this.text.mouseEnabled = false;
		this.text.multiline = true;
		this.text.wordWrap = true;
		this.text.text = param2;
		addChild(this.text);
		int loc15 = this.text.textWidth + 4;
		this.offset.x = -loc15 / 2;
		this.backgroundFill.color = param6;
		this.backgroundFill.alpha = param7;
		this.outlineFill.color = param8;
		this.outlineFill.alpha = param9;
		graphics.clear();
		GraphicsUtil.clearPath(this.path);
		GraphicsUtil.drawCutEdgeRect(-6, -6, loc15 + 12, (int) (height + 12), 4, new Vector<Integer>(1, 1, 1, 1), this.path);
		this.path.commands.splice(6, 0, GraphicsPathCommand.LINE_TO, GraphicsPathCommand.LINE_TO, GraphicsPathCommand.LINE_TO);
		int loc16 = (int) height;
		this.path.data.splice(12, 0, loc15 / 2 + 8, loc16 + 6, loc15 / 2, loc16 + 18, loc15 / 2 - 8, loc16 + 6);
		graphics.drawGraphicsData(this.graphicsData);
		filters = new Vector<>(new DropShadowFilter(0, 0, 0, 1, 16, 16));
		this.offset.y = -height - this.go.texture.height * (param1.size / 100) * 5 - 2;
		visible = false;
		addEventListener(MouseEvent.RIGHT_CLICK, new EventConsumer<>(this::onSpeechBalloonRightClicked));
	}

	private void onSpeechBalloonRightClicked(MouseEvent param1) {
		/*HUDModel hmod = null;
		Player aPlayer = null;
		MouseEvent e = param1;
		int playerObjectId = this.go.objectId;
		try {
			hmod = HUDModel.getInstance();
			if (hmod.gameSprite.map.goDict[playerObjectId] != null && hmod.gameSprite.map.goDict[playerObjectId] instanceof Player && hmod.gameSprite.map.player.objectId != playerObjectId) {
				aPlayer = (Player)hmod.gameSprite.map.goDict.get(playerObjectId) ;
				hmod.gameSprite.addChatPlayerMenu(aPlayer, e.stageX, e.stageY);
			} else if (!this.isTrade && this.senderName != null && this.senderName != "" && hmod.gameSprite.map.player.name != this.senderName) {
				hmod.gameSprite.addChatPlayerMenu(null, e.stageX, e.stageY, this.senderName, this.isGuild);
			} else if (this.isTrade && this.senderName != null && this.senderName != "" && hmod.gameSprite.map.player.name != this.senderName) {
				hmod.gameSprite.addChatPlayerMenu(null, e.stageX, e.stageY, this.senderName, false, true);
			}
			return;
		} catch (Error error){
			return;
		}**/
	}

	public boolean draw(Camera param1, int param2) {
		if (this.startTime == 0) {
			this.startTime = param2;
		}
		int loc3 = param2 - this.startTime;
		if (loc3 > this.lifetime || this.go != null && this.go.map == null) {
			return false;
		}
		if (this.go == null || !this.go.drawn) {
			visible = false;
			return true;
		}
		if (this.hideable && !Parameters.data.textBubbles) {
			visible = false;
			return true;
		}
		visible = true;
		x = this.go.posS.get(0) + this.offset.x;
		y = this.go.posS.get(1) + this.offset.y;
		return true;
	}

	public GameObject getGameObject() {
		return this.go;
	}

	public void dispose() {
		parent.removeChild(this);
	}


}
