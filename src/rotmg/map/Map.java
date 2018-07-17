package rotmg.map;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.display.DisplayObject;
import flash.display.IGraphicsData;
import flash.display.Sprite;
import flash.filters.BlurFilter;
import flash.filters.ColorMatrixFilter;
import flash.geom.ColorTransform;
import flash.geom.Point;
import flash.geom.Rectangle;
import flash.library.Array;
import flash.utils.Dictionary;
import mx.filters.BaseFilter;
import rotmg.AGameSprite;
import rotmg.WebMain;
import rotmg.background.Background;
import rotmg.map.mapoverlay.MapOverlay;
import rotmg.map.partyoverlay.PartyOverlay;
import rotmg.model.GameModel;
import rotmg.objects.BasicObject;
import rotmg.objects.GameObject;
import rotmg.objects.Party;
import rotmg.objects.Square;
import rotmg.parameters.Parameters;
import rotmg.particles.ParticleEffect;
import rotmg.stage3D.GraphicsFillExtra;
import rotmg.stage3D.Render3D;
import rotmg.stage3D.Renderer;
import rotmg.stage3D.graphic3D.Object3DStage3D;
import rotmg.stage3D.graphic3D.Program3DFactory;
import rotmg.stage3D.graphic3D.TextureFactory;
import rotmg.util.ConditionEffect;

/**
 * 99% match
 */
public class Map extends AbstractMap {

	public static final String CLOTH_BAZAAR = "Cloth Bazaar";

	public static final String NEXUS = "Nexus";

	public static final String DAILY_QUEST_ROOM = "Daily Quest Room";

	public static final String DAILY_LOGIN_ROOM = "Daily Login Room";

	public static final String PET_YARD_1 = "Pet Yard";

	public static final String PET_YARD_2 = "Pet Yard 2";

	public static final String PET_YARD_3 = "Pet Yard 3";

	public static final String PET_YARD_4 = "Pet Yard 4";

	public static final String PET_YARD_5 = "Pet Yard 5";

	public static final String GUILD_HALL = "Guild Hall";

	public static final String NEXUS_EXPLANATION = "Nexus_Explanation";

	public static final String VAULT = "Vault";
	protected static final ColorMatrixFilter BLIND_FILTER = new ColorMatrixFilter(0.05, 0.05, 0.05, 0, 0, 0.05, 0.05, 0.05, 0, 0, 0.05, 0.05, 0.05, 0, 0, 0.05, 0.05, 0.05, 1, 0);
	private static final Vector<String> VISIBLE_SORT_FIELDS = new Vector<>("sortVal_", "objectId_");

	private static final Vector<Integer> VISIBLE_SORT_PARAMS = new Vector<>(Array.NUMERIC, Array.NUMERIC);
	public static boolean forceSoftwareRender = false;
	public static BitmapData texture;
	protected static ColorTransform BREATH_CT = new ColorTransform(1, 55 / 255, 0 / 255, 0);
	public boolean ifDrawEffectFlag = true;

	//private RollingMeanLoopMonitor loopMonitor;
	public Vector<BasicObject> visible;
	public Vector<BasicObject> visibleUnder;
	public Vector<Square> visibleSquares;
	public Vector<Square> topSquares;
	private boolean inUpdate = false;
	private Vector<BasicObject> objsToAdd;
	private Vector<Integer> idsToRemove;
	private Dictionary<String, Boolean> forceSoftwareMap;
	private boolean lastSoftwareClear = false;
	private DisplayObject darkness;
	private Sprite bgCont;
	private Vector<IGraphicsData> graphicsData;
	private Vector<IGraphicsData> graphicsDataStageSoftware;
	private Vector<Object3DStage3D> graphicsData3d;

	public Map(AGameSprite param1) {
		super();
		this.objsToAdd = new Vector<>();
		this.idsToRemove = new Vector<>();
		this.forceSoftwareMap = new Dictionary<>();
		//this.darkness = new EmbeddedAssets.DarknessBackground();
		this.bgCont = new Sprite();
		this.graphicsData = new Vector<>();
		this.graphicsDataStageSoftware = new Vector<>();
		this.graphicsData3d = new Vector<>();
		this.visible = new Vector<>();
		this.visibleUnder = new Vector<>();
		this.visibleSquares = new Vector<>();
		this.topSquares = new Vector<>();
		gs = param1;
		hurtOverlay = new HurtOverlay();
		gradientOverlay = new GradientOverlay();
		mapOverlay = new MapOverlay();
		partyOverlay = new PartyOverlay(this);
		party = new Party(this);
		quest = new Quest(this);
		//this.loopMonitor = RollingMeanLoopMonitor.getInstance();
		GameModel.getInstance().gameObjects = goDict;
		this.forceSoftwareMap.put(PET_YARD_1, true);
		this.forceSoftwareMap.put(PET_YARD_2, true);
		this.forceSoftwareMap.put(PET_YARD_3, true);
		this.forceSoftwareMap.put(PET_YARD_4, true);
		this.forceSoftwareMap.put(PET_YARD_5, true);
		this.forceSoftwareMap.put("Nexus", true);
		this.forceSoftwareMap.put("Tomb of the Ancients", true);
		this.forceSoftwareMap.put("Tomb of the Ancients (Heroic)", true);
		this.forceSoftwareMap.put("Mad Lab", true);
		this.forceSoftwareMap.put("Guild Hall", true);
		this.forceSoftwareMap.put("Guild Hall 2", true);
		this.forceSoftwareMap.put("Guild Hall 3", true);
		this.forceSoftwareMap.put("Guild Hall 4", true);
		this.forceSoftwareMap.put("Cloth Bazaar", true);
		this.forceSoftwareMap.put("Santa Workshop", true);
		wasLastFrameGpu = Parameters.isGpuRender();
	}

	public void setProps(int param1, int param2, String param3, int param4, boolean param5, boolean param6) {
		width = param1;
		height = param2;
		name = param3;
		back = param4;
		allowPlayerTeleport = param5;
		showDisplays = param6;
		this.forceSoftwareRenderCheck(name);
	}

	private void forceSoftwareRenderCheck(String param1) {
		forceSoftwareRender = this.forceSoftwareMap.get(param1) != null || WebMain.STAGE != null /*&& WebMain.STAGE.stage3Ds[0].context3D == null*/;
	}

	public void initialize() {

		//squares.length = width * height;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				squares.add(new Square(this, x, y));
			}
		}

		addChild(this.bgCont);
		background = Background.getBackground(back);
		if (!Parameters.isGpuRender()) {
			if (background != null) {
				this.bgCont.addChild(background);
			}
		}
		addChild(map);
		addChild(hurtOverlay);
		addChild(gradientOverlay);
		addChild(mapOverlay);
		addChild(partyOverlay);
		isPetYard = name.substring(0, 8).equals("Pet Yard");
	}

	public void dispose() {
		gs = null;
		background = null;
		map = null;
		hurtOverlay = null;
		gradientOverlay = null;
		mapOverlay = null;
		partyOverlay = null;
		for (Square loc1 : squareList) {
			loc1.dispose();
		}
		squareList.length = 0;
		squareList = null;
		squares.length = 0;
		squares.clear();
		for (GameObject loc2 : goDict) {
			loc2.dispose();
		}
		goDict.clear();
		for (BasicObject loc3 : boDict) {
			loc3.dispose();
		}
		boDict.clear();
		merchLookup = null;
		player = null;
		party = null;
		quest = null;
		this.objsToAdd = null;
		this.idsToRemove = null;
		TextureFactory.disposeTextures();
		GraphicsFillExtra.dispose();
		Program3DFactory.getInstance().dispose();
	}

	public void update(int param1, int param2) {
		this.inUpdate = true;
		for (BasicObject loc3 : goDict) {
			if (!loc3.update(param1, param2)) {
				this.idsToRemove.add(loc3.objectId);
			}
		}
		for (BasicObject loc3 : boDict) {
			if (!loc3.update(param1, param2)) {
				this.idsToRemove.add(loc3.objectId);
			}
		}
		this.inUpdate = false;
		for (BasicObject loc3 : this.objsToAdd) {
			this.internalAddObj(loc3);
		}
		this.objsToAdd.length = 0;
		for (int loc4 : this.idsToRemove) {
			this.internalRemoveObj(loc4);
		}
		this.idsToRemove.length = 0;
		party.update(param1, param2);
	}

	public Point pSTopW(double param1, double param2) {
		for (Square loc3 : this.visibleSquares) {
			if (loc3.faces.length != 0 && loc3.faces.get(0).face.contains(param1, param2)) {
				return new Point(loc3.center.x, loc3.center.y);
			}
		}
		return null;
	}

	@Override
	public void setGroundTile(int x, int y, int tileType) {

		//System.out.println("Ground type : " + tileType + " for x : " + x + " y : " + y);

		int yi = 0;
		int ind = 0;
		Square n = null;
		Square square = this.getSquare(x, y);
		square.setTileType(tileType);
		int xend = x < this.width - 1 ? x + 1 : x;
		int yend = y < this.height - 1 ? y + 1 : y;
		for (int xi = x > 0 ? x - 1 : x; xi <= xend; xi++) {
			for (yi = y > 0 ? y - 1 : y; yi <= yend; yi++) {
				ind = xi + yi * this.width;
				n = this.squares.get(ind);
				if (n != null && (n.props.hasEdge || n.tileType != tileType)) {
					n.faces.length = 0;
				}
			}
		}
	}


	public void addObj(BasicObject param1, double param2, double param3) {
		param1.x = param2;
		param1.y = param3;
		if (param1 instanceof ParticleEffect) {
			((ParticleEffect) param1).reducedDrawEnabled = Parameters.data.particleEffect == 0;
		}
		if (this.inUpdate) {
			this.objsToAdd.add(param1);
		} else {
			this.internalAddObj(param1);
		}
	}

	public void internalAddObj(BasicObject param1) {
		if (!param1.addTo(this, param1.x, param1.y)) {
			return;
		}
		Dictionary loc2 = param1 instanceof GameObject ? goDict : boDict;
		if (loc2.get(param1.objectId) != null) {
			if (!isPetYard) {
				return;
			}
		}
		loc2.put(param1.objectId, param1);
	}

	public void removeObj(int param1) {
		if (this.inUpdate) {
			this.idsToRemove.add(param1);
		} else {
			this.internalRemoveObj(param1);
		}
	}

	public void internalRemoveObj(int param1) {
		BasicObject loc3 = goDict.get(param1);
		if (loc3 == null) {
			loc3 = boDict.get(param1);
			if (loc3 == null) {
				return;
			}
		}
		loc3.removeFromMap();
		boDict.remove(param1);
	}

	public Square getSquare(double par1, double par2) {

		int param1 = (int) par1;
		int param2 = (int) par2;

		if (param1 < 0 || param1 >= width || param2 < 0 || param2 >= height) {
			return null;
		}
		int loc3 = (int) (param1 + param2 * width);
		Square loc4 = squares.get(loc3);
		if (loc4 == null) {
			loc4 = new Square(this, param1, param2);
			squares.put(loc3, loc4);
			squareList.add(loc4);
		}
		return loc4;
	}

	public Square lookupSquare(double param1, double param2) {
		if (param1 < 0 || param1 >= width || param2 < 0 || param2 >= height) {
			return null;
		}
		return squares.get((int) (param1 + param2 * width));
	}

	@Override
	public void draw(Camera param1, int param2) {
		Square loc6;
		double loc15;
		double loc16;
		double loc17;
		double loc18;
		double loc19;
		double loc20;
		int loc21 = 0;
		Render3D loc22 = null;
		int loc23 = 0;
		Vector<BaseFilter> loc24 = null;
		double loc25 = 0;
		Rectangle loc3 = param1.clipRect;
		x = -loc3.x;
		y = -loc3.y;
		double loc4 = (-loc3.y - loc3.height / 2) / 50;
		Point loc5 = new Point(param1.x + loc4 * Math.cos(param1.angleRad - Math.PI / 2), param1.y + loc4 * Math.sin(param1.angleRad - Math.PI / 2));
		if (background != null && this.bgCont.contains(background)) {
			background.draw(param1, param2);
		}
		this.visible.clear();
		this.visibleUnder.clear();
		this.visibleSquares.clear();
		this.topSquares.clear();
		double loc7 = param1.maxDist;
		double loc8 = Math.max(0, loc5.x - loc7);
		double loc9 = Math.min(width - 1, loc5.x + loc7);
		double loc10 = Math.max(0, loc5.y - loc7);
		double loc11 = Math.min(height - 1, loc5.y + loc7);
		this.graphicsData.clear();
		this.graphicsDataStageSoftware.clear();
		this.graphicsData3d.clear();
		double loc12 = loc8;
		while (loc12 <= loc9) {
			loc15 = loc10;
			while (loc15 <= loc11) {
				loc6 = squares.get((int) (loc12 + loc15 * width));
				if (loc6 != null) {
					loc16 = loc5.x - loc6.center.x;
					loc17 = loc5.y - loc6.center.y;
					loc18 = loc16 * loc16 + loc17 * loc17;
					if (loc18 <= param1.maxDistSq) {
						loc6.lastVisible = param2;
						loc6.draw(this.graphicsData, param1, param2);
						this.visibleSquares.add(loc6);
						if (loc6.topFace != null) {
							this.topSquares.add(loc6);
						}
					}
				}
				loc15++;
			}
			loc12++;
		}
		for (GameObject loc13 : goDict) {
			loc13.drawn = false;
			if (!loc13.dead) {
				loc6 = loc13.square;
				if (!(loc6 == null || loc6.lastVisible != param2)) {
					loc13.drawn = true;
					loc13.computeSortVal(param1);
					if (loc13.props.drawUnder) {
						if (loc13.props.drawOnGround) {
							loc13.draw(this.graphicsData, param1, param2);
						} else {
							this.visibleUnder.add(loc13);
						}
					} else {
						this.visible.add(loc13);
					}
				}
			}
		}
		for (BasicObject loc14 : boDict) {
			loc14.drawn = false;
			loc6 = loc14.square;
			if (!(loc6 == null || loc6.lastVisible != param2)) {
				loc14.drawn = true;
				loc14.computeSortVal(param1);
				this.visible.add(loc14);
			}
		}
		if (this.visibleUnder.length > 0) {
			this.visibleUnder.sortOn(VISIBLE_SORT_FIELDS, VISIBLE_SORT_PARAMS);
			for (BasicObject c : this.visibleUnder) {
				c.draw(this.graphicsData, param1, param2);
			}
		}
		this.visible.sortOn(VISIBLE_SORT_FIELDS, VISIBLE_SORT_PARAMS);
		if (Parameters.data.drawShadows) {
			for (BasicObject x : this.visible) {
				if (x.hasShadow) {
					x.drawShadow(this.graphicsData, param1, param2);
				}
			}
		}
		for (BasicObject v : this.visible) {
			v.draw(this.graphicsData, param1, param2);
			if (Parameters.isGpuRender()) {
				v.draw3d(this.graphicsData3d);
			}
		}
		if (this.topSquares.length > 0) {
			for (Square z : this.topSquares) {
				z.drawTop(this.graphicsData, param1, param2);
			}
		}
		if (player != null && player.breath >= 0 && player.breath < Parameters.BREATH_THRESH) {
			loc19 = (Parameters.BREATH_THRESH - player.breath) / Parameters.BREATH_THRESH;
			loc20 = Math.abs(Math.sin(param2 / 300)) * 0.75;
			BREATH_CT.alphaMultiplier = (int) (loc19 * loc20);
			hurtOverlay.transform.colorTransform = BREATH_CT;
			hurtOverlay.visible = true;
			hurtOverlay.x = loc3.left;
			hurtOverlay.y = loc3.top;
		} else {
			hurtOverlay.visible = false;
		}
		if (player != null && !Parameters.screenShotMode) {
			gradientOverlay.visible = true;
			gradientOverlay.x = loc3.right - 10;
			gradientOverlay.y = loc3.top;
		} else {
			gradientOverlay.visible = false;
		}
		/*if (Parameters.isGpuRender() && Renderer.inGame) {
			loc21 = this.getFilterIndex();
			loc22 = Render3D.getInstance();
			loc22.dispatch(this.graphicsData, this.graphicsData3d, width, height, param1, loc21);
			loc23 = 0;
			while (loc23 < this.graphicsData.length) {
				if (this.graphicsData[loc23] instanceof GraphicsBitmapFill && GraphicsFillExtra.isSoftwareDraw(GraphicsBitmapFill(this.graphicsData[loc23]))) {
					this.graphicsDataStageSoftware.add(this.graphicsData[loc23]);
					this.graphicsDataStageSoftware.add(this.graphicsData[loc23 + 1]);
					this.graphicsDataStageSoftware.add(this.graphicsData[loc23 + 2]);
				} else if (this.graphicsData[loc23] instanceof GraphicsSolidFill && GraphicsFillExtra.isSoftwareDrawSolid(GraphicsSolidFill(this.graphicsData[loc23]))) {
					this.graphicsDataStageSoftware.add(this.graphicsData[loc23]);
					this.graphicsDataStageSoftware.add(this.graphicsData[loc23 + 1]);
					this.graphicsDataStageSoftware.add(this.graphicsData[loc23 + 2]);
				}
				loc23++;
			}
			if (this.graphicsDataStageSoftware.length > 0) {
				map.graphics.clear();
				map.graphics.drawGraphicsData(this.graphicsDataStageSoftware);
				if (this.lastSoftwareClear) {
					this.lastSoftwareClear = false;
				}
			} else if (!this.lastSoftwareClear) {
				map.graphics.clear();
				this.lastSoftwareClear = true;
			}
			if (param2 % 149 == 0) {
				GraphicsFillExtra.manageSize();
			}
		} else {*/
		map.graphics.clear();
		map.graphics.drawGraphicsData(this.graphicsData);
		/*}*/

		System.out.println("Drew");

		map.filters.clear();
		if (player != null && (player.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.MAP_FILTER_BITMASK) != 0) {
			loc24 = new Vector<>();
			if (player.isDrunk()) {
				loc25 = 20 + 10 * Math.sin(param2 / 1000);
				loc24.add(new BlurFilter(loc25, loc25));
			}
			if (player.isBlind()) {
				loc24.add(BLIND_FILTER);
			}
			map.filters = loc24;
		} else if (map.filters.length > 0) {
			map.filters = new Vector<>();
		}
		mapOverlay.draw(param1, param2);
		partyOverlay.draw(param1, param2);
		if (player != null && player.isDarkness()) {
			this.darkness.x = -300;
			this.darkness.y = !!Parameters.data.centerOnPlayer ? -525 : -515;
			this.darkness.alpha = 0.95;
			addChild(this.darkness);
		} else if (contains(this.darkness)) {
			removeChild(this.darkness);
		}
	}

	private int getFilterIndex() {
		int loc1 = 0;
		if (player != null && (player.condition.get(ConditionEffect.CE_FIRST_BATCH) & ConditionEffect.MAP_FILTER_BITMASK) != 0) {
			if (player.isPaused()) {
				loc1 = Renderer.STAGE3D_FILTER_PAUSE;
			} else if (player.isBlind()) {
				loc1 = Renderer.STAGE3D_FILTER_BLIND;
			} else if (player.isDrunk()) {
				loc1 = Renderer.STAGE3D_FILTER_DRUNK;
			}
		}
		return loc1;
	}
}