package de.marcely.bedwarsaddon.kits.builders;

import de.marcely.bedwarsaddon.kits.checks.NonNuller;
import de.marcely.bedwarsaddon.kits.helpers.jutils.Pair;
import de.marcely.bedwarsaddon.kits.helpers.jutils.SpigotUtils;
import de.marcely.bedwarsaddon.kits.helpers.jutils.StringUtils;
import lombok.Getter;
import lombok.ToString;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.marcely.bedwarsaddon.kits.helpers.Colorizer.c;

@ToString
public class ItemBuilder {

    @Getter
    private final Material material;

    private String name;
    private short damage = 0;
    private int count = 1;
    private List<String> lore = new ArrayList<>();

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

    public ItemBuilder addLore(String... lore) {
        this.lore.addAll(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public ItemStack build() {
        Material inputMaterial =
                NonNuller.revert(
                        material,
                        Material.STONE
                );


        ItemStack stack = new ItemStack(inputMaterial, count, damage);
        ItemMeta meta = stack.getItemMeta();

        if(name != null)
            meta.setDisplayName(c(name));
        if(!lore.isEmpty())
            meta.setLore(c(lore));


        stack.setItemMeta(meta);
        return stack;
    }

    public String forceMaterialName() {
        return material.name();
    }

    public static ItemBuilder of(Material mat, int damage, int count, String name, String... lore) {
        return new ItemBuilder(mat)
                .damage(damage)
                .count(count)
                .name(name)
                .addLore(lore);
    }

    public static ItemBuilder of(Material mat, int damage, int count, String name, List<String> lore) {
        return new ItemBuilder(mat)
                .damage(damage)
                .count(count)
                .name(name)
                .lore(lore);
    }

    public static ItemBuilder of(Material mat) {
        return new ItemBuilder(mat);
    }

    public static ItemBuilder fromYaml(YamlConfiguration yml, String path) {
        Pair<String, Integer> inputMaterial = StringUtils.splitBy(yml.getString(path + ".item.stack"));
        return of(
                SpigotUtils.toMat(inputMaterial.getKey()),
                inputMaterial.getValue(), yml.getInt(path + ".item.count"),
                yml.getString(path + ".name"),
                yml.getStringList(path + ".lore")
        );
    }

}
