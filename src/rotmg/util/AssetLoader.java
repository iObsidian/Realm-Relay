package rotmg.util;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import flash.utils.Dictionary;
import org.w3c.dom.Document;
import rotmg.WebMain;
import rotmg.map.GroundLibrary;
import rotmg.map.RegionLibrary;
import rotmg.objects.ObjectLibrary;
import rotmg.objects.animation.AnimatedChar;
import rotmg.parameters.Parameters;
import rotmg.particles.ParticleLibrary;
import rotmg.sound.IMusic;
import rotmg.sound.SFX;
import rotmg.sound.SoundEffectLibrary;
import rotmg.ui.Options;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class AssetLoader {

	public static boolean currentXmlIsTesting = false;

	public IMusic music;

	public AssetLoader() {
		this.music = new MusicProxy();
	}

	public static void main(String[] args) {
		for (XML x : EmbeddedData.objectFiles()) {
			for (XML a : x.children()) {
				System.out.println(a.name());
			}
		}
	}

	public void load() {
		this.addImages();
		this.addAnimatedCharacters();
		this.addSoundEffects();
		this.parse3DModels();
		this.parseParticleEffects();
		this.parseGroundFiles();
		this.parseObjectFiles();
		this.parseRegionFiles();
		Parameters.load();
		Options.refreshCursor();
		this.music.load();
		SFX.load();
	}

	//@formatter:off
	private void addImages() {
		AssetLibrary.addImageSet("lofiChar8x8", "lofiCharEmbed_", 8, 8);
		AssetLibrary.addImageSet("lofiChar16x8", "lofiCharEmbed_", 16, 8);
		AssetLibrary.addImageSet("lofiChar16x16", "lofiCharEmbed_", 16, 16);
		AssetLibrary.addImageSet("lofiChar28x8", "lofiChar2Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiChar216x8", "lofiChar2Embed_", 16, 8);
		AssetLibrary.addImageSet("lofiChar216x16", "lofiChar2Embed_", 16, 16);
		AssetLibrary.addImageSet("lofiCharBig", "lofiCharBigEmbed_", 16, 16);
		AssetLibrary.addImageSet("lofiEnvironment", "lofiEnvironmentEmbed_", 8, 8);
		AssetLibrary.addImageSet("lofiEnvironment2", "lofiEnvironment2Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiEnvironment3", "lofiEnvironment3Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiInterface", "lofiInterfaceEmbed_", 8, 8);
		AssetLibrary.addImageSet("redLootBag", "redLootBagEmbed_", 8, 8);
		AssetLibrary.addImageSet("lofiInterfaceBig", "lofiInterfaceBigEmbed_", 16, 16);
		AssetLibrary.addImageSet("lofiInterface2", "lofiInterface2Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiObj", "lofiObjEmbed_", 8, 8);
		AssetLibrary.addImageSet("lofiObj2", "lofiObj2Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiObj3", "lofiObj3Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiObj4", "lofiObj4Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiObj5", "lofiObj5Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiObj5new", "lofiObj5bEmbed_", 8, 8);
		AssetLibrary.addImageSet("lofiObj6", "lofiObj6Embed_", 8, 8);
		AssetLibrary.addImageSet("lofiObjBig", "lofiObjBigEmbed_", 16, 16);
		AssetLibrary.addImageSet("lofiObj40x40", "lofiObj40x40Embed_", 40, 40);
		AssetLibrary.addImageSet("lofiProjs", "lofiProjsEmbed_", 8, 8);
		AssetLibrary.addImageSet("lofiParticlesShocker", "lofiParticlesShockerEmbed_", 16, 16);
		AssetLibrary.addImageSet("lofiProjsBig", "lofiProjsBigEmbed_", 16, 16);
		AssetLibrary.addImageSet("lofiParts", "lofiPartsEmbed_", 8, 8);
		AssetLibrary.addImageSet("stars", "starsEmbed_", 5, 5);
		AssetLibrary.addImageSet("textile4x4", "textile4x4Embed_", 4, 4);
		AssetLibrary.addImageSet("textile5x5", "textile5x5Embed_", 5, 5);
		AssetLibrary.addImageSet("textile9x9", "textile9x9Embed_", 9, 9);
		AssetLibrary.addImageSet("textile10x10", "textile10x10Embed_", 10, 10);
		AssetLibrary.addImageSet("inner_mask", "innerMaskEmbed_", 4, 4);
		AssetLibrary.addImageSet("sides_mask", "sidesMaskEmbed_", 4, 4);
		AssetLibrary.addImageSet("outer_mask", "outerMaskEmbed_", 4, 4);
		AssetLibrary.addImageSet("innerP1_mask", "innerP1MaskEmbed_", 4, 4);
		AssetLibrary.addImageSet("innerP2_mask", "innerP2MaskEmbed_", 4, 4);
		AssetLibrary.addImageSet("invisible", new BitmapDataSpy(8, 8, true, 0), 8, 8);
		AssetLibrary.addImageSet("d3LofiObjEmbed", "d3LofiObjEmbed_", 8, 8);
		AssetLibrary.addImageSet("d3LofiObjEmbed16", "d3LofiObjEmbed_", 16, 16);
		AssetLibrary.addImageSet("d3LofiObjBigEmbed", "d3LofiObjBigEmbed_", 16, 16);
		AssetLibrary.addImageSet("d2LofiObjEmbed", "d2LofiObjEmbed_", 8, 8);
		AssetLibrary.addImageSet("d2LofiObjBigEmbed", "d2LofiObjBigEmbed_", 16, 16);
		AssetLibrary.addImageSet("d1lofiObjBig", "d1LofiObjBigEmbed_", 16, 16);
		AssetLibrary.addImageSet("cursorsEmbed", "cursorsEmbed_", 32, 32);
		AssetLibrary.addImageSet("mountainTempleObjects8x8", "mountainTempleObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("mountainTempleObjects16x16", "mountainTempleObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("oryxHordeObjects8x8", "oryxHordeObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("oryxHordeObjects16x16", "oryxHordeObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("santaWorkshopObjects8x8", "santaWorkshopObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("santaWorkshopObjects16x16", "santaWorkshopObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("parasiteDenObjects8x8", "parasiteDenObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("parasiteDenObjects16x16", "parasiteDenObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("stPatricksObjects8x8", "stPatricksObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("stPatricksObjects16x16", "stPatricksObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("buffedBunnyObjects8x8", "buffedBunnyObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("buffedBunnyObjects16x16", "buffedBunnyObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("SakuraEnvironment16x16", "SakuraEnvironment16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("SakuraEnvironment8x8", "SakuraEnvironment8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("HanamiParts", "HanamiParts8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("summerNexusObjects8x8", "summerNexusObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("summerNexusObjects16x16", "summerNexusObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("autumnNexusObjects8x8", "autumnNexusObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("autumnNexusObjects16x16", "autumnNexusObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("epicHiveObjects8x8", "epicHiveObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("epicHiveObjects16x16", "epicHiveObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("lostHallsObjects8x8", "lostHallsObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("lostHallsObjects16x16", "lostHallsObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("cnidarianReefObjects8x8", "cnidarianReefObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("cnidarianReefObjects16x16", "cnidarianReefObjects16x16Embed_", 16, 16);
		AssetLibrary.addImageSet("magicWoodsObjects8x8", "magicWoodsObjects8x8Embed_", 8, 8);
		AssetLibrary.addImageSet("magicWoodsObjects16x16", "magicWoodsObjects16x16Embed_", 16, 16);
	}
	//@formatter:on

	private void addAnimatedCharacters() {
		AnimatedChars.add("chars8x8rBeach", "chars8x8rBeachEmbed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8dBeach", "chars8x8dBeachEmbed_", null, 8, 8, 56, 8, AnimatedChar.DOWN);
		AnimatedChars.add("chars8x8rLow1", "chars8x8rLow1Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8rLow2", "chars8x8rLow2Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8rMid", "chars8x8rMidEmbed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8rMid2", "chars8x8rMid2Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8rHigh", "chars8x8rHighEmbed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8rHero1", "chars8x8rHero1Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8rHero2", "chars8x8rHero2Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8dHero1", "chars8x8dHero1Embed_", null, 8, 8, 56, 8, AnimatedChar.DOWN);
		AnimatedChars.add("chars16x16dMountains1", "chars16x16dMountains1Embed_", null, 16, 16, 112, 16, AnimatedChar.DOWN);
		AnimatedChars.add("chars16x16dMountains2", "chars16x16dMountains2Embed_", null, 16, 16, 112, 16, AnimatedChar.DOWN);
		AnimatedChars.add("chars8x8dEncounters", "chars8x8dEncountersEmbed_", null, 8, 8, 56, 8, AnimatedChar.DOWN);
		AnimatedChars.add("chars8x8rEncounters", "chars8x8rEncountersEmbed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars16x8dEncounters", "chars16x8dEncountersEmbed_", null, 16, 8, 112, 8, AnimatedChar.DOWN);
		AnimatedChars.add("chars16x8rEncounters", "chars16x8rEncountersEmbed_", null, 16, 8, 112, 8, AnimatedChar.DOWN);
		AnimatedChars.add("chars16x16dEncounters", "chars16x16dEncountersEmbed_", null, 16, 16, 112, 16, AnimatedChar.DOWN);
		AnimatedChars.add("chars16x16dEncounters2", "chars16x16dEncounters2Embed_", null, 16, 16, 112, 16, AnimatedChar.DOWN);
		AnimatedChars.add("chars16x16rEncounters", "chars16x16rEncountersEmbed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("d3Chars8x8rEmbed", "d3Chars8x8rEmbed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("d3Chars16x16rEmbed", "d3Chars16x16rEmbed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("d2Chars8x8rEmbed", "d2Chars8x8rEmbed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("d2Chars16x16rEmbed", "d2Chars16x16rEmbed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("players", "playersEmbed_", "playersMaskEmbed_", 8, 8, 56, 24, AnimatedChar.RIGHT);
		AnimatedChars.add("playerskins", "playersSkinsEmbed_", "playersSkinsMaskEmbed_", 8, 8, 56, 24, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8rPets1", "chars8x8rPets1Embed_", "chars8x8rPets1MaskEmbed_", 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("chars8x8rPets2", "chars8x8rPets2Embed_", "chars8x8rPets1MaskEmbed_", 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("petsDivine", "petsDivineEmbed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("playerskins16", "playersSkins16Embed_", "playersSkins16MaskEmbed_", 16, 16, 112, 48, AnimatedChar.RIGHT);
		AnimatedChars.add("d1chars16x16r", "d1Chars16x16rEmbed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("parasiteDenChars8x8", "parasiteDenChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("parasiteDenChars16x16", "parasiteDenChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("stPatricksChars8x8", "stPatricksChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("stPatricksChars16x16", "stPatricksChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("buffedBunnyChars16x16", "buffedBunnyChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("mountainTempleChars8x8", "mountainTempleChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("mountainTempleChars16x16", "mountainTempleChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("oryxHordeChars8x8", "oryxHordeChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("oryxHordeChars16x16", "oryxHordeChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("santaWorkshopChars8x8", "santaWorkshopChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("santaWorkshopChars16x16", "santaWorkshopChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("Hanami8x8chars", "Hanami8x8charsEmbed_", null, 8, 8, 64, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("summerNexusChars8x8", "summerNexusChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("summerNexusChars16x16", "summerNexusChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("autumnNexusChars16x16", "autumnNexusChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("autumnNexusChars8x8", "autumnNexusChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("epicHiveChars8x8", "epicHiveChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("epicHiveChars16x16", "epicHiveChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("lostHallsChars16x16", "lostHallsChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
		AnimatedChars.add("lostHallsChars8x8", "lostHallsChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("magicWoodsChars8x8", "magicWoodsChars8x8Embed_", null, 8, 8, 56, 8, AnimatedChar.RIGHT);
		AnimatedChars.add("magicWoodsChars16x16", "magicWoodsChars16x16Embed_", null, 16, 16, 112, 16, AnimatedChar.RIGHT);
	}

	private void addSoundEffects() {
		SoundEffectLibrary.load("button_click");
		SoundEffectLibrary.load("death_screen");
		SoundEffectLibrary.load("enter_realm");
		SoundEffectLibrary.load("error");
		SoundEffectLibrary.load("inventory_move_item");
		SoundEffectLibrary.load("level_up");
		SoundEffectLibrary.load("loot_appears");
		SoundEffectLibrary.load("no_mana");
		SoundEffectLibrary.load("use_key");
		SoundEffectLibrary.load("use_potion");
	}

	private void parse3DModels() {
		// Not implemented
	}

	private void parseParticleEffects() {
		ParticleLibrary.parseFromXML(EmbeddedData.particlesEmbed());
	}

	private void parseGroundFiles() {
		for (XML xml : EmbeddedData.groundFiles()) {
			GroundLibrary.parseFromXML(xml);
		}
	}

	private void parseObjectFiles() {
		for (XML objectOBJ : EmbeddedData.objectFiles()) {
			ObjectLibrary.parseDungeonXML(objectOBJ.getAttribute("type"), objectOBJ); //type attribute is a substitute for the class name
		}
		currentXmlIsTesting = false;
	}

	private boolean checkIsTestingXML(Class param1) {
		return param1.getSimpleName().contains("TestingCXML");
	}

	private void parseRegionFiles() {
		for (XML loc1 : EmbeddedData.regionFiles()) {
			RegionLibrary.parseFromXML(loc1);
		}
	}


}

/**
 * Utility class to represent Embeded stats (XML), images are loaded directly.
 */
class EmbeddedData {

	static Dictionary<String, String> models = new Dictionary<>();
	private static DocumentBuilderFactory dbFactory;
	private static DocumentBuilder dBuilder;

	static {
		models.put("Monster Tank1", "monsterTank1Embed_");
		models.put("Monster Tank2", "monsterTank2Embed_");
		models.put("Monster Tank3", "monsterTank3Embed_");
		models.put("Monster Tank4", "monsterTank4Embed_");
		models.put("GasEmitter", "gasEmitter_");
		models.put("Lab Tank", "labTankEmbed_");
		models.put("Tesla", "teslaEmbed_");
		models.put("CloningVat", "cloningVatEmbed_");
		models.put("Crate", "crateEmbed_");
		models.put("ThreeSideCube", "threeSideCubeEmbed_");
		models.put("Squatty3Side", "squatty3Side_");
		models.put("Cube", "cubeEmbed_");
		models.put("Big Cube", "bigcubeEmbed_");
		models.put("Ico", "icosahedronEmbed_");
		models.put("Octa", "octahedronEmbed_");
		models.put("Pyramid", "pyramidEmbed_");
		models.put("Tetra", "tetrahedronEmbed_");
		models.put("Dodec", "dodecahedronEmbed_");
		models.put("Pillar", "pillarEmbed_");
		models.put("Broken Pillar", "brokenPillarEmbed_");
		models.put("Tower", "towerEmbed_");
		models.put("Obelisk", "obeliskEmbed_");
		models.put("Table", "tableEmbed_");
		models.put("Table Edge", "tableEdgeEmbed_");
		models.put("Sign", "signEmbed_");
		models.put("Web", "webEmbed_");
		models.put("Candy Col Broken", "candyColBrokenEmbed_");
		models.put("Candy Col Whole", "candyColWholeEmbed_");
		models.put("Column One and a Half", "columnOneAndHalfEmbed_");
		models.put("Two High Wall", "twoHighWall_");
		models.put("Candy Doughnut 1", "candyDoughnut1Embed_");
		models.put("Candy Doughnut 2", "candyDoughnut2Embed_");
		models.put("Candy Doughnut 3", "candyDoughnut3Embed_");
		models.put("Candy Doughnut 4", "candyDoughnut4Embed_");
		models.put("Gate", "newGateEmbed_");
		models.put("Gate Entry", "newGateEntryEmbed_");
		models.put("Gate Entry 2", "newGateEntry2Embed_");
		models.put("Gate End 1", "newGateEnd1Embed_");
		models.put("Gate End 2", "newGateEnd2Embed_");
		models.put("Monument 1", "newMonument1Embed_");
		models.put("Monument 2", "newMonument2Embed_");
		models.put("Monument 3", "newMonument3Embed_");
		models.put("Large Monument 1", "largeMonument1Embed_");
		models.put("Large Monument 2", "largeMonument2Embed_");
		models.put("Large Monument 3", "largeMonument3Embed_");
		models.put("Jacko", "jackoEmbed_");
		models.put("Pet Upgrader Obj", "petUpgrader_");
	}


	/**
	 * I kept the names (objectFiles, groundFiles) but there is only really 1 file for each type.
	 * Se we return a list with only one XML.
	 */

	// Remember : "Items" and "Objects" are put in the same file!
	public static Vector<XML> objectFiles() { //Object
		XML x = getXML(getDocument("/xml/objects.xml"));
		return new Vector<>(x);
	}

	public static Vector<XML> groundFiles() { //Ground
		return new Vector<>(getXML(getDocument("/xml/tiles.xml")));
	}

	public static XML particlesEmbed() { //Particle
		return getXML(getDocument("/xml/particles.xml"));
	}

	public static Vector<XML> regionFiles() { //Region
		return new Vector<>(getXML(getDocument("/xml/regions.xml")));
	}


	private static XML getXML(Document d) {
		return new XML(d.getDocumentElement());
	}

	private static Document getDocument(String resourceFile) {
		try {
			if (dbFactory == null) {
				dbFactory = DocumentBuilderFactory.newInstance();
			}
			if (dBuilder == null) {
				dBuilder = dbFactory.newDocumentBuilder();
			}

			InputStream in = WebMain.class.getResourceAsStream(resourceFile);

			if (in == null) {
				System.out.println("Error, null input... : " + resourceFile);
			}

			Document doc = dBuilder.parse(in);
			in.close();
			doc.getDocumentElement().normalize();
			return doc;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to load Local File: " + resourceFile);
		}
	}

}