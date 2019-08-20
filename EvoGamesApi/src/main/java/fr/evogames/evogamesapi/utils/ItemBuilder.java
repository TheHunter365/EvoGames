package fr.evogames.evogamesapi.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Map;

public interface ItemBuilder extends Cloneable {

    // Enchantment methods
    ItemBuilder addEnchantment(final Enchantment enchantment, final int level);
    ItemBuilder addEnchantments(final Map<Enchantment, Integer> enchantments);
    ItemBuilder addUnsafeEnchantment(final Enchantment enchantment, final int level);
    ItemBuilder addUnsafeEnchantments(final Map<Enchantment, Integer> enchantments);
    ItemBuilder addEnchantment(final Enchantment enchantment, final int level, final boolean ignoreLevelRestriction);

    // Lore methods
    ItemBuilder setName(final String name);
    ItemBuilder setLore(String... lore);
    ItemBuilder setLore(List<String> lore);
    ItemBuilder addLoreLine(final String line);
    ItemBuilder addLoreLines(String... lines);
    ItemBuilder addLoreLines(List<String> lines);
    boolean addLoreLine(String line, int index);
    boolean removeLoreLine(String line);
    boolean removeLoreLine(int index);

    // Texture modification methods
    ItemBuilder setDyeColor(final DyeColor color);
    ItemBuilder setWoolColor(final DyeColor color);
    ItemBuilder setLeatherArmorColor(final Color color);
    ItemBuilder setSkullOwner(final String owner);
    ItemBuilder setEffect(PotionEffectType effect, int duration, int amplifier);

    // Utils methods
    ItemBuilder setAmount(final int amount);
    ItemBuilder setData(final MaterialData data);
    ItemBuilder setDurability(final short durability);
    ItemBuilder setType(final Material type);
    ItemBuilder setTypeId(final int type);
    ItemBuilder setUnbreakable(final boolean state);
    ItemBuilder setGlowing(final boolean state);

    // Json methods
    String toJson();
    ItemBuilder fromJson(String json);

    // Get finished item
    ItemStack toItemStack();
}
