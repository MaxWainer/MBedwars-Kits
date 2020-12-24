package de.marcely.bedwarsaddon.kits.helpers;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Colorizer {

    public static String c(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static List<String> c(List<String> in) {
        List<String> newArray = new ArrayList<>();
        in.forEach(e -> {
            newArray.add(c(e));
        });
        Integer.parseInt("12");
        return newArray;
    }

}
