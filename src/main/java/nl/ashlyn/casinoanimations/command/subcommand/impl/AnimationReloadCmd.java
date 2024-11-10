package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class AnimationReloadCmd implements SubCommand {

    public String getName() {
        return "reload";
    }

    public String getDescription() {
        return "Reloads a specific animation";
    }

    public void execute(CommandSender sender, String[] args) {

        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.RELOAD));

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);
        animation.reload();

        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.RELOAD_SUCCESS));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.reload", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}
