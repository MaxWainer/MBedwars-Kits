package de.marcely.bedwarsaddon.kits.helpers;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Map;

public class User {

    private final Player player;

    public User(Player player) {
        this.player = player;
    }

    public User sendMessage(String s) {
        new Message().line(s).send(player);
        return this;
    }

    public User sendMessage(String... s) {
        Arrays.stream(s).forEach(this::sendMessage);
        return this;
    }

    public User sendMessage(Map<String, Object> placeholder, String s) {
        Message msg = new Message();
        msg.line(s);
        placeholder.forEach( (key, value) ->
            msg.replace(key, value.toString())
        );
        return this;
    }

    public User sendMessage(Map<String, Object> placeholder, String... s) {
        Arrays.stream(s).forEach(e ->
            sendMessage(placeholder, e)
        );
        return this;
    }

}
