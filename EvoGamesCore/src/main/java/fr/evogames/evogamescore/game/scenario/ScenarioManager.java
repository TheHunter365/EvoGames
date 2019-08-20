package fr.evogames.evogamescore.game.scenario;

import fr.evogames.evogamesapi.game.scenario.core.Scenario;
import fr.evogames.evogamescore.game.Game;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class ScenarioManager implements fr.evogames.evogamesapi.game.scenario.ScenarioManager {

    private Game evoGame;

    private List<Scenario> scenarioList;
    private Scenario selectedScenario;

    public ScenarioManager(Game evoGame) {
        this.evoGame = evoGame;
        this.scenarioList = new ArrayList<>();
    }

    // add a new possible scenario to the game
    @Override
    public void addScenario(Scenario scenario){
        getScenarioList().add(scenario);
        evoGame.getMain().getServer().getPluginManager().registerEvents(scenario, evoGame.getMain());
    }

    // get all possible scenario
    @Override
    public List<Scenario> getScenarioList() {
        return scenarioList;
    }

    // select a scenario to play
    @Override
    public void selectScenario(Scenario selectedScenario) {
        this.selectedScenario = selectedScenario;
    }

    // select randomly a scenario to play
    @Override
    public void setRandomScenario() {
        if (!scenarioList.isEmpty()) {
            this.selectedScenario = scenarioList.get(new Random().nextInt(scenarioList.size()));
        } else {
            Bukkit.getLogger().log(Level.WARNING, "No scenario loaded.");
        }
    }

    // get the selected scenario
    @Override
    public Scenario getSelectedScenario() {
        return selectedScenario;
    }
}
