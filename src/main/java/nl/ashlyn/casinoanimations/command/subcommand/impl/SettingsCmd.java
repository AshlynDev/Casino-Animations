package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.api.util.PermissionUtil;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SettingsCmd implements SubCommand {

    private final List<SubCommand> subCommands = new ArrayList<>();

    public SettingsCmd() {
        subCommands.add(new SetWorldCmd());
        subCommands.add(new ToggleReverseCmd());
        subCommands.add(new ToggleRandomCmd());
        subCommands.add(new SetLoadCmd());
        subCommands.add(new SetDelayCmd());
    }

    public String getName() {
        return "settings";
    }

    public String getDescription() {
        return "Settings for an animation";
    }

    public void execute(CommandSender sender, String[] args) {

        if(args.length == 3) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.SETTINGS, args[1], ""));
        } else if(args.length > 3) {
            for(SubCommand subCommand : subCommands) {
                if(subCommand.getName().equalsIgnoreCase(args[3]) && PermissionUtil.hasPermission(Arrays.asList(subCommand.getPermission()), sender)) {
                    subCommand.execute(sender, args);
                }
            }
        }
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.settings", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if(!PermissionUtil.hasPermission(Arrays.asList(getPermission()), sender)) {
            return Collections.emptyList();
        }

        List<String> completions = new ArrayList<>();
        if(args.length > 3) {
            for(SubCommand subCommand : subCommands) {
                if(subCommand.getName().equalsIgnoreCase(args[3])) {
                    return (List<String>) subCommand.getTabCompletions(sender, args);
                } else {
                    completions.add(subCommand.getName());
                }
            }
        }
        return completions;
    }
}