package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class GetFrameCmd implements SubCommand {

    public String getName() {
        return "getframe";
    }

    public String getDescription() {
        return "Gets the current frame of an animation";
    }

    public void execute(CommandSender sender, String[] args) {

        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.GETFRAME,
                args[1], CasinoAnimations.INSTANCE.getAnimations().get(args[1]).getFrame()));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.getframe", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}