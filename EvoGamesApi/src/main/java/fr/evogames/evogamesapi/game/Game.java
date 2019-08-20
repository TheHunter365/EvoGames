package fr.evogames.evogamesapi.game;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class Game {

    public abstract void enable();

    public abstract List<Player> getOnlinePlayers();
    public abstract String getName();

    public abstract String getDescription();
}