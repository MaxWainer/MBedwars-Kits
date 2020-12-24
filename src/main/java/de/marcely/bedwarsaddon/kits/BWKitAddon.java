package de.marcely.bedwarsaddon.kits;

import de.marcely.bedwarsaddon.kits.files.ConfigLoader;
import de.marcely.bedwarsaddon.kits.files.Language;
import de.marcely.bedwarsaddon.kits.helpers.LoggerFactory;
import de.marcely.bedwarsaddon.kits.storage.CachedStorage;
import de.marcely.bedwarsaddon.kits.storage.IDatabase;
import de.marcely.bedwarsaddon.kits.storage.types.MySQL;
import de.marcely.bedwarsaddon.kits.storage.types.SQLite;
import lombok.Getter;
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
        logFactory = new LoggerFactory(this);
        cachedStorage = new CachedStorage();

        loadFiles();

        loadDb();
    }

    @Override
    public void onDisable() {

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

}
