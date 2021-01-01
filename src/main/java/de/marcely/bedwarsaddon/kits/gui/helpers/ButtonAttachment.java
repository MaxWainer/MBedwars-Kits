package de.marcely.bedwarsaddon.kits.gui.helpers;

import de.marcely.bedwarsaddon.kits.BWKitAddon;
import de.marcely.bedwarsaddon.kits.gui.events.builtin.menu.MenuClickEvent;
import de.marcely.bedwarsaddon.kits.helpers.jutils.SpigotUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;
import java.util.function.Consumer;

public class ButtonAttachment {

    public static Consumer<MenuClickEvent> fromYaml(YamlConfiguration yml, String path) {
        if(yml.get(path) == null) return menuClickEvent -> menuClickEvent.setCancelled(true);
        Consumer<MenuClickEvent> to = (e) -> e.setCancelled(true);

        for (String e : yml.getConfigurationSection(path).getKeys(true)) {
            to.andThen(ev -> {
                switch (e) {
                    case "cmd":
                        if(yml.get(e + ".values") instanceof List<?>)
                            SpigotUtils.performCommands(ev.getClicker().asPlayer(), yml.getStringList(e + ".values"));
                        else
                            ev.getClicker().asPlayer().performCommand(yml.getString(e + ".values"));
                    case "msg":
                        if(yml.get(e + ".values") instanceof List<?>)
                            yml.getStringList(e + ".values").forEach(msg -> ev.getClicker().asUser().sendMessage(msg));
                        else
                            ev.getClicker().asUser().sendMessage(yml.getString(e + ".values"));
                    default:
                        BWKitAddon.getLogFactory().warn("Error while loading button " + path + " attaches! Unknown attach type: " + e);
                }
            });
        }

        return to;
    }

}
