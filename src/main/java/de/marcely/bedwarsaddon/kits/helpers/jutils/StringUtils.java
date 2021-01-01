package de.marcely.bedwarsaddon.kits.helpers.jutils;

public class StringUtils {

    public static String toEnum(String name) {
        return name.toUpperCase().replace(" ", "_");
    }

    public static Pair<String, Integer> splitBy(String input) {
        return new Pair<>(
                toEnum(input.split(":")[0]),
                Integer.parseInt(input.split(":")[1])
        );
    }

    public static String splitPath(String in, int i) {
        String[] out = in.split(":");
        if (out.length < i)
            return "null";
        else
            return out[i];
    }

    public static int intOf(String in) {
        try {
            return Integer.parseInt(in);
        } catch (NumberFormatException e) {
            return 0;
        }

    }

}
