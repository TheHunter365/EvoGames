package fr.evogames.evogamescore.utils.evoInventory;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class EvoInventoryItem implements fr.evogames.evogamesapi.utils.inventory.EvoInventoryItem {

    private ItemStack itemStack;
    private Consumer<InventoryClickEvent> clickEventConsumer;

    public EvoInventoryItem(ItemStack itemStack, Consumer<InventoryClickEvent> clickEventConsumer) {
        this.itemStack = itemStack;
        this.clickEventConsumer = clickEventConsumer;
    }

    public EvoInventoryItem(Material material, Consumer<InventoryClickEvent> clickEventConsumer) {
        this.itemStack = new ItemStack(material);
        this.clickEventConsumer = clickEventConsumer;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Consumer<InventoryClickEvent> getClickEventConsumer() {
        return clickEventConsumer;
    }
}
