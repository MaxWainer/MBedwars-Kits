package de.marcely.bedwarsaddon.kits.gui;

import de.marcely.bedwarsaddon.kits.BWKitAddon;
import de.marcely.bedwarsaddon.kits.builders.ItemBuilder;
import de.marcely.bedwarsaddon.kits.files.ConfigLoader;
import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import de.marcely.bedwarsaddon.kits.gui.events.builtin.Clicker;
import de.marcely.bedwarsaddon.kits.gui.helpers.MenuDrawer;
import de.marcely.bedwarsaddon.kits.gui.helpers.SizeController;
import de.marcely.bedwarsaddon.kits.helpers.Colorizer;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

import static de.marcely.bedwarsaddon.kits.helpers.LoggerFactory.debugger;

@Getter@ToString
public class Menu implements IMenuHolder {

    private MenuDrawer drawer;

    private List<Player> viewers = new ArrayList<>();

    private Menu parent;

    private String title = "MBedWars - Kits";

    private Inventory inventory;

    public Menu drawer(MenuDrawer drawer) {
        debugger("Loading drawer: " + drawer);
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
            SizeController.isAllowed(drawer.getRows().size());
            drawer.fillMap(getInventory());

            /**drawer.getOutputMap().forEach( (slot, button) -> {
                debugger("Loading button in " + slot + ": " + button);
                inventory.setItem(slot, button.getBuilder().build());
            } );
            Arrays.stream(inventory.getContents()).forEach(e -> {
                if(e != null) debugger("inv: Mat:" + e.getType().name());
            });**/
        } catch (Exception e) {
            BWKitAddon.getLogFactory().error(e);
            return errorMenu();
        }

        return this;
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    public static Menu errorMenu() {
        return new Menu()
                .drawer(new MenuDrawer()
                        .row("@@@@@@@@@")
                        .row("@@@@E@@@@")
                        .row("@@@@@@@@@")
                        .freeSpace('@')
                        .button('E',
                                new Button()
                                    .stack(ItemBuilder.of(
                                            Material.STONE,
                                            0, 1, "&cError while loading gui!",
                                            "&7Please, check console", "&7for any errors."
                                    ))
                                    .action(e -> {
                                        Clicker clicker = e.getClicker();
                                        clicker.sendMessage(
                                                "&cError while loading this gui! Please, contact server administrator(-s)!"
                                        );
                                        if (clicker.asPlayer().isOp())
                                            clicker.sendMessage(
                                                    "&cIf you are server administrator, please check console for any errors.",
                                                    "&cIf this problem on author's site, please contact plugin developer(-s)."
                                            );
                                    })
                        )
                )
                .title("&cError")
                .build()
                ;
    }

    @Override
    public Menu getByPlayer(Player player) {
        if(getViewers().contains(player))
            return this;
        else
            return null;
    }

}
