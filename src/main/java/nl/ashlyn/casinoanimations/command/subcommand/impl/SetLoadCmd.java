package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.api.util.PermissionUtil;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SetLoadCmd implements SubCommand {

    public String getName() {
        return "setload";
    }

    public String getDescription() {
        return "Sets the loading system for the animation";
    }

    public void execute(CommandSender sender, String[] args) {

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);

        if (args.length == 4) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.SETLOAD,
                    animation.getName(), animation.isSaveToRam() ? "RAM" : "FILE"));
            return;
        }

        if(animation.getDataStore().getSetting("frame-load").toString().equalsIgnoreCase(args[4])) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.SETLOAD_ALREADY_SET,
                    animation.getName(), args[4]));
            return;
        }

        animation.stop();
        animation.getFrames().clear();

        animation.getDataStore().saveSetting("frame-load", args[4]);

        animation.reload();

        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.SETLOAD_SUCCESS,
                animation.getName(), args[4]));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.setload", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if(!PermissionUtil.hasPermission(Arrays.asList(getPermission()), sender)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(Arrays.asList("ram", "file"));
    }
}
