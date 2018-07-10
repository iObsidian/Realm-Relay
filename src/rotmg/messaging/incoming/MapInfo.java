package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MapInfo extends IncomingMessage {

	public int width;
	public int height;
	public String name;
	public String displayName;
	public int difficulty;
	public int fp;
	public int background;
	public boolean allowPlayerTeleport;
	public boolean showDisplays;
	public String[] clientXML;
	public String[] extraXML;

	public MapInfo(int id, MessageConsumer callback) {
		super(id, callback);
		this.clientXML = new String[0];
		this.extraXML = new String[0];
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.parseProperties(in);
		this.parseXML(in);
	}

	public void parseProperties(DataInput in) throws IOException {
		this.width = in.readInt();
		this.height = in.readInt();
		this.name = in.readUTF();
		this.displayName = in.readUTF();
		this.fp = in.readInt();
		this.background = in.readInt();
		this.difficulty = in.readInt();
		this.allowPlayerTeleport = in.readBoolean();
		this.showDisplays = in.readBoolean();
	}

	public void parseXML(DataInput in) throws IOException {
		this.clientXML = new String[in.readShort()];
		for (int i = 0; i < this.clientXML.length; i++) {
			byte[] utf = new byte[in.readInt()];
			in.readFully(utf);
			this.clientXML[i] = new String(utf, "UTF-8");
		}
		this.extraXML = new String[in.readShort()];
		for (int i = 0; i < this.extraXML.length; i++) {
			byte[] utf = new byte[in.readInt()];
			in.readFully(utf);
			this.extraXML[i] = new String(utf, "UTF-8");
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.width);
		out.writeInt(this.height);
		out.writeUTF(this.name);
		out.writeUTF(this.displayName);
		out.writeInt(this.fp);
		out.writeInt(this.background);
		out.writeInt(this.difficulty);
		out.writeBoolean(this.allowPlayerTeleport);
		out.writeBoolean(this.showDisplays);
		out.writeShort(this.clientXML.length);
		for (String xml : this.clientXML) {
			byte[] utf = xml.getBytes("UTF-8");
			out.writeInt(utf.length);
			out.write(utf);
		}
		out.writeShort(this.extraXML.length);
		for (String xml : this.extraXML) {
			byte[] utf = xml.getBytes("UTF-8");
			out.writeInt(utf.length);
			out.write(utf);
		}
	}
}
