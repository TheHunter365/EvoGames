package fr.evogames.evogamesapi.game.scenario;

import fr.evogames.evogamesapi.game.scenario.core.Scenario;

import java.util.List;

public interface ScenarioManager {

    void addScenario(Scenario scenario);

    // to select a scenario
    void selectScenario(Scenario scenario);
    void setRandomScenario();

    // to get information about scenario
    List<Scenario> getScenarioList();
    Scenario getSelectedScenario();

}
