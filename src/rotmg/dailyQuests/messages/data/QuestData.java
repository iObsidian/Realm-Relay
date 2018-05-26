package rotmg.dailyQuests.messages.data;

import alde.flash.utils.IData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 100% match
 */
public class QuestData implements IData {

	public String id;

	public String name;

	public String description;

	public int[] requirements;

	public int[] rewards;

	public boolean completed;

	public int category;

	public QuestData() {
		this.requirements = new int[0];
		this.rewards = new int[0];
	}

	public void parseFromInput(DataInput in) throws IOException {
		this.id = in.readUTF();
		this.name = in.readUTF();
		this.description = in.readUTF();
		this.category = in.readInt();

		this.requirements = new int[in.readShort()];

		for (int i = 0; i < requirements.length; i++) {
			this.requirements[i] = in.readInt();
		}

		this.rewards = new int[in.readShort()];

		for (int j = 0; j < rewards.length; j++) {
			this.rewards[j] = in.readInt();
		}

		this.completed = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(this.id);
		out.writeUTF(this.name);
		out.writeUTF(this.description);
		out.writeInt(this.category);
		out.writeShort(this.requirements.length);
		for (int i : this.requirements) {
			out.writeInt(i);
		}

		out.writeShort(this.rewards.length);
		for (int i : this.rewards) {
			out.writeInt(i);
		}

		out.writeBoolean(this.completed);

	}

}
