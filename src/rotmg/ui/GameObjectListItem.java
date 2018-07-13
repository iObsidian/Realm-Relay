package rotmg.ui;

import alde.flash.utils.Vector;
import flash.display.Bitmap;
import flash.display.Sprite;
import flash.geom.ColorTransform;
import org.osflash.signals.Signal;
import rotmg.objects.GameObject;
import rotmg.objects.ObjectLibrary;
import rotmg.objects.Player;
import rotmg.parameters.Parameters;
import rotmg.text.view.stringBuilder.TemplateBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.util.MoreColorUtil;
import spark.filters.DropShadowFilter;

public class GameObjectListItem extends Sprite {

	public Bitmap portrait;
	public boolean isLongVersion;
	public GameObject go;
	public Signal textReady;
	private TextFieldDisplayConcrete text;
	private TemplateBuilder builder;
	private int color;
	private String objname;

	private int type;

	private int level;

	private boolean positionClassBelow;


	public GameObjectListItem(int param1, boolean param2, GameObject param3) {
		this(param1, param2, param3, false);
	}

	public GameObjectListItem(int param1, boolean param2, GameObject param3, boolean param4) {
		super();
		this.positionClassBelow = param4;
		this.isLongVersion = param2;
		this.color = param1;
		this.portrait = new Bitmap();
		this.portrait.x = -4;
		this.portrait.y = !!param4 ? -1 : -4;
		addChild(this.portrait);
		this.text = new TextFieldDisplayConcrete().setSize(13).setColor(param1).setHTML(param2);
		if (!param2) {
			this.text.setTextWidth(66).setTextHeight(20).setBold(true);
		}
		this.text.x = 32;
		this.text.y = 6;
		this.text.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		addChild(this.text);
		this.textReady = this.text.textChanged;
		this.draw(param3);
	}

	public void draw(GameObject param1) {
		draw(param1, null);
	}

	public void draw(GameObject param1, ColorTransform param2) {
		boolean loc3 = false;
		loc3 = this.isClear();
		this.go = param1;
		visible = param1 != null;
		if (visible && (this.hasChanged() || loc3)) {
			this.redraw();

			if (param2 != null) {
				transform.colorTransform = param2;
			} else {
				transform.colorTransform = MoreColorUtil.identity;
			}
		}
	}

	public void clear() {
		this.go = null;
		visible = false;
	}

	public boolean isClear() {
		return this.go == null && visible == false;
	}

	private boolean hasChanged() {
		boolean loc1 = this.go.name != this.objname || this.go.level != this.level || this.go.objectType != this.type;
		if (loc1) {
			this.updateData();
		}
		return loc1;
	}

	private void updateData() {
		this.objname = this.go.name;
		this.level = this.go.level;
		this.type = this.go.objectType;
	}

	private void redraw() {
		this.portrait.bitmapData = this.go.getPortrait();
		this.text.setStringBuilder(this.prepareText());
		this.text.setColor(this.getDrawColor());
		this.text.update();
	}

	private TemplateBuilder prepareText() {

		if (this.builder == null) {
			this.builder = new TemplateBuilder();
		}
		if (this.isLongVersion) {
			this.applyLongTextToBuilder();
		} else if (this.isNameDefined()) {
			this.builder.setTemplate(this.objname);
		} else {
			this.builder.setTemplate(ObjectLibrary.typeToDisplayId.get(this.type));
		}
		return this.builder;
	}

	private void applyLongTextToBuilder() {
		/*String loc1 = null;
		GameObject loc2 = null;
		if (this.isNameDefined()) {
			if (this.positionClassBelow) {
				loc1 = "<b>{name}</b>\n({type}{level})";
			} else {
				loc1 = "<b>{name}</b> ({type}{level})";
			}
			loc2.name = this.go.name;
			loc2.type = ObjectLibrary.typeToDisplayId.get(this.type);
			loc2.level = this.level < 1 ? "" : " " + this.level;
		} else {
			loc1 = "<b>{name}</b>";
			loc2.name = ObjectLibrary.typeToDisplayId.get(this.type);
		}
		this.builder.setTemplate(loc1, loc2);*/
	}

	private boolean isNameDefined() {
		return this.go.name != null && this.go.name != "";
	}

	private int getDrawColor() {
		Player loc1 = (Player) this.go;
		if (loc1 == null) {
			return this.color;
		}
		if (loc1.isFellowGuild) {
			return Parameters.FELLOW_GUILD_COLOR;
		}
		if (loc1.nameChosen) {
			return Parameters.NAME_CHOSEN_COLOR;
		}
		return this.color;
	}
}

