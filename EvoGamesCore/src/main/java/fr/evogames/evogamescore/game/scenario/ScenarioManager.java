package fr.evogames.evogamescore.game.scenario;

import fr.evogames.evogamescore.game.EvoGame;
import fr.evogames.evogamescore.game.scenario.core.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenarioManager {

    private EvoGame evoGame;

    private List<Scenario> scenarioList;
    private Scenario scenarioSelected;

    public ScenarioManager(EvoGame evoGame) {
        this.evoGame = evoGame;
        this.scenarioList = new ArrayList<>();
    }

    public void addScenario(Scenario scenario){
        getScenarioList().add(scenario);
        evoGame.getMain().getServer().getPluginManager().registerEvents(scenario, evoGame.getMain());
    }

    public List<Scenario> getScenarioList() {
        return scenarioList;
    }

    public Scenario getScenarioSelected() {
        return scenarioSelected;
    }

    public void setScenarioSelected(Scenario scenarioSelected) {
        this.scenarioSelected = scenarioSelected;
    }
}
