package rotmg.objects;

import alde.flash.utils.Vector;
import flash.display.*;
import flash.geom.Matrix;
import flash.geom.Point;
import flash.geom.Vector3D;
import flash.utils.Dictionary;
import rotmg.engine3d.Point3D;
import rotmg.map.Camera;
import rotmg.map.Map;
import rotmg.objects.particles.HitEffect;
import rotmg.objects.particles.SparkParticle;
import rotmg.parameters.Parameters;
import rotmg.tutorial.Tutorial;
import rotmg.util.*;

import static rotmg.tutorial.doneAction.doneAction;

/**
 * This class is about 10% done. It requires a lot of graphics stuff.
 */
public class Projectile extends BasicObject {

	private static Dictionary<Integer, Integer> objBullIdToObjId = new Dictionary();

	public ObjectProperties props;

	public ObjectProperties containerProps;

	public ProjectileProperties projProps;

	public BitmapData texture;

	public int bulletId;

	public int ownerId;

	public int containerType;

	public int bulletType;

	public boolean damagesEnemies;

	public boolean damagesPlayers;

	public int damage;

	public String sound;

	public double startX;

	public double startY;

	public int startTime;

	public double angle = 0;

	public Dictionary multiHitDict;

	public Point3D p;
	protected GraphicsGradientFill shadowGradientFill;
	protected GraphicsPath shadowPath;
	private Point staticPoint;
	private Vector3D staticVector3D;

	public Projectile() {
		super();
		this.p = new Point3D(100);
		this.staticPoint = new Point();
		this.staticVector3D = new Vector3D();
		this.shadowGradientFill = new GraphicsGradientFill(GradientType.RADIAL, new Vector<Integer>(0, 0), new Vector<Double>(0.5, 0.0), null, new Matrix());
		this.shadowPath = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, new Vector<Double>());
	}

	public static int findObjId(int param1, int param2) {
		return objBullIdToObjId.get(param2 << 24 | param1);
	}

	public static int getNewObjId(int param1, int param2) {
		int loc3 = getNextFakeObjectId();
		objBullIdToObjId.put(param2 << 24 | param1, loc3);
		return loc3;
	}

	public static void removeObjId(int param1, int param2) {
		objBullIdToObjId.remove(param2 << 24 | param1);
	}

	@Override
	public void dispose() {
		objBullIdToObjId = new Dictionary<>();
	}


	public void reset(int param1, int param2, int param3, int param4, double param5, int param6) {
		this.reset(param1, param2, param3, param4, param5, param6, "", "");
	}

	public void reset(int param1, int param2, int param3, int param4, double param5, int param6, String param7, String param8) {
		double loc11 = 0;
		clear();
		this.containerType = param1;
		this.bulletType = param2;
		this.ownerId = param3;
		this.bulletId = param4;
		this.angle = Trig.boundToPI(param5);
		this.startTime = param6;
		objectId = getNewObjId(this.ownerId, this.bulletId);
		z = 0.5;
		this.containerProps = ObjectLibrary.propsLibrary.get(this.containerType);

		if (this.containerProps == null) { //This is debug TODO
			System.err.println("Error, could not find projectile " + this.containerType + " ObjectLibrary.propsLibrary : " + ObjectLibrary.propsLibrary.size());
		} else {
			this.projProps = this.containerProps.projectiles.get(param2);
			String loc9 = !param7.equals("") && this.projProps.objectId.equals(param8) ? param7 : this.projProps.objectId;
			this.props = ObjectLibrary.getPropsFromId(loc9);
			hasShadow = this.props.shadowSize > 0;
			TextureData loc10 = ObjectLibrary.typeToTextureData.get(this.props.type);
			this.texture = loc10.getTexture(objectId);
			this.damagesPlayers = this.containerProps.isEnemy;
			this.damagesEnemies = !this.damagesPlayers;
			this.sound = this.containerProps.oldSound;
			this.multiHitDict = !!this.projProps.multiHit ? new Dictionary() : null;
			if (this.projProps.size >= 0) {
				loc11 = this.projProps.size;
			} else {
				loc11 = ObjectLibrary.getSizeFromType(this.containerType);
			}
			this.p.setSize(8 * (loc11 / 100));
			this.damage = 0;
		}
	}

	public void setDamage(int param1) {
		this.damage = param1;
	}

	public boolean addTo(Map param1, double param2, double param3) {
		Player loc4 = null;
		this.startX = param2;
		this.startY = param3;
		if (!super.addTo(param1, param2, param3)) {
			return false;
		}
		if (!this.containerProps.flying && square.sink != 0) {
			z = 0.1;
		} else {
			loc4 = (Player) param1.goDict.get(this.ownerId);
			if (loc4 != null && loc4.sinkLevel > 0) {
				z = 0.5 - 0.4 * (loc4.sinkLevel / Parameters.MAX_SINK_LEVEL);
			}
		}
		return true;
	}

	public boolean moveTo(double param1, double param2) {
		Square loc3 = map.getSquare(param1, param2);
		if (loc3 == null) {
			return false;
		}
		x = param1;
		y = param2;
		square = loc3;
		return true;
	}

	public void removeFromMap() {
		super.removeFromMap();
		removeObjId(this.ownerId, this.bulletId);
		this.multiHitDict = null;
		//FreeList.deleteObject(this);
	}

	private void positionAt(int param1, Point param2) {
		double loc5 = 0;
		double loc6 = 0;
		double loc7 = 0;
		double loc8 = 0;
		double loc9 = 0;
		double loc10 = 0;
		double loc11 = 0;
		double loc12 = 0;
		double loc13 = 0;
		double loc14 = 0;
		param2.x = this.startX;
		param2.y = this.startY;
		double loc3 = param1 * (this.projProps.speed / 10000);
		double loc4 = this.bulletId % 2 == 0 ? 0 : Math.PI;
		if (this.projProps.wavy) {
			loc5 = 6 * Math.PI;
			loc6 = Math.PI / 64;
			loc7 = this.angle + loc6 * Math.sin(loc4 + loc5 * param1 / 1000);
			param2.x = param2.x + loc3 * Math.cos(loc7);
			param2.y = param2.y + loc3 * Math.sin(loc7);
		} else if (this.projProps.parametric) {
			loc8 = param1 / this.projProps.lifetime * 2 * Math.PI;
			loc9 = Math.sin(loc8) * (this.bulletId % 2 != 0 ? 1 : -1);
			loc10 = Math.sin(2 * loc8) * (this.bulletId % 4 < 2 ? 1 : -1);
			loc11 = Math.sin(this.angle);
			loc12 = Math.cos(this.angle);
			param2.x = param2.x + (loc9 * loc12 - loc10 * loc11) * this.projProps.magnitude;
			param2.y = param2.y + (loc9 * loc11 + loc10 * loc12) * this.projProps.magnitude;
		} else {
			if (this.projProps.boomerang) {
				loc13 = this.projProps.lifetime * (this.projProps.speed / 10000) / 2;
				if (loc3 > loc13) {
					loc3 = loc13 - (loc3 - loc13);
				}
			}
			param2.x = param2.x + loc3 * Math.cos(this.angle);
			param2.y = param2.y + loc3 * Math.sin(this.angle);
			if (this.projProps.amplitude != 0) {
				loc14 = this.projProps.amplitude * Math.sin(loc4 + param1 / this.projProps.lifetime * this.projProps.frequency * 2 * Math.PI);
				param2.x = param2.x + loc14 * Math.cos(this.angle + Math.PI / 2);
				param2.y = param2.y + loc14 * Math.sin(this.angle + Math.PI / 2);
			}
		}
	}

	public boolean update(int param1, int param2) {
		Vector<Integer> loc5 = null;
		Player loc7 = null;
		boolean loc8 = false;
		boolean loc9 = false;
		boolean loc10 = false;
		int loc11 = 0;
		boolean loc12 = false;
		int loc3 = param1 - this.startTime;
		if (loc3 > this.projProps.lifetime) {
			return false;
		}
		Point loc4 = this.staticPoint;
		this.positionAt(loc3, loc4);
		if (!this.moveTo(loc4.x, loc4.y) || square.tileType == 65535) {
			if (this.damagesPlayers) {
				map.gs.gsc.squareHit(param1, this.bulletId, this.ownerId);
			} else if (square.obj != null) {
				if (!(boolean) Parameters.data.noParticlesMaster) {
					loc5 = BloodComposition.getColors(this.texture);
					map.addObj(new HitEffect(loc5, 100, 3, this.angle, this.projProps.speed), loc4.x, loc4.y);
				}
			}
			return false;
		}
		if (square.obj != null && (!square.obj.props.isEnemy || !this.damagesEnemies) && (square.obj.props.enemyOccupySquare || !this.projProps.passesCover && square.obj.props.occupySquare)) {
			if (this.damagesPlayers) {
				map.gs.gsc.otherHit(param1, this.bulletId, this.ownerId, square.obj.objectId);
			} else if (!(boolean) Parameters.data.noParticlesMaster) {
				loc5 = BloodComposition.getColors(this.texture);
				map.addObj(new HitEffect(loc5, 100, 3, this.angle, this.projProps.speed), loc4.x, loc4.y);
			}
			return false;
		}
		GameObject loc6 = this.getHit(loc4.x, loc4.y);
		if (loc6 != null) {
			loc7 = map.player;
			loc8 = loc7 != null;
			loc9 = loc6.props.isEnemy;
			loc10 = loc8 && !loc7.isPaused() && (this.damagesPlayers || loc9 && this.ownerId == loc7.objectId);
			if (loc10) {
				loc11 = GameObject.damageWithDefense(this.damage, loc6.defense, this.projProps.armorPiercing, loc6.condition);
				loc12 = false;
				if (loc6.hp <= loc11) {
					loc12 = true;
					if (loc6.props.isEnemy) {
						doneAction(map.gs, Tutorial.KILL_ACTION);
					}
				}
				if (loc6 == loc7) {
					map.gs.gsc.playerHit(this.bulletId, this.ownerId);
					loc6.damage(true, loc11, this.projProps.effects, false, this);
				} else if (loc6.props.isEnemy) {
					map.gs.gsc.enemyHit(param1, this.bulletId, loc6.objectId, loc12);
					loc6.damage(true, loc11, this.projProps.effects, loc12, this);
				} else if (!this.projProps.multiHit) {
					map.gs.gsc.otherHit(param1, this.bulletId, this.ownerId, loc6.objectId);
				}
			}
			if (this.projProps.multiHit) {
				this.multiHitDict.put(loc6, true);
			} else {
				return false;
			}
		}
		return true;
	}

	public GameObject getHit(double param1, double param2) {
		double loc6 = 0;
		double loc7 = 0;
		double loc8 = 0;
		double loc9 = 0;
		double loc3 = Double.MAX_VALUE;
		GameObject loc4 = null;
		for (GameObject loc5 : map.goDict) {
			if (!loc5.isInvincible()) {
				if (!loc5.isStasis()) {
					if (this.damagesEnemies && loc5.props.isEnemy || this.damagesPlayers && loc5.props.isPlayer) {
						if (!(loc5.dead || loc5.isPaused())) {
							loc6 = loc5.x > param1 ? loc5.x - param1 : param1 - loc5.x;
							loc7 = loc5.y > param2 ? loc5.y - param2 : param2 - loc5.y;
							if (!(loc6 > loc5.radius || loc7 > loc5.radius)) {
								if (!(this.projProps.multiHit && this.multiHitDict.get(loc5) != null)) {
									if (loc5 == map.player) {
										return loc5;
									}
									loc8 = Math.sqrt(loc6 * loc6 + loc7 * loc7);
									loc9 = loc6 * loc6 + loc7 * loc7;
									if (loc9 < loc3) {
										loc3 = loc9;
										loc4 = loc5;
									}
								}
							}
						}
					}
				}
			}
		}
		return loc4;
	}

	public void draw(Vector<IGraphicsData> param1, Camera param2, int param3) {
		int loc8 = 0;
		int loc9 = 0;
		int loc10 = 0;
		int loc11 = 0;
		if (!Parameters.drawProj) {
			return;
		}
		BitmapData loc4 = this.texture;
		if (Parameters.projColorType != 0) {
			switch (Parameters.projColorType) {
				case 1:
					loc8 = 16777100;
					loc9 = 16777215;
					break;
				case 2:
					loc8 = 16777100;
					loc9 = 16777100;
					break;
				case 3:
					loc8 = 16711680;
					loc9 = 16711680;
					break;
				case 4:
					loc8 = 255;
					loc9 = 255;
					break;
				case 5:
					loc8 = 16777215;
					loc9 = 16777215;
					break;
				case 6:
					loc8 = 0;
					loc9 = 0;
			}
			loc4 = TextureRedrawer.redraw(loc4, 120, true, loc9);
		}
		double loc5 = this.props.rotation == 0 ? 0 : param3 / this.props.rotation;
		this.staticVector3D.x = x;
		this.staticVector3D.y = y;
		this.staticVector3D.z = z;
		double loc6 = !!this.projProps.faceDir ? this.getDirectionAngle(param3) : this.angle;
		double loc7 = !!this.projProps.noRotation ? param2.angleRad + this.props.angleCorrection : loc6 - param2.angleRad + this.props.angleCorrection + loc5;
		this.p.draw(param1, this.staticVector3D, loc7, param2.wToS, param2, loc4);
		if (!Parameters.data.noParticlesMaster && this.projProps.particleTrail) {
			loc10 = this.projProps.particleTrailLifetimeMS != -1 ? this.projProps.particleTrailLifetimeMS : 600;
			loc11 = 0;
			for (; loc11 < 3; loc11++) {
				if (map != null && map.player.objectId != this.ownerId) {
					if (this.projProps.particleTrailIntensity == -1 && Math.random() * 100 > this.projProps.particleTrailIntensity) {
						continue;
					}
				}
				map.addObj(new SparkParticle(100, this.projProps.particleTrailColor, loc10, 0.5, RandomUtil.plusMinus(3), RandomUtil.plusMinus(3)), x, y);
			}
		}
	}

	private double getDirectionAngle(double param1) {
		int loc2 = (int) (param1 - this.startTime);
		Point loc3 = new Point();
		this.positionAt(loc2 + 16, loc3);
		double loc4 = loc3.x - x;
		double loc5 = loc3.y - y;
		return Math.atan2(loc5, loc4);
	}

	public void drawShadow(Vector<IGraphicsData> param1, Camera param2, int param3) {
		if (!Parameters.drawProj) {
			return;
		}
		double loc4 = this.props.shadowSize / 400;
		double loc5 = 30 * loc4;
		double loc6 = 15 * loc4;
		this.shadowGradientFill.matrix.createGradientBox(loc5 * 2, loc6 * 2, 0, posS.get(0) - loc5, posS.get(1) - loc6);
		param1.add(this.shadowGradientFill);
		this.shadowPath.data.length = 0;
		this.shadowPath.data.add(posS.get(0) - loc5, posS.get(1) - loc6, posS.get(0) + loc5, posS.get(1) - loc6, posS.get(0) + loc5, posS.get(1) + loc6, posS.get(0) - loc5, posS.get(1) + loc6);
		param1.add(this.shadowPath);
		param1.add(GraphicsUtil.END_FILL);
	}


}
