package model;

import java.util.NoSuchElementException;

/**
 * Created by ejjac on 9/16/2017.
 */
public class BoardSquare {
    Piece piece;

    public BoardSquare() {
    }

    public BoardSquare(Piece piece) {
        this.piece = piece;
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece getPiece() {
        if (isEmpty()) {
            throw new NoSuchElementException("No piece on this square");
        }
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void clear() {
        this.piece = null;
    }
}
