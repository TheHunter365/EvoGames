package fr.evogames.evogamescore.game.waitingRoom;

import fr.evogames.evogamescore.game.Game;
import fr.evogames.evogamesapi.game.status.EvoGameStatus;
import fr.evogames.evogamescore.game.profile.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class WaitingRoomListener implements Listener, fr.evogames.evogamesapi.game.waitingRoom.WaitingRoomListener {

    private Game evoGame;

    public WaitingRoomListener(Game evoGame) {
        this.evoGame = evoGame;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        if (evoGame.getState() == EvoGameStatus.WAITING) {
            Player player = event.getPlayer();

            evoGame.broadcast(ChatColor.GREEN + "+ " + ChatColor.YELLOW + player.getName() + " s'est connecté. " + ChatColor.RED + "(" + evoGame.getGameProfileManager().size() + "/" + evoGame.getTeamManager().getLimit() + ")");
            evoGame.getWaitingRoomManager().giveItem(player);

            player.teleport(new Location(Bukkit.getWorld("world"), 1002, 104, 971));
            player.setGameMode(GameMode.ADVENTURE);

            check();
            evoGame.getGameProfileManager().getGameProfileMap().values().forEach(gameProfiles -> WRScoreBoard.waitingPlayer(evoGame, gameProfiles));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onQuit(PlayerQuitEvent event){
        if(evoGame.getState() == EvoGameStatus.WAITING) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            evoGame.getGameProfileManager().remove(uuid);
            evoGame.getGameProfileManager().getGameProfileMap().values().forEach(gameProfile -> gameProfile.getTeamSelectorGui().load());
            evoGame.broadcast(ChatColor.RED + "- " + ChatColor.YELLOW + player.getName() + " s'est déconnecté. " + ChatColor.RED + "(" + evoGame.getGameProfileManager().size() + "/" + evoGame.getTeamManager().getLimit() + ")");
            check();
            evoGame.getGameProfileManager().getGameProfileMap().values().forEach(gameProfiles -> WRScoreBoard.waitingPlayer(evoGame, gameProfiles));
        }
    }

    public void check(){
        if(evoGame.getOnlinePlayers().size() >= 2) {
            evoGame.getWaitingRoomManager().startRunnable();
        } else {
            evoGame.getWaitingRoomManager().stopRunnable();
        }
        evoGame.getWaitingRoomManager().xpUpdate();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (evoGame.getState() == EvoGameStatus.WAITING) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if (evoGame.getState() == EvoGameStatus.WAITING) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){
        if (evoGame.getState() == EvoGameStatus.WAITING) {
            event.setCancelled(true);
        }
    }
}
