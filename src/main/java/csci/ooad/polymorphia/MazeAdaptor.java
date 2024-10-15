package csci.ooad.polymorphia;

// TODO : implement IMaze Methods

/*
*   Make sure you don’t break encapsulation, meaning you should not return a mutable list that is your actual list of rooms.
*   Ideally, you’d return an immutable list to the getRooms() method. Rooms are referenced by their name.
*   The getContents() method returns a list of strings. Each one of those strings is printed on a different line in the display.
* */

import java.util.List;

public class MazeAdaptor implements IMaze {

    private Maze maze;  // Reference to the Maze object

    public MazeAdaptor(Maze maze) {
        this.maze = maze;
    }

    List<String> getRooms(){};

    List<String> getNeighborsOf(){};

    List<String> getContents(String room){};

}
