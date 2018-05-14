package realmrelay.game.objects;

import javafx.geometry.Point3D;
import javafx.scene.Camera;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

import realmrelay.game.map.Map;
import realmrelay.game.parameters.Parameters;
import realmrelay.packets.data.unused.BitmapData;
import realmspy.service.util.RandomUtil;

import java.util.Dictionary;
import java.util.HashMap;

/**
 * This class is about 10% done. It requires a lot of graphics stuff.
 */
public class Projectile extends BasicObject {

	private static Map<Integer, Integer> objBullIdToObjId = new HashMap<>();

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
	public HashMap multiHitDict;
	public Point3D p;

	public Projectile() {

		super();
	}

	public static int findObjId(int param1, int param2) {
		return (objBullIdToObjId.get(((param2 << 24) | param1)));
	}

	public static int getNewObjId(int param1, int param2) {
		int loc3 = getNextFakeObjectId();
		objBullIdToObjId.put(((param2 << 24) | param1), loc3);
		return loc3;
	}

	public static void removeObjId(int param1, int param2) {
		objBullIdToObjId.remove(objBullIdToObjId.get(((param2 << 24) | param1)));
	}

	@Override
	public void dispose() {
		objBullIdToObjId = new HashMap<Integer, Integer>();
	}

	public void reset(int containerType, int bulletType, int ownerId, int bulletId, double angle, int startTime) {
		clear();
		this.containerType = containerType;
		this.bulletType = bulletType;
		this.ownerId = ownerId;
		this.bulletId = bulletId;
		this.angle = Trig.boundToPI(angle);
		this.startTime = startTime;
		objectId = getNewObjId(this.ownerId, this.bulletId);
		z = 0.5;
		this.containerProps = ObjectLibrary.propsLibrary.get(this.containerType);
		this.projProps = this.containerProps.projectiles.get(bulletType);
		this.props = ObjectLibrary.getPropsFromId(this.projProps.objectId);
		hasShadow = this.props.shadowSize > 0;
		TextureData textureData = ObjectLibrary.typeToTextureData.get(this.props.type);
		this.texture = textureData.getTexture(objectId);
		this.damagesPlayers = this.containerProps.isEnemy;
		this.damagesEnemies = !this.damagesPlayers;
		this.sound = this.containerProps.oldSound;
		this.multiHitDict = !!this.projProps.multiHit ? new Map<>() : null;

		double size = 0F;

		if (this.projProps.size >= 0) {
			size = this.projProps.size;
		} else {
			size = ObjectLibrary.getSizeFromType(this.containerType);
		}
		this.p.setSize(8 * (size / 100));
		this.damage = 0;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public boolean addTo(Map map, double x, double y) {
		Player player = null;
		this.startX = x;
		this.startY = y;
		if (!super.addTo(map, x, y)) {
			return false;
		}
		if (!this.containerProps.flying && square.sink) {
			z = 0.1;
		} else {
			player = (Player) map.goDict.get(this.ownerId);
			if (player != null && player.sinkLevel > 0) {
				z = 0.5 - 0.4 * (player.sinkLevel / Parameters.MAX_SINK_LEVEL);
			}
		}
		return true;
	}

	public boolean moveTo(double x, double y) {
		Square square = map.getSquare(x, y);
		if (square == null) {
			return false;
		}
		x = x;
		y = y;
		square = square;
		return true;
	}

	public void removeFromMap()
    {
        super.removeFromMap();
        removeObjId(this.ownerId,this.bulletId);
        this.multiHitDict = null;
        FreeList.deleteObject(this);
    }

	private void positionAt(elapsed:int, p:Point) : void
    {
        double periodFactor = NaN;
        double amplitudeFactor = NaN;
        double theta = NaN;
        double t = NaN;
        double x = NaN;
        double y = NaN;
        double sin = NaN;
        double cos = NaN;
        double halfway = NaN;
        double deflection = NaN;
        p.x = this.startX_;
        p.y = this.startY_;
        double dist = elapsed * (this.projProps.speed_ / 10000);
        var phase:double = this.bulletId_ % 2 == 0?0:Math.PI;
        if(this.projProps.wavy_)
        {
            periodFactor = 6 * Math.PI;
            amplitudeFactor = Math.PI / 64;
            theta = this.angle_ + amplitudeFactor * Math.sin(phase + periodFactor * elapsed / 1000);
            p.x = p.x + dist * Math.cos(theta);
            p.y = p.y + dist * Math.sin(theta);
        }
        else if(this.projProps.parametric_)
        {
            t = elapsed / this.projProps.lifetime_ * 2 * Math.PI;
            x = Math.sin(t) * (boolean(this.bulletId_ % 2)?1:-1);
            y = Math.sin(2 * t) * (this.bulletId_ % 4 < 2?1:-1);
            sin = Math.sin(this.angle);
            cos = Math.cos(this.angle);
            p.x = p.x + (x * cos - y * sin) * this.projProps.magnitude_;
            p.y = p.y + (x * sin + y * cos) * this.projProps.magnitude_;
        }
        else
        {
            if(this.projProps.boomerang)
            {
                halfway = this.projProps.lifetime * (this.projProps.speed_ / 10000) / 2;
                if(dist > halfway)
                {
                    dist = halfway - (dist - halfway);
                }
            }
            p.x = p.x + dist * Math.cos(this.angle);
            p.y = p.y + dist * Math.sin(this.angle);
            if(this.projProps.amplitude_ != 0)
            {
                deflection = this.projProps.amplitude_ * Math.sin(phase + elapsed / this.projProps.lifetime_ * this.projProps.frequency_ * 2 * Math.PI);
                p.x = p.x + deflection * Math.cos(this.angle_ + Math.PI / 2);
                p.y = p.y + deflection * Math.sin(this.angle_ + Math.PI / 2);
            }
        }
    }

    override

	public function update(time:int, dt:int) : boolean
    {
        List<int> colors = null;
        Player player = null;
        boolean isPlayer = false;
        boolean isTargetAnEnemy = false;
        boolean sendMessage = false;
        int d = 0;
        boolean dead = false;
        int elapsed = time - this.startTime_;
        if(elapsed > this.projProps.lifetime_)
        {
            return false;
        }
        Point p = this.staticPoint_;
        this.positionAt(elapsed,p);
        if(!this.moveTo(p.x,p.y) || square.tileType_ == 255)
        {
            if(this.damagesPlayers_)
            {
                map.gs.gsc.squareHit(time,this.bulletId,this.ownerId);
            }
            else if(square.obj_ != null)
            {
                colors = BloodComposition.getColors(this.texture);
                map.addObj(new HitEffect(colors,100,3,this.angle,this.projProps.speed_),p.x,p.y);
            }
            return false;
        }
        if(square.obj_ != null && (!square.obj.props.isEnemy_ || !this.damagesEnemies_) && (square.obj.props.enemyOccupySquare_ || !this.projProps.passesCover_ && square.obj.props.occupySquare_))
        {
            if(this.damagesPlayers_)
            {
                map.gs.gsc.otherHit(time,this.bulletId,this.ownerId,square.obj.objectId);
            }
            else
            {
                colors = BloodComposition.getColors(this.texture);
                map.addObj(new HitEffect(colors,100,3,this.angle,this.projProps.speed_),p.x,p.y);
            }
            return false;
        }
        GameObject target = this.getHit(p.x,p.y);
        if(target != null)
        {
            player = map.player_;
            isPlayer = player != null;
            isTargetAnEnemy = target.props.isEnemy_;
            sendMessage = isPlayer && !player.isPaused() && (this.damagesPlayers_ || isTargetAnEnemy && this.ownerId_ == player.objectId);
            if(sendMessage)
            {
                d = GameObject.damageWithDefense(this.damage,target.defense,this.projProps.armorPiercing,target.condition);
                dead = false;
                if(target.hp_ <= d)
                {
                    dead = true;
                    if(target.props.isEnemy_)
                    {
                        doneAction(map.gs,Tutorial.KILL_ACTION);
                    }
                }
                if(target == player)
                {
                    map.gs.gsc.playerHit(this.bulletId,this.ownerId);
                    target.damage(this.containerType,d,this.projProps.effects,false,this);
                }
                else if(target.props.isEnemy_)
                {
                    map.gs.gsc.enemyHit(time,this.bulletId,target.objectId,dead);
                    target.damage(this.containerType,d,this.projProps.effects,dead,this);
                }
                else if(!this.projProps.multiHit_)
                {
                    map.gs.gsc.otherHit(time,this.bulletId,this.ownerId,target.objectId);
                }
            }
            if(this.projProps.multiHit_)
            {
                this.multiHitDict[target] = true;
            }
            else
            {
                return false;
            }
        }
        return true;
    }

	public function getHit(pX:double, pY:double) : GameObject
    {
        GameObject go = null;
        double xDiff = NaN;
        double yDiff = NaN;
        double dist = NaN;
        double minDist = double.MAX_VALUE;
        GameObject minGO = null;
        for(go in map.goDict_)
        {
            if(!go.isInvincible())
            {
                if(!go.isStasis())
                {
                    if(this.damagesEnemies_ && go.props.isEnemy_ || this.damagesPlayers_ && go.props.isPlayer_)
                    {
                        if(!(go.dead_ || go.isPaused()))
                        {
                            xDiff = go.x_ > pX?go.x_ - pX:pX - go.x_;
                            yDiff = go.y_ > pY?go.y_ - pY:pY - go.y_;
                            if(!(xDiff > go.radius_ || yDiff > go.radius_))
                            {
                                if(!(this.projProps.multiHit_ && this.multiHitDict[go] != null))
                                {
                                    if(go == map.player_)
                                    {
                                        return go;
                                    }
                                    dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                                    if(dist < minDist)
                                    {
                                        minDist = dist;
                                        minGO = go;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return minGO;
    }

    override

	public function draw(graphicsData:List<IGraphicsData>, camera:Camera, time:int) : void
    {
        int outlineColor = 0;
        int glowColor = 0;
        if(!Parameters.drawProj_)
        {
            return;
        }
        BitmapData texture = this.texture_;
        if(Parameters.projColorType_ != 0)
        {
            switch(Parameters.projColorType_)
            {
                case 1:
                    outlineColor = 16777100;
                    glowColor = 16777215;
                    break;
                case 2:
                    outlineColor = 16777100;
                    glowColor = 16777100;
                    break;
                case 3:
                    outlineColor = 16711680;
                    glowColor = 16711680;
                    break;
                case 4:
                    outlineColor = 255;
                    glowColor = 255;
                    break;
                case 5:
                    outlineColor = 16777215;
                    glowColor = 16777215;
                    break;
                case 6:
                    outlineColor = 0;
                    glowColor = 0;
            }
            texture = TextureRedrawer.redraw(texture,120,true,outlineColor,glowColor);
        }
        var r:double = this.props.rotation_ == 0?0:time / this.props.rotation_;
        this.staticVector3D.x = x_;
        this.staticVector3D.y = y_;
        this.staticVector3D.z = z_;
        this.p.draw(graphicsData,this.staticVector3D,this.angle_ - camera.angleRad_ + this.props.angleCorrection_ + r,camera.wToS,camera,texture);
        if(this.projProps.particleTrail_)
        {
            map.addObj(new SparkParticle(100,16711935,600,0.5, RandomUtil.plusMinus(3),RandomUtil.plusMinus(3)),x,y);
            map.addObj(new SparkParticle(100,16711935,600,0.5,RandomUtil.plusMinus(3),RandomUtil.plusMinus(3)),x,y);
            map.addObj(new SparkParticle(100,16711935,600,0.5,RandomUtil.plusMinus(3),RandomUtil.plusMinus(3)),x,y);
        }
    }

    override

	public function drawShadow(graphicsData:List<IGraphicsData>, camera:Camera, time:int) : void
    {
        if(!Parameters.drawProj_)
        {
            return;
        }
        double s = this.props.shadowSize_ / 400;
        double w = 30 * s;
        double h = 15 * s;
        this.shadowGradientFill.matrix.createGradientBox(w * 2,h * 2,0,posS[0] - w,posS[1] - h);
        graphicsData.add(this.shadowGradientFill);
        this.shadowPath.data.length = 0;
        this.shadowPath.data.add(posS[0] - w,posS[1] - h,posS[0] + w,posS[1] - h,posS[0] + w,posS[1] + h,posS[0] - w,posS[1] + h);
        graphicsData.add(this.shadowPath);
        graphicsData.add(GraphicsUtil.END_FILL);
    }



}
