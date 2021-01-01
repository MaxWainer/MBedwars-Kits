package de.marcely.bedwarsaddon.kits.gui.buttons;

import de.marcely.bedwarsaddon.kits.builders.ItemBuilder;
import de.marcely.bedwarsaddon.kits.gui.events.builtin.menu.MenuClickEvent;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Getter@ToString
public class Button {

    @ToString.Exclude private Consumer<MenuClickEvent> action = (e) -> e.setCancelled(true);
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

    public static List<Button> generate(int count, char icon, String name, Material mat) {
        List<Button> returnable = new ArrayList<>();
        for (int i = 0; i >= count; i++) {
            final int fi = i;
            returnable.add(
                    of(icon, ItemBuilder.of(
                                        mat, 0, 1, name + "&r &c&l#" + i
                            ),
                            e -> e.getClicker().asUser().sendMessage("You are clicked on button #" + fi)
                    )
            );
        }
        return returnable;
    }



}
