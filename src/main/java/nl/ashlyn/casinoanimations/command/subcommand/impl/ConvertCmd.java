package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import nl.ashlyn.casinoanimations.storage.impl.MultipleFileFrameStorage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ConvertCmd implements SubCommand {

    public String getName() {
        return "convert";
    }

    public String getDescription() {
        return "Converts the current animations file save system";
    }

    public void execute(CommandSender sender, String[] args) {

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);

        if(CasinoAnimations.INSTANCE.getConverting().contains(args[1])) {
            sender.sendMessage(AnimationsConfig.PREFIX + AnimationsConfig.Messages.CONVERTING_IN_PROGRESS
            );
            return;
        }

        // TODO: add warning and confirmation command and make code better
        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.CONVERTING,
                args[1],
                animation.getDataStore() instanceof MultipleFileFrameStorage ? "singlefile" : "multiplefile"));

        animation.stop();

        CasinoAnimations.INSTANCE.getConverting().add(animation.getName());

        Bukkit.getScheduler().runTaskAsynchronously(CasinoAnimations.INSTANCE, () -> {

            animation.setDataStore(animation.getDataStore().convertFrames(animation));
            animation.reload();

            CasinoAnimations.INSTANCE.getConverting().remove(args[1]);

            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.CONVERTING_SUCCESS,
                    args[1],
                    animation.getDataStore() instanceof MultipleFileFrameStorage ? "multiplefile" : "singlefile"));
        });
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.convert", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}