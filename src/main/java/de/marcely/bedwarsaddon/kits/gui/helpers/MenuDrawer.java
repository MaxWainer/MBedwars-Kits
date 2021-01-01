package de.marcely.bedwarsaddon.kits.gui.helpers;

import de.marcely.bedwarsaddon.kits.builders.ItemBuilder;
import de.marcely.bedwarsaddon.kits.gui.buttons.Button;
import de.marcely.bedwarsaddon.kits.helpers.jutils.Pair;
import lombok.Getter;
import lombok.ToString;
import net.minecraft.server.v1_12_R1.SystemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.*;
import java.util.stream.Collectors;

import static de.marcely.bedwarsaddon.kits.helpers.LoggerFactory.debugger;

@ToString
public class MenuDrawer {

    @Getter private final List<Button> buttons = new ArrayList<>();

    private char freeSpace;
    @Getter private final Map<Integer, Button> outputMap = new LinkedHashMap<>();
    @Getter private final Map<Character, Button> buttonMap = new LinkedHashMap<>();

    @Getter private List<String> rows = new ArrayList<>();

    public MenuDrawer row(String s) {
        rows.add(s);
        return this;
    }

    public MenuDrawer setRows(String... s) {
        rows = Arrays.stream(s).collect(Collectors.toList());
        return this;
    }

    public MenuDrawer setRows(List<String> s) {
        rows = s;
        return this;
    }

    public MenuDrawer button(char icon, Button button) {
        buttons.add(button.symbol(icon));
        buttonMap.put(icon, button);
        return this;
    }

    public MenuDrawer button(Button button) {
        button(button.getIcon(), button);
        return this;
    }

    public MenuDrawer buttons(Button... button) {
        Arrays.asList(button).forEach(e -> button(e.getIcon(), e));
        return this;
    }

    public MenuDrawer buttons(List<Button> button) {
        button.forEach(e -> button(e.getIcon(), e));
        return this;
    }

    public MenuDrawer backButton(Button button) {
        button(button.getIcon(), button.action(e ->
                e.getMenu().getParent().open(e.getClicker().asPlayer())
        ));
        return this;
    }

    public MenuDrawer buttons(Pair<Character, Button>... button) {
        Arrays.stream(button).forEach(e ->
            button(e.getKey(), e.getValue())
        );
        return this;
    }

    public MenuDrawer freeSpace(char i) {
        this.freeSpace = i;
        button(i, Button.of(freeSpace, new ItemBuilder(Material.AIR), e -> e.setCancelled(true)));
        return this;
    }

    public MenuDrawer fillMap(Inventory inv) throws Exception {
        int row = 0;
        for (String design : rows) {
            String input = design.replace("\\s+", "");
            row++;
            int slot = row == 1 ? 0 : (row * 9) - 9;
            for (char ch : input.toCharArray()) {
                Button b = getByChar(ch);
                debugger("Button: " + b);
                if(slot == SizeController.isAllowed(rows.size() * 9)) break;
                if(b.getBuilder().getMaterial() == Material.AIR) continue;
                else {
                    debugger("Loading item in slot: " + slot + "(" + b.getBuilder().forceMaterialName() + "), for row: " + row + "(" + design + ")");
                    inv.setItem(slot, b.getBuilder().build());
                    outputMap.put(slot, b);
                }
                slot++;
            }
        }

        return this;
    }

    public Button getBySlot(int i) {
        return outputMap.get(i);
    }

    public Button getByChar(char i) {
        return buttonMap.get(i);
    }

    /**private Map<Integer, Button> loadSpecificRow(int row) throws Exception {
        Map<Integer, Button> returnable = new LinkedHashMap<>();

        if (row > rows.size()) throw new Exception("Error while loading row #" + row + "! Reason: Unspecified");

        int slot = (row == 0 || row == 1) ? 0 : (row * 9) - 9;
        for(char c : rows.get(row).replace("\\s+", "").toCharArray()) {



        }

        return returnable;
    }**/

}
