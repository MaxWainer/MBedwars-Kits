package de.marcely.bedwarsaddon.kits;

import de.marcely.bedwarsaddon.kits.files.ConfigLoader;
import de.marcely.bedwarsaddon.kits.files.Language;
import de.marcely.bedwarsaddon.kits.helpers.LoggerFactory;
import de.marcely.bedwarsaddon.kits.storage.CachedStorage;
import de.marcely.bedwarsaddon.kits.storage.IDatabase;
import de.marcely.bedwarsaddon.kits.storage.types.MySQL;
import de.marcely.bedwarsaddon.kits.storage.types.SQLite;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BWKitAddon extends JavaPlugin {

    @Getter
    private static BWKitAddon instance;
    @Getter
    private static LoggerFactory logFactory;
    @Getter
    private static CachedStorage cachedStorage;
    @Getter
    private static IDatabase dbProvider;

    @Override
    public void onEnable() {

        instance = this;
        logFactory = new LoggerFactory();

        getLogFactory().log("Loading plugin &5" + getName() + "&7...");
        loadComponents();
        getLogFactory().log("Plugin successfully loaded!");
    }

    @Override
    public void onDisable() {
        getLogFactory().log("Disabling plugin...");
        getCachedStorage().getKitMap().clear();
        getLogFactory().log("Plugin successfully disabled!");
    }

    private void loadComponents() {
        try {
            checkDependency("MBedwars");
            cachedStorage = new CachedStorage();
            loadFiles();
            loadDb();
        } catch (Exception e) {
            getLogFactory().error(e);
        }
    }

    private void loadFiles() {
        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        Language.loadLocales();
        ConfigLoader.loadConfigs("config", "gui", "kits");
    }

    private void loadDb() {
        boolean bool = ConfigLoader.fromFile("config").getBoolean("storage.use-mysql");
        if (bool)
            dbProvider = new MySQL();
        else
            dbProvider = new SQLite();

        getLogFactory().log(
                "Using " + (bool ? "&3MySQL" : "&6SQLite") + "&7 as storage provider!"
        );
    }

    private void checkDependency(String name) throws Exception {
        boolean pluginState = (Bukkit.getPluginManager().getPlugin(name) != null);
        if(!pluginState) {
            Bukkit.getPluginManager().disablePlugin(this);
            throw new Exception("Error while enabling plugin &6" + getName() + "&7, can't found needed plugin &4" + name + "&7!");
        } else
            getLogFactory().log("Plugin successfully hooked into &5" + name + "&7!");
    }

}
