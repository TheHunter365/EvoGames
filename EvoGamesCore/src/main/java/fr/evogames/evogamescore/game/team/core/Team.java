package fr.evogames.evogamescore.game.team.core;

import fr.evogames.evogamescore.game.profile.GameProfile;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team implements fr.evogames.evogamesapi.game.team.Team {

    private TeamColor teamColor;
    private String teamName;
    private TeamState state;
    private int limit;

    private List<GameProfile> gameProfileList;

    public Team(TeamColor teamColor, int limit) {
        this.teamColor = teamColor;
        this.limit = limit;
        this.teamName = teamColor.getName();
        this.gameProfileList = new ArrayList<>();
        this.state = TeamState.NONE;
    }

    public Team(TeamColor teamColor, int limit, String teamName) {
        this.teamColor = teamColor;
        this.limit = limit;
        this.teamName = teamName;
        this.gameProfileList = new ArrayList<>();
        this.state = TeamState.NONE;
    }

    public boolean add(GameProfile gameProfile){
        if(!gameProfileList.contains(gameProfile)){
            if(canAdd()) {
                gameProfileList.add(gameProfile);
                return true;
            }
        }
        return false;
    }

    public boolean canAdd(){
        if(gameProfileList.size() < limit) {
            return true;
        }
        return false;
    }

    public List<Player> getPlayers(){
        List<Player> playerList = new ArrayList<>();
        gameProfileList.forEach(gameProfile -> playerList.add(gameProfile.getPlayer()));
        return playerList;
    }

    public String getPlayersName() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gameProfileList.size(); i++) {
            GameProfile gameProfile = gameProfileList.get(i);

            int index = gameProfileList.indexOf(gameProfile);
            String playerName = gameProfile.getPlayer().getName();

            if(gameProfileList.size() - index == 1){
                sb.append(playerName);
            }
            else if(gameProfileList.size() - index == 2){
                sb.append(playerName + " et ");
            }
            else{
                sb.append(playerName + ", ");
            }
        }
        return sb.toString();
    }

    public String getPlayersNameWithPrefix(){
        return getTeamColor().getChatColor() + getPlayersName();
    }

    public void remove(GameProfile gameProfile){
        gameProfileList.remove(gameProfile);
    }

    public boolean contains(GameProfile gameProfile){
        return gameProfileList.contains(gameProfile);
    }

    public int size(){
        return gameProfileList.size();
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<GameProfile> getGameProfileList() {
        return gameProfileList;
    }

    public TeamState getState() {
        return state;
    }

    public void setState(TeamState state) {
        this.state = state;
    }

    public int getLimit() {
        return limit;
    }
}
