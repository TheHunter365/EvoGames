package fr.evogames.evogamesapi;

import fr.evogames.evogamesapi.gamemanager.EvoGameManager;
import fr.evogames.evogamesapi.player.EvoPlayerManager;
import fr.evogames.evogamesapi.player.EvoRankManager;

public interface EvoGamesApi {

    EvoGameManager getEvoGamesManager();
    EvoPlayerManager getPlayerManager();
    EvoRankManager getRankManager();

}
