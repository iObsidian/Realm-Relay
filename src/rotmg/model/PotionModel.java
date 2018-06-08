package rotmg.model;

import org.osflash.signals.Signal;

import java.util.List;

public class PotionModel {

	public int objectId;
	List<Integer> costs;
	int priceCooldownMillis;
	public int purchaseCooldownMillis;
	public int maxPotionCount;
	public int position;
	public boolean available;
	int costIndex;

	/**
	 * private Timer costCoolDownTimer;
	 * private NativeSignal costTimerSignal;
	 * private Timer purchaseCoolDownTimer;
	 * private NativeSignal purchaseTimerSignal;
	 */

	public Signal<Integer> update;

}
