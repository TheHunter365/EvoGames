package fr.evogames.evogamescore.player;

import java.util.HashSet;
import java.util.Set;

public class EvoRank implements fr.evogames.evogamesapi.player.EvoRank {

    private String name;
    private Set<String> permissions;

    public EvoRank(String name) {
        this.name = name;
        this.permissions = new HashSet<>();
    }

    public void addPermission(String permission) {
        this.permissions.add(permission);
    }

    public void removePermission(String permission) {
        this.permissions.remove(permission);
    }

    public String getName() {
        return this.name;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }
}
