package fr.evogames.evogamescore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.evogames.evogamesapi.EvoGamesApi;
import fr.evogames.evogamesapi.database.DataBase;
import fr.evogames.evogamesapi.game.EvoGameManager;
import fr.evogames.evogamesapi.player.EvoPlayerManager;
import fr.evogames.evogamesapi.player.EvoRankManager;
import fr.evogames.evogamescore.database.MongoDataBase;
import fr.evogames.evogamescore.database.RedisDataBase;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class EvoGamesCore extends JavaPlugin implements EvoGamesApi {

    private Gson gson;
    private DataBase mongodb, redisdb;

    private EvoPlayerManager playerManager;

    @Override
    public void onEnable() {

        this.getServer()
                .getServicesManager()
                .register(EvoGamesApi.class, this, this, ServicePriority.Highest);

        this.gson = new GsonBuilder()
                .serializeNulls()
                .create();
        this.mongodb = new MongoDataBase("mongodb", 2375, "EvoGames", "EvoGames");
        this.redisdb = new RedisDataBase("redis", 6379, 2);

        this.playerManager = new fr.evogames.evogamescore.player.EvoPlayerManager(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public EvoGameManager getEvoGamesManager() {
        return null;
    }

    @Override
    public EvoPlayerManager getPlayerManager() {
        return this.playerManager;
    }

    @Override
    public EvoRankManager getRankManager() {
        return null;
    }

    public Gson getGson() {
        return gson;
    }

    public DataBase getMongodb() {
        return mongodb;
    }

    public DataBase getRedisdb() {
        return redisdb;
    }
}
