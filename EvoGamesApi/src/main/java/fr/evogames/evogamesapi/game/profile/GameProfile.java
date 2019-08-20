package fr.evogames.evogamesapi.game.profile;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface GameProfile {

    // Get player common information
    Player getPlayer();
    UUID getUUID();

}
