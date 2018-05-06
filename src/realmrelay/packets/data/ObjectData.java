package realmrelay.packets.data;

public class ObjectData {

	public String file;
	public int index;

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

}
