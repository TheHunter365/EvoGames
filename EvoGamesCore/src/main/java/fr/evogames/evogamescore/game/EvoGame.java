package fr.evogames.evogamescore.game;

import fr.evogames.evogamesapi.game.status.EvoGameStatus;
import fr.evogames.evogamescore.game.profile.GameProfileManager;
import fr.evogames.evogamescore.game.scenario.ScenarioManager;
import fr.evogames.evogamescore.game.team.TeamManager;
import fr.evogames.evogamescore.game.team.core.Team;
import fr.evogames.evogamescore.game.waitingRoom.WRScoreBoard;
import fr.evogames.evogamescore.game.waitingRoom.WaitingRoomManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public abstract class EvoGame extends fr.evogames.evogamesapi.game.EvoGame {

    private JavaPlugin main;
    private String name;
    private String description;

    private EvoGameStatus state;

    private GameProfileManager gameProfileManager;
    private TeamManager teamManager;
    private ScenarioManager scenarioManager;
    private WaitingRoomManager waitingRoomManager;

    public abstract void onGameStart();
    public abstract void onGameDisable();

    public EvoGame(JavaPlugin main, String name, String description) {
        this.main = main;
        this.name = name;
        this.description = description;
        this.teamManager = new TeamManager(this);
        this.gameProfileManager = new GameProfileManager(this);
        this.scenarioManager = new ScenarioManager(this);
        this.state = EvoGameStatus.WAITING;
    }

    public void defaultLoad(List<Team> teamList) {
        teamList.forEach(team -> teamManager.getTeamList().add(team));
        this.waitingRoomManager = new WaitingRoomManager(this);
        loadListener();
    }

    /*
    Load all listeners required
     */
    private void loadListener(){
        Arrays.asList(
                new EvoGameListener(this)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, getMain()));
    }

    /*
    To launch a game
     */
    public void launch() {
        if(getState() == EvoGameStatus.WAITING) {
            getGameProfileManager().getGameProfileMap().values().forEach(WRScoreBoard::reloadScoreBoard);
            getTeamManager().randomizePlayerWithoutTeam();
            setState(EvoGameStatus.STARTED);
            broadcast(ChatColor.YELLOW + "Démarrage de la partie !");
            getOnlinePlayers().forEach(player -> {
                player.getInventory().clear();
            });
            onGameStart();
        }
    }

    public void stop() {
        if(getState() == EvoGameStatus.STARTED) {
            getGameProfileManager().getGameProfileMap().values().forEach(WRScoreBoard::reloadScoreBoard);
            setState(EvoGameStatus.FINISHED);
            broadcast(ChatColor.YELLOW + "Fin !");
            getOnlinePlayers().forEach(player -> {
                player.getInventory().clear();
            });
            onGameDisable();
        }
    }

    /*
    Broadcast a message to the game.
     */
    public void broadcast(String message){
        getGameProfileManager().getGameProfileMap().values().forEach(gameProfile -> gameProfile.getPlayer().sendMessage(message));
    }

    public List<Player> getOnlinePlayers(){
        List<Player> playerList = new ArrayList<>();
        getGameProfileManager().getGameProfileMap().values().stream().filter(gameProfile -> gameProfile.getPlayer() != null).forEach(gameProfile -> playerList.add(gameProfile.getPlayer()));
        return playerList;
    }

    public void enable(){

    }

    public EvoGameStatus getState() {
        return state;
    }

    public void setState(EvoGameStatus state) {
        this.state = state;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public JavaPlugin getMain() {
        return main;
    }

    public WaitingRoomManager getWaitingRoomManager() {
        return waitingRoomManager;
    }

    public GameProfileManager getGameProfileManager() {
        return gameProfileManager;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ScenarioManager getScenarioManager() {
        return scenarioManager;
    }
}
