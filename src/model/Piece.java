package model;

/**
 * Created by Joey on 3/14/2017.
 */
public class Piece {
    private int x;
    private int y;
    private Player p;
    private boolean isKing;

    public Piece(int x, int y, Player p, boolean isKing) {
        this.x = x;
        this.y = y;
        this.p = p;
        this.isKing = isKing;
    }

    public boolean isValidMove(int toX, int toY) {
        if (toX < 0 || toX > 7 || toY < 0 || toY > 7) {
            throw new IndexOutOfBoundsException("Cannot move off board");
        }
        if (isKing) {
            if ((y - 1 == toY || y + 1 == toY)
                    && (x + 1 == toX || x - 1 == toX)) {
                return true;
            }
        } else if (p == Player.TOP) {
            if ((y + 1 == toY)
                && (x + 1 == toX || x - 1 == toX)) {
                return true;
            }
        } else {
            if ((y - 1 == toY)
                && (x + 1 == toX || x - 1 == toX)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidJump(int toX, int toY) {
        if (toX < 0 || toX > 7 || toY < 0 || toY > 7) {
            throw new IndexOutOfBoundsException("Cannot move off board");
        }
        if (p == Player.TOP) {
            if (isKing) {
                if (toX == x + 2 && toY == y - 2
                        && !Board.getTile(x + 1, y - 1).isEmpty()
                        && Board.getTile(x + 1, y - 1)
                        .getPiece().getPlayer() == Player.BOTTOM) {
                    return true;
                } else if (toX == x - 2 && toY == y - 2
                        && !Board.getTile(x - 1, y - 1).isEmpty()
                        && Board.getTile(x - 1, y - 1)
                        .getPiece().getPlayer() == Player.BOTTOM) {
                    return true;
                }
            }
            if (toX == x + 2 && toY == y + 2
                    && !Board.getTile(x + 1, y + 1).isEmpty()
                    && Board.getTile(x + 1, y + 1)
                    .getPiece().getPlayer() == Player.BOTTOM) {
                return true;
            } else if (toX == x - 2 && toY == y + 2
                    && !Board.getTile(x - 1, y + 1).isEmpty()
                    && Board.getTile(x - 1, y + 1)
                    .getPiece().getPlayer() == Player.BOTTOM) {
                return true;
            }
        } else {
            if (isKing) {
                if (toX == x + 2 && toY == y + 2
                        && !Board.getTile(x + 1, y + 1).isEmpty()
                        && Board.getTile(x + 1, y + 1)
                        .getPiece().getPlayer() == Player.TOP) {
                    return true;
                } else if (toX == x - 2 && toY == y + 2
                        && !Board.getTile(x - 1, y + 1).isEmpty()
                        && Board.getTile(x - 1, y + 1)
                        .getPiece().getPlayer() == Player.TOP) {
                    return true;
                }
            }
            if (toX == x + 2 && toY == y - 2
                    && !Board.getTile(x + 1, y - 1).isEmpty()
                    && Board.getTile(x + 1, y - 1)
                    .getPiece().getPlayer() == Player.TOP) {
                return true;
            } else if (toX == x - 2 && toY == y - 2
                    && !Board.getTile(x - 1, y - 1).isEmpty()
                    && Board.getTile(x - 1, y - 1)
                    .getPiece().getPlayer() == Player.TOP) {
                return true;
            }
        }
        return false;
    }

    private void testKing() {
        if ((p == Player.BOTTOM && y == 0)
                || (p == Player.TOP && y == Board.getGrid()[0].length - 1)) {
            isKing = true;
        }
    }

    public boolean move(int toX, int toY) {
        if (isValidMove(toX, toY)) {
            if (Board.getTile(toX, toY).isEmpty()) {
                Board.getTile(x, y).clear();
                x = toX;
                y = toY;
                Board.getTile(x, y).setPiece(this);
                testKing();
                return true;
            }
        }
        return false;
    }

    public boolean jump(int toX, int toY) {
        if (isValidJump(toX, toY)) {
            Board.getTile(x, y).clear();
            Board.getTile(((toX - x) / 2) + x, ((toY - y) / 2) + y).clear();
            x = toX;
            y = toY;
            Board.getTile(x, y).setPiece(this);
            testKing();
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Player getPlayer() {
        return p;
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

    public void setKing(boolean isKing) {
        this.isKing = isKing;
    }

    public boolean isKing() {
        return isKing;
    }
}