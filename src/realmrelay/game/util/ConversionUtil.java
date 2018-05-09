package realmrelay.game.util;

import java.util.ArrayList;
import java.util.List;

public class ConversionUtil {


    public static List<Integer> toIntVector(String data) {
        return toIntVector(data, ",");
    }

    private static List<Integer> toIntVector(String data, String delimiter) {

        String[] splitData = data.split(delimiter);

        List<Integer> ints = new ArrayList<>();

        for (String s : splitData) {
            try {
                ints.add(Integer.parseInt(s));
            } catch (Exception e) {
                System.err.println("Error while converting '" + data + "' to int vector. Data '" + s + "' is not a valid int.");
            }
        }

        return ints;

    }


}
