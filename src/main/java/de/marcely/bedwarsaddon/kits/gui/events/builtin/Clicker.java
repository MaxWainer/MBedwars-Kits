package de.marcely.bedwarsaddon.kits.gui.events.builtin;

import de.marcely.bedwarsaddon.kits.helpers.User;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

public class Clicker {

    private final Player player;

    public Clicker(Player player) {
        this.player = player;
    }

    public Player asPlayer() {
        return player;
    }

    public User asUser() {
        return new User(player);
    }

    public void sendMessage(String... s) {
        asUser().sendMessage(s);
    }

}
