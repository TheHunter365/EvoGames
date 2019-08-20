package fr.evogames.evogamescore.game.waitingRoom;

import fr.evogames.evogamescore.game.Game;
import fr.evogames.evogamesapi.game.status.EvoGameStatus;
import fr.evogames.evogamescore.game.waitingRoom.launch.StartTask;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WaitingRoomManager implements fr.evogames.evogamesapi.game.waitingRoom.WaitingRoomManager {

    private Game evoGame;
    private WaitingRoomStatus status;
    private StartTask startTask;

    public WaitingRoomManager(Game evoGame) {
        this.evoGame = evoGame;
        this.startTask = new StartTask(evoGame);
        this.status = WaitingRoomStatus.WAITING_PLAYER;
        Bukkit.getPluginManager().registerEvents(new WaitingRoomListener(evoGame), evoGame.getMain());
    }

    public void giveItem(Player player){
        if (evoGame.getState() == EvoGameStatus.WAITING) {
            UUID uuid = player.getUniqueId();
            player.getInventory().setItem(0, evoGame.getGameProfileManager().get(uuid).getItemLibrary().getTeamSelectorItem().getItemStack());
            if (player.hasPermission("evoGame.admin")){
                player.getInventory().setItem(8, evoGame.getGameProfileManager().get(uuid).getItemLibrary().getScenarioItem());
                player.getInventory().setItem(7, evoGame.getGameProfileManager().get(uuid).getItemLibrary().getAdminItem());
            }
        }
    }

    public String getPlayerTotalString(){
        return evoGame.getGameProfileManager().size() + "/" + evoGame.getTeamManager().getLimit();
    }

    public void startRunnable(){
        if (status == WaitingRoomStatus.WAITING_PLAYER) {
            startTask.enable();
        }
    }

    public void stopRunnable(){
        if (status == WaitingRoomStatus.COUNTDOWN) {
            startTask.disable();
        }
    }

    public void xpUpdate(){
        if (getStatus() == WaitingRoomStatus.COUNTDOWN) {
            evoGame.getOnlinePlayers().forEach(player -> player.setLevel(getStartTask().getTimeLeft()));
        } else {
            evoGame.getOnlinePlayers().forEach(player -> player.setLevel(0));
        }
    }

    public StartTask getStartTask(){
        return startTask;
    }

    public void setStartTask(StartTask runnable){
        this.startTask = runnable;
    }

    public WaitingRoomStatus getStatus() {
        return status;
    }

    public void setStatus(WaitingRoomStatus status) {
        this.status = status;
    }
}
