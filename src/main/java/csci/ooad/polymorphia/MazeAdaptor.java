package csci.ooad.polymorphia;

// TODO : implement IMaze Methods

/*
*   Make sure you don’t break encapsulation, meaning you should not return a mutable list that is your actual list of rooms.
*   Ideally, you’d return an immutable list to the getRooms() method. Rooms are referenced by their name.
*   The getContents() method returns a list of strings. Each one of those strings is printed on a different line in the display.
* */

import java.util.ArrayList;
import java.util.List;

// TODO "implements IMaze" (how to access file if in .jar file)
public class MazeAdaptor {

    private Maze maze;  // Reference to the Maze object

    public MazeAdaptor(Maze maze) {
        this.maze = maze;
    }

    public List<String> getRooms(){
        List<Room> mazeRooms = maze.getRooms();
        List<String> returnList = new ArrayList<>();
        for (Room room : mazeRooms) {
            returnList.add(room.getName());
        }
        return returnList;
    };

    public List<String> getNeighborsOf(String roomName) {
        List<String> neighborNames = new ArrayList<>();
        Room room = maze.getRoom(roomName);

        // If no room matches the given roomName, throw an exception
        if (room == null) {
            throw new IllegalArgumentException("Room with name " + roomName + " not found in the maze.");
        }

        // Get the neighbors of the found room
        List<Room> neighbors = room.getNeighbors();

        // Convert the neighbors to a list of room names
        for (Room neighbor : neighbors) {
            neighborNames.add(neighbor.getName());
        }

        return neighborNames;
    }


    public List<String> getContents(String room){
        Room mazeRoom = maze.getRoom(room);
        return mazeRoom.getContents();
    };

}
