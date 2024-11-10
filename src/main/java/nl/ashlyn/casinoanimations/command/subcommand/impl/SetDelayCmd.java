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

public class SetDelayCmd implements SubCommand {

    public String getName() {
        return "setdelay";
    }

    public String getDescription() {
        return "Sets the delay for the animation frame";
    }

    public void execute(CommandSender sender, String[] args) {

        if (args.length == 4) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(
                    AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELAY_NO_FRAME));
            return;
        }

        if(CasinoAnimations.INSTANCE.getAnimations().get(args[1]).getDataStore().getFrame(args[4]) == null) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX +
                    AnimationsConfig.Messages.DELAY_NOT_EXISTS, args[1], args[4]));
            return;
        }

        if (args.length == 5) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(
                    AnimationsConfig.PREFIX + AnimationsConfig.Messages.DELAY_NO_VALUE,
                    CasinoAnimations.INSTANCE.getAnimations().get(args[1]).getDataStore().getFrame(args[4]).getInt("delay")));
            return;
        }

        int delay = Integer.parseInt(args[5]);
        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);

        animation.getDataStore().setFrameSetting(args[4], "delay", delay);

        animation.reload();

        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.WORLD_CHANGE,
                args[1],
                CasinoAnimations.INSTANCE.getAnimations().get(args[1]).getWorld().getName()));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.setdelay", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if(!PermissionUtil.hasPermission(Arrays.asList(getPermission()), sender)) {
            return Collections.emptyList();
        }
        List<String> frames = new ArrayList<>();
        int i = 0;
        while(i <= CasinoAnimations.INSTANCE.getAnimations().get(args[1]).getFrameCount() - 1){
            frames.add(String.valueOf(i));
            i++;
        }
        return frames;
    }
}
