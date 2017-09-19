package Tests;

import model.Board;
import model.Piece;
import model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ejjac on 9/16/2017.
 */
public class ModelTests {

    public static final int TIMEOUT = 200;
    Piece piece;
    Board b = Board.getInstance();

    @Before
    public void setup() {
    }

    @Test(timeout = TIMEOUT)
    public void testIsValidMoveRegular() {
        Piece piece1 = new Piece(0, 0, Player.TOP, false);
        assertTrue(piece1.isValidMove(1, 1));
        Piece piece2 = new Piece(2, 2, Player.TOP, false);
        assertFalse(piece2.isValidMove(3, 1));
        assertTrue(piece2.isValidMove(1, 3));
        assertFalse(piece2.isValidMove(2, 3));
    }

    @Test(timeout = TIMEOUT)
    public void testMoveRegular() {
        b.getTile(2, 2).setPiece(new Piece(2, 2, Player.BOTTOM, false));
        b.getTile(3, 1).setPiece(new Piece(3, 1, Player.TOP, false));
        assertFalse(b.getTile(3, 1).getPiece().move(2, 2));
        assertTrue(b.getTile(2, 2).getPiece().move(1, 1));
        assertTrue(b.getTile(3, 1).getPiece().move(2, 2));
    }

    @Test(timeout = TIMEOUT)
    public void testJumpRegular() {
        b.getTile(2, 2).setPiece(new Piece(2, 2, Player.TOP, true));
        b.getTile(3, 1).setPiece(new Piece(3, 1, Player.BOTTOM, false));
        assertFalse(b.getTile(3, 1).getPiece().jump(1, 3));
        assertTrue(b.getTile(2,2).getPiece().jump(4, 0));
        assertTrue(b.getTile(4, 0).getPiece().move(3, 1));
    }
}
