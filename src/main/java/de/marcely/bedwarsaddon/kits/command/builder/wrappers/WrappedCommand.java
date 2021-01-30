package de.marcely.bedwarsaddon.kits.command.builder.wrappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter@AllArgsConstructor
public class WrappedCommand {

    private final CommandSender sender;
    private final String[] args;
    private final String label;

    public Player getSenderAsPlayer() {
        return (Player) sender;
    }
}
