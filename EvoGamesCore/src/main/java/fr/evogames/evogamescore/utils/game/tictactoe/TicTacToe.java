package fr.evogames.evogamescore.utils.game.tictactoe;

import fr.evogames.evogamescore.utils.EvoInventory.EvoInvItem;
import fr.evogames.evogamescore.utils.EvoInventory.EvoInventory;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicTacToe {

    private EvoInventory inventory;

    private JavaPlugin javaPlugin;
    private int[] grid;

    private List<TicTacToeProfile> tttProfileList;
    private TicTacToeProfile whoHasToPlay;

    public TicTacToe(JavaPlugin javaPlugin, Player p1, Player p2) {
        this.javaPlugin = javaPlugin;
        this.tttProfileList = new ArrayList<>();
        this.tttProfileList.add(new TicTacToeProfile(p1, DyeColor.RED));
        this.tttProfileList.add(new TicTacToeProfile(p2, DyeColor.BLUE));
        this.inventory = new EvoInventory(javaPlugin, InventoryType.DISPENSER, "TicTacToe");
        defaultLoad();
    }

    public void defaultLoad(){
        grid = new int[]{
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        };
        setWhoHasToPlay(tttProfileList.get(new Random().nextInt(tttProfileList.size())));
        nextTurn();
        loadInventory();
        tttProfileList.forEach(ticTacToeProfile -> ticTacToeProfile.getPlayer().openInventory(inventory.getInventory()));
    }

    public void loadInventory(){
        for (int i = 0; i < grid.length; i++) {
            inventory.setItem(i, getItem(i));
        }
    }

    public TicTacToeProfile getProfile(Player player){
        return tttProfileList.stream().filter(ticTacToeProfile -> ticTacToeProfile.getPlayer().equals(player)).findFirst().orElse(null);
    }

    public EvoInvItem getItem(int i){
        TicTacToeProfile itemTTTProfile = tttProfileList.stream().filter(ticTacToeProfiles -> grid[i] == ticTacToeProfiles.getId()).findFirst().orElse(null);
        ItemStack itemStack = itemTTTProfile != null ? new Wool(itemTTTProfile.getColor()).toItemStack() : new Wool(DyeColor.GRAY).toItemStack();
        itemStack.setAmount(1);
        return new EvoInvItem(itemStack, event -> {
            if(event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();
                TicTacToeProfile ticTacToeProfile = getProfile(player);
                if(ticTacToeProfile == getWhoHasToPlay()) {
                    int id = ticTacToeProfile.getId();
                    if(grid[event.getSlot()] == 0) {
                        grid[event.getSlot()] = id;
                        if (doesHeWin(id)) {
                            win(ticTacToeProfile);
                        }
                        nextTurn();
                        loadInventory();
                    }
                }
            }
        });
    }

    public void win(TicTacToeProfile ticTacToeProfile){
        tttProfileList.forEach(ticTacToeProfiles -> ticTacToeProfiles.getPlayer().closeInventory());
        tttProfileList.forEach(ticTacToeProfiles -> ticTacToeProfiles.getPlayer().sendMessage("Bravo à " + ticTacToeProfile.getPlayer().getName() + " qui a gagné le TicTacToe !"));
    }

    public boolean doesHeWin(int i){
        if(grid[0] == i && grid[1] == i && grid[2] == i) {
            return true;
        }
        if(grid[3] == i && grid[4] == i && grid[5] == i) {
            return true;
        }
        if(grid[6] == i && grid[7] == i && grid[8] == i) {
            return true;
        }
        if(grid[0] == i && grid[3] == i && grid[6] == i) {
            return true;
        }
        if(grid[1] == i && grid[4] == i && grid[7] == i) {
            return true;
        }
        if(grid[2] == i && grid[5] == i && grid[8] == i) {
            return true;
        }
        if(grid[0] == i && grid[4] == i && grid[8] == i) {
            return true;
        }
        if(grid[2] == i && grid[4] == i && grid[6] == i) {
            return true;
        }
        return false;
    }

    public void nextTurn(){
        int indexOfTurn = tttProfileList.indexOf(getWhoHasToPlay());
        whoHasToPlay = tttProfileList.size() == indexOfTurn + 1
                ? tttProfileList.get(0)
                : tttProfileList.get(indexOfTurn + 1);
        inventory.setName("TicTacToe | " + whoHasToPlay.getPlayer().getName());
    }

    public TicTacToeProfile getWhoHasToPlay(){
        return whoHasToPlay;
    }

    public void setWhoHasToPlay(TicTacToeProfile ticTacToeProfile){
        this.whoHasToPlay = ticTacToeProfile;
    }
}
