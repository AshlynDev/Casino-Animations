package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ToggleRandomCmd implements SubCommand {

    public String getName() {
        return "togglerandom";
    }

    public String getDescription() {
        return "Toggles if the animations should play in reverse when they get to the end instead of resetting";
    }

    public void execute(CommandSender sender, String[] args) {

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);

        animation.getDataStore().saveSetting("random-frame", !animation.isRandomFrame());
        animation.setRandomFrame(!CasinoAnimations.INSTANCE.getAnimations().get(args[1]).isRandomFrame());
        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.RANDOM_SUCCESS,
                args[1],
                animation.isRandomFrame()));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.togglerandom", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
