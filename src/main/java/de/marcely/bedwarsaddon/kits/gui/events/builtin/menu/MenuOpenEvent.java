package de.marcely.bedwarsaddon.kits.gui.events.builtin.menu;

import de.marcely.bedwarsaddon.kits.gui.Menu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

@Getter
public final class MenuOpenEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final Menu menu;
    private final InventoryOpenEvent e;

    public MenuOpenEvent(Menu menu, InventoryOpenEvent e) {
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
