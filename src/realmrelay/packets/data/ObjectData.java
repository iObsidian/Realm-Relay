package realmrelay.packets.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.data.unused.IData;

public class ObjectData implements IData {

	public String file;
	public int index;

	private boolean isShocked;
	private boolean isShockedTransformSet = false;
	private boolean isCharging;
	private boolean isChargingTransformSet = false;
	public String name_;
	public double radius =  0.5;
	public double facing =  0;
	public boolean flying =  false;
	public double attackAngle =  0;
	public int attackStart =  0;
	public boolean dead =  false;
	public int deadCounter =  0;
	public int maxHP =  200;
	public int hp =  200;
	public int level =  -1;
	public int defense =  0;
	protected int tex1Id =  0;
	protected int tex2Id =  0;
	public boolean isInteractive =  false;
	public int objectType_;
	private int nextBulletId =  1;
	private double sizeMult =  1;
	public int sinkLevel =  0;
	public int connectType =  -1;
	private boolean isStunImmune =  false;
	private boolean isParalyzeImmune =  false;
	private boolean isDazedImmune =  false;
	private boolean isStasisImmune =  false;
	private boolean ishpScaleSet = false;
	protected int lastTickUpdateTime =  0;
	protected int myLastTickId =  -1;

	public String id = ""; // attribute id
	public int type = -1; // attribute type

	public String dungeonName = "";

	public int maxHitPoints = -1; // element MaxHitPoints
	public int maxSize = -1; // element MaxSize
	public int minSize = -1; // element MinSize
	public int size = -1; // element Size
	public int sizeStep = -1; // element SizeStep
	public int shadowSize = -1; // element ShadowSize
	public int color = -1; // element Color
	public float xpMult = 0; // element XpMult
	public float rotation = 0; // element Rotation
	public boolean drawOnGround = false; // element DrawOnGround
	public boolean enemy = false; // element Enemy
	public boolean fullOccupy = false; // element FullOccupy
	public boolean occupySquare = false; // element OccupySquare
	public boolean enemyOccupySquare = false; // element EnemyOccupySquare
	public boolean blocksSight = false; // element BlocksSight
	public boolean noMiniMap = false; // element NoMiniMap
	public boolean stasisImmune = false; // element StasisImmune
	public boolean protectFromGroundDamage = false; // element ProtectFromGroundDamage
	public boolean protectFromSink = false; // element ProtectFromSink
	public boolean connects = false; // element Connects
	public float z = 0; // element Z

	public boolean hasAnimatedTexture;

	public int objectType;

	public ObjectStatusData status;

	public ObjectData() {
		this.status = new ObjectStatusData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		objectType = in.readUnsignedShort();
		status.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(objectType);
		status.writeToOutput(out);
	}

}
