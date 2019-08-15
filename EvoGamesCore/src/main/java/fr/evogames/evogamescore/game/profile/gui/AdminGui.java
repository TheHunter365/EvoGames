package fr.evogames.evogamescore.game.profile.gui;

import fr.evogames.evogamescore.game.EvoGame;
import fr.evogames.evogamescore.utils.EvoInventory.EvoInvItem;
import fr.evogames.evogamescore.utils.EvoInventory.EvoInventory;
import fr.evogames.evogamescore.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AdminGui {

    private EvoInventory inventory;
    private EvoGame evoGame;

    public AdminGui(EvoGame evoGame) {
        this.evoGame = evoGame;
        this.inventory = new EvoInventory(evoGame.getMain(), 9*3, "Admin");
    }

    public void open(Player player){
        load();
        player.openInventory(inventory.getInventory());
    }

    public void load(){
        inventory.setItem(12, new EvoInvItem(new ItemBuilder(Material.BLAZE_POWDER).setName(ChatColor.GREEN + "Démarrage").toItemStack(), event -> {
            evoGame.getWaitingRoomManager().startRunnable();
            evoGame.getWaitingRoomManager().getLaunchRunnable().setTimeLeft(11);
        }));
        inventory.setItem(13, new EvoInvItem(new ItemBuilder(Material.NETHERRACK).setName(ChatColor.RED + "STOP").toItemStack(), event -> {
            evoGame.getWaitingRoomManager().stopRunnable();
        }));
    }
}
