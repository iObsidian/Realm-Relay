package com.hurlant.crypto.symmetric;

import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.params.KeyParameter;


/**
 * Formelly known as ICipher
 */
public class ICipher {

	private final StreamCipher cipher;

	public ICipher(String key) {
		this(hexStringToBytes(key));
	}

	public ICipher(byte[] bytes) {
		this.cipher = new RC4Engine();
		KeyParameter keyParam = new KeyParameter(bytes);
		this.cipher.init(true, keyParam);
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

	/**
	 * Cipher bytes and update cipher
	 *
	 * @param bytes
	 */
	public void cipher(byte[] bytes) {
		this.cipher.processBytes(bytes, 0, bytes.length, bytes, 0);
	}

}