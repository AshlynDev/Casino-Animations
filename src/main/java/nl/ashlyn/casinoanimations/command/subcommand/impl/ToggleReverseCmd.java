package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ToggleReverseCmd implements SubCommand {

    public String getName() {
        return "togglereverse";
    }

    public String getDescription() {
        return "Toggles if the animations should play in reverse when they get to the end instead of resetting";
    }

    public void execute(CommandSender sender, String[] args) {

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);

        // TODO: add speed multiplier
        animation.getDataStore().saveSetting("reverse", !animation.isReverse());
        animation.setGoingReverse(false);
        animation.setReverse(!animation.isReverse());
        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.TOGGLE_REVERSE,
                args[1],
                animation.isReverse()));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.togglereverse", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}