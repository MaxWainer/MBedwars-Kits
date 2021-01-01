package de.marcely.bedwarsaddon.kits.gui.events.builtin;

import de.marcely.bedwarsaddon.kits.gui.IMenuHolder;
import de.marcely.bedwarsaddon.kits.gui.Menu;
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
import org.bukkit.inventory.Inventory;

import static de.marcely.bedwarsaddon.kits.helpers.LoggerFactory.debugger;

public class PrivateListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onClick(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        if(e.getClickedInventory() == null) return;
        if (inv.getHolder() == null) return;
        if (inv.getHolder() instanceof IMenuHolder) {

            debugger("Handling default click event!");

            IMenuHolder menu = (IMenuHolder) inv.getHolder();
            Player player = (Player) e.getWhoClicked();

            e.setCancelled(true);

            if(e.getCurrentItem() == null) return;

            checkClick(
                    menu.getByPlayer(player),
                    menu.getDrawer().getBySlot(e.getSlot()),
                    e, new Clicker(player)
            );
        }


    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onOpen(InventoryOpenEvent e) {
        Inventory inv = e.getInventory();
        if(e.getInventory() == null) return;
        if (inv.getHolder() == null) return;
        if (inv.getHolder() instanceof IMenuHolder) {

            debugger("Handling default open event!");

            IMenuHolder menu = (IMenuHolder) inv.getHolder();
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
        Inventory inv = e.getInventory();
        if(e.getInventory() == null) return;
        if (inv.getHolder() == null) return;
        if (inv.getHolder() instanceof IMenuHolder) {

            debugger("Handling default close event!");

            IMenuHolder menu = (IMenuHolder) inv.getHolder();
            Player player = (Player) e.getPlayer();

            if(!(e.getPlayer() instanceof Player)) return;

            menu.getViewers().remove(player);

            checkClose(
                    menu.getByPlayer(player),
                    e
            );

        }


    }

    private void checkClick(Menu menu, Button button, InventoryClickEvent e, Clicker p) {
        debugger("Calling custom click event!");
        MenuClickEvent event = new MenuClickEvent(menu, button, e, p);
        Bukkit.getPluginManager().callEvent(event);
    }

    private void checkOpen(Menu menu, InventoryOpenEvent e) {
        debugger("Calling custom open event!");
        MenuOpenEvent event = new MenuOpenEvent(menu, e);
        Bukkit.getPluginManager().callEvent(event);
    }

    private void checkClose(Menu menu, InventoryCloseEvent e) {
        debugger("Calling custom close event!");
        MenuCloseEvent event = new MenuCloseEvent(menu, e);
        Bukkit.getPluginManager().callEvent(event);
    }

}
