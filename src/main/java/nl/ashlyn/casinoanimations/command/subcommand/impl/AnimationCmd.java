package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.api.util.PermissionUtil;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnimationCmd implements SubCommand {

    private final List<SubCommand> subCommands = new ArrayList<>();

    public AnimationCmd() {
        subCommands.add(new SettingsCmd());
        subCommands.add(new GetFrameCmd());
        subCommands.add(new AddFrameCmd());
        subCommands.add(new EditFrameCmd());
        subCommands.add(new RemoveFrameCmd());
        subCommands.add(new PlayCmd());
        subCommands.add(new StopCmd());
        subCommands.add(new ConvertCmd());
        subCommands.add(new GoToFrameCmd());
        subCommands.add(new FrameSelectionCmd());
        subCommands.add(new AnimationReloadCmd());
        subCommands.add(new RenameCmd());
        //subCommands.add(new CopyFrameCmd());
    }

    public String getName() {
        return "animation";
    }

    public String getDescription() {
        return "An animation";
    }

    public void execute(CommandSender sender, String[] args) {

        if(args.length == 1 || !CasinoAnimations.INSTANCE.getAnimations().containsKey(args[1])) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.ANIMATION_NOT_EXISTS));
            return;
        }

        if(args.length > 2) {
            for(SubCommand subCommand : subCommands) {
                if(subCommand.getName().equalsIgnoreCase(args[2]) && PermissionUtil.hasPermission(Arrays.asList(subCommand.getPermission()), sender)) {
                    subCommand.execute(sender, args);
                    return;
                }
            }
        } else {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.SETTINGS, args[1], ""));
        }
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.animation", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if(!PermissionUtil.hasPermission(Arrays.asList(getPermission()), sender)) {
            return Collections.emptyList();
        }
        List<String> completions = new ArrayList<>();
        if(args.length > 2) {
            for(SubCommand subCommand : subCommands) {
                if(subCommand.getName().equalsIgnoreCase(args[2])) {
                    return (List<String>) subCommand.getTabCompletions(sender, args);
                } else {
                    completions.add(subCommand.getName());
                }
            }
        } else {
            return new ArrayList<>(CasinoAnimations.INSTANCE.getAnimations().keySet());
        }
        return completions;
    }
}