package fr.evogames.evogamescore.player;

import fr.evogames.evogamesapi.player.EvoGameStat;
import fr.evogames.evogamesapi.player.EvoRank;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class EvoPlayer implements fr.evogames.evogamesapi.player.EvoPlayer {

    private UUID playerId;
    private int coins;
    private int evoCoins;
    private EvoRank evoRank;
    private Set<EvoGameStat> evoGameStats;

    public EvoPlayer(UUID playerId) {
        this.playerId = playerId;
        this.coins = 0;
        this.evoCoins = 0;
        this.evoRank = new fr.evogames.evogamescore.player.EvoRank("Joueur");
        this.evoGameStats = new HashSet<>();
    }

    @Override
    public UUID getUniqueId() {
        return this.playerId;
    }

    @Override
    public int getCoins() {
        return this.coins;
    }

    @Override
    public int getEvoCoins() {
        return this.evoCoins;
    }

    @Override
    public EvoRank getRank() {
        return this.evoRank;
    }

    @Override
    public Set<EvoGameStat> getGamesStat() {
        return this.evoGameStats;
    }

    @Override
    public EvoGameStat getGameStat(String gameName) {
        return null;
    }

    @Override
    public void addCoins(int amount) {
        this.coins += amount;
    }

    @Override
    public void addEvoCoins(int amount) {
        this.evoCoins += amount;
    }

    @Override
    public void setRank(EvoRank rank) {
        this.evoRank = rank;
    }
}
