package de.marcely.bedwarsaddon.kits.files;

import de.marcely.bedwarsaddon.kits.BWKitAddon;
import de.marcely.bedwarsaddon.kits.helpers.Message;
import lombok.SneakyThrows;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;

public enum Language {

    PREFIX(new Message()
        .line("&cBedWars-Kits &8»&7")
    ),
    PERMISSIONS_DENIED(new Message()
        .line("{prefix} &cYou doesn't have enough permissions to do this!")
    )
    ;

    private final Message msg;

    Language(Message msg) {
        this.msg = msg;
    }

    public void send(Player player) {
        Message.ofPath(format(name()), "locales")
                .replace("{prefix}", PREFIX.toString())
                .replace("{player}", player.getName())
                .send(player);
    }

    public String toString() {
        return String.join("", msg.getLines());
    }

    @SneakyThrows
    public static void loadLocales() {
        File file = new File(BWKitAddon.getInstance().getDataFolder(), "locales.yml");
        if(!file.exists()) {
            file.createNewFile();

            YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);

            Arrays.stream(values()).forEach(e -> {
                String path = format(e.name());

                if(e.msg.getLines().size() == 1)
                    yml.addDefault(path, e.msg.getLines().get(0));
                else
                    yml.addDefault(path, e.msg.getLines());

            });

            yml.options().copyDefaults(true);
            yml.save(file);
            yml.load(file);
        }
    }

    private static String format(String in) {
        return in.toLowerCase().replace("__", ".").replace("_", "-");
    }
}
