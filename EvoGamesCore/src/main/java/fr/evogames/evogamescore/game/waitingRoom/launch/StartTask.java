package fr.evogames.evogamescore.game.waitingRoom.launch;

import fr.evogames.evogamescore.game.Game;
import fr.evogames.evogamescore.game.waitingRoom.WRScoreBoard;
import fr.evogames.evogamescore.game.waitingRoom.WaitingRoomStatus;
import fr.evogames.evogamescore.utils.Title;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private Game evoGame;
    private int timeLeft;

    public StartTask(Game evoGame) {
        this.evoGame = evoGame;
        this.timeLeft = 60;
    }

    @Override
    public void run() {
        this.timeLeft--;
        evoGame.getOnlinePlayers().forEach(player -> {
            player.setLevel(timeLeft);
            if(timeLeft == 10){
                Title.sendFullTitle(player, 10, 20*5, 10, ChatColor.AQUA + evoGame.getName(), ChatColor.GOLD + evoGame.getDescription());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
            }
            if(timeLeft <= 5) {
                Title.sendFullTitle(player, 0, 20, 10, "", ChatColor.GRAY + "Démarrage de la partie dans " + timeLeft);
            }
        });
        if(timeLeft == 0){
            disableSilent();
            evoGame.launch();
            evoGame.getOnlinePlayers().forEach(player -> Title.sendFullTitle(player, 5, 20, 10, ChatColor.GOLD + "C'est parti !", ""));
        } else {
            evoGame.getGameProfileManager().getGameProfileMap().values().forEach(gameProfiles -> WRScoreBoard.waitingPlayer(evoGame, gameProfiles));
        }
    }

    public void enable(){
        evoGame.broadcast(ChatColor.YELLOW + "Démarrage du compte à rebours, assez de joueurs");
        evoGame.getWaitingRoomManager().setStatus(WaitingRoomStatus.COUNTDOWN);
        this.runTaskTimer(evoGame.getMain(), 20, 20);
    }

    public void disable(){
        evoGame.broadcast(org.bukkit.ChatColor.YELLOW + "Arrêt du compte à rebours, pas assez de joueurs");
        disableSilent();
    }

    public void disableSilent(){
        cancel();
        evoGame.getWaitingRoomManager().setStatus(WaitingRoomStatus.WAITING_PLAYER);
        evoGame.getWaitingRoomManager().setStartTask(new StartTask(evoGame));
    }

    public int getTimeLeft(){
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft){
        this.timeLeft = timeLeft;
    }
}
