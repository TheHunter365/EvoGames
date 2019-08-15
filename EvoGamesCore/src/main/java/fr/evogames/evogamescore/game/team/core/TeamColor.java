package fr.evogames.evogamescore.game.team.core;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;

public enum TeamColor {

    RED("Rouge", ChatColor.RED, DyeColor.RED, Color.RED),
    BLUE("Bleu", ChatColor.BLUE, DyeColor.BLUE, Color.BLUE),
    GREEN("Vert", ChatColor.GREEN, DyeColor.GREEN, Color.GREEN),
    YELLOW("Jaune", ChatColor.YELLOW, DyeColor.YELLOW, Color.YELLOW),
    WHITE("Blanc", ChatColor.WHITE, DyeColor.WHITE, Color.WHITE),
    BLACK("Noir", ChatColor.DARK_GRAY, DyeColor.BLACK, Color.BLACK),
    PURPLE("Violet", ChatColor.LIGHT_PURPLE, DyeColor.PURPLE, Color.PURPLE),
    ORANGE("Orange", ChatColor.GOLD, DyeColor.ORANGE, Color.ORANGE);

    private String string;
    private ChatColor chatColor;
    private DyeColor dyeColor;
    private Color color;

    TeamColor(String string, ChatColor chatColor, DyeColor dyeColor, Color color) {
        this.string = string;
        this.chatColor = chatColor;
        this.dyeColor = dyeColor;
        this.color = color;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return string;
    }
}
