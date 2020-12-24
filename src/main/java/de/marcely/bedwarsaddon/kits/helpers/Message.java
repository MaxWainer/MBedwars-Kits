package de.marcely.bedwarsaddon.kits.helpers;

import de.marcely.bedwarsaddon.kits.files.ConfigLoader;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Message {

    @Getter
    private List<String> lines = new ArrayList<>();

    public Message line(String s) {
        lines.add(s);
        return this;
    }

    public Message lines(String... s) {
        lines.addAll(Arrays.asList(s));
        return this;
    }

    public Message lines(List<String> s) {
        lines.addAll(s);
        return this;
    }

    public void send(Player player) {
        lines.forEach(e -> {
            player.sendMessage(Colorizer.c(e));
        });
    }

    public Message replace(String oldVal, String newVal) {
        List<String> newLines = new ArrayList<>();

        lines.forEach(e -> {
            newLines.add(e.replace(oldVal, newVal));
        });


        lines = newLines;
        return this;
    }

    public void send(CommandSender player) {
        lines.forEach(e -> {
            player.sendMessage(Colorizer.c(e));
        });
    }

    public static Message ofPath(String path, String fileName) {
        YamlConfiguration cfg = ConfigLoader.fromFile(fileName);

        if (cfg.get(path) instanceof List<?>)
            return new Message().lines(cfg.getStringList(path));
        else
            return new Message().line(cfg.getString(path));
    }

}
