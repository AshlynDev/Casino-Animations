package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class RenameCmd implements SubCommand {

    public String getName() {
        return "rename";
    }

    public String getDescription() {
        return "Renames the animation";
    }

    public void execute(CommandSender sender, String[] args) {

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);

        if(args.length == 3) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX
                    + AnimationsConfig.Messages.RENAME_NO_VALUE));
            return;
        }

        if(CasinoAnimations.INSTANCE.getAnimations().containsKey(args[3].toLowerCase())){
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX
                    + AnimationsConfig.Messages.RENAME_EXISTS));
            return;
        }

        animation.setName(args[3].toLowerCase());
        CasinoAnimations.INSTANCE.getAnimations().remove(args[1]);
        CasinoAnimations.INSTANCE.getAnimations().put(animation.getName(), animation);

        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.RENAME_SUCCESS,
                args[1],
                args[3]));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.rename", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }
}