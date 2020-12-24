package de.marcely.bedwarsaddon.kits.helpers;

import de.marcely.bedwarsaddon.kits.BWKitAddon;
import org.bukkit.Bukkit;

import java.util.Arrays;

public class LoggerFactory {

    private BWKitAddon main = BWKitAddon.getInstance();

    private String def = "&3[" + main.getName() + "] &7";
    private String error = "&c[" + main.getName() + " Error] &7";
    private String debug = "&2[" + main.getName() + " Debug] &7";
    private String warn = "&6[" + main.getName() + " Warn] &7";

    public void log(String s) {
        printer(def + s);
    }

    public void log(String... s) {
        Arrays.stream(s).forEach(this::log);
    }

    public void debug(String s) {
        printer(debug + s);
    }

    public void debug(String... s) {
        Arrays.stream(s).forEach(this::debug);
    }

    public void error(String s) {
        printer(error + s);
    }

    public void error(String... s) {
        Arrays.stream(s).forEach(this::error);
    }

    public void error(Throwable s) {
        error(s.getMessage());
        Arrays.stream(s.getStackTrace()).forEach(e ->
                printer(" &c- " + e.toString())
        );
    }

    public void warn(String s) {
        printer(warn + s);
    }

    public void warn(String... s) {
        Arrays.stream(s).forEach(this::warn);
    }

    private void printer(String s) {
        Bukkit.getConsoleSender().sendMessage(Colorizer.c(s));
    }

}
