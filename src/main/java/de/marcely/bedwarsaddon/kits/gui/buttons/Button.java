package de.marcely.bedwarsaddon.kits.gui.buttons;

import de.marcely.bedwarsaddon.kits.builders.ItemBuilder;
import de.marcely.bedwarsaddon.kits.gui.events.builtin.menu.MenuClickEvent;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

@Getter@ToString
public class Button {

    private Consumer<MenuClickEvent> action = (e) -> e.setCancelled(true);
    private ItemBuilder builder;
    private char icon;

    public Button symbol(char icon) {
        this.icon = icon;
        return this;
    }

    public Button action(Consumer<MenuClickEvent> action) {
         this.action = action;
        return this;
    }

    public Button stack(ItemBuilder builder) {
        this.builder = builder;
        return this;
    }

    public static Button of(char icon, ItemBuilder builder, Consumer<MenuClickEvent> action) {
        return new Button()
                .symbol(icon)
                .stack(builder)
                .action(action);
    }



}
