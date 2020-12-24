package de.marcely.bedwarsaddon.kits.helpers;

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

}
