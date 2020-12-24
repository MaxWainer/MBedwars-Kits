package de.marcely.bedwarsaddon.kits.gui.events.builtin;

import de.marcely.bedwarsaddon.kits.gui.IMenuHolder;
import de.marcely.bedwarsaddon.kits.gui.Menu;
import de.marcely.bedwarsaddon.kits.gui.PagedMenu;
import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import de.marcely.bedwarsaddon.kits.gui.events.builtin.menu.MenuClickEvent;
import de.marcely.bedwarsaddon.kits.gui.events.builtin.menu.MenuCloseEvent;
import de.marcely.bedwarsaddon.kits.gui.events.builtin.menu.MenuOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public class PrivateListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {
        InventoryHolder holder = e.getClickedInventory().getHolder();
        if(e.getClickedInventory() == null) return;
        if (holder == null) return;
        if (holder instanceof IMenuHolder) {

            IMenuHolder menu = (IMenuHolder) holder;
            Player player = (Player) e.getWhoClicked();

            e.setCancelled(true);

            if(e.getCurrentItem() == null) return;

            checkClick(
                    menu.getByPlayer(player),
                    menu.getBySlot(e.getSlot()),
                    e
            );
        }


    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onOpen(InventoryOpenEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if(e.getInventory() == null) return;
        if (holder == null) return;
        if (holder instanceof IMenuHolder) {

            IMenuHolder menu = (IMenuHolder) holder;
            Player player = (Player) e.getPlayer();

            if(!(e.getPlayer() instanceof Player)) return;

            menu.getViewers().add(player);

            checkOpen(
                    menu.getByPlayer(player),
                    e
            );

        }


    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onOpen(InventoryCloseEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if(e.getInventory() == null) return;
        if (holder == null) return;
        if (holder instanceof IMenuHolder) {

            IMenuHolder menu = (IMenuHolder) holder;
            Player player = (Player) e.getPlayer();

            if(!(e.getPlayer() instanceof Player)) return;

            menu.getViewers().remove(player);

            checkClose(
                    menu.getByPlayer(player),
                    e
            );

        }


    }

    private void checkClick(Menu menu, Button button, InventoryClickEvent e) {
        MenuClickEvent event = new MenuClickEvent(menu, button, e);
        Bukkit.getPluginManager().callEvent(event);
    }

    private void checkOpen(Menu menu, InventoryOpenEvent e) {
        MenuOpenEvent event = new MenuOpenEvent(menu, e);
        Bukkit.getPluginManager().callEvent(event);
    }

    private void checkClose(Menu menu, InventoryCloseEvent e) {
        MenuCloseEvent event = new MenuCloseEvent(menu, e);
        Bukkit.getPluginManager().callEvent(event);
    }

}
