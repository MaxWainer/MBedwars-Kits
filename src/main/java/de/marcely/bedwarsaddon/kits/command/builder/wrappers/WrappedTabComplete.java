package de.marcely.bedwarsaddon.kits.command.builder.wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter@AllArgsConstructor
public class WrappedTabComplete {

    private final CommandSender sender;
    private final String[] arg;

    public Player getSenderAsPlayer() {
        return (Player) sender;
    }
}
