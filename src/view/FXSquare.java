package view;

import controller.GameController;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import model.BoardSquare;
import model.Player;

/**
 * Created by ejjac on 9/17/2017.
 */
public class FXSquare extends StackPane {
    private ImageView background;
    private ImageView piece;
    private BoardSquare square;
    private int x;
    private int y;

    public FXSquare(BoardSquare square, int x, int y) {
        this.x = x;
        this.y = y;
        this.square = square;
        background = new ImageView();
        if ((x + y) % 2 == 0) {
            background.setImage(new Image("File:./src/view/Assets/red_square.png"));
        } else {
            background.setImage(new Image("File:./src/view/Assets/black_square.png"));
        }
        piece = new ImageView();

        Node target = background;
        Node source = piece;
        Circle overlay = new Circle(20, Color.YELLOW);


        source.setOnDragDetected(e -> {
            //System.out.println("Drag started");
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("");
            db.setContent(content);
            GameController.setSelected(this.x, this.y);
            this.getChildren().add(overlay);

            e.consume();
        });

        source.setOnDragDone(e -> {
            this.getChildren().remove(overlay);
            //System.out.println("Drag done");
            e.consume();

        });

        target.setOnDragOver(e -> {
            if (e.getGestureSource() != target &&
                    e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.ANY);
            }

            //if (!this.getChildren().contains(overlay)) {

            //System.out.println("Over");
            e.consume();
        });

        target.setOnDragEntered(e -> {
            if (e.getGestureSource() != target &&
                    e.getDragboard().hasString()) {

            }
            //overlay.setFill(Color.GREENYELLOW);
            //System.out.println("Entered");
            e.consume();
        });

        target.setOnDragExited(e -> {
            //overlay.setFill(Color.GREEN);
            //System.out.println("Exited");

            e.consume();
        });

        target.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            boolean success = false;
            if (db.hasString()) {

                //target.setText(db.getString());
                success = true;

            }
            this.getChildren().remove(overlay);
            GameController.move(this.x, this.y);

            e.setDropCompleted(success);

            e.consume();
        });

        this.getChildren().addAll(background);
        update();
    }

    public void update() {
        if (!square.isEmpty()) {
            if (square.getPiece().getPlayer() == Player.TOP) {
                if (square.getPiece().isKing()) {
                    piece.setImage(new Image("File:./src/view/Assets/black_king.png"));
                } else {
                    piece.setImage(new Image("File:./src/view/Assets/black_piece.png"));
                }
            } else {
                if (square.getPiece().isKing()) {
                    piece.setImage(new Image("File:./src/view/Assets/red_king.png"));
                } else {
                    piece.setImage(new Image("File:./src/view/Assets/red_piece.png"));
                }
            }
            if (!this.getChildren().contains(piece)) {
                this.getChildren().add(piece);
            }
        } else {
            if (this.getChildren().contains(piece)) {
                this.getChildren().remove(piece);
            }
        }
    }

    public BoardSquare getSquare() {
        return square;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public ImageView getPiece() {
        return piece;
    }

    public ImageView get_Background() {
        return background;
    }
}
