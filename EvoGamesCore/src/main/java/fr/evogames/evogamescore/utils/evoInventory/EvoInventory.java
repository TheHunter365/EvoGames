package fr.evogames.evogamescore.utils.evoInventory;

import fr.evogames.evogamesapi.utils.inventory.EvoInventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvoInventory implements fr.evogames.evogamesapi.utils.inventory.EvoInventory {

    private transient JavaPlugin main;
    private transient Inventory inventory;
    private transient EvoInventoryListener oldListener;

    private String name;
    private Map<Integer, EvoInventoryItem> invItemMap = new HashMap<>();

    public EvoInventory(JavaPlugin main, int size, String name) {
        this.main = main;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, size, name);

        defaultLoad();
    }

    public EvoInventory(JavaPlugin main, InventoryType inventoryType, String name) {
        this.main = main;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, inventoryType, name);

        defaultLoad();
    }

    public EvoInventory(JavaPlugin main, Inventory inventory) {
        this.main = main;
        this.name = inventory.getName();
        this.inventory = inventory;

        defaultLoad();
    }

    public void defaultLoad() {
        update();
    }

    public void setItem(int slot, EvoInventoryItem item) {
        this.invItemMap.put(slot, item);
        update();
    }

    public void removeItem(int slot){
        this.invItemMap.remove(slot);
    }

    public void clear() {
        this.invItemMap.clear();
        update();
    }

    public void update() {
        registerItems();
        registerEvent();
    }

    private void registerItems() {
        this.invItemMap.forEach((slot, item)-> inventory.setItem(slot, item.getItemStack()));
    }

    private void registerEvent() {
        if(oldListener != null) InventoryClickEvent.getHandlerList().unregister(oldListener);
        EvoInventoryListener event = new EvoInventoryListener(this);
        Bukkit.getServer().getPluginManager().registerEvents(
                event,
                main
        );
        oldListener = event;
    }

    public void setName(String name) {
        List<Player> playerList = new ArrayList<>();
        for (HumanEntity humanEntity : inventory.getViewers()) {
            if (humanEntity instanceof Player) {
                Player player = (Player) humanEntity;
                playerList.add(player);
            }
        }
        this.inventory = Bukkit.createInventory(null, inventory.getType(), inventory.getName());
        update();
        playerList.forEach(player -> player.openInventory(inventory));
    }

    public String getName() {
        return name;
    }

    public Map<Integer, EvoInventoryItem> getInvItemMap() {
        return invItemMap;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
