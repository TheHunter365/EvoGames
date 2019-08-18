package fr.evogames.evogamesapi.game.waitingRoom;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public interface WaitingRoomListener {

    // To know the number of player in the game
    void onJoin(PlayerJoinEvent event);
    void onQuit(PlayerQuitEvent event);

    // Player waiting state
    void onInventoryClick(InventoryClickEvent event);
    void onDrop(PlayerDropItemEvent event);
    void onEntityDamage(EntityDamageByEntityEvent event);

}
