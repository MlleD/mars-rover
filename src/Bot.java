import java.util.List;

/**
 * Classe du robot qui parse les commandes reçues
 */
public class Bot {

    private Coordinate coordinate;
    private Direction direction;
    private List<Coordinate> obstacles;
    
    public Bot(Coordinate coordinate, Direction direction) {
        this(coordinate, direction, null);
    }

    public Bot(Coordinate coordinate, Direction direction, List<Coordinate> obstacles) {
        this.coordinate = coordinate;
        this.direction = direction;
        this.obstacles = obstacles;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void parseCommands(char[] commands) {
        if (commands == null) {
            return;
        }
        for (char command: commands) {
            Move move = new Move(coordinate, direction);
            if (obstacles != null && this.contains(this.coordinate)) {
                System.out.println("Obstacle rencontré à ("  + this.coordinate.getX() + ", " + this.coordinate.getY() + ")");
                Move newMove = move.cancel(command);
                this.coordinate = newMove.getCoordinate();
                break;
            }
            else if (move.process(command) != null) {
                this.coordinate = move.getCoordinate();
                this.direction = move.getDirection();
            }
        } 
    }

    private boolean contains(Coordinate coordinateInput) {
        for (Coordinate coordinate : obstacles) {
            if (coordinate.equals(coordinateInput)) {
                return true;
            }
        }
        return false;
    }
}
