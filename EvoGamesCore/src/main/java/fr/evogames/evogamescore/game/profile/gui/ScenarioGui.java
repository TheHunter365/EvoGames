package fr.evogames.evogamescore.game.profile.gui;

import fr.evogames.evogamescore.game.Game;
import fr.evogames.evogamescore.game.scenario.ScenarioManager;
import fr.evogames.evogamescore.game.scenario.core.Scenario;
import fr.evogames.evogamescore.utils.evoInventory.EvoInvItem;
import fr.evogames.evogamescore.utils.evoInventory.EvoInventory;
import fr.evogames.evogamescore.utils.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ScenarioGui {

    private Game evoGame;
    private EvoInventory evoInventory;

    public ScenarioGui(Game evoGame) {
        this.evoGame = evoGame;
        this.evoInventory = new EvoInventory(evoGame.getMain(), 9*3, "Sélection de scénario");
    }

    public void open(Player player){
        load();
        player.openInventory(evoInventory.getInventory());
    }

    public void load(){
        ScenarioManager scenarioManager = evoGame.getScenarioManager();
        for (int i = 0; i < scenarioManager.getScenarioList().size(); i++) {
            Scenario scenario = scenarioManager.getScenarioList().get(i);
            evoInventory.setItem(i, new EvoInvItem(getItem(scenario), event -> {
                scenarioManager.setScenarioSelected(scenario);
                load();
            }));
        }
    }

    public ItemStack getItem(Scenario scenario){
        ScenarioManager scenarioManager = evoGame.getScenarioManager();
        ItemBuilder itemBuilder = new ItemBuilder(scenario.getMaterial()).setDurability(scenario.getData());
        itemBuilder.setName(scenario.getName());
        if(scenarioManager.getScenarioSelected().equals(scenario)) {
            itemBuilder.setGlowing(true);
        }
        return itemBuilder.toItemStack();
    }
}
