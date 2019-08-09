package fr.evogames.evogamescore.player;

import fr.evogames.evogamesapi.player.EvoGameStat;
import fr.evogames.evogamesapi.player.EvoRank;

import java.util.Set;
import java.util.UUID;

public class EvoPlayer implements fr.evogames.evogamesapi.player.EvoPlayer {

    //Needs to be filled

    @Override
    public UUID getPlayerId() {
        return null;
    }

    @Override
    public int getCoins() {
        return 0;
    }

    @Override
    public int getEvoCoins() {
        return 0;
    }

    @Override
    public EvoRank getRank() {
        return null;
    }

    @Override
    public Set<EvoGameStat> getGamesStat() {
        return null;
    }

    @Override
    public EvoGameStat getGameStat(String gameName) {
        return null;
    }

    @Override
    public void addCoins(int amount) {

    }

    @Override
    public void addEvoCoins(int amount) {

    }

    @Override
    public void setRank(EvoRank rank) {

    }
}
