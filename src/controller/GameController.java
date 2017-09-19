package controller;

import model.Board;
import model.Coordinate;
import model.GameTurn;
import model.Player;
import view.FXBoard;

/**
 * Created by ejjac on 9/17/2017.
 */
public class GameController {

    public static Coordinate selected = new Coordinate(-1, -1);

    public static void move(int endx, int endy) {
        if (Board.getInstance().move(selected.getX(), selected.getY(), endx, endy)
                || Board.getInstance().jump(selected.getX(), selected.getY(), endx, endy)) {
            GameTurn.next();
        }
        FXBoard.update();
    }

    public static void resetBoard() {
        Board.getInstance().reset();
        GameTurn.setTurn(Player.BOTTOM);
        CheckersGame.getTurnLabel().setText(GameTurn.getTurn().getLabel());
        CheckersGame.getTurnLabel().setTextFill(GameTurn.getTurn().getColor());
        FXBoard.update();
    }

    public static void setSelected(int x, int y) {
        selected.setX(x);
        selected.setY(y);
    }

    public static Coordinate getSelected() {
        return selected;
    }
}
