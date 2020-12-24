package de.marcely.bedwarsaddon.kits.gui.helpers;

import de.marcely.bedwarsaddon.kits.BWKitAddon;
import de.marcely.bedwarsaddon.kits.builders.ItemBuilder;
import de.marcely.bedwarsaddon.kits.gui.Menu;
import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.*;

@Getter
public class MenuDrawer {

    private List<Button> buttons;
    private char freeSpace;
    private final Map<Integer, Button> outputMap = new LinkedHashMap<>();

    private List<String> rows = new ArrayList<>();

    public MenuDrawer row(String s) {
        rows.add(s);
        return this;
    }

    public MenuDrawer button(Button button) {
        buttons.add(button);
        return this;
    }

    public MenuDrawer buttons(Button... button) {
        buttons.addAll(Arrays.asList(button));
        return this;
    }

    public MenuDrawer freeSpace(char i) {
        this.freeSpace = i;
        return this;
    }

    public MenuDrawer fillMap() throws Exception {

        int rowNumber = 0;
        for (String row : rows) {
            char[] design = row.replace("\\s+", "").toCharArray();
            int slot = ( (rowNumber == 1 || rowNumber == 0) ? 0 : (rowNumber * 9) - 9 );
            buttons.add(Button.of(freeSpace, new ItemBuilder(Material.AIR), e -> e.setCancelled(true)));
            rowNumber++;
            if(design.length <= 9) {
                for (char c : design) {
                    if (row.length() == 0) {
                        throw new Exception("Error while loading gui!");
                    } else {
                        Button b = buttons.get(slot);
                        char i = b.getIcon();

                        Material mat = b.getBuilder().build().getType();
                        if (i == c) {
                            if(mat != Material.AIR) {
                                if(slot > rows.size() * 9) break;
                                else {
                                    outputMap.put(slot, b);
                                }
                            }
                        }
                        slot++;
                    }
                }
            }

        }

        return this;
    }

    public MenuDrawer drawInventory(Inventory inventory) {
        outputMap.forEach( (slot, button) -> {
            inventory.setItem(slot, button.getBuilder().build());
        } );

        return this;
    }

}
