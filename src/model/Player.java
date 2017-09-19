package model;

import javafx.scene.paint.Color;

/**
 * Created by Joey on 3/19/2017.
 */
public enum Player {
    TOP ("BLACK", Color.BLACK),
    BOTTOM ("RED", Color.RED);

    String label;
    Color c;

    Player(String label, Color c) {
        this.label = label;
        this.c = c;
    }

    public String getLabel() {
        return label;
    }

    public Color getColor() {
        return c;
    }


}
