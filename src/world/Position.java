package world;

import utils.Gameconstants;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static boolean isValidPos(Position p){
        return  p.x >= 0 && p.x < Gameconstants.WORLD_HEIGHT &&
                p.y >= 0 && p.y < Gameconstants.WORLD_WIDTH;
    }

    public Position move(Direction direction) {
        switch (direction) {
            case UP:
                return new Position(x - 1, y);
            case DOWN:
                return new Position(x + 1, y);
            case LEFT:
                return new Position(x, y - 1);
            case RIGHT:
                return new Position(x, y + 1);
            default:
                return this;
        }
    }
    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof Position)){
            return false;
        }

        Position otherPosition = (Position)other;

        return x == otherPosition.x && y == otherPosition.y;

    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }


}
