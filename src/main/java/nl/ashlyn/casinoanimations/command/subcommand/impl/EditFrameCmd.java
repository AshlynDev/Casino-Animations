package nl.ashlyn.casinoanimations.command.subcommand.impl;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.animation.frame.AnimationFrame;
import nl.ashlyn.casinoanimations.api.util.BlockVector;
import nl.ashlyn.casinoanimations.api.util.PermissionUtil;
import nl.ashlyn.casinoanimations.api.util.TranslationUtil;
import nl.ashlyn.casinoanimations.command.subcommand.SubCommand;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.session.SessionManager;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

import java.util.*;

public class EditFrameCmd implements SubCommand {

    public String getName() {
        return "editframe";
    }

    public String getDescription() {
        return "Edits a frame of an animation";
    }

    public void execute(CommandSender sender, String[] args) {

        IAnimation animation = CasinoAnimations.INSTANCE.getAnimations().get(args[1]);
        
        if(args.length < 4) {
            sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.EDIT_FRAME_NOT_EXISTS,
                    args[1], ""));
            return;
        }

        org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;
        Player actor = BukkitAdapter.adapt(player);
        SessionManager manager = WorldEdit.getInstance().getSessionManager();
        LocalSession localSession = manager.get(actor);
        Map<BlockVector, BlockData> frame = new HashMap<>();
        ConfigurationSection section = new MemoryConfiguration();
        try {
            for (int i = localSession.getSelection().getMinimumPoint().getBlockX(); i <= localSession.getSelection().getMaximumPoint().getBlockX(); i++) {
                for (int j = localSession.getSelection().getMinimumPoint().getBlockY(); j <= localSession.getSelection().getMaximumPoint().getBlockY(); j++) {
                    for (int k = localSession.getSelection().getMinimumPoint().getBlockZ(); k <= localSession.getSelection().getMaximumPoint().getBlockZ(); k++) {
                        Block block = player.getWorld().getBlockAt(i, j, k);
                        frame.put(new BlockVector(i, j, k), block.getBlockData());
                        section.createSection(i + "." + j + "." + k);
                        section.set(i + "." + j + "." + k, block.getBlockData().getAsString().replaceFirst("minecraft:", ""));
                    }
                }
            }
        } catch (IncompleteRegionException e) {
            e.printStackTrace();
        }

        AnimationFrame animationFrame = new AnimationFrame(frame,
                args.length < 5 ? animation.getDataStore().getFrame(args[3]).getInt("delay") : Integer.parseInt(args[4]));
        animation.editFrame(args[3], animationFrame, section);

        sender.sendMessage(TranslationUtil.translatePlaceholders(AnimationsConfig.PREFIX + AnimationsConfig.Messages.ADDFRAME,
                args[1], CasinoAnimations.INSTANCE.getAnimations().get(args[1]).getFrameCount() - 1));
    }

    public String[] getPermission() {
        return new String[]{"casinoanimations.editframe", "casinoanimations.admin"};
    }

    public List<SubCommand> getSubCommands() {
        return null;
    }

    public List<String> getTabCompletions(CommandSender sender, String[] args) {
        if(!PermissionUtil.hasPermission(Arrays.asList(getPermission()), (org.bukkit.entity.Player) sender)) {
            return Collections.emptyList();
        }
        if (CasinoAnimations.INSTANCE.getAnimations().get(args[1]) == null) return Collections.emptyList();
        List<String> frames = new ArrayList<>();
        int i = 0;
        while(i <= CasinoAnimations.INSTANCE.getAnimations().get(args[1]).getFrameCount() - 1){
            frames.add(String.valueOf(i));
            i++;
        }
        return frames;
    }
}