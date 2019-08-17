package fr.evogames.evogamescore.player;

import com.google.gson.Gson;
import fr.evogames.evogamesapi.database.DataBase;
import fr.evogames.evogamesapi.player.EvoPlayer;
import fr.evogames.evogamescore.EvoGamesCore;

import java.util.UUID;

public class EvoPlayerManager implements fr.evogames.evogamesapi.player.EvoPlayerManager {

    private EvoGamesCore core;

    private Gson gson;
    private DataBase mongodb, redisdb;

    public EvoPlayerManager(EvoGamesCore core) {
        this.core = core;

        this.gson = this.core.getGson();
        this.mongodb = this.core.getMongodb();
        this.redisdb = this.core.getRedisdb();
    }

    @Override
    public EvoPlayer getPlayer(UUID playerId) {
        EvoPlayer evoPlayer;

        String jsonPayload;
        if (!(jsonPayload = this.redisdb.get(playerId.toString())).equals("")) {
            evoPlayer = this.deserialize(jsonPayload);
        } else if (!(jsonPayload = this.mongodb.get(playerId.toString())).equals("")) {
            evoPlayer = this.deserialize(jsonPayload);
        } else {
            evoPlayer = new fr.evogames.evogamescore.player.EvoPlayer(playerId);
        }
        return evoPlayer;
    }


    private fr.evogames.evogamescore.player.EvoPlayer deserialize(String payload) {
        return this.gson.fromJson(payload, fr.evogames.evogamescore.player.EvoPlayer.class);
    }

}
