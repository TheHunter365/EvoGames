package fr.evogames.evogamescore.game.team;

import fr.evogames.evogamescore.game.Game;
import fr.evogames.evogamescore.game.profile.GameProfile;
import fr.evogames.evogamescore.game.team.core.Team;

import java.util.*;
import java.util.stream.Collectors;

public class TeamManager {

    private Game evoGame;
    protected List<Team> teamList;

    public TeamManager(Game evoGame) {
        this.evoGame = evoGame;
        this.teamList = new ArrayList<>();
    }

    public int getLimit(){
        int i = 0;
        for (Team team : teamList) {
            i = i + team.getLimit();
        }
        return i;
    }

    public void removePlayerFromAllTeam(GameProfile gameProfile){
        evoGame.getTeamManager().getTeamList().stream().filter(team -> team.contains(gameProfile)).forEach(team -> team.remove(gameProfile));
    }

    public Team getTeam(GameProfile gameProfile){
        return evoGame.getTeamManager().getTeamList().stream().filter(team -> team.contains(gameProfile)).findFirst().orElse(null);
    }

    public boolean isInATeam(GameProfile gameProfile){
        return evoGame.getTeamManager().getTeamList().stream().anyMatch(team -> team.contains(gameProfile));
    }

    public void randomizePlayerWithoutTeam() {
        getPlayerWithoutTeam().forEach(gameProfile -> {
            List<Team> possiblesTeams = getTeamsWithTheLessPlayers();
            Random rand = new Random();
            Team team = possiblesTeams.get(rand.nextInt(possiblesTeams.size()));
            team.add(gameProfile);
        });
    }

    private List<Team> getTeamsWithTheLessPlayers() {
        return getTeamList().stream().filter(Team::canAdd).collect(Collectors.toList());
    }

    public List<Team> getTeamWithPlayer(){
        return getTeamList().stream().filter(team -> team.getGameProfileList().size() > 0).collect(Collectors.toList());
    }

    public Set<GameProfile> getPlayerWithoutTeam(){
        return evoGame.getGameProfileManager().getGameProfileMap().values().stream().filter(gameProfile -> !isInATeam(gameProfile)).collect(Collectors.toSet());
    }

    public List<Team> getTeamList() {
        return teamList;
    }
}
