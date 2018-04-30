package realmrelay.data;

import java.util.ArrayList;

public class EnemyData {

	public String file;
	public int index;

	public boolean enemy = false;
	public boolean quest = false;
	public boolean god = false;
	public boolean oryx = false;
	public boolean stasisImmune = false;
	public boolean noAtricle = false;
	public boolean flying;
	public boolean occupySquare;

	//public String id = "";
	public String displayId = "";
	public String group = "";
	public String enemyClass = ""; //Restricted variable name in replacement of "class".
	public String hitSound;
	public String deathSound;

	public String id = "";
	public int size = 0;
	public int type = -1;
	public int maxHitPoints = 0;
	public int defense = 0;

	public int shadowSize = 0;
	public int level = 0;

	public float z;
	public float xpMult = 0;

	public ArrayList<EnemyProjectileData> projectiles = new ArrayList<EnemyProjectileData>();

}
