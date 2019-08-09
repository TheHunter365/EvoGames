package fr.evogames.evogamescore.player;

import fr.evogames.evogamesapi.database.DataBase;
import fr.evogames.evogamesapi.player.EvoRank;

import java.util.Set;

public class EvoRankManager implements fr.evogames.evogamesapi.player.EvoRankManager {

    private DataBase dataBase;

    public EvoRankManager(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public Set<EvoRank> getRanks() {
        return null;
    }

    @Override
    public EvoRank newRank(String name, String... permissions) {
        return null;
    }

    @Override
    public void deleteRank(String name) {

    }
}
