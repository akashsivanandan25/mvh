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

    public static boolean isValidPos(Position newPosition){
        return  newPosition.getX() < Gameconstants.WORLD_WIDTH && newPosition.getY() < Gameconstants.WORLD_HEIGHT &&
                newPosition.getX() >= 0 && newPosition.getY() >= 0;
    }

    public Position move(Direction direction) {
        switch (direction) {
            case UP:
                 return new Position(x, y + 1);
            case DOWN:
                return new Position(x, y - 1);
            case LEFT:
                return  new Position(x-1, y);
            case RIGHT:
                return new Position(x+1, y);
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
