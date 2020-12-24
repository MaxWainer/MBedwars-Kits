package de.marcely.bedwarsaddon.kits.files;

import de.marcely.bedwarsaddon.kits.BWKitAddon;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Arrays;

public class ConfigLoader {

    public static YamlConfiguration fromFile(String name) {
        return YamlConfiguration.
                loadConfiguration(
                        new File(BWKitAddon.getInstance().getDataFolder() + "/" + name + ".yml"
                        )
                );
    }

    @SneakyThrows
    private static void loadConfig(String name) {
        File file = new File(BWKitAddon.getInstance().getDataFolder(), name + ".yml");
        if(!file.exists()){
            file.getParentFile().mkdirs();
            BWKitAddon.getInstance().saveResource(name + ".yml", false);
        }
        FileConfiguration fileConfiguration = new YamlConfiguration();
        fileConfiguration.load(file);
    }

    public static void loadConfigs(String... names) {
        Arrays.stream(names).forEach(ConfigLoader::loadConfig);
    }

}
