package fr.evogames.evogamesapi;

import fr.evogames.evogamesapi.game.GameManager;
import fr.evogames.evogamesapi.player.EvoPlayerManager;
import fr.evogames.evogamesapi.player.EvoRankManager;

public interface EvoGamesApi {

    GameManager getEvoGamesManager();
    EvoPlayerManager getPlayerManager();
    EvoRankManager getRankManager();

}
