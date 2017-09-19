package controller;

import model.GameTurn;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.FXBoard;

/**
 * Created by Joey on 3/19/2017.
 */
public class CheckersGame extends Application {
    private static Label turn;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new Stage();
        primaryStage.setTitle("Checkers");
        VBox parent = new VBox();

        Button resetButton = new Button("RESET");
        resetButton.setOnAction(e -> {
            GameController.resetBoard();
        });

        turn = new Label(GameTurn.getTurn().getLabel());
        turn.setTextFill(GameTurn.getTurn().getColor());

        parent.setAlignment(Pos.CENTER);

        parent.getChildren().addAll(FXBoard.getInstance(), turn, resetButton);
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Label getTurnLabel() {
        return turn;
    }
}
