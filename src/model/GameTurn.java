package model;

import controller.CheckersGame;
import model.Player;

/**
 * Created by ejjac on 9/18/2017.
 */
public class GameTurn {
    private static Player current = Player.BOTTOM;

    public static void next() {
        if (current == Player.TOP) {
            current = Player.BOTTOM;
        } else {
            current = Player.TOP;
        }
        CheckersGame.getTurnLabel().setText(current.getLabel());
        CheckersGame.getTurnLabel().setTextFill(current.getColor());
    }

    public static Player getTurn() {
        return current;
    }

    public static void setTurn(Player p) {current = p;}
}
