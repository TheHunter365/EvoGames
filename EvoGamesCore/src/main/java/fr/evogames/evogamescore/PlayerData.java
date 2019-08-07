package fr.evogames.evogamescore;

import java.util.UUID;

public class PlayerData implements fr.evogames.evogamesapi.PlayerData {

    private UUID uuid;
    private String name;

    public PlayerData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }
}
