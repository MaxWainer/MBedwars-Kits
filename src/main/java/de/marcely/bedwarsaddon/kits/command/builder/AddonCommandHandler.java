package de.marcely.bedwarsaddon.kits.command.builder;

import de.marcely.bedwars.api.command.CommandHandler;
import de.marcely.bedwars.api.command.CommandsCollection;
import de.marcely.bedwars.api.command.SubCommand;
import de.marcely.bedwarsaddon.kits.BWKitAddon;
import de.marcely.bedwarsaddon.kits.command.builder.cmd.AddonCommand;
import de.marcely.bedwarsaddon.kits.command.builder.wrappers.WrappedCommand;
import de.marcely.bedwarsaddon.kits.command.builder.wrappers.WrappedTabComplete;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class AddonCommandHandler {

    public void registerCommand(AddonCommand command) {

        new CommandsCollection() {
            @Override
            public String getName() {
                return command.getName();
            }

            @Override
            public @Nullable CommandsCollection getParent() {
                return command.getParent();
            }

            @Override
            public String[] getAliases() {
                return command.getAliases();
            }

            @Override
            public void setAliases(String... strings) {

            }

            @Override
            public @Nullable String getPermission() {
                return command.getPermission();
            }

            @Override
            public void setPermission(@Nullable String s) {

            }

            @Override
            public String getUsage() {
                return command.getUsage();
            }

            @Override
            public void setUsage(String s) {

            }

            @Override
            public CommandHandler getHandler() {
                return registerHandler(command, false);
            }

            @Override
            public void setHandler(CommandHandler commandHandler) {

            }

            @Override
            public boolean isOnlyForPlayers() {
                return command.isPlayerOnly();
            }

            @Override
            public void setOnlyForPlayers(boolean b) {

            }

            @Override
            public boolean isVisible() {
                return command.isVisible();
            }

            @Override
            public void setVisible(boolean b) {

            }

            @Override
            public boolean hasContentAmount() {
                return command.isContentAmount();
            }

            @Override
            public void setHasContentAmount(boolean b) {

            }

            @Override
            public Collection<SubCommand> getCommands() {
                return null;
            }

            @Override
            public @Nullable SubCommand addCommand(String s) {
                return null;
            }

            @Override
            public @Nullable CommandsCollection addCommandsCollection(String s) {
                return null;
            }

            @Override
            public boolean removeCommand(SubCommand subCommand) {
                return false;
            }

            @Override
            public @Nullable SubCommand getCommand(String s, boolean b) {
                return null;
            }

            @Override
            public CommandHandler getHelpCommand() {
                return registerHandler(command, true);
            }

            @Override
            public void setHelpCommand(CommandHandler commandHandler) {

            }
        };


    }

    private CommandHandler registerHandler(AddonCommand command, boolean isHelp) {
        return new CommandHandler() {

            @Override
            public Plugin getPlugin() {
                return BWKitAddon.getInstance();
            }

            @Override
            public void onRegister(SubCommand subCommand) {}

            @Override
            public void onFire(CommandSender commandSender, String s, String[] strings) {
                (isHelp ? command.getHelpCommand() : command.getCommand())
                        .accept(new WrappedCommand(commandSender, strings, s));
            }

            @Override
            public @Nullable List<String> onAutocomplete(CommandSender commandSender, String[] strings) {
                return command.getComplete().apply(new WrappedTabComplete(commandSender, strings));
            }

        };
    }

}
