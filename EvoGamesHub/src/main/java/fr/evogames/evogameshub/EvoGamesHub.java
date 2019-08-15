package fr.evogames.evogameshub;

import fr.evogames.evogamesapi.EvoGamesApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EvoGamesHub extends JavaPlugin {

    private EvoGamesHub instance;

    @Override
    public void onEnable() {
        instance = this;
        EvoGamesApi api = (EvoGamesApi) Bukkit.getServicesManager().getRegistration(EvoGamesApi.class);
    }

    public EvoGamesHub getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
    }
}
