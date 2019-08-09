package fr.evogames.evogamesapi.player;

import com.sun.istack.internal.Nullable;

import java.util.Set;
import java.util.UUID;

public interface EvoPlayer {

    UUID getPlayerId();

    int getCoins();
    int getEvoCoins();

    EvoRank getRank();

    Set<EvoGameStat> getGamesStat();

    @Nullable
    EvoGameStat getGameStat(String gameName);

    //for this methods values can be negative to decrement coins/evocoins
    void addCoins(int amount);
    void addEvoCoins(int amount);

    void setRank(EvoRank rank);

}
