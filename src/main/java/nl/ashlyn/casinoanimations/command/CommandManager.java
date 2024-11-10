package nl.ashlyn.casinoanimations.command;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.api.util.PermissionUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.command.subcommand.impl.AnimationCmd;
import nl.ashlyn.casinoanimations.command.subcommand.impl.CreateCmd;
import nl.ashlyn.casinoanimations.command.subcommand.impl.DeleteCmd;
import nl.ashlyn.casinoanimations.command.subcommand.impl.ReloadCmd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final List<SubCommand> subcommands = new ArrayList<>();

    public CommandManager() {
        subcommands.add(new ReloadCmd());
        subcommands.add(new CreateCmd());
        subcommands.add(new DeleteCmd());
        subcommands.add(new AnimationCmd());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!PermissionUtil.hasPermission(Arrays.asList(getPermission()), sender)) {
            return true;
        }

        if(args.length == 0) {
            sender.sendMessage("§c/casinoanimations reload");
        } else {
            if(CasinoAnimations.INSTANCE.getAnimations().containsKey(args[0])) {
                for(SubCommand subCommand : subcommands) { // TODO: probably a better way to do this
                    if(subCommand.getName().equalsIgnoreCase("animation") && PermissionUtil.hasPermission(Arrays.asList(subCommand.getPermission()), sender)) {
                        subCommand.execute(sender, args);
                        return true;
                    }
                }
            } else {
                for (SubCommand subCommand : subcommands) {
                    if (subCommand.getName().equalsIgnoreCase(args[0]) && PermissionUtil.hasPermission(Arrays.asList(subCommand.getPermission()), sender)) {
                        subCommand.execute(sender, args);
                        return true;
                    }
                }
                sender.sendMessage("§cUnknown subcommand");
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(!PermissionUtil.hasPermission(Arrays.asList(getPermission()), sender)) {
            return Collections.emptyList();
        }

        List<String> completions = new ArrayList<>();

        for (SubCommand subcommand : subcommands) {
            if (subcommand.getName().equalsIgnoreCase(args[0])) {
                return (List<String>) subcommand.getTabCompletions(sender, args);
            } else {
                completions.add(subcommand.getName());
            }
        }

        return completions;
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.command", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return subcommands;
    }
}