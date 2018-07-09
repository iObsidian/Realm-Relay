package alde.flash.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * In the AS3 source, Message don't implement any interface.
 * Since they all have parseFromInput/write methods, I decided to make messages implement the IData interface (like Realm-Relay).
 */
public interface IData {

	void parseFromInput(DataInput in) throws IOException;

	void writeToOutput(DataOutput out) throws IOException;

}
