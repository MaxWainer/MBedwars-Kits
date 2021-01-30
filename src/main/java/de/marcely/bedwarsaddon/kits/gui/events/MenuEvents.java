package de.marcely.bedwarsaddon.kits.gui.events;

import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import de.marcely.bedwarsaddon.kits.gui.events.builtin.menu.MenuClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MenuEvents implements Listener {

    @EventHandler
    public void onMenuClick(MenuClickEvent e) {
        Button b = e.getClickedButton();
        if (b != null)
            if (b.getAction() != null)
                e.getClickedButton().getAction().accept(e);
    }

}
