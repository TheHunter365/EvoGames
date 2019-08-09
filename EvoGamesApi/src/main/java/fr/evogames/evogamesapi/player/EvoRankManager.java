package fr.evogames.evogamesapi.player;

import java.util.Set;

public interface EvoRankManager {

    Set<EvoRank> getRanks();
    EvoRank newRank(String name, String... permissions);
    void deleteRank(String name);

}
