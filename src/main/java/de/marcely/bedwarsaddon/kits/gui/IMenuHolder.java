package de.marcely.bedwarsaddon.kits.gui;

import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;

import java.util.List;

public interface IMenuHolder extends InventoryHolder {

    List<Button> getButtons();
    List<Player> getViewers();
    Button getBySlot(int i);
    Menu getByPlayer(Player player);
    Menu getParent();

}
