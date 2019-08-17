package fr.evogames.evogamescore.game.profile.library;

import fr.evogames.evogamescore.game.EvoGame;
import fr.evogames.evogamescore.game.profile.GameProfile;
import fr.evogames.evogamescore.game.profile.gui.AdminGui;
import fr.evogames.evogamescore.game.profile.gui.ScenarioGui;
import fr.evogames.evogamescore.utils.evoItem.EvoItem;
import fr.evogames.evogamescore.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemLibrary {

    private GameProfile gameProfile;
    private EvoGame evoGame;

    public ItemLibrary(GameProfile gameProfile, EvoGame evoGame) {
        this.gameProfile = gameProfile;
        this.evoGame = evoGame;
    }

    public EvoItem getTeamSelectorItem(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.STAINED_CLAY);
        if(evoGame.getTeamManager().getTeam(gameProfile) != null){
            itemBuilder.setDyeColor(evoGame.getTeamManager().getTeam(gameProfile).getTeamColor().getDyeColor());
        } else {
            itemBuilder.setType(Material.HARD_CLAY);
        }
        itemBuilder.setName(ChatColor.GOLD + "Choisie une équipe !");
        return new EvoItem(itemBuilder.toItemStack(), event -> {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if(evoGame.getGameProfileManager().getGameProfileMap().keySet().contains(uuid)) {
                Action action = event.getAction();
                //if(evoGame.getState() == EvoGameStatus.WAITING) {
                    if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                        evoGame.getGameProfileManager().get(uuid).getTeamSelectorGui().open(player);
                    }
                //}
            }
            event.setCancelled(true);
        }, evoGame.getMain());
    }

    public ItemStack getScenarioItem(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.NETHER_STAR);
        itemBuilder.setName(ChatColor.GRAY + "Sélection de scénario !");
        return new EvoItem(itemBuilder.toItemStack(), event -> {
            Player player = event.getPlayer();
            new ScenarioGui(evoGame).open(player);
            event.setCancelled(true);
        }, evoGame.getMain()).getItemStack();
    }

    public ItemStack getAdminItem(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.BLAZE_POWDER);
        itemBuilder.setName(ChatColor.RED + "Admin Menu");
        return new EvoItem(itemBuilder.toItemStack(), event -> {
            Player player = event.getPlayer();
            new AdminGui(evoGame).open(player);
            event.setCancelled(true);
        }, evoGame.getMain()).getItemStack();
    }
}
