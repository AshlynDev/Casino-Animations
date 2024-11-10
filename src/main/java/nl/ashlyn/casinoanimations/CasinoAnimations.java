package nl.ashlyn.casinoanimations;

import nl.ashlyn.casinoanimations.animation.IAnimation;
import nl.ashlyn.casinoanimations.command.CommandManager;
import nl.ashlyn.casinoanimations.config.AnimationsConfig;
import nl.ashlyn.casinoanimations.listener.PlayerListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public final class CasinoAnimations extends JavaPlugin {

    public static CasinoAnimations INSTANCE;

    private final Map<String, IAnimation> animations = new HashMap<>();
    private final List<String> converting = new ArrayList<>();

    private final String animationDataFolder = getDataFolder() + "/data/";

    public CasinoAnimations() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        AnimationsConfig.load();

        getCommand("casinoanimations").setExecutor(new CommandManager());
        getCommand("casinoanimations").setTabCompleter(new CommandManager());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}