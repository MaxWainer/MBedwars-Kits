package de.marcely.bedwarsaddon.kits.command;

import de.marcely.bedwarsaddon.kits.BWKitAddon;
import de.marcely.bedwarsaddon.kits.gui.MenuConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class KitCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        new MenuConfig(new File(BWKitAddon.getInstance().getDataFolder(), "gui.yml"))
                .load().build().open(
                        (Player) commandSender
                );
        return false;
    }

}
