package realmrelay.crypto;

import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;

public class RC4 {
	
	private final StreamCipher rc4;
	
	public RC4(String key) {
		this(hexStringToBytes(key));
	}
	
	public RC4(byte[] bytes) {
		this.rc4 = new RC4Engine();
		KeyParameter keyParam = new KeyParameter(bytes);
		this.rc4.init(true, keyParam);
	}
	
	/**
	 * Cipher bytes and update cipher
	 * 
	 * @param bytes
	 */
	public void cipher(byte[] bytes) {
		this.rc4.processBytes(bytes, 0, bytes.length, bytes, 0);
	}
	
	private static byte[] hexStringToBytes(String key) {
		if (key.length() % 2 != 0) {
			throw new IllegalArgumentException("invalid hex string");
		}
		byte[] bytes = new byte[key.length() / 2];
		char[] c = key.toCharArray();
		for (int i = 0; i < c.length; i += 2) {
			StringBuilder sb = new StringBuilder(2).append(c[i]).append(c[(i + 1)]);
			int j = Integer.parseInt(sb.toString(), 16);
			bytes[(i / 2)] = (byte) j;
		}
		return bytes;
	}
	
}
