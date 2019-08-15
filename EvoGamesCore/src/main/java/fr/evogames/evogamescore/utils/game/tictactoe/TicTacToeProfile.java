package fr.evogames.evogamescore.utils.game.tictactoe;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

import java.util.Random;

public class TicTacToeProfile {

    private int id;
    private Player player;
    private DyeColor color;

    public TicTacToeProfile(Player player, DyeColor color) {
        this.player = player;
        this.color = color;
        this.id = new Random().nextInt();
    }

    public Player getPlayer() {
        return player;
    }

    public DyeColor getColor() {
        return color;
    }

    public int getId() {
        return id;
    }
}
