import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BotTest {

    @Test
    public void testBotWithNullInputShouldNotModifyBot() {
        testBotWithInvalidInput(null);
    }
    @Test
    public void testBotWithUnknownCommandDoNotModifyBot() {
        testBotWithInvalidInput(new char[]{'_'});
    }
    private void testBotWithInvalidInput(char[] commands) {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Direction initialDirection = Direction.SOUTH;
        Bot bot = new Bot(initialCoordinate, initialDirection);
        bot.parseCommands(commands);
        Assertions.assertEquals(initialDirection, bot.getDirection());
        Assertions.assertTrue(initialCoordinate.equals(bot.getCoordinate()));
    }
    @Test
    public void testWithOneValidCommand() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Direction initialDirection = Direction.SOUTH;
        Bot bot = new Bot(initialCoordinate, initialDirection);
        bot.parseCommands(new char[]{'f'});
        Assertions.assertEquals(initialDirection, bot.getDirection());
        Assertions.assertEquals(bot.getCoordinate().getY(), initialCoordinate.getY());
    }

    @Test
    public void testWithAllValidCommandsProcessAll() {
        char[] commands = new char[] {'r', 'l', 'f', 'b'};
        Coordinate initialCoordinate = new Coordinate(1, 2);
        Direction initialDirection = Direction.EAST;
        testWithMultipleMoveCommandsProcessAll(commands, initialDirection, initialDirection, initialCoordinate, initialCoordinate);
    }

    @Test
    public void testWithMultipleSameTurnRightCommandsProcessAll() {
        char[] commands = new char[] {'r', 'r', 'r'};
        Coordinate initialCoordinate = new Coordinate(1, 2);
        testWithMultipleMoveCommandsProcessAll(commands, Direction.EAST, Direction.NORTH, initialCoordinate, initialCoordinate);
    }

    @Test
    public void testWithMultipleSameTurnLeftCommandsProcessAll() {
        char[] commands = new char[] {'l', 'l', 'l'};
        Coordinate initialCoordinate = new Coordinate(1, 2);
        testWithMultipleMoveCommandsProcessAll(commands, Direction.EAST, Direction.SOUTH, initialCoordinate, initialCoordinate);
    }

    @Test
    public void testWithMultipleSameMoveForwardCommandsProcessAll() {
        char[] commands = new char[] {'f', 'f', 'f'};
        Coordinate initialCoordinate = new Coordinate(2, 3);
        Direction initialDirection = Direction.NORTH;
        Coordinate finalCoordinate = new Coordinate(2, 0);
        testWithMultipleMoveCommandsProcessAll(commands, initialDirection, initialDirection, initialCoordinate, finalCoordinate);
    }

    @Test
    public void testWithMultipleSameMoveBackwardCommandsProcessAll() {
        char[] commands = new char[] {'b', 'b', 'b'};
        Coordinate initialCoordinate = new Coordinate(2, 0);
        Direction initialDirection = Direction.NORTH;
        Coordinate finalCoordinate = new Coordinate(2, 3);
        testWithMultipleMoveCommandsProcessAll(commands, initialDirection, initialDirection, initialCoordinate, finalCoordinate);
    }

    @Test
    public void testBotWithSomeCommands() {
        char[] commands = new char[] {'b', 'r', 'r', 'l', 'f'};
        Coordinate initialCoordinate = new Coordinate(2, 0);
        Direction initialDirection = Direction.NORTH;
        Coordinate finalCoordinate = initialCoordinate;
        Direction finalDirection = Direction.EAST;
        testWithMultipleMoveCommandsProcessAll(commands, initialDirection, finalDirection, initialCoordinate, finalCoordinate);
    }

    private void testWithMultipleMoveCommandsProcessAll(char[] commands, Direction initialDirection, Direction finalDirection, Coordinate initialCoordinate, Coordinate finalCoordinate) {
        Bot bot = new Bot(initialCoordinate, initialDirection);
        bot.parseCommands(commands);
        Assertions.assertEquals(finalDirection, bot.getDirection());
        Assertions.assertTrue(finalCoordinate.equals(bot.getCoordinate()));
    }

    @Test
    public void testBotWithDirectionForwardSouthShouldStopBeforeObstacle() {
        Coordinate initialCoordinate = new Coordinate(2, 0);
        Direction initialDirection = Direction.SOUTH;
        List<Coordinate> obstacles = new ArrayList<>(1);
        Coordinate obstacleCoordinate = new Coordinate(2, 2);
        obstacles.add(obstacleCoordinate);
        Bot bot = new Bot(initialCoordinate, initialDirection, obstacles);
        char commands[] = new char[] {'f', 'f', 'f', 'f'}; // Arrêt à la 1ère commande 'f'
        bot.parseCommands(commands);
        Coordinate lastPossibleCoordinate = new Coordinate(2, 1);
        Assertions.assertTrue(lastPossibleCoordinate.equals(bot.getCoordinate()));
    }

    @Test
    public void testBotWithDirectionForwardNorthShouldStopBeforeObstacle() {
        Coordinate initialCoordinate = new Coordinate(2, 3);
        Direction initialDirection = Direction.NORTH;
        List<Coordinate> obstacles = new ArrayList<>(1);
        Coordinate obstacleCoordinate = new Coordinate(2, 1);
        obstacles.add(obstacleCoordinate);
        Bot bot = new Bot(initialCoordinate, initialDirection, obstacles);
        char commands[] = new char[] {'f', 'f', 'f', 'f'}; // Arrêt à la 1ère commande 'f'
        bot.parseCommands(commands);
        Coordinate lastPossibleCoordinate = new Coordinate(2, 2);
        Assertions.assertTrue(lastPossibleCoordinate.equals(bot.getCoordinate()));
    }

    @Test
    public void testBotWithDirectionForwardEastShouldStopBeforeObstacle() {
        Coordinate initialCoordinate = new Coordinate(2, 2);
        Direction initialDirection = Direction.EAST;
        List<Coordinate> obstacles = new ArrayList<>(1);
        Coordinate obstacleCoordinate = new Coordinate(4, 2);
        obstacles.add(obstacleCoordinate);
        Bot bot = new Bot(initialCoordinate, initialDirection, obstacles);
        char commands[] = new char[] {'f', 'f', 'f', 'f'}; // Arrêt à la 1ère commande 'f'
        bot.parseCommands(commands);
        Coordinate lastPossibleCoordinate = new Coordinate(3, 2);
        Assertions.assertTrue(lastPossibleCoordinate.equals(bot.getCoordinate()));
    }
    @Test
    public void testBotWithDirectionForwardWestShouldStopBeforeObstacle() {
        Coordinate initialCoordinate = new Coordinate(4, 2);
        Direction initialDirection = Direction.WEST;
        List<Coordinate> obstacles = new ArrayList<>(1);
        Coordinate obstacleCoordinate = new Coordinate(2, 2);
        obstacles.add(obstacleCoordinate);
        Bot bot = new Bot(initialCoordinate, initialDirection, obstacles);
        char commands[] = new char[] {'f', 'f', 'f', 'f'}; // Arrêt à la 1ère commande 'f'
        bot.parseCommands(commands);
        Coordinate lastPossibleCoordinate = new Coordinate(3, 2);
        Assertions.assertTrue(lastPossibleCoordinate.equals(bot.getCoordinate()));
    }
    @Test
    public void testBotWithDirectionNorthShouldStopBeforeObstacle() {
        Coordinate initialCoordinate = new Coordinate(2, 1);
        Direction initialDirection = Direction.NORTH;
        List<Coordinate> obstacles = new ArrayList<>(1);
        Coordinate obstacleCoordinate = new Coordinate(2, 3);
        obstacles.add(obstacleCoordinate);
        Bot bot = new Bot(initialCoordinate, initialDirection, obstacles);
        char commands[] = new char[] {'b', 'b', 'b', 'b'}; // Arrêt à la 1ère commande 'b'
        bot.parseCommands(commands);
        Coordinate lastPossibleCoordinate = new Coordinate(2, 2);
        Assertions.assertTrue(lastPossibleCoordinate.equals(bot.getCoordinate()));
    }

    @Test
    public void testBotWithDirectionSouthShouldStopBeforeObstacle() {
        Coordinate initialCoordinate = new Coordinate(2, 3);
        Direction initialDirection = Direction.SOUTH;
        List<Coordinate> obstacles = new ArrayList<>(1);
        Coordinate obstacleCoordinate = new Coordinate(2, 1);
        obstacles.add(obstacleCoordinate);
        Bot bot = new Bot(initialCoordinate, initialDirection, obstacles);
        char commands[] = new char[] {'b', 'b', 'b', 'b'}; // Arrêt à la 1ère commande 'b'
        bot.parseCommands(commands);
        Coordinate lastPossibleCoordinate = new Coordinate(2, 2);
        Assertions.assertTrue(lastPossibleCoordinate.equals(bot.getCoordinate()));
    }

    @Test
    public void testBotWithDirectionEastShouldStopBeforeObstacle() {
        Coordinate initialCoordinate = new Coordinate(2, 3);
        Direction initialDirection = Direction.EAST;
        List<Coordinate> obstacles = new ArrayList<>(1);
        Coordinate obstacleCoordinate = new Coordinate(0, 3);
        obstacles.add(obstacleCoordinate);
        Bot bot = new Bot(initialCoordinate, initialDirection, obstacles);
        char commands[] = new char[] {'b', 'b', 'b', 'b'}; // Arrêt à la 1ère commande 'b'
        bot.parseCommands(commands);
        Coordinate lastPossibleCoordinate = new Coordinate(1, 3);
        Assertions.assertTrue(lastPossibleCoordinate.equals(bot.getCoordinate()));
    }

    @Test
    public void testBotWithDirectionWestShouldStopBeforeObstacle() {
        Coordinate initialCoordinate = new Coordinate(1, 3);
        Direction initialDirection = Direction.WEST;
        List<Coordinate> obstacles = new ArrayList<>(1);
        Coordinate obstacleCoordinate = new Coordinate(3, 3);
        obstacles.add(obstacleCoordinate);
        Bot bot = new Bot(initialCoordinate, initialDirection, obstacles);
        char commands[] = new char[] {'b', 'b', 'b', 'b'}; // Arrêt à la 1ère commande 'b'
        bot.parseCommands(commands);
        Coordinate lastPossibleCoordinate = new Coordinate(2, 3);
        Assertions.assertTrue(lastPossibleCoordinate.equals(bot.getCoordinate()));
    }
}
