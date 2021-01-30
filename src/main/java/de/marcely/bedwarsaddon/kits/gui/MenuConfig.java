package de.marcely.bedwarsaddon.kits.gui;

import de.marcely.bedwarsaddon.kits.builders.ItemBuilder;
import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import de.marcely.bedwarsaddon.kits.gui.helpers.ButtonAttachment;
import de.marcely.bedwarsaddon.kits.gui.helpers.MenuDrawer;
import de.marcely.bedwarsaddon.kits.helpers.jutils.CharUtils;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static de.marcely.bedwarsaddon.kits.helpers.LoggerFactory.debugger;

@Getter
public class MenuConfig {

    private final File path;

    public MenuConfig(File path) {
        this.path = path;
    }

    private YamlConfiguration yml() {
        return YamlConfiguration.loadConfiguration(path);
    }

    public Menu load() {
        return new Menu()
                .title(yml().getString("title"))
                .drawer(loadDrawer());
    }

    private MenuDrawer loadDrawer() {
        debugger("Rows: " + yml().getStringList("layout"));
        debugger("Empty char: " + CharUtils.fromString(yml().getString("empty-char")));
        return new MenuDrawer()
                .setRows(yml().getStringList("layout"))
                .freeSpace(CharUtils.fromString(yml().getString("empty-char")))
                .buttons(loadButtons());
    }

    private List<Button> loadButtons() {
        List<Button> buttons = new ArrayList<>();

        yml().getConfigurationSection("items").getKeys(false).forEach(e -> {
            Button b = Button.of(
                    CharUtils.fromString(e),
                    ItemBuilder.fromYaml(yml(), "items." + e),
                    ButtonAttachment.fromYaml(yml(), "attaches." + e)
            );

            buttons.add(
                    b
            );

            debugger("Loading button: " + b);
        });

        return buttons;
    }

}
