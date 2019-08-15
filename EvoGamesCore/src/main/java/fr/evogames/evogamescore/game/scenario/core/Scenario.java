package fr.evogames.evogamescore.game.scenario.core;

import fr.evogames.evogamescore.game.EvoGame;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import java.util.List;

public abstract class Scenario implements Listener {

    private EvoGame evoGame;

    public Scenario(EvoGame evoGame) {
        this.evoGame = evoGame;
    }

    abstract public String getName();
    abstract public List<String> getDescription();

    abstract public Material getMaterial();
    abstract public short getData();

    public EvoGame getEvoGame() {
        return evoGame;
    }
}