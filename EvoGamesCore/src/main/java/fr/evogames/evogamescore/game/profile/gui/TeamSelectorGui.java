package fr.evogames.evogamescore.game.profile.gui;

import fr.evogames.evogamescore.game.Game;
import fr.evogames.evogamescore.game.profile.GameProfile;
import fr.evogames.evogamescore.game.team.core.Team;
import fr.evogames.evogamescore.utils.evoInventory.EvoInventoryItem;
import fr.evogames.evogamescore.utils.evoInventory.EvoInventory;
import fr.evogames.evogamescore.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

public class TeamSelectorGui implements Listener {

    private EvoInventory inventory;
    private Game evoGame;
    private GameProfile gameProfile;

    public TeamSelectorGui(GameProfile gameProfile, Game evoGame) {
        this.inventory = new EvoInventory(evoGame.getMain(), 9, "Sélection d'équipe");
        this.evoGame = evoGame;
        this.gameProfile = gameProfile;

        load();
    }

    public void open(Player player){
        load();
        player.openInventory(inventory.getInventory());
    }

    public void load(){
        for (int i = 0; i < evoGame.getTeamManager().getTeamList().size(); i++) {
            Team team = evoGame.getTeamManager().getTeamList().get(i);
            inventory.setItem(i, new EvoInventoryItem(getTeamItem(team), e -> {
                if(e.getWhoClicked() instanceof Player){
                    Player player = (Player) e.getWhoClicked();
                    GameProfile gameProfile = evoGame.getGameProfileManager().get(player.getUniqueId());
                    if(team.canAdd()){
                        evoGame.getTeamManager().removePlayerFromAllTeam(gameProfile);
                        team.add(gameProfile);
                        evoGame.getWaitingRoomManager().giveItem(player);
                    }
                }
                evoGame.getGameProfileManager().getGameProfileMap().values().forEach(gameProfile -> gameProfile.getTeamSelectorGui().load());
            }));
            inventory.setItem(inventory.getInventory().getSize() - 1, new EvoInventoryItem(getRandomTeamItem(), e -> {
                evoGame.getTeamManager().removePlayerFromAllTeam(gameProfile);
                evoGame.getGameProfileManager().getGameProfileMap().values().forEach(gameProfile -> gameProfile.getTeamSelectorGui().load());
            }));
        }
    }

    public ItemStack getTeamItem(Team team){
        ItemBuilder itemBuilder = new ItemBuilder(new Wool(team.getTeamColor().getDyeColor()).toItemStack());
        itemBuilder.setAmount(team.size());
        itemBuilder.setName(team.getTeamColor().getChatColor() + team.getTeamName() + ChatColor.WHITE + " (" + team.size() + "/" + team.getLimit() + ")");
        if(team.contains(gameProfile)) {
            itemBuilder.addLoreLine(ChatColor.GREEN + "[Sélectionné]");
            itemBuilder.setGlowing(true);
        }
        team.getGameProfileList().forEach(gameProfile -> itemBuilder.addLoreLine(ChatColor.WHITE + "- " + gameProfile.getPlayer().getName()));
        return itemBuilder.toItemStack();
    }

    public ItemStack getRandomTeamItem(){
        ItemBuilder itemBuilder = new ItemBuilder(Material.BARRIER);
        itemBuilder.setName(ChatColor.RED + "Aléatoire");
        if(!evoGame.getTeamManager().isInATeam(gameProfile)){
            itemBuilder.addLoreLine(ChatColor.GREEN + "[Sélectionné]");
        }
        return itemBuilder.toItemStack();
    }
}
