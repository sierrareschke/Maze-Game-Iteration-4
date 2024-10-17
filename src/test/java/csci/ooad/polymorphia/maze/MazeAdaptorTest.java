package csci.ooad.polymorphia.maze;

import csci.ooad.polymorphia.Maze;
import csci.ooad.polymorphia.MazeAdaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MazeAdaptorTest {
    private Maze maze;
    private MazeAdaptor mazeAdaptor;

    // TODO need to test actual diagram drawn

    @BeforeEach
    void setup() {

        // TODO createNbyMGrid or add rooms manually (roomName issues)

        // Setup the maze object with a 2x2 grid and create an adaptor for it
        maze = Maze.newBuilder()
                .createNbyMGrid(2, 2)
                .distributeSequentially()
                .createAndAddFoodItems(2)
                .createAndAddAdventurers(2)
                .createAndAddCreatures(1, true)
                .build();

        mazeAdaptor = new MazeAdaptor(maze);  // Create the adaptor
    }

    @Test
    void testGetRooms() {
        List<String> rooms = mazeAdaptor.getRooms();
        assertNotNull(rooms);
        assertEquals(4, rooms.size());  // For a 2x2 grid, there should be 4 rooms
    }

    @Test
    void testGetNeighborsOfRoom() {
        String roomName = maze.getRooms().get(0).getName();
        List<String> neighbors = mazeAdaptor.getNeighborsOf(roomName);

        assertNotNull(neighbors);
        assertFalse(neighbors.isEmpty());
        assertTrue(neighbors.size() > 0);  // Room should have at least one neighbor
    }

    @Test
    void testGetNeighborsOfInvalidRoom() {
        String invalidRoomName = "NonExistingRoom";
        try {
            mazeAdaptor.getNeighborsOf(invalidRoomName);  // Call the method
            fail("Expected IllegalArgumentException to be thrown");  // Fail the test if exception is not thrown
        } catch (IllegalArgumentException e) {
            String expectedMessage = "Room with name " + invalidRoomName + " not found in the maze.";
            String actualMessage = e.getMessage();
            assertTrue(actualMessage.contains(expectedMessage));  // Check that the message is as expected
        }
    }

    @Test
    void testGetContentsOfRoom() {
        String roomName = maze.getRooms().get(0).getName();
        List<String> contents = mazeAdaptor.getContents(roomName);

        assertNotNull(contents);
        assertFalse(contents.isEmpty());  // Room should contain items (e.g., adventurers or food)
    }

    @Test
    void testGetContentsOfEmptyRoom() {
        // Set up a new empty room without any adventurers or creatures
        Maze emptyMaze = Maze.newBuilder()
                .createNbyMGrid(1, 1)
                .build();

        MazeAdaptor emptyMazeAdaptor = new MazeAdaptor(emptyMaze);
        String roomName = emptyMaze.getRooms().get(0).getName();
        List<String> contents = emptyMazeAdaptor.getContents(roomName);

        assertNotNull(contents);
        assertTrue(contents.isEmpty());  // Room should be empty
    }
}