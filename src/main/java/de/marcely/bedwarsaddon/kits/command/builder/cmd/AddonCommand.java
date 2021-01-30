package de.marcely.bedwarsaddon.kits.command.builder.cmd;

import de.marcely.bedwars.api.BedwarsAPI;
import de.marcely.bedwars.api.command.CommandsCollection;
import de.marcely.bedwarsaddon.kits.command.builder.wrappers.WrappedCommand;
import de.marcely.bedwarsaddon.kits.command.builder.wrappers.WrappedTabComplete;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter@Setter
public abstract class AddonCommand {

    private Consumer<WrappedCommand> command;
    private Consumer<WrappedCommand> helpCommand;
    private Function<WrappedTabComplete, List<String>> complete;
    private String name;
    private String[] aliases;
    private String permission;
    private boolean playerOnly = true;
    private CommandsCollection parent = BedwarsAPI.getRootCommandsCollection();
    private String usage;
    private boolean visible = true;
    private boolean contentAmount = true;

    public void setAliases(String... aliases) {
        this.aliases = aliases;
    }
}
