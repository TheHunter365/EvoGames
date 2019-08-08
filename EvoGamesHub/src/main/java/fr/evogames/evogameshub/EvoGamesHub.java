package fr.evogames.evogameshub;

import org.bukkit.plugin.java.JavaPlugin;

public class EvoGamesHub extends JavaPlugin {

    private EvoGamesHub instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    public EvoGamesHub getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
    }
}
