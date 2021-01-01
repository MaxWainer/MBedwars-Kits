package de.marcely.bedwarsaddon.kits.builders;

import de.marcely.bedwarsaddon.kits.files.ConfigLoader;
import de.marcely.bedwarsaddon.kits.helpers.jutils.Pair;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static de.marcely.bedwarsaddon.kits.helpers.jutils.StringUtils.splitBy;
import static de.marcely.bedwarsaddon.kits.helpers.jutils.StringUtils.toEnum;

public class BWKit {

    @Getter
    private String name;

    private YamlConfiguration yml = ConfigLoader.fromFile("kits");

    public BWKit(String name) {
        this.name = name;
    }


    private String defaultPath = "kits." + name + ".";

    public List<ItemStack> loadItems() {
        List<ItemStack> stacks = new ArrayList<>();

        yml.getConfigurationSection(defaultPath + "items").getKeys(false).forEach(e -> {
            String path = defaultPath + "items." + e + ".";

            stacks.add(new ItemBuilder(
                    Material.valueOf(splitBy(yml.getString(path + "item.stack")).getKey())
            ).damage(splitBy(yml.getString(path + "item.stack")).getValue())
                    .name(yml.getString(yml.getString(path + "name")))
                    .lore(yml.getStringList(path + "lore"))
                    .count(yml.getInt(path + "count"))
                    .build());
        });

        return stacks;
    }

    public String getCustomName() {
        return yml.getString(defaultPath + "custom-name");
    }

    public List<String> getDescription() {
        return yml.getStringList(defaultPath + "description");
    }

    public long getPriceVal() {
        return yml.getLong(defaultPath + "price.value");
    }

    public PriceType getPriceType() {
        return PriceType.of(yml.getString(defaultPath + "price.type"));
    }

    public Pair<PriceType, Long> getPrice() {
        return new Pair<>(getPriceType(), getPriceVal());
    }

    public enum PriceType {
        VAULT, TOKEN_ENCHANT, TOKEN_MANAGER;

        public static PriceType of(String in) {
            return PriceType.valueOf(toEnum(in));
        }

    }

}
