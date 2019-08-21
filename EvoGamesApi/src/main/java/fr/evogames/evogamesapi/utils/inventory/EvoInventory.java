package fr.evogames.evogamesapi.utils.inventory;

public interface EvoInventory {

    // Item placement methods
    void setItem(int slot, EvoInventoryItem item);
    void removeItem(int slot);

    // Name methods
    void setName(String name);
    String getName();
}
