package de.marcely.bedwarsaddon.kits.gui;

import de.marcely.bedwarsaddon.kits.BWKitAddon;
import de.marcely.bedwarsaddon.kits.builders.ItemBuilder;
import de.marcely.bedwarsaddon.kits.files.ConfigLoader;
import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import de.marcely.bedwarsaddon.kits.gui.helpers.MenuDrawer;
import de.marcely.bedwarsaddon.kits.helpers.Colorizer;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

@Getter
public class Menu implements IMenuHolder {

    private MenuDrawer drawer;

    private List<Button> buttons = drawer.getButtons();
    private List<Player> viewers;

    private Menu parent;

    private String title = "MBedWars - Kits";

    private Map<Integer, Button> buttonMap = drawer.getOutputMap();

    private Inventory inventory;

    public Menu drawer(MenuDrawer drawer) {
        this.drawer = drawer;
        return this;
    }

    public Menu parent(Menu menu) {
        this.parent = menu;
        return this;
    }

    public Menu title(String s) {
        title = s;
        return this;
    }

    public Menu build() {
        inventory = Bukkit.createInventory(
                this,
                drawer.getRows().size() * 9,
                Colorizer.c(title)
        );

        try {
            drawer.fillMap()
                    .drawInventory(inventory);
        } catch (Exception e) {
            BWKitAddon.getLogFactory().error(e);
            return errorMenu();
        }

        return this;
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public static Menu of(String config) {
        YamlConfiguration yml = ConfigLoader.fromFile(config);
        return null;
    }

    public static Menu errorMenu() {
        return new Menu()
                .drawer(new MenuDrawer()
                        .row("@@@@@@@@@")
                        .row("@@@@E@@@@")
                        .row("@@@@@@@@@")
                        .freeSpace('@')
                        .buttons(
                                new Button().symbol('E')
                                        .stack(new ItemBuilder(Material.STONE)
                                                .damage(15).name("&cError while building this gui!")
                                                .addLore("&7Please, check config for")
                                                .addLore("&7any mistakes!")
                                        ).action(e -> e.setCancelled(true))
                        )
                )
                .title("&cError")
                .build()
                ;
    }

    @Override
    public Button getBySlot(int i) {
        return buttonMap.get(i);
    }

    @Override
    public Menu getByPlayer(Player player) {
        if(getViewers().contains(player))
            return this;
        else
            return null;
    }
}
