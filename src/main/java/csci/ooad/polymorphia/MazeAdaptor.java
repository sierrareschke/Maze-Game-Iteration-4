package csci.ooad.polymorphia;

//package csci.ooad.polymorphia;
//
//// TODO : implement IMaze Methods
//
///*
//*   Make sure you don’t break encapsulation, meaning you should not return a mutable list that is your actual list of rooms.
//*   Ideally, you’d return an immutable list to the getRooms() method. Rooms are referenced by their name.
//*   The getContents() method returns a list of strings. Each one of those strings is printed on a different line in the display.
//* */
//
//import java.util.List;
//
//public class MazeAdaptor implements IMaze {
//
//    private Maze maze;  // Reference to the Maze object
//
//    public MazeAdaptor(Maze maze) {
//        this.maze = maze;
//    }
//
//    List<String> getRooms(){};
//
//    List<String> getNeighborsOf(){};
//
//    List<String> getContents(String room){};
//
//}

// TODO : implement IMaze Methods

/*
*   Make sure you don’t break encapsulation, meaning you should not return a mutable list that is your actual list of rooms.
*   Ideally, you’d return an immutable list to the getRooms() method. Rooms are referenced by their name.
*   The getContents() method returns a list of strings. Each one of those strings is printed on a different line in the display.
* */

import csci.ooad.layout.intf.IMaze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import csci.ooad.polymorphia.Maze;
import csci.ooad.polymorphia.Room;


public class MazeAdaptor implements IMaze {

    private Maze maze;  // Reference to the Maze object

    public MazeAdaptor(Maze maze) {
        this.maze = maze;
    }

    public Set<String> getRooms(){
        List<Room> mazeRooms = maze.getRooms();
        Set<String> roomNames = new HashSet<>();
        for (Room room : mazeRooms) {
            roomNames.add(room.getName());
        }
        return roomNames;
    };

    public Set<String> getNeighborsOf(String roomName) {
        Set<String> neighborNames = new HashSet<>();
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
