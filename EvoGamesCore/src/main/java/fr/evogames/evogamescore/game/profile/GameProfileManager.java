package fr.evogames.evogamescore.game.profile;

import fr.evogames.evogamescore.game.Game;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameProfileManager {

    private Game evoGame;
    private Map<UUID, GameProfile> gameProfileMap;

    public GameProfileManager(Game evoGame) {
        this.evoGame = evoGame;
        this.gameProfileMap = new HashMap<>();
    }

    public GameProfile get(UUID uuid){
        gameProfileMap.putIfAbsent(uuid, new GameProfile(evoGame, uuid));
        return gameProfileMap.get(uuid);
    }

    public void remove(UUID uuid){
        evoGame.getTeamManager().removePlayerFromAllTeam(get(uuid));
        gameProfileMap.remove(uuid);
        getGameProfileMap().values().forEach(gameProfile -> gameProfile.getTeamSelectorGui().load());
    }

    public int size(){
        return gameProfileMap.size();
    }

    public Map<UUID, GameProfile> getGameProfileMap() {
        return gameProfileMap;
    }
}
