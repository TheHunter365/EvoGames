package fr.evogames.evogamescore.game.profile;

import fr.evogames.evogamescore.game.EvoGame;
import fr.evogames.evogamescore.game.profile.gui.TeamSelectorGui;
import fr.evogames.evogamescore.game.profile.library.ItemLibrary;
import fr.evogames.evogamescore.utils.ScoreboardSign;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class GameProfile {

    private EvoGame evoGame;

    private UUID uuid;
    private TeamSelectorGui teamSelectorGui;
    private ItemLibrary itemLibrary;

    private ScoreboardSign scoreboardSign;

    public GameProfile(EvoGame evoGame, UUID uuid) {
        this.evoGame = evoGame;
        this.uuid = uuid;
        this.teamSelectorGui = new TeamSelectorGui(this, evoGame);
        this.itemLibrary = new ItemLibrary(this, evoGame);
        this.scoreboardSign = new ScoreboardSign(getPlayer(), "Default Name");
        scoreboardSign.create();
    }

    public Player getPlayer(){
        return Bukkit.getPlayer(uuid);
    }

    public TeamSelectorGui getTeamSelectorGui() {
        return teamSelectorGui;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ItemLibrary getItemLibrary() {
        return itemLibrary;
    }

    public EvoGame getEvoGame() {
        return evoGame;
    }

    public ScoreboardSign getScoreboardSign() {
        return scoreboardSign;
    }
}
