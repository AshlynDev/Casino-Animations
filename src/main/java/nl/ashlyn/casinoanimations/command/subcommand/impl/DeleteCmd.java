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

public class DeleteCmd implements SubCommand {

    public String getName() {
        return "delete";
    }

    public String getDescription() {
        return "Deletes an animation";
    }

    public void execute(CommandSender sender, String[] args) {

        if(args.length == 1) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELETE_NOT_EXISTS,
                    "invalid", ""));
            return;
        }

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);

        if (animation == null) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELETE_NOT_EXISTS));
            return;
        }
        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELETE));
        animation.stop();
        animation.getDataStore().deleteAnimation(args[1].toLowerCase());
        CasinoAnimations.INSTANCE.getAnimations().remove(args[1].toLowerCase());
        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELETE_SUCCESS));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.delete", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if(!PermissionUtil.hasPermission(Arrays.asList(getPermission()), sender)) {
            return Collections.emptyList();
        }
        if(args.length > 2) return Collections.emptyList();
        return new ArrayList<>(CasinoAnimations.INSTANCE.getAnimations().keySet());
    }
}
