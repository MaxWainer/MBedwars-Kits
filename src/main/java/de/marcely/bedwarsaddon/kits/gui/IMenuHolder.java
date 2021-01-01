package de.marcely.bedwarsaddon.kits.gui;

import de.marcely.bedwarsaddon.kits.gui.helpers.MenuDrawer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public interface IMenuHolder extends InventoryHolder {

    List<Player> getViewers();
    Menu getByPlayer(Player player);
    Menu getParent();
    MenuDrawer getDrawer();

}
