import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MoveTest {

    @Test
    public void testMoveForwardSouth() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, Direction.SOUTH).process('f');
        Coordinate expected = new Coordinate(initialCoordinate.getX(), initialCoordinate.incrementY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
    }

    @Test
    public void testMoveForwardNorth() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, Direction.NORTH).process('f');
        Coordinate expected = new Coordinate(initialCoordinate.getX(), initialCoordinate.decrementY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
    }

    @Test
    public void testMoveForwardWest() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, Direction.WEST).process('f');
        Coordinate expected = new Coordinate(initialCoordinate.decrementX(), initialCoordinate.getY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
    }

    @Test
    public void testMoveForwardEast() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, Direction.EAST).process('f');
        Coordinate expected = new Coordinate(initialCoordinate.incrementX(), initialCoordinate.getY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
    }

    @Test
    public void testMoveBackwardSouth() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, Direction.SOUTH).process('b');
        Coordinate expected = new Coordinate(initialCoordinate.getX(), initialCoordinate.decrementY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
    }

    @Test
    public void testMoveBackwardNorth() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, Direction.NORTH).process('b');
        Coordinate expected = new Coordinate(initialCoordinate.getX(), initialCoordinate.incrementY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
    }

    @Test
    public void testMoveBackwardWest() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove= new Move(initialCoordinate, Direction.WEST).process('b');
        Coordinate expected = new Coordinate(initialCoordinate.incrementX(), initialCoordinate.getY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
    }

    @Test
    public void testMoveBackwardEast() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, Direction.EAST).process('b');
        Coordinate expected = new Coordinate(initialCoordinate.decrementX(), initialCoordinate.getY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
    }

    @Test
    public void testMoveForwardNorthButAlreadyAtNorthPole() {
        Coordinate initialCoordinate = new Coordinate(2, Coordinate.getYMIN());
        testMoveForwardWhenAlreadyAtEdge(initialCoordinate, Direction.NORTH);
    }
    @Test
    public void testMoveForwardSouthButAlreadyAtSouthPole() {
        Coordinate initialCoordinate = new Coordinate(2, Coordinate.getYMAX());
        testMoveForwardWhenAlreadyAtEdge(initialCoordinate, Direction.SOUTH);
    }
    @Test
    public void testMoveForwardWestButAlreadyAtMaxWest() {
        Coordinate initialCoordinate = new Coordinate(Coordinate.getXMIN(), 2);
        testMoveForwardWhenAlreadyAtEdge(initialCoordinate, Direction.WEST);
    }
    @Test
    public void testMoveForwardEastButAlreadyAtMaxEast() {
        Coordinate initialCoordinate = new Coordinate(Coordinate.getXMAX(), 2);
        testMoveForwardWhenAlreadyAtEdge(initialCoordinate, Direction.EAST);
    }

    private void testMoveForwardWhenAlreadyAtEdge(Coordinate initialCoordinate,  Direction currentDirection) {
        Move actualMove = new Move(initialCoordinate, currentDirection);
        Coordinate actual = actualMove.moveForward();
        Coordinate expected = new Coordinate(initialCoordinate.getX(), initialCoordinate.getY());
        Assertions.assertTrue(actual.equals(expected));
    }

    @Test
    public void testMoveBackwardWestWhenAlreadyAtEdge() {
        Coordinate initialCoordinate = new Coordinate(Coordinate.getXMIN(), 2);
        testMoveBackwardWhenAlreadyAtEdge(initialCoordinate, Direction.WEST);
    }
    @Test
    public void testMoveBackwardEastWhenAlreadyAtEdge() {
        Coordinate initialCoordinate = new Coordinate(Coordinate.getXMAX(), 2);
        testMoveBackwardWhenAlreadyAtEdge(initialCoordinate, Direction.EAST);
    }
    @Test
    public void testMoveBackwardNorthWhenAlreadyAtEdge() {
        Coordinate initialCoordinate = new Coordinate(2, Coordinate.getYMIN());
        testMoveBackwardWhenAlreadyAtEdge(initialCoordinate, Direction.NORTH);
    }
    @Test
    public void testMoveBackwardSouthWhenAlreadyAtEdge() {
        Coordinate initialCoordinate = new Coordinate(2, Coordinate.getYMAX());
        testMoveBackwardWhenAlreadyAtEdge(initialCoordinate, Direction.SOUTH);
    }

    private void testMoveBackwardWhenAlreadyAtEdge(Coordinate initialCoordinate,  Direction currentDirection) {
        Move actualMove = new Move(initialCoordinate, currentDirection);
        Coordinate actual = actualMove.moveBackward();
        Coordinate expected = new Coordinate(initialCoordinate.getX(), initialCoordinate.getY());
        Assertions.assertTrue(actual.equals(expected));
    }

    @Test
    public void testTurnLeftWhenFacingWest() {
        testMoveLeft(Direction.WEST, Direction.SOUTH);
    }

    @Test
    public void testTurnLeftWhenFacingNorth() {
        testMoveLeft(Direction.NORTH, Direction.WEST);
    }

    @Test
    public void testTurnLeftWhenFacingSouth() {
        testMoveLeft(Direction.SOUTH, Direction.EAST);
    }

    @Test
    public void testMoveLeftWhenFacingEast() {
        testMoveLeft(Direction.EAST, Direction.NORTH);
    }

    private void testMoveLeft(Direction oldDirection, Direction newDirection) {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, Direction.EAST);
        Coordinate actual = actualMove.turnLeft();
        Coordinate expected = new Coordinate(initialCoordinate.getX(), initialCoordinate.getY());
        Assertions.assertTrue(actual.equals(expected));
        Assertions.assertTrue(actualMove.getDirection().equals(Direction.NORTH));
    }

    @Test
    public void testMoveRightWhenFacingNorth() {
        testMoveRight(Direction.NORTH, Direction.EAST);
    }

    @Test
    public void testMoveRightWhenFacingSouth() {
        testMoveRight(Direction.SOUTH, Direction.WEST);
    }

    @Test
    public void testMoveRightWhenFacingEast() {
        testMoveRight(Direction.EAST, Direction.SOUTH);
    }

    @Test
    public void testMoveRightWhenFacingWest() {
        testMoveRight(Direction.WEST, Direction.NORTH);
    }

    private void testMoveRight(Direction oldDirection, Direction newDirection) {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Move actualMove = new Move(initialCoordinate, oldDirection);
        Coordinate actual = actualMove.turnRight();
        Coordinate expected = new Coordinate(initialCoordinate.getX(), initialCoordinate.getY());
        Assertions.assertTrue(actual.equals(expected));
        Assertions.assertTrue(actualMove.getDirection().equals(newDirection));
    }

    @Test
    public void testMoveForwardWithDirectionNorthAtNorthPole() {
        Coordinate initialCoordinate = new Coordinate(2, Coordinate.getYMIN());
        Move actualMove = new Move(initialCoordinate, Direction.NORTH).process('f');
        Coordinate expected = new Coordinate(initialCoordinate.getX(), 1);
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
        Assertions.assertEquals(Direction.SOUTH, actualMove.getDirection());
    }

    @Test
    public void testMoveForwardWithDirectionSouthAtSouthPole() {
        Coordinate initialCoordinate = new Coordinate(2, Coordinate.getYMAX());
        Move actualMove = new Move(initialCoordinate, Direction.SOUTH).process('f');
        Coordinate expected = new Coordinate(initialCoordinate.getX(),  Coordinate.getYMAX() - 1);
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
        Assertions.assertEquals(Direction.NORTH, actualMove.getDirection());
    }

    @Test
    public void testMoveForwardWithDirectionEastAtMaxX() {
        Coordinate initialCoordinate = new Coordinate(Coordinate.getXMAX(), 2);
        Move actualMove = new Move(initialCoordinate, Direction.EAST).process('f');
        Coordinate expected = new Coordinate(Coordinate.getXMIN(), initialCoordinate.getY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
        Assertions.assertEquals(Direction.EAST, actualMove.getDirection());
    }

    @Test
    public void testMoveForwardWithDirectionWestAtMinX() {
        Coordinate initialCoordinate = new Coordinate(Coordinate.getXMIN(), 2);
        Move actualMove = new Move(initialCoordinate, Direction.WEST).process('f');
        Coordinate expected = new Coordinate(Coordinate.getXMAX(), initialCoordinate.getY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
        Assertions.assertEquals(Direction.WEST, actualMove.getDirection());
    }

    @Test
    public void testMoveBackwardWithDirectionNorthAtNorthPole() {
        Coordinate initialCoordinate = new Coordinate(2, Coordinate.getYMIN());
        Move actualMove = new Move(initialCoordinate, Direction.SOUTH).process('b');
        Coordinate expected = new Coordinate(initialCoordinate.getX(), Coordinate.getYMIN() + 1);
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
        Assertions.assertEquals(Direction.NORTH, actualMove.getDirection());
    }

    @Test
    public void testMoveBackwardWithDirectionSouthAtSouthPole() {
        Coordinate initialCoordinate = new Coordinate(2, Coordinate.getYMAX());
        Move actualMove = new Move(initialCoordinate, Direction.NORTH).process('b');
        Coordinate expected = new Coordinate(2, Coordinate.getYMAX() - 1);
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
        Assertions.assertEquals(Direction.SOUTH, actualMove.getDirection());
    }

    @Test
    public void testMoveBackwardWithDirectionEastAtMinX() {
        Coordinate initialCoordinate = new Coordinate(Coordinate.getXMIN(), 2);
        Move actualMove = new Move(initialCoordinate, Direction.EAST).process('b');
        Coordinate expected = new Coordinate(Coordinate.getXMAX(), initialCoordinate.getY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
        Assertions.assertEquals(Direction.EAST, actualMove.getDirection());
    }

    @Test
    public void testMoveBackwardWithDirectionWestAtMaxX() {
        Coordinate initialCoordinate = new Coordinate(Coordinate.getXMAX(), 2);
        Move actualMove = new Move(initialCoordinate, Direction.WEST).process('b');
        Coordinate expected = new Coordinate(Coordinate.getXMIN(), initialCoordinate.getY());
        Assertions.assertTrue(actualMove.getCoordinate().equals(expected));
        Assertions.assertEquals(Direction.WEST, actualMove.getDirection());
    }

    @Test
    public void testCancelForward() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Direction initialDirection = Direction.NORTH;
        Move move = new Move(initialCoordinate, initialDirection);
        Move newMove = move.cancel('f');
        Coordinate expectedCoordinate = new Coordinate(2, 3);
        Assertions.assertTrue(expectedCoordinate.equals(newMove.getCoordinate()));
        Assertions.assertEquals(initialDirection, newMove.getDirection());
    }

    @Test
    public void testCancelBackward() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Direction initialDirection = Direction.NORTH;
        Move move = new Move(initialCoordinate, initialDirection);
        Move newMove = move.cancel('b');
        Coordinate expectedCoordinate = new Coordinate(2, 1);
        Assertions.assertTrue(expectedCoordinate.equals(newMove.getCoordinate()));
        Assertions.assertEquals(initialDirection, newMove.getDirection());
    }

    @Test
    public void testCancelTurnLeft() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Direction initialDirection = Direction.NORTH;
        Move move = new Move(initialCoordinate, initialDirection);
        Move newMove = move.cancel('l');
        Assertions.assertTrue(initialCoordinate.equals(newMove.getCoordinate()));
        Assertions.assertEquals(Direction.EAST, newMove.getDirection());
    }

    @Test
    public void testCancelTurnRight() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Direction initialDirection = Direction.NORTH;
        Move move = new Move(initialCoordinate, initialDirection);
        Move newMove = move.cancel('r');
        Assertions.assertTrue(initialCoordinate.equals(newMove.getCoordinate()));
        Assertions.assertEquals(Direction.WEST, newMove.getDirection());
    }
}
