package fr.evogames.evogamesapi.player;

import java.util.UUID;

public interface EvoPlayerManager {

    EvoPlayer getPlayer(UUID playerId);

}
