package rotmg.model;

import alde.flash.utils.SignalConsumer;
import alde.flash.utils.XML;
import flash.utils.Dictionary;
import org.osflash.signals.Signal;

import java.util.ArrayList;
import java.util.List;

public class PotionInventoryModel {

	public static final int HEALTH_POTION_ID = 2594;
	public static final int HEALTH_POTION_SLOT = 254;
	public static final int MAGIC_POTION_ID = 2595;
	public static final int MAGIC_POTION_SLOT = 255;
	public static PotionInventoryModel instance;
	public Dictionary<Integer, PotionModel> potionModels;
	public Signal<Integer> updatePosition;

	public PotionInventoryModel() {
		super();
		this.potionModels = new Dictionary<>();
		this.updatePosition = new Signal<Integer>();
	}

	public static PotionInventoryModel getInstance() {
		if (instance == null) {
			instance = new PotionInventoryModel();
		}

		return instance;
	}

	public static int getPotionSlot(int param1) {
		switch (param1) {
			case HEALTH_POTION_ID:
				return HEALTH_POTION_SLOT;
			case MAGIC_POTION_ID:
				return MAGIC_POTION_SLOT;
			default:
				return -1;
		}
	}

	public void initializePotionModels(XML xml) {
		PotionModel potModel = null;
		int purchaseCooldownMillis = xml.getIntValue("PotionPurchaseCooldown");
		int costCooldownMillis = xml.getIntValue("PotionPurchaseCostCooldown");
		int maxStackable = xml.getIntValue("MaxStackablePotions");
		List<Integer> costs = new ArrayList<>();
		for (XML potionCost : xml.children("PotionPurchaseCosts")) {
			costs.add(potionCost.getIntValue("cost"));
		}

		potModel = new PotionModel();
		potModel.purchaseCooldownMillis = purchaseCooldownMillis;
		potModel.priceCooldownMillis = costCooldownMillis;
		potModel.maxPotionCount = maxStackable;
		potModel.objectId = HEALTH_POTION_ID;
		potModel.position = 0;
		potModel.costs = costs;
		this.potionModels.put(potModel.position, potModel);
		potModel.update.add(new SignalConsumer<>(this::update));
		potModel = new PotionModel();
		potModel.purchaseCooldownMillis = purchaseCooldownMillis;
		potModel.priceCooldownMillis = costCooldownMillis;
		potModel.maxPotionCount = maxStackable;
		potModel.objectId = MAGIC_POTION_ID;
		potModel.position = 1;
		potModel.costs = costs;
		this.potionModels.put(potModel.position, potModel);
		potModel.update.add(new SignalConsumer<>(this::update));
	}

	private void update(int param1) {
		this.updatePosition.dispatch(param1);
	}

	public Object getPotionModel(int magicPotionId) {
		return null;
	}
}
