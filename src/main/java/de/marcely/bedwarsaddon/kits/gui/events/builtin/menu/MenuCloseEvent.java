package de.marcely.bedwarsaddon.kits.gui.events.builtin.menu;

import de.marcely.bedwarsaddon.kits.gui.Menu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryCloseEvent;

@Getter
public final class MenuCloseEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Menu menu;
    private final InventoryCloseEvent e;

    public MenuCloseEvent(Menu menu, InventoryCloseEvent e) {
        this.menu = menu;
        this.e = e;
    }

    @Setter
    private boolean cancelled;

    @Override
    public HandlerList getHandlers() {
        return null;
    }
}
