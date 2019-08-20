package fr.evogames.evogamesapi.game.scenario.core;

import fr.evogames.evogamesapi.game.Game;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.List;

public abstract class Scenario implements Listener {

    private Game game;

    public Scenario(Game game) {
        this.game = game;
    }

    abstract public String getName();
    abstract public List<String> getDescription();

    abstract public Material getMaterial();
    abstract public short getData();

    public Game getGame() {
        return game;
    }
}
