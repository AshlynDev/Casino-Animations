package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class ReloadCmd implements SubCommand {

    public String getName() {
        return "reload";
    }

    public String getDescription() {
        return "Reloads the config and animations";
    }

    public void execute(CommandSender sender, String[] args) {

        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.RELOAD));
        AnimationsConfig.load();
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
