/**
 * Classe représantant un mouvement
 */
public class Move {
    private Coordinate coordinates;
    private Direction direction;

    public Move(Coordinate coordinate, Direction direction) {
        this.coordinates  = coordinate;
        this.direction = direction;
    }

    public Coordinate getCoordinate() {
        return this.coordinates;
    }

    public Direction getDirection() {
        return this.direction;
    } 

    public Move process(char command) {
        if (command == 'b') {
            moveBackward();
        } else if (command == 'f') {
            moveForward();
        } else if (command == 'l') {
            turnLeft();
        } else if (command == 'r') {
            turnRight();
        } else {
            return null;
        }
        return new Move(this.coordinates, this.direction);
    }

    public Coordinate moveForward() {
        
        switch (direction) {
            case NORTH:
                if (!coordinates.isAtNorthPole()) {
                    coordinates.decrementY();
                } else {
                    coordinates.incrementY();
                    direction = Direction.SOUTH;
                }
                break;
            case SOUTH:
                if(!coordinates.isAtSouthPole()) {
                    coordinates.incrementY();
                } else {
                    coordinates.decrementY();
                    direction = Direction.NORTH;
                }
                break;
            case EAST:
                if (coordinates.getX() != Coordinate.getXMAX()) {
                    coordinates.incrementX();
                } else {
                    coordinates.wrapXMax();
                }
                break;
            case WEST:
                if (coordinates.getX() != Coordinate.getXMIN()) {
                    coordinates.decrementX();
                } else {
                    coordinates.wrapXMin();
                }
                break;
            default:
                break;
        }
        return this.coordinates;
    }
    public Coordinate moveBackward() {
        switch (direction) {
            case NORTH:
                if(coordinates.getY() != Coordinate.getYMAX()) {
                    coordinates.incrementY();
                } else {
                    coordinates.decrementY();
                    direction = Direction.SOUTH;
                }
                break;
            case SOUTH:
                if(coordinates.getY() != Coordinate.getYMIN()) {
                    coordinates.decrementY();
                } else {
                    coordinates.incrementY();
                    direction = Direction.NORTH;
                }
                break;
            case EAST:
                if(coordinates.getX() != Coordinate.getXMIN()) {
                    coordinates.decrementX();
                } else {
                    coordinates.wrapXMin();
                }
                break;
            case WEST:
                if(coordinates.getX() != Coordinate.getXMAX()) {
                    coordinates.incrementX();
                } else {
                    coordinates.wrapXMax();
                }
                break;
            default:
                break;
        }
        return this.coordinates;
    }
    public Coordinate turnLeft() {
        switch (direction) {
            case NORTH:
               this.direction = Direction.WEST;
                break;
            case SOUTH:
               this.direction = Direction.EAST;
                break;
            case EAST:
               this.direction = Direction.NORTH;
                break;
            case WEST:
              this.direction = Direction.SOUTH;
                break;
            default:
                break;
        }
        return this.coordinates;
    }

    public Coordinate turnRight() {
        switch (direction) {
            case NORTH:
                this.direction = Direction.EAST;
                break;
            case SOUTH:
                this.direction = Direction.WEST;
                break;
            case EAST:
                this.direction = Direction.SOUTH;
                break;
            case WEST:
                this.direction = Direction.NORTH;
                break;
            default:
                break;
        }
        return this.coordinates;
    }

    public Move cancel(char command) {
        if (command == 'b') {
            moveForward();
        } else if (command == 'f') {
            moveBackward();
        } else if (command == 'l') {
            turnRight();
        } else if (command == 'r') {
            turnLeft();
        } else {
            return null;
        }
        return new Move(this.coordinates, this.direction);
    }

}
