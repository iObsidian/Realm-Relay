package realmrelay.game.util;

public class ConversionUtil {

	public static int[] toIntVector(String data) {
		return toIntVector(data, ",");
	}

	private static int[] toIntVector(String data, String delimiter) {

		String[] splitData = data.split(delimiter);

		int[] ints = new int[splitData.length];

		for (int i = 0; i < splitData.length; i++) {
			try {
				ints[i] = (Integer.parseInt(splitData[i]));
			} catch (Exception e) {
				System.err.println("Error while converting '" + data + "' to int vector. Data '" + data + ", " + splitData[i] + "' is not a valid int.");
			}
		}

		return ints;

	}
}
