package rotmg.util;

import alde.flash.utils.Vector;

public class ConversionUtil {


	public static Vector<Integer> toIntVector(int[] array) {
		Vector<Integer> vector = new Vector<>();

		for (int i = 0; i < array.length; i++) {
			vector.add(array[i]);
		}

		return vector;
	}

	public static Vector<Integer> toIntVector(String data) {
		return toIntVector(data, ",");
	}

	private static Vector<Integer> toIntVector(String data, String delimiter) {

		String[] splitData = data.split(delimiter);

		Vector<Integer> ints = new Vector<Integer>(splitData.length);

		for (int i = 0; i < splitData.length; i++) {

			String d = splitData[i];

			d = d.replaceAll("[^\\d.]", "");

			try {
				ints.put(i, Integer.parseInt(d));
			} catch (Exception e) {
				System.err.println("Error while converting '" + data + "' to int vector. Data '" + data + "', : '" + splitData[i] + "' is not a valid int.");
			}
		}

		return ints;

	}

}
