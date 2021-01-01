package de.marcely.bedwarsaddon.kits.helpers.jutils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class SpigotUtils {
    
    public static Material toMat(String in) {
        return Material.valueOf(
                in.toUpperCase().replace(" ", "_")
        );
    }

    public static void performCommands(Player player, List<String> cmds) {
        cmds.forEach(player::performCommand);
    }
    
}
