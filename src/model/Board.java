package model;

/**
 * Created by ejjac on 9/16/2017.
 */
public class Board {
    private static BoardSquare[][] grid;
    private static Board instance = new Board();

    private Board() {
        grid = new BoardSquare[8][8];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new BoardSquare();
            }
        }
        reset();
    }

    public static BoardSquare[][] getGrid() {
        return grid;
    }

    public static BoardSquare getTile(int x, int y) {
        return grid[x][y];
    }

    public static Board getInstance() {
        return instance;
    }

    public static void clear() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                getTile(i, j).clear();
            }
        }
    }

    public static void reset() {
        clear();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i + j) % 2 == 0) {
                    getTile(i, j).setPiece(new Piece(i, j, Player.TOP, false));
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = grid[i].length - 3; j < grid[i].length; j++) {
                if ((i + j) % 2 == 0) {
                    getTile(i, j).setPiece(new Piece(i, j, Player.BOTTOM, false));
                }
            }
        }
    }

    public static boolean move(int startx, int starty, int endx, int endy) {
        Piece p;
        if (!getTile(startx, starty).isEmpty()) {
            p = getTile(startx, starty).getPiece();
            if (p.getPlayer() == GameTurn.getTurn()) {
                return p.move(endx, endy);
            } else {
                return false;
            }
        } else {
            throw new IllegalArgumentException("No piece at (" + startx + ", " + starty + ")");
        }
    }

    public static boolean jump(int startx, int starty, int endx, int endy) {
        Piece p;
        if (!getTile(startx, starty).isEmpty()) {
            p = getTile(startx, starty).getPiece();
            if (p.getPlayer() == GameTurn.getTurn()) {
                return p.jump(endx, endy);
            } else {
                return false;
            }
        } else {
            throw new IllegalArgumentException("No piece at (" + startx + ", " + starty + ")");
        }
    }
}
