package rotmg.model;

import java.util.List;

import org.osflash.signals.Signal;

public class PotionModel {

	public int objectId;
	public int purchaseCooldownMillis;
	public int maxPotionCount;
	public int position;
	public boolean available;
	/**
	 * private Timer costCoolDownTimer;
	 * private NativeSignal costTimerSignal;
	 * private Timer purchaseCoolDownTimer;
	 * private NativeSignal purchaseTimerSignal;
	 */

	public Signal<Integer> update;
	List<Integer> costs;
	int priceCooldownMillis;
	int costIndex;

}
