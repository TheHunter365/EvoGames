package fr.evogames.evogamescore;

import fr.evogames.evogamesapi.EvoGamesApi;
import fr.evogames.evogamesapi.player.EvoPlayerManager;
import fr.evogames.evogamesapi.player.EvoRankManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EvoGamesCore extends JavaPlugin implements EvoGamesApi {

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    @Override
    public EvoPlayerManager getPlayerManager() {
        return null;
    }

    @Override
    public EvoRankManager getRankManager() {
        return null;
    }
}
