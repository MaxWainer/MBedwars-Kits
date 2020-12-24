package de.marcely.bedwarsaddon.kits;

import de.marcely.bedwarsaddon.kits.files.ConfigLoader;
import de.marcely.bedwarsaddon.kits.files.Language;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class BWKitAddon extends JavaPlugin {

    @Getter
    private static BWKitAddon instance;

    @Override
    public void onEnable() {
        instance = this;
        loadFiles();
    }

    @Override
    public void onDisable() {

    }

    private void loadFiles() {
        Language.loadLocales();
        ConfigLoader.loadConfigs("config", "gui", "kits");
    }

}
