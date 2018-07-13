package rotmg;

import rotmg.messaging.data.MoveRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a 100% match
 */
public class MoveRecords {

	public int lastClearTime = -1;
	public List<MoveRecord> records;

	public MoveRecords() {
		this.records = new ArrayList<MoveRecord>();
	}

	public void addRecord(int time, double x, double y) {
		if (this.lastClearTime < 0) {
			return;
		}
		int id = this.getId(time);
		if (id < 1 || id > 10) {
			return;
		}
		if (this.records.size() == 0) {
			this.records.add(new MoveRecord(time, x, y));
			return;
		}
		MoveRecord currRecord = this.records.get(this.records.size() - 1);
		int currId = this.getId(currRecord.time);
		if (id != currId) {
			this.records.add(new MoveRecord(time, x, y));
			return;
		}
		int score = this.getScore(id, time);
		int currScore = this.getScore(id, currRecord.time);
		if (score < currScore) {
			currRecord.time = time;
			currRecord.x = x;
			currRecord.y = y;
			return;
		}
	}

	public void clear(int time) {
		this.records.clear();
		this.lastClearTime = time;
	}

	private int getId(int time) {
		return (time - this.lastClearTime + 50) / 100;
	}

	private int getScore(int id, int time) {
		return Math.abs(time - this.lastClearTime - id * 100);
	}

}
