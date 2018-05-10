package realmrelay.packets.data.unused;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public interface IData {

    void parseFromInput(DataInput in) throws IOException;

    void writeToOutput(DataOutput out) throws IOException;

}