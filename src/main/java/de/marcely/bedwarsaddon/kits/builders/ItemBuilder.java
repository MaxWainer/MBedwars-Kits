package de.marcely.bedwarsaddon.kits.builders;

import de.marcely.bedwarsaddon.libs.XMaterial;
import lombok.ToString;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static de.marcely.bedwarsaddon.kits.helpers.Colorizer.c;

@ToString
public class ItemBuilder {

    private final Material material;

    private String name;
    private short damage;
    private int count;
    private List<String> lore;

    public ItemBuilder(Material material) {
        this.material = material;
    }

    public ItemBuilder damage(int damage) {
        this.damage = (short) damage;
        return this;
    }

    public ItemBuilder count(int count) {
        this.count = count;
        return this;
    }

    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder addLore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStack build() {
        ItemStack stack = new ItemStack(XMaterial.matchXMaterial(material).parseMaterial(), count, damage);
        ItemMeta meta = stack.getItemMeta();

        if(name != null)
            meta.setDisplayName(c(name));
        if(!lore.isEmpty())
            meta.setLore(c(lore));


        stack.setItemMeta(meta);
        return stack;
    }

}
