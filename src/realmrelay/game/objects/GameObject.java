package realmrelay.game.objects;

public class GameObject extends BasicObject {

	protected static final ColorMatrixFilter PAUSEDFILTER = new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix);

	protected static final ColorMatrixFilter CURSEDFILTER = new ColorMatrixFilter(MoreColorUtil.redFilterMatrix);

	protected static final Matrix IDENTITYMATRIX = new Matrix();

	private static final Number ZEROLIMIT = 0.00001;

	private static final Number NEGATIVEZEROLIMIT = -ZEROLIMIT;

	public static final int ATTACKPERIOD = 300;

	private static final int DEFAULTHPBARYOFFSET = 6;

	public BitmapData nameBitmapData = null;

	private GraphicsBitmapFill nameFill = null;

	private GraphicsPath namePath = null;

	public ShockerEffect shockEffect;

	private boolean isShocked;

	private boolean isShockedTransformSet = false;

	private boolean isCharging;

	private boolean isChargingTransformSet = false;

	public ObjectProperties props;

	public String name;

	public Number radius = 0.5;

	public Number facing = 0;

	public boolean flying = false;

	public Number attackAngle = 0;

	public int attackStart = 0;

	public AnimatedChar animatedChar = null;

	public BitmapData texture = null;

	public BitmapData mask = null;

	public List<TextureData> randomTextureData = null;

	public Object3D obj3D = null;

	public Object3DStage3D object3d = null;

	public ParticleEffect effect = null;

	public Animations animations = null;

	public boolean dead = false;

	public int deadCounter = 0;

	protected BitmapData portrait = null;

	protected Dictionary texturingCache = null;

	public int maxHP = 200;

	public int hp = 200;

	public int size = 100;

	public int level = -1;

	public int defense = 0;

	public List<int>slotTypes=null;

	public List<int>equipment=null;

	public List<int>lockedSlot=null;

	public List<int>condition;

	protected int tex1Id = 0;

	protected int tex2Id = 0;

	public boolean isInteractive = false;

	public int objectType;

	private int nextBulletId = 1;

	private Number sizeMult = 1;

	public int sinkLevel = 0;

	public BitmapData hallucinatingTexture = null;

	public FlashDescription flash = null;

	public int connectType = -1;

	private boolean isStunImmune = false;

	private boolean isParalyzeImmune = false;

	private boolean isDazedImmune = false;

	private boolean isStasisImmune = false;

	private boolean ishpScaleSet = false;

	protected int lastTickUpdateTime = 0;

	protected int myLastTickId = -1;

	protected Point posAtTick;

	protected Point tickPosition;

	protected Vector3D moveVec;

	protected GraphicsBitmapFill bitmapFill;

	protected GraphicsPath path;

	protected List<Number> vS;

	protected List<Number> uvt;

	protected Matrix fillMatrix;

	private GraphicsSolidFill hpbarBackFill = null;

	private GraphicsPath hpbarBackPath = null;

	private GraphicsSolidFill hpbarFill = null;

	private GraphicsPath hpbarPath = null;

	private List<BitmapData> icons = null;

	private List<GraphicsBitmapFill> iconFills = null;

	private List<GraphicsPath> iconPaths = null;

	protected GraphicsGradientFill shadowGradientFill = null;

	protected GraphicsPath shadowPath = null;

	public    GameObject(XML param1)  {
         int loc4 = 0;
        this.props = ObjectLibrary.defaultProps;
        this.condition = new int[]{0, 0};
        this.posAtTick = new Point();
        this.tickPosition = new Point();
        this.moveVec = new Vector3D();
        this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
        this.path = new GraphicsPath(GraphicsUtil.QUADCOMMANDS, null);
        this.vS = new ArrayList<Number>();
        this.uvt = new ArrayList<Number>();
        this.fillMatrix = new Matrix();
        super();
        if (param1 == null) {
            return;
        }
        this.objectType = int(param1.@type);
        this.props = ObjectLibrary.propsLibrary[this.objectType];
        hasShadow = this.props.shadowSize > 0;
         TextureData loc2 = ObjectLibrary.typeToTextureData[this.objectType];
        this.texture = loc2.texture;
        this.mask = loc2.mask;
        this.animatedChar = loc2.animatedChar;
        this.randomTextureData = loc2.randomTextureData;
        if (loc2.effectProps != null) {
            this.effect = ParticleEffect.fromProps(loc2.effectProps, this);
        }
        if (this.texture != null) {
            this.sizeMult = this.texture.height / 8;
        }
        if (param1.hasOwnProperty("Model")) {
            this.obj3D = Model3D.getObject3D(String(param1.Model));
            this.object3d = Model3D.getStage3dObject3D(String(param1.Model));
            if (this.texture != null) {
                this.object3d.setBitMapData(this.texture);
            }
        }
         AnimationsData loc3 = ObjectLibrary.typeToAnimationsData[this.objectType];
        if (loc3 != null) {
            this.animations = new Animations(loc3);
        }
        z = this.props.z;
        this.flying = this.props.flying;
        if (param1.hasOwnProperty("MaxHitPoints")) {
            this.hp = this.maxHP = int(param1.MaxHitPoints);
        }
        if (param1.hasOwnProperty("Defense")) {
            this.defense = int(param1.Defense);
        }
        if (param1.hasOwnProperty("SlotTypes")) {
            this.slotTypes = ConversionUtil.toIntVector(param1.SlotTypes);
            this.equipment = new ArrayList<int>(this.slotTypes.length);
            loc4 = 0;
            while (loc4 < this.equipment.length) {
                this.equipment[loc4] = -1;
                loc4++;
            }
            this.lockedSlot = new ArrayList<int>(this.slotTypes.length);
        }
        if (param1.hasOwnProperty("Tex1")) {
            this.tex1Id = int(param1.Tex1);
        }
        if (param1.hasOwnProperty("Tex2")) {
            this.tex2Id = int(param1.Tex2);
        }
        if (param1.hasOwnProperty("StunImmune")) {
            this.isStunImmune = true;
        }
        if (param1.hasOwnProperty("ParalyzeImmune")) {
            this.isParalyzeImmune = true;
        }
        if (param1.hasOwnProperty("DazedImmune")) {
            this.isDazedImmune = true;
        }
        if (param1.hasOwnProperty("StasisImmune")) {
            this.isStasisImmune = true;
        }
        this.props.loadSounds();
    }

	public static  int   damageWithDefense(int param1, int param2, boolean param3, List<int> param4)  {
         int loc5 = param2;
        if (param3 || (param4[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.ARMORBROKENBIT) != 0) {
            loc5 = 0;
        } else if ((param4[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.ARMOREDBIT) != 0) {
            loc5 = loc5 * 2;
        }
         int loc6 = param1 * 3 / 20;
         int loc7 = Math.max(loc6, param1 - loc5);
        if ((param4[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.INVULNERABLEBIT) != 0) {
            loc7 = 0;
        }
        if ((param4[ConditionEffect.CESECONDBATCH] & ConditionEffect.PETRIFIEDBIT) != 0) {
            loc7 = loc7 * 0.9;
        }
        if ((param4[ConditionEffect.CESECONDBATCH] & ConditionEffect.CURSEBIT) != 0) {
            loc7 = loc7 * 1.2;
        }
        return loc7;
    }

	public void setObjectId(int param1) {
		TextureData loc2 = null;
		objectId = param1;
		if (this.randomTextureData != null) {
			loc2 = this.randomTextureData[objectId % this.randomTextureData.length];
			this.texture = loc2.texture;
			this.mask = loc2.mask;
			this.animatedChar = loc2.animatedChar;
			if (this.object3d != null) {
				this.object3d.setBitMapData(this.texture);
			}
		}
	}

	public void setTexture(int param1) {
		TextureData loc2 = ObjectLibrary.typeToTextureData[param1];
		this.texture = loc2.texture;
		this.mask = loc2.mask;
		this.animatedChar = loc2.animatedChar;
	}

	public void setAltTexture(int param1) {
		TextureData loc3 = null;
		TextureData loc2 = ObjectLibrary.typeToTextureData[this.objectType];
		if (param1 == 0) {
			loc3 = loc2;
		} else {
			loc3 = loc2.getAltTextureData(param1);
			if (loc3 == null) {
				return;
			}
		}
		this.texture = loc3.texture;
		this.mask = loc3.mask;
		this.animatedChar = loc3.animatedChar;
		if (this.effect != null) {
			map.removeObj(this.effect.objectId);
			this.effect = null;
		}
		if (!Parameters.data.noParticlesMaster && loc3.effectProps != null) {
			this.effect = ParticleEffect.fromProps(loc3.effectProps, this);
			if (map != null) {
				map.addObj(this.effect, x, y);
			}
		}
	}

	public void setTex1(int param1) {
		if (param1 == this.tex1Id) {
			return;
		}
		this.tex1Id = param1;
		this.texturingCache = new Dictionary();
		this.portrait = null;
	}

	public void setTex2(int param1) {
		if (param1 == this.tex2Id) {
			return;
		}
		this.tex2Id = param1;
		this.texturingCache = new Dictionary();
		this.portrait = null;
	}

	public void playSound(int param1) {
		SoundEffectLibrary.play(this.props.sounds[param1]);
	}

	@Override public  void   dispose()  {
         Object loc1 = null;
         BitmapData loc2 = null;
         Dictionary loc3 = null;
         Object loc4 = null;
         BitmapData loc5 = null;
        super.dispose();
        this.texture = null;
        if (this.portrait != null) {
            this.portrait.dispose();
            this.portrait = null;
        }
        if (this.texturingCache != null) {
            for(loc1 in this.texturingCache) {
                loc2 = loc1 as BitmapData;
                if (loc2 != null) {
                    loc2.dispose();
                } else {
                    loc3 = loc1 as Dictionary;
                    for(loc4 in loc3) {
                        loc5 = loc4 as BitmapData;
                        if (loc5 != null) {
                            loc5.dispose();
                        }
                    }
                }
            }
            this.texturingCache = null;
        }
        if (this.obj3D != null) {
            this.obj3D.dispose();
            this.obj3D = null;
        }
        if (this.object3d != null) {
            this.object3d.dispose();
            this.object3d = null;
        }
        this.slotTypes = null;
        this.equipment = null;
        this.lockedSlot = null;
        if (this.nameBitmapData != null) {
            this.nameBitmapData.dispose();
            this.nameBitmapData = null;
        }
        this.nameFill = null;
        this.namePath = null;
        this.bitmapFill = null;
        this.path.commands = null;
        this.path.data = null;
        this.vS = null;
        this.uvt = null;
        this.fillMatrix = null;
        this.icons = null;
        this.iconFills = null;
        this.iconPaths = null;
        this.shadowGradientFill = null;
        if (this.shadowPath != null) {
            this.shadowPath.commands = null;
            this.shadowPath.data = null;
            this.shadowPath = null;
        }
    }

	public boolean isQuiet() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.QUIETBIT) != 0;
	}

	public boolean isWeak() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.WEAKBIT) != 0;
	}

	public boolean isSlowed() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.SLOWEDBIT) != 0;
	}

	public boolean isSick() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.SICKBIT) != 0;
	}

	public boolean isDazed() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.DAZEDBIT) != 0;
	}

	public boolean isStunned() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.STUNNEDBIT) != 0;
	}

	public boolean isBlind() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.BLINDBIT) != 0;
	}

	public boolean isDrunk() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.DRUNKBIT) != 0;
	}

	public boolean isConfused() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.CONFUSEDBIT) != 0;
	}

	public boolean isStunImmune() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.STUNIMMUNEBIT) != 0 || this.isStunImmune;
	}

	public boolean isInvisible() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.INVISIBLEBIT) != 0;
	}

	public boolean isParalyzed() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.PARALYZEDBIT) != 0;
	}

	public boolean isSpeedy() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.SPEEDYBIT) != 0;
	}

	public boolean isNinjaSpeedy() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.NINJASPEEDYBIT) != 0;
	}

	public boolean isHallucinating() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.HALLUCINATINGBIT) != 0;
	}

	public boolean isHealing() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.HEALINGBIT) != 0;
	}

	public boolean isDamaging() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.DAMAGINGBIT) != 0;
	}

	public boolean isBerserk() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.BERSERKBIT) != 0;
	}

	public boolean isPaused() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.PAUSEDBIT) != 0;
	}

	public boolean isStasis() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.STASISBIT) != 0;
	}

	public boolean isStasisImmune() {
		return this.isStasisImmune
				|| (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.STASISIMMUNEBIT) != 0;
	}

	public boolean isInvincible() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.INVINCIBLEBIT) != 0;
	}

	public boolean isInvulnerable() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.INVULNERABLEBIT) != 0;
	}

	public boolean isArmored() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.ARMOREDBIT) != 0;
	}

	public boolean isArmorBroken() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.ARMORBROKENBIT) != 0;
	}

	public boolean isArmorBrokenImmune() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.ARMORBROKENIMMUNEBIT) != 0;
	}

	public boolean isSlowedImmune() {
		return (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.SLOWEDIMMUNEBIT) != 0;
	}

	public boolean isUnstable() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.UNSTABLEBIT) != 0;
	}

	public boolean isShowPetEffectIcon() {
		return (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.PETEFFECTICON) != 0;
	}

	public boolean isDarkness() {
		return (this.condition[ConditionEffect.CEFIRSTBATCH] & ConditionEffect.DARKNESSBIT) != 0;
	}

	public boolean isParalyzeImmune() {
		return this.isParalyzeImmune
				|| (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.PARALYZEDIMMUNEBIT) != 0;
	}

	public boolean isDazedImmune() {
		return this.isDazedImmune
				|| (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.DAZEDIMMUNEBIT) != 0;
	}

	public boolean isPetrified() {
		return (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.PETRIFIEDBIT) != 0;
	}

	public boolean isPetrifiedImmune() {
		return (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.PETRIFIEDIMMUNEBIT) != 0;
	}

	public boolean isCursed() {
		return (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.CURSEBIT) != 0;
	}

	public boolean isCursedImmune() {
		return (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.CURSEIMMUNEBIT) != 0;
	}

	public boolean isSilenced() {
		return (this.condition[ConditionEffect.CESECONDBATCH] & ConditionEffect.SILENCEDBIT) != 0;
	}

	public String getName() {
		return this.name == null || this.name == "" ? ObjectLibrary.typeToDisplayId[this.objectType] : this.name;
	}

	public int getColor() {
		if (this.props.color != -1) {
			return this.props.color;
		}
		return BitmapUtil.mostCommonColor(this.texture);
	}

	public int getBulletId() {
		int loc1 = this.nextBulletId;
		this.nextBulletId = (this.nextBulletId + 1) % 128;
		return loc1;
	}

	public Number distTo(WorldPosData param1) {
		Number loc2 = param1.x - x;
		Number loc3 = param1.y - y;
		return Math.sqrt(loc2 * loc2 + loc3 * loc3);
	}

	public void toggleShockEffect(boolean param1) {
		if (param1) {
			this.isShocked = true;
		} else {
			this.isShocked = false;
			this.isShockedTransformSet = false;
		}
	}

	public void toggleChargingEffect(boolean param1) {
		if (param1) {
			this.isCharging = true;
		} else {
			this.isCharging = false;
			this.isChargingTransformSet = false;
		}
	}

	@Override
	public boolean addTo(Map param1, Number param2, Number param3) {
		map = param1;
		this.posAtTick.x = this.tickPosition.x = param2;
		this.posAtTick.y = this.tickPosition.y = param3;
		if (!this.moveTo(param2, param3)) {
			map = null;
			return false;
		}
		if (this.effect != null) {
			map.addObj(this.effect, param2, param3);
		}
		return true;
	}

	@Override public  void   removeFromMap()  {
        if (this.props.static && square != null) {
            if (square.obj == this) {
                square.obj = null;
            }
            square = null;
        }
        if (this.effect != null) {
            map.removeObj(this.effect.objectId);
        }
        super.removeFromMap();
        this.dispose();
    }

	public  boolean   moveTo(Number param1, Number param2)  {
         Square loc3 = map.getSquare(param1, param2);
        if (loc3 == null) {
            return false;
        }
        x = param1;
        y = param2;
        if (this.props.static) {
            if (square != null) {
                square.obj = null;
            }
            loc3.obj = this;
        }
        square = loc3;
        if (this.obj3D != null) {
            this.obj3D.setPosition(x, y, 0, this.props.rotation);
        }
        if (this.object3d != null) {
            this.object3d.setPosition(x, y, 0, this.props.rotation);
        }
        return true;
    }

	@Override
	public boolean update(int param1, int param2) {
		int loc4 = 0;
		Number loc5 = NaN;
		Number loc6 = NaN;
		boolean loc3 = false;
		if (!(this.moveVec.x == 0 && this.moveVec.y == 0)) {
			if (this.myLastTickId < map.gs.gsc.lastTickId) {
				this.moveVec.x = 0;
				this.moveVec.y = 0;
				this.moveTo(this.tickPosition.x, this.tickPosition.y);
			} else {
				loc4 = param1 - this.lastTickUpdateTime;
				loc5 = this.posAtTick.x + loc4 * this.moveVec.x;
				loc6 = this.posAtTick.y + loc4 * this.moveVec.y;
				this.moveTo(loc5, loc6);
				loc3 = true;
			}
		}
		if (this.props.whileMoving != null) {
			if (!loc3) {
				z = this.props.z;
				this.flying = this.props.flying;
			} else {
				z = this.props.whileMoving.z;
				this.flying = this.props.whileMoving.flying;
			}
		}
		return true;
	}

	public void onGoto(Number param1, Number param2, int param3) {
		this.moveTo(param1, param2);
		this.lastTickUpdateTime = param3;
		this.tickPosition.x = param1;
		this.tickPosition.y = param2;
		this.posAtTick.x = param1;
		this.posAtTick.y = param2;
		this.moveVec.x = 0;
		this.moveVec.y = 0;
	}

	public void onTickPos(Number param1, Number param2, int param3, int param4) {
		if (this.myLastTickId < map.gs.gsc.lastTickId) {
			this.moveTo(this.tickPosition.x, this.tickPosition.y);
		}
		this.lastTickUpdateTime = map.gs.lastUpdate;
		this.tickPosition.x = param1;
		this.tickPosition.y = param2;
		this.posAtTick.x = x;
		this.posAtTick.y = y;
		this.moveVec.x = (this.tickPosition.x - this.posAtTick.x) / param3;
		this.moveVec.y = (this.tickPosition.y - this.posAtTick.y) / param3;
		this.myLastTickId = param4;
	}

	public  void   damage(boolean param1, int param2, List<int> param3, boolean param4, Projectile param5, boolean = false param6)  {
         int loc8 = 0;
         int loc9 = 0;
         ConditionEffect loc10 = null;
         CharacterStatusText loc11 = null;
         PetsModel loc12 = null;
         PetVO loc13 = null;
         String loc14 = null;
         List<int> loc15 = null;
         boolean loc16 = false;
         boolean loc7 = false;
        if (param4) {
            this.dead = true;
        } else if (param3 != null) {
            loc8 = 0;
            for(loc9 in param3) {
                loc10 = null;
                if (param5 != null && param5.projProps.isPetEffect && param5.projProps.isPetEffect[loc9]) {
                    loc12 = StaticInjectorContext.getInjector().getInstance(PetsModel);
                    loc13 = loc12.getActivePet();
                    if (loc13 != null) {
                        loc10 = ConditionEffect.effects[loc9];
                        this.showConditionEffectPet(loc8, loc10.name);
                        loc8 = loc8 + 500;
                    }
                } else {
                    switch (loc9) {
                        case ConditionEffect.NOTHING:
                            break;
                        case ConditionEffect.WEAK:
                        case ConditionEffect.SICK:
                        case ConditionEffect.BLIND:
                        case ConditionEffect.HALLUCINATING:
                        case ConditionEffect.DRUNK:
                        case ConditionEffect.CONFUSED:
                        case ConditionEffect.STUNIMMUNE:
                        case ConditionEffect.INVISIBLE:
                        case ConditionEffect.SPEEDY:
                        case ConditionEffect.BLEEDING:
                        case ConditionEffect.STASISIMMUNE:
                        case ConditionEffect.NINJASPEEDY:
                        case ConditionEffect.UNSTABLE:
                        case ConditionEffect.DARKNESS:
                        case ConditionEffect.PETRIFIEDIMMUNE:
                        case ConditionEffect.SILENCED:
                            loc10 = ConditionEffect.effects[loc9];
                            break;
                        case ConditionEffect.QUIET:
                            if (map.player == this) {
                                map.player.mp = 0;
                            }
                            loc10 = ConditionEffect.effects[loc9];
                            break;
                        case ConditionEffect.STASIS:
                            if (this.isStasisImmune()) {
                                loc11 = new CharacterStatusText(this, 16711680, 3000);
                                loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECTIMMUNE));
                                map.mapOverlay.addStatusText(loc11);
                            } else {
                                loc10 = ConditionEffect.effects[loc9];
                            }
                            break;
                        case ConditionEffect.SLOWED:
                            if (this.isSlowedImmune()) {
                                loc11 = new CharacterStatusText(this, 16711680, 3000);
                                loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECTIMMUNE));
                                map.mapOverlay.addStatusText(loc11);
                            } else {
                                loc10 = ConditionEffect.effects[loc9];
                            }
                            break;
                        case ConditionEffect.ARMORBROKEN:
                            if (this.isArmorBrokenImmune()) {
                                loc11 = new CharacterStatusText(this, 16711680, 3000);
                                loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECTIMMUNE));
                                map.mapOverlay.addStatusText(loc11);
                            } else {
                                loc10 = ConditionEffect.effects[loc9];
                            }
                            break;
                        case ConditionEffect.STUNNED:
                            if (this.isStunImmune()) {
                                loc11 = new CharacterStatusText(this, 16711680, 3000);
                                loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECTIMMUNE));
                                map.mapOverlay.addStatusText(loc11);
                            } else {
                                loc10 = ConditionEffect.effects[loc9];
                            }
                            break;
                        case ConditionEffect.DAZED:
                            if (this.isDazedImmune()) {
                                loc11 = new CharacterStatusText(this, 16711680, 3000);
                                loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECTIMMUNE));
                                map.mapOverlay.addStatusText(loc11);
                            } else {
                                loc10 = ConditionEffect.effects[loc9];
                            }
                            break;
                        case ConditionEffect.PARALYZED:
                            if (this.isParalyzeImmune()) {
                                loc11 = new CharacterStatusText(this, 16711680, 3000);
                                loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECTIMMUNE));
                                map.mapOverlay.addStatusText(loc11);
                            } else {
                                loc10 = ConditionEffect.effects[loc9];
                            }
                            break;
                        case ConditionEffect.PETRIFIED:
                            if (this.isPetrifiedImmune()) {
                                loc11 = new CharacterStatusText(this, 16711680, 3000);
                                loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECTIMMUNE));
                                map.mapOverlay.addStatusText(loc11);
                            } else {
                                loc10 = ConditionEffect.effects[loc9];
                            }
                            break;
                        case ConditionEffect.CURSE:
                            if (this.isCursedImmune()) {
                                loc11 = new CharacterStatusText(this, 16711680, 3000);
                                loc11.setStringBuilder(new LineBuilder().setParams(TextKey.GAMEOBJECTIMMUNE));
                                map.mapOverlay.addStatusText(loc11);
                            } else {
                                loc10 = ConditionEffect.effects[loc9];
                            }
                            break;
                        case ConditionEffect.GROUNDDAMAGE:
                            loc7 = true;
                    }
                    if (loc10 != null) {
                        if (loc9 < ConditionEffect.NEWCONTHREASHOLD) {
                            if ((this.condition[ConditionEffect.CEFIRSTBATCH] | loc10.bit) == this.condition[ConditionEffect.CEFIRSTBATCH]) {
                                continue;
                            }
                            this.condition[ConditionEffect.CEFIRSTBATCH] = this.condition[ConditionEffect.CEFIRSTBATCH] | loc10.bit;
                        } else {
                            if ((this.condition[ConditionEffect.CESECONDBATCH] | loc10.bit) == this.condition[ConditionEffect.CESECONDBATCH]) {
                                continue;
                            }
                            this.condition[ConditionEffect.CESECONDBATCH] = this.condition[ConditionEffect.CESECONDBATCH] | loc10.bit;
                        }
                        loc14 = loc10.localizationKey;
                        this.showConditionEffect(loc8, loc14);
                        loc8 = loc8 + 500;
                    }
                }
            }
        }
        if (!(this.props.isEnemy && Parameters.data.disableEnemyParticles) && !(!this.props.isEnemy && Parameters.data.disablePlayersHitParticles)) {
            loc15 = BloodComposition.getBloodComposition(this.objectType, this.texture, this.props.bloodProb, this.props.bloodColor);
            if (this.dead) {
                map.addObj(new ExplosionEffect(loc15, this.size, 30), x, y);
            } else if (param5 != null) {
                map.addObj(new HitEffect(loc15, this.size, 10, param5.angle, param5.projProps.speed), x, y);
            } else {
                map.addObj(new ExplosionEffect(loc15, this.size, 10), x, y);
            }
        }
        if (!param1 && (Parameters.data.noEnemyDamage && this.props.isEnemy || Parameters.data.noAllyDamage && this.props.isPlayer)) {
            return;
        }
        if (param2 > 0) {
            loc16 = this.isArmorBroken() || param5 != null && param5.projProps.armorPiercing || loc7 || param6;
            this.showDamageText(param2, loc16);
        }
    }

	public void showConditionEffect(int param1, String param2) {
		CharacterStatusText loc3 = new CharacterStatusText(this, 16711680, 3000, param1);
		loc3.setStringBuilder(new LineBuilder().setParams(param2));
		map.mapOverlay.addStatusText(loc3);
	}

	public void showConditionEffectPet(int param1, String param2) {
		CharacterStatusText loc3 = new CharacterStatusText(this, 16711680, 3000, param1);
		loc3.setStringBuilder(new StaticStringBuilder("Pet " + param2));
		map.mapOverlay.addStatusText(loc3);
	}

	public  void   showDamageText(int param1, boolean param2)  {
         String loc3 = "-" + param1;
        var loc4:CharacterStatusText = new CharacterStatusText(this, !!param2 ? int(9437439) : int(16711680), 1000);
        loc4.setStringBuilder(new StaticStringBuilder(loc3));
        map.mapOverlay.addStatusText(loc4);
    }

	protected BitmapData makeNameBitmapData() {
		StringBuilder loc1 = new StaticStringBuilder(this.name);
		BitmapTextFactory loc2 = StaticInjectorContext.getInjector().getInstance(BitmapTextFactory);
		return loc2.make(loc1, 16, 16777215, true, IDENTITYMATRIX, true);
	}

	public void drawName(List<IGraphicsData> param1, Camera param2) {
		if (this.nameBitmapData == null) {
			this.nameBitmapData = this.makeNameBitmapData();
			this.nameFill = new GraphicsBitmapFill(null, new Matrix(), false, false);
			this.namePath = new GraphicsPath(GraphicsUtil.QUADCOMMANDS, new ArrayList<Number>());
		}
		int loc3 = this.nameBitmapData.width / 2 + 1;
		int loc4 = 30;
		List<Number> loc5 = this.namePath.data;
		loc5.length = 0;
		loc5.add(posS[0] - loc3, posS[1], posS[0] + loc3, posS[1], posS[0] + loc3, posS[1] + loc4, posS[0] - loc3,
				posS[1] + loc4);
		this.nameFill.bitmapData = this.nameBitmapData;
		Matrix loc6 = this.nameFill.matrix;
		loc6.identity();
		loc6.translate(loc5[0], loc5[1]);
		param1.add(this.nameFill);
		param1.add(this.namePath);
		param1.add(GraphicsUtil.ENDFILL);
	}

	protected  BitmapData   getHallucinatingTexture()  {
        if (this.hallucinatingTexture == null) {
            this.hallucinatingTexture = AssetLibrary.getImageFromSet("lofiChar8x8", int(Math.random() * 239));
        }
        return this.hallucinatingTexture;
    }

	protected  BitmapData   getTexture(Camera param1, int param2)  {
         Pet loc6 = null;
         Number loc7 = NaN;
         int loc8 = 0;
         MaskedImage loc9 = null;
         int loc10 = 0;
         BitmapData loc11 = null;
         int loc12 = 0;
         BitmapData loc13 = null;
        if (this is Pet) {
            loc6 = Pet(this);
            if (this.condition[ConditionEffect.CEFIRSTBATCH] != 0 && !this.isPaused()) {
                if (loc6.skinId != 32912) {
                    loc6.setSkin(32912);
                }
            } else if (!loc6.isDefaultAnimatedChar) {
                loc6.setDefaultSkin();
            }
        }
         BitmapData loc3 = this.texture;
         int loc4 = this.size;
         BitmapData loc5 = null;
        if (this.animatedChar != null) {
            loc7 = 0;
            loc8 = AnimatedChar.STAND;
            if (param2 < this.attackStart + ATTACKPERIOD) {
                if (!this.props.dontFaceAttacks) {
                    this.facing = this.attackAngle;
                }
                loc7 = (param2 - this.attackStart) % ATTACKPERIOD / ATTACKPERIOD;
                loc8 = AnimatedChar.ATTACK;
            } else if (this.moveVec.x != 0 || this.moveVec.y != 0) {
                loc10 = 0.5 / this.moveVec.length;
                loc10 = loc10 + (400 - loc10 % 400);
                if (this.moveVec.x > ZEROLIMIT || this.moveVec.x < NEGATIVEZEROLIMIT || this.moveVec.y > ZEROLIMIT || this.moveVec.y < NEGATIVEZEROLIMIT) {
                    if (!this.props.dontFaceMovement) {
                        this.facing = Math.atan2(this.moveVec.y, this.moveVec.x);
                    }
                    loc8 = AnimatedChar.WALK;
                } else {
                    loc8 = AnimatedChar.STAND;
                }
                loc7 = param2 % loc10 / loc10;
            }
            loc9 = this.animatedChar.imageFromFacing(this.facing, param1, loc8, loc7);
            loc3 = loc9.image;
            loc5 = loc9.mask;
        } else if (this.animations != null) {
            loc11 = this.animations.getTexture(param2);
            if (loc11 != null) {
                loc3 = loc11;
            }
        }
        if (this.props.drawOnGround || this.obj3D != null) {
            return loc3;
        }
        if (param1.isHallucinating) {
            loc12 = loc3 == null ? 8 : int(loc3.width);
            loc3 = this.getHallucinatingTexture();
            loc5 = null;
            loc4 = this.size * Math.min(1.5, loc12 / loc3.width);
        }
        if (!(this is Pet)) {
            if (this.isStasis() || this.isPetrified()) {
                loc3 = CachingColorTransformer.filterBitmapData(loc3, PAUSEDFILTER);
            }
        }
        if (this.tex1Id == 0 && this.tex2Id == 0) {
            if (this.isCursed() && Parameters.data.curseIndication) {
                loc3 = TextureRedrawer.redraw(loc3, loc4, false, 16711680);
            } else {
                loc3 = TextureRedrawer.redraw(loc3, loc4, false, 0);
            }
        } else {
            loc13 = null;
            if (this.texturingCache == null) {
                this.texturingCache = new Dictionary();
            } else {
                loc13 = this.texturingCache[loc3];
            }
            if (loc13 == null) {
                loc13 = TextureRedrawer.resize(loc3, loc5, loc4, false, this.tex1Id, this.tex2Id);
                loc13 = GlowRedrawer.outlineGlow(loc13, 0);
                this.texturingCache[loc3] = loc13;
            }
            loc3 = loc13;
        }
        return loc3;
    }

	public void useAltTexture(String param1, int param2) {
		this.texture = AssetLibrary.getImageFromSet(param1, param2);
		this.sizeMult = this.texture.height / 8;
	}

	public BitmapData getPortrait() {
		BitmapData loc1 = null;
		int loc2 = 0;
		if (this.portrait == null) {
			loc1 = this.props.portrait != null ? this.props.portrait.getTexture() : this.texture;
			loc2 = 4 / loc1.width * 100;
			this.portrait = TextureRedrawer.resize(loc1, this.mask, loc2, true, this.tex1Id, this.tex2Id);
			this.portrait = GlowRedrawer.outlineGlow(this.portrait, 0);
		}
		return this.portrait;
	}

	public void setAttack(int param1, Number param2) {
		this.attackAngle = param2;
		this.attackStart = getTimer();
	}

	@Override
	public void draw3d(List<Object3DStage3D> param1) {
		if (this.object3d != null) {
			param1.add(this.object3d);
		}
	}

	protected  void   drawHpBar(List<IGraphicsData> param1, int = 6 param2)  {
         Number loc6 = NaN;
         Number loc7 = NaN;
        if (this.hpbarPath == null) {
            this.hpbarBackFill = new GraphicsSolidFill();
            this.hpbarBackPath = new GraphicsPath(GraphicsUtil.QUADCOMMANDS, new ArrayList<Number>());
            this.hpbarFill = new GraphicsSolidFill();
            this.hpbarPath = new GraphicsPath(GraphicsUtil.QUADCOMMANDS, new ArrayList<Number>());
        }
        if (this.hp > this.maxHP) {
            this.maxHP = this.hp;
        }
        this.hpbarBackFill.color = 1118481;
         int loc3 = 20;
         int loc4 = 5;
        this.hpbarBackPath.data.length = 0;
         Number loc5 = 1.2;
        (this.hpbarBackPath.data as List<Number>).add(posS[0] - loc3 - loc5, posS[1] + param2 - loc5, posS[0] + loc3 + loc5, posS[1] + param2 - loc5, posS[0] + loc3 + loc5, posS[1] + param2 + loc4 + loc5, posS[0] - loc3 - loc5, posS[1] + param2 + loc4 + loc5);
        param1.add(this.hpbarBackFill);
        param1.add(this.hpbarBackPath);
        param1.add(GraphicsUtil.ENDFILL);
        if (this.hp > 0) {
            loc6 = this.hp / this.maxHP;
            loc7 = loc6 * 2 * loc3;
            this.hpbarPath.data.length = 0;
            (this.hpbarPath.data as List<Number>).add(posS[0] - loc3, posS[1] + param2, posS[0] - loc3 + loc7, posS[1] + param2, posS[0] - loc3 + loc7, posS[1] + param2 + loc4, posS[0] - loc3, posS[1] + param2 + loc4);
            this.hpbarFill.color = loc6 < 0.5 ? loc6 < 0.2 ? int(14684176) : int(16744464) : int(1113856);
            param1.add(this.hpbarFill);
            param1.add(this.hpbarPath);
            param1.add(GraphicsUtil.ENDFILL);
        }
        GraphicsFillExtra.setSoftwareDrawSolid(this.hpbarFill, true);
        GraphicsFillExtra.setSoftwareDrawSolid(this.hpbarBackFill, true);
    }

	@Override public  void   draw(List<IGraphicsData> param1, Camera param2, int param3)  {
         BitmapData loc9 = null;
         int loc10 = 0;
         int loc11 = 0;
         int loc12 = 0;
         BitmapData loc4 = this.getTexture(param2, param3);
        if (this.props.drawOnGround) {
            if (square.faces.length == 0) {
                return;
            }
            this.path.data = square.faces[0].face.vout;
            this.bitmapFill.bitmapData = loc4;
            square.baseTexMatrix.calculateTextureMatrix(this.path.data);
            this.bitmapFill.matrix = square.baseTexMatrix.tToS;
            param1.add(this.bitmapFill);
            param1.add(this.path);
            param1.add(GraphicsUtil.ENDFILL);
            return;
        }
         boolean loc5 = this.props && (this.props.isEnemy || this.props.isPlayer) && !this.isInvincible() && (this.props.isPlayer || !this.isInvulnerable()) && !this.props.noMiniMap;
        if (this.obj3D != null) {
            if (loc5 && this.bHPBarParamCheck() && this.props.healthBar) {
                this.drawHpBar(param1, this.props.healthBar);
            }
            if (!Parameters.isGpuRender()) {
                this.obj3D.draw(param1, param2, this.props.color, loc4);
                return;
            }
            if (Parameters.isGpuRender()) {
                param1.add(null);
                return;
            }
        }
         int loc6 = loc4.width;
         int loc7 = loc4.height;
         int loc8 = square.sink + this.sinkLevel;
        if (loc8 > 0 && (this.flying || square.obj != null && square.obj.props.protectFromSink)) {
            loc8 = 0;
        }
        if (Parameters.isGpuRender()) {
            if (loc8 != 0) {
                GraphicsFillExtra.setSinkLevel(this.bitmapFill, Math.max(loc8 / loc7 * 1.65 - 0.02, 0));
                loc8 = -loc8 + 0.02;
            } else if (loc8 == 0 && GraphicsFillExtra.getSinkLevel(this.bitmapFill) != 0) {
                GraphicsFillExtra.clearSink(this.bitmapFill);
            }
        }
        this.vS.length = 0;
        this.vS.add(posS[3] - loc6 / 2, posS[4] - loc7 + loc8, posS[3] + loc6 / 2, posS[4] - loc7 + loc8, posS[3] + loc6 / 2, posS[4], posS[3] - loc6 / 2, posS[4]);
        this.path.data = this.vS;
        if (this.flash != null) {
            if (!this.flash.doneAt(param3)) {
                if (Parameters.isGpuRender()) {
                    this.flash.applyGPUTextureColorTransform(loc4, param3);
                } else {
                    loc4 = this.flash.apply(loc4, param3);
                }
            } else {
                this.flash = null;
            }
        }
        if (this.isShocked && !this.isShockedTransformSet) {
            if (Parameters.isGpuRender()) {
                GraphicsFillExtra.setColorTransform(loc4, new ColorTransform(-1, -1, -1, 1, 255, 255, 255, 0));
            } else {
                loc9 = loc4.clone();
                loc9.colorTransform(loc9.rect, new ColorTransform(-1, -1, -1, 1, 255, 255, 255, 0));
                loc9 = CachingColorTransformer.filterBitmapData(loc9, new ColorMatrixFilter(MoreColorUtil.greyscaleFilterMatrix));
                loc4 = loc9;
            }
            this.isShockedTransformSet = true;
        }
        if (this.isCharging && !this.isChargingTransformSet) {
            if (Parameters.isGpuRender()) {
                GraphicsFillExtra.setColorTransform(loc4, new ColorTransform(1, 1, 1, 1, 255, 255, 255, 0));
            } else {
                loc9 = loc4.clone();
                loc9.colorTransform(loc9.rect, new ColorTransform(1, 1, 1, 1, 255, 255, 255, 0));
                loc4 = loc9;
            }
            this.isChargingTransformSet = true;
        }
        this.bitmapFill.bitmapData = loc4;
        this.fillMatrix.identity();
        this.fillMatrix.translate(this.vS[0], this.vS[1]);
        this.bitmapFill.matrix = this.fillMatrix;
        param1.add(this.bitmapFill);
        param1.add(this.path);
        param1.add(GraphicsUtil.ENDFILL);
        if (!this.isPaused() && (this.condition[ConditionEffect.CEFIRSTBATCH] || this.condition[ConditionEffect.CESECONDBATCH]) && !Parameters.screenShotMode && !(this is Pet)) {
            this.drawConditionIcons(param1, param2, param3);
        }
        if (this.props.showName && this.name != null && this.name.length != 0) {
            this.drawName(param1, param2);
        }
        if (loc5) {
            loc10 = loc4.getPixel32(loc4.width / 4, loc4.height / 4) | loc4.getPixel32(loc4.width / 2, loc4.height / 2) | loc4.getPixel32(loc4.width * 3 / 4, loc4.height * 3 / 4);
            loc11 = loc10 >> 24;
            if (loc11 != 0) {
                hasShadow = true;
                loc12 = this.props.isPlayer && this != map.player ? 12 : 0;
                if (this.bHPBarParamCheck() && this.props.healthBar != -1) {
                    this.drawHpBar(param1, !!this.props.healthBar ? int(this.props.healthBar) : int(loc12 + DEFAULTHPBARYOFFSET));
                }
            } else {
                hasShadow = false;
            }
        }
    }

	private boolean bHPBarParamCheck() {
		return Parameters.data.HPBar && (Parameters.data.HPBar == 1 || Parameters.data.HPBar == 2 && this.props.isEnemy
				|| Parameters.data.HPBar == 3 && (this == map.player || this.props.isEnemy)
				|| Parameters.data.HPBar == 4 && this == map.player
				|| Parameters.data.HPBar == 5 && this.props.isPlayer);
	}

	public  void   drawConditionIcons(List<IGraphicsData> param1, Camera param2, int param3)  {
         BitmapData loc9 = null;
         GraphicsBitmapFill loc10 = null;
         GraphicsPath loc11 = null;
         Number loc12 = NaN;
         Number loc13 = NaN;
         Matrix loc14 = null;
        if (this.icons == null) {
            this.icons = new ArrayList<BitmapData>();
            this.iconFills = new ArrayList<GraphicsBitmapFill>();
            this.iconPaths = new ArrayList<GraphicsPath>();
        }
        this.icons.length = 0;
         int loc4 = param3 / 500;
        ConditionEffect.getConditionEffectIcons(this.condition[ConditionEffect.CEFIRSTBATCH], this.icons, loc4);
        ConditionEffect.getConditionEffectIcons2(this.condition[ConditionEffect.CESECONDBATCH], this.icons, loc4);
         Number loc5 = posS[3];
         Number loc6 = this.vS[1];
         int loc7 = this.icons.length;
         int loc8 = 0;
        while (loc8 < loc7) {
            loc9 = this.icons[loc8];
            if (loc8 >= this.iconFills.length) {
                this.iconFills.add(new GraphicsBitmapFill(null, new Matrix(), false, false));
                this.iconPaths.add(new GraphicsPath(GraphicsUtil.QUADCOMMANDS, new ArrayList<Number>()));
            }
            loc10 = this.iconFills[loc8];
            loc11 = this.iconPaths[loc8];
            loc10.bitmapData = loc9;
            loc12 = loc5 - loc9.width * loc7 / 2 + loc8 * loc9.width;
            loc13 = loc6 - loc9.height / 2;
            loc11.data.length = 0;
            (loc11.data as List<Number>).add(loc12, loc13, loc12 + loc9.width, loc13, loc12 + loc9.width, loc13 + loc9.height, loc12, loc13 + loc9.height);
            loc14 = loc10.matrix;
            loc14.identity();
            loc14.translate(loc12, loc13);
            param1.add(loc10);
            param1.add(loc11);
            param1.add(GraphicsUtil.ENDFILL);
            loc8++;
        }
    }

	@Override public  void   drawShadow(List<IGraphicsData> param1, Camera param2, int param3)  {
        if (this.shadowGradientFill == null) {
            this.shadowGradientFill = new GraphicsGradientFill(GradientType.RADIAL, [this.props.shadowColor, this.props.shadowColor], [0.5, 0], null, new Matrix());
            this.shadowPath = new GraphicsPath(GraphicsUtil.QUADCOMMANDS, new ArrayList<Number>());
        }
         Number loc4 = this.size / 100 * (this.props.shadowSize / 100) * this.sizeMult;
         Number loc5 = 30 * loc4;
         Number loc6 = 15 * loc4;
        this.shadowGradientFill.matrix.createGradientBox(loc5 * 2, loc6 * 2, 0, posS[0] - loc5, posS[1] - loc6);
        param1.add(this.shadowGradientFill);
        this.shadowPath.data.length = 0;
        (this.shadowPath.data as List<Number>).add(posS[0] - loc5, posS[1] - loc6, posS[0] + loc5, posS[1] - loc6, posS[0] + loc5, posS[1] + loc6, posS[0] - loc5, posS[1] + loc6);
        param1.add(this.shadowPath);
        param1.add(GraphicsUtil.ENDFILL);
    }

	public void clearTextureCache() {
		this.texturingCache = new Dictionary();
	}

	public  String   toString()  {
        return "[" + getQualifiedClassName(this) + "  id;
    }
}
