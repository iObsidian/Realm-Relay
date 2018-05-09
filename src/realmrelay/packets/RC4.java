/**package realmrelay.packets;

public class RC4 {

	private static byte[] hexStringToBytes(String key) {

		if (key.length() % 2 != 0) {
			throw new IllegalArgumentException("invalid hex string");
		}
		byte[] arrayOfByte = new byte[key.length() / 2];
		char[] c = key.toCharArray();
		for (int i = 0; i < c.length; i += 2) {
			StringBuilder localStringBuilder = new StringBuilder(2).append(c[i]).append(c[i + 1]);
			int j = Integer.parseInt(localStringBuilder.toString(), 16);
			arrayOfByte[i / 2] = (byte) j;
		}
		return arrayOfByte;
	}

	private byte[] a;
	private int b;

	private int c;


	private RC4(byte[] bytes) {

		a = new byte[256];
		for (int i = 0; i < a.length; i++) {
			a[i] = (byte) i;
		}
		b = 0;
		c = 0;
		int i = 0;
		int j = 0;
		if (bytes.length == 0) {
			throw new IllegalArgumentException("invalid rc4 key");
		}
		for (int k = 0; k < a.length; k++) {
			j = (bytes[i] & 0xFF) + (a[k] & 0xFF) + j & 0xFF;
			int l = a[k];
			a[k] = a[j];
			a[j] = (byte) l;
			i = (i + 1) % bytes.length;
		}
	}


	public RC4(String key) {

		this(RC4.hexStringToBytes(key));
	}


	public void cipher(byte[] bytes) {

		for (int i = 0; i < bytes.length; i++) {
			b = b + 1 & 0xFF;
			c = (a[b] & 0xFF) + c & 0xFF;
			int j = a[b];
			a[b] = a[c];
			a[c] = (byte) j;
			j = (a[b] & 0xFF) + (a[c] & 0xFF) & 0xFF;
			bytes[i] = (byte) (bytes[i] ^ a[j]);
		}
	}

}
*/