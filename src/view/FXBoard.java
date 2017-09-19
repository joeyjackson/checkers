package view;

import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import model.Board;

/**
 * Created by ejjac on 9/17/2017.
 */
public class FXBoard extends GridPane {
    private static FXBoard instance = new FXBoard();

    public FXBoard() {

        for (int i = 0; i < Board.getGrid().length; i++) {
            for (int j = 0; j < Board.getGrid()[i].length; j++) {
                FXSquare s = new FXSquare(Board.getTile(i, j), i, j);
                this.add(s, i, j);
            }
        }
    }

    public static FXBoard getInstance() {
        return instance;
    }

    public static void update() {
        instance.getChildren().forEach(node -> {
            ((FXSquare) node).update();
        });
    }
}
