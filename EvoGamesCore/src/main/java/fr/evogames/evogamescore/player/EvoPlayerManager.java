package fr.evogames.evogamescore.player;

import fr.evogames.evogamesapi.player.EvoPlayer;

import java.util.Set;
import java.util.UUID;

public class EvoPlayerManager implements fr.evogames.evogamesapi.player.EvoPlayerManager {

    private Set<EvoPlayer> evoPlayerSet;

    @Override
    public EvoPlayer getPlayer(UUID playerId) {
        return evoPlayerSet.stream().filter(evoPlayer -> evoPlayer.getUniqueId().equals(playerId)).findFirst().orElse(null);
    }
}
