package fr.evogames.evogamescore.game.waitingRoom;

import fr.evogames.evogamescore.game.EvoGame;
import fr.evogames.evogamescore.game.EvoGameStatus;
import fr.evogames.evogamescore.game.waitingRoom.launch.LaunchRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class WaitingRoomManager {

    private EvoGame evoGame;
    private WaitingRoomStatus status;
    private LaunchRunnable launchRunnable;

    public WaitingRoomManager(EvoGame evoGame) {
        this.evoGame = evoGame;
        this.launchRunnable = new LaunchRunnable(evoGame);
        this.status = WaitingRoomStatus.WAITING_PLAYER;
        Bukkit.getPluginManager().registerEvents(new WaitingRoomListener(evoGame), evoGame.getMain());
    }

    public void giveItem(Player player){
        if(evoGame.getState() == EvoGameStatus.WAITING) {
            UUID uuid = player.getUniqueId();
            player.getInventory().setItem(0, evoGame.getGameProfileManager().get(uuid).getItemLibrary().getTeamSelectorItem().getItemStack());
            if(player.hasPermission("evoGame.admin")){
                player.getInventory().setItem(8, evoGame.getGameProfileManager().get(uuid).getItemLibrary().getScenarioItem());
                player.getInventory().setItem(7, evoGame.getGameProfileManager().get(uuid).getItemLibrary().getAdminItem());
            }
        }
    }

    public String getPlayerTotalString(){
        return evoGame.getGameProfileManager().size() + "/" + evoGame.getTeamManager().getLimit();
    }

    public void startRunnable(){
        if(status == WaitingRoomStatus.WAITING_PLAYER) {
            launchRunnable.enable();
        }
    }

    public void stopRunnable(){
        if(status == WaitingRoomStatus.COUNTDOWN) {
            launchRunnable.disable();
        }
    }

    public void xpUpdate(){
        if(getStatus() == WaitingRoomStatus.COUNTDOWN) {
            evoGame.getOnlinePlayers().forEach(player -> player.setLevel(getLaunchRunnable().getTimeLeft()));
        } else {
            evoGame.getOnlinePlayers().forEach(player -> player.setLevel(0));
        }
    }

    public LaunchRunnable getLaunchRunnable(){
        return launchRunnable;
    }

    public void setLaunchRunnable(LaunchRunnable runnable){
        this.launchRunnable = runnable;
    }

    public WaitingRoomStatus getStatus() {
        return status;
    }

    public void setStatus(WaitingRoomStatus status) {
        this.status = status;
    }
}
