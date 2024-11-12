package nl.ashlyn.casinoanimations.listener;

import nl.ashlyn.casinoanimations.CasinoAnimations;
import nl.ashlyn.casinoanimations.animation.IAnimation;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    // Why was this ever implemented?
    
    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        /*if (Bukkit.getOnlinePlayers().size() == 1) {
            for (IAnimation animation : CasinoAnimations.INSTANCE.getAnimations().values()) {
                if (animation.isInactive()) continue;
                animation.play(false);
            }
        }*/
    }

    @EventHandler
    public void playerLeave(PlayerQuitEvent event) {
        if (Bukkit.getOnlinePlayers().size() == 0) {
            for (IAnimation animation : CasinoAnimations.INSTANCE.getAnimations().values()) {
                animation.stop();
            }
        }
    }
}