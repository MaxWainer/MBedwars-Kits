package de.marcely.bedwarsaddon.kits.gui.events.builtin.menu;

import de.marcely.bedwarsaddon.kits.gui.Menu;
import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;

@Getter
public final class MenuClickEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Menu menu;
    private final Button clickedButton;
    private final InventoryClickEvent e;

    public MenuClickEvent(Menu menu, Button button, InventoryClickEvent e) {
        this.menu = menu;
        this.clickedButton = button;
        this.e = e;
    }

    @Setter
    private boolean cancelled;

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
