package fr.evogames.evogameshub;

import fr.evogames.evogamesapi.EvoGamesApi;
import fr.evogames.evogamesapi.player.EvoPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class EvoGamesHub extends JavaPlugin {

    private EvoGamesHub instance;

    @Override
    public void onEnable() {
        instance = this;
        EvoGamesApi api = (EvoGamesApi) Bukkit.getServicesManager().getRegistration(EvoGamesApi.class);

        EvoPlayer player = api.getPlayerManager().getPlayer(UUID.randomUUID());
    }

    public EvoGamesHub getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
    }
}
