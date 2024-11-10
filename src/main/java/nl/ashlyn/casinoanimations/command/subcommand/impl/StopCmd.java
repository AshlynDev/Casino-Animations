package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class StopCmd implements SubCommand {

    public String getName() {
        return "stop";
    }

    public String getDescription() {
        return "Stops the current animation";
    }

    public void execute(CommandSender sender, String[] args) {

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);

        if (!animation.isRunning()) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.STOP_ANIMATION_NOT_RUNNING,
                    args[1], ""));
            return;
        }
        animation.getDataStore().saveSetting("inactive", true);
        animation.setInactive(true);
        animation.stop();
        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.STOP_ANIMATION,
                args[1], ""));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.stop", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}