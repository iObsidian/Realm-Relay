package rotmg.packages.services;

import alde.flash.utils.Vector;
import flash.utils.Dictionary;
import org.osflash.signals.Signal;
import rotmg.packages.models.PackageInfo;

public class PackageModel {

	public int numSpammed = 0;

	public Signal dataChanged;

	private Dictionary<String, PackageInfo> models;

	private boolean initialized;

	private int maxSlots = 4;

	public PackageModel() {
		super();
		this.dataChanged = new Signal();
	}

	public Vector<PackageInfo> getBoxesForGrid() {
		Vector<PackageInfo> loc1 = new Vector<PackageInfo>(this.maxSlots);
		for (PackageInfo loc2 : this.models) {
			if (loc2.slot != 0) {
				loc1.put(loc2.slot - 1, loc2);
			}
		}
		return loc1;
	}

	public PackageInfo startupPackage() {
		for (PackageInfo loc1 : this.models) {
			if (loc1.showOnLogin && loc1.popupImage != "") {
				return loc1;
			}
		}
		return null;
	}

	public boolean getInitialized() {
		return this.initialized;
	}

	public PackageInfo getPackageById(int param1) {
		return this.models.get(param1);
	}

	public boolean hasPackage(int param1) {
		return this.models.contains(param1);
	}

	public void setPackages(Vector<PackageInfo> param1) {
		this.models = new Dictionary<>();
		for (PackageInfo loc2 : param1) {
			this.models.put(loc2.id, loc2);
		}
		this.initialized = true;
		this.dataChanged.dispatch();
	}

	private void onDataChanged() {
		this.dataChanged.dispatch();
	}

	public boolean canPurchasePackage(int param1) {
		PackageInfo loc2 = this.models.get(param1);
		return loc2 != null;
	}

	public PackageInfo getPriorityPackage() {
		PackageInfo loc1 = null;
		return loc1;
	}

	public boolean hasPackages() {
		for (Object loc1 : this.models) {
			return true;
		}
		return false;
	}

}
