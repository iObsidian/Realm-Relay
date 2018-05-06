package realmrelay.packets;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

class ByteArrayDataInput implements DataInput {

    private ByteBuffer buffer;

    public ByteArrayDataInput(byte[] src) {
        if (src == null) {
            throw new NullPointerException("SRC is null. (On ByteArrayDataInput.)");
        }
        buffer = ByteBuffer.wrap(src);
    }

    @Override
    public boolean readBoolean() throws EOFException {
        try {
            int value = buffer.get();
            return value > 0;
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public byte readByte() throws EOFException {
        try {
            return buffer.get();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public char readChar() throws EOFException {
        try {
            return buffer.getChar();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override
    public void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
        try {
            buffer.get(b, 0, len);
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int readInt() throws EOFException {
        try {
            return buffer.getInt();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public String readLine() throws IOException {
        throw new UnsupportedOperationException("don't you ever let me catch you using this again");
    }

    @Override
    public long readLong() throws EOFException {
        try {
            return buffer.getLong();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public short readShort() throws EOFException {
        try {
            return buffer.getShort();
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public int readUnsignedByte() throws EOFException {
        try {
            int value = buffer.get();
            return value & 0xFF;
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public int readUnsignedShort() throws EOFException {
        try {
            int value = buffer.getShort();
            return value & 0xFFFF;
        } catch (BufferUnderflowException e) {
            throw new EOFException();
        }
    }

    @Override
    public String readUTF() throws IOException {
        return DataInputStream.readUTF(this);
    }

    public int size() {
        return buffer.remaining();
    }

    @Override
    public int skipBytes(int n) throws EOFException {
        buffer.position(buffer.position() + n);
        return n;
    }

}
