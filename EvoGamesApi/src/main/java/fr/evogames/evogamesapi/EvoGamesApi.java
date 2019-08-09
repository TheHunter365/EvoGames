package fr.evogames.evogamesapi;

import fr.evogames.evogamesapi.player.EvoPlayerManager;
import fr.evogames.evogamesapi.player.EvoRankManager;

public interface EvoGamesApi {

    EvoPlayerManager getPlayerManager();
    EvoRankManager getRankManager();


}
