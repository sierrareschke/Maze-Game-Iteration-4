package csci.ooad.polymorphia;

import csci.ooad.layout.intf.IMaze;
import csci.ooad.layout.intf.IMazeObserver;
import csci.ooad.layout.intf.IMazeSubject;
import csci.ooad.layout.intf.MazeObserver;
import csci.ooad.polymorphia.characters.Character;
import csci.ooad.polymorphia.characters.Creature;
import csci.ooad.polymorphia.events.*;
import jdk.jfr.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static csci.ooad.polymorphia.events.EventType.*;


/*
 *  TODO:    Register observer before you start the game
 *  TODO:    Connect observer up to the Event Bus, inside of this class?
 *  TODO:    Implement the IMazeSubject on this object and attach the viewer
 *  TODO:    Call the notifyObservers() method when relevant information is posted to the Event Bus.
 *           At a minimum, you should call this method at the end of each turn (this is also probably sufficient)
 *   TODO:   Implement the getMaze() method, which returns an object that implements the IMaze interface

 * */


public class Polymorphia implements IMazeSubject {
    private static final Logger logger = LoggerFactory.getLogger(Polymorphia.class);
    IObserver audibleObserver;
    EventBus eventBus;

    Maze maze;
    Integer turnCount = 0;
    final Random rand = new Random();

    public Polymorphia(Maze maze) {

        eventBus = EventBus.getInstance();
        int delayInSeconds = 100;
        audibleObserver = new AudibleObserver(delayInSeconds);
        eventBus.attach(audibleObserver, EventType.All);

        this.maze = maze;
    }

    public String toString() {
        return "Polymorphia MAZE: turn " + turnCount + "\n" + maze.toString();
    }

    // Game is over when all creatures are killed
    // or all adventurers are killed
    public Boolean isOver() {
        return !hasLivingAdventurers() || !hasLivingCreatures();
    }

    public Boolean hasLivingCreatures() {
        return maze.hasLivingCreatures();
    }

    public Boolean hasLivingAdventurers() {
        return maze.hasLivingAdventurers();
    }

    public void playTurn() {
        if (turnCount == 0) {
            logger.info("Starting play...");
        }
        turnCount += 1;

        // Process all the characters in random order
        List<Character> characters = getLivingCharacters();
        while (!characters.isEmpty()) {
            int index = rand.nextInt(characters.size());
            characters.get(index).doAction();
            characters.remove(index);
            characters = characters.stream()
                    .filter(Character::isAlive)
                    .collect(Collectors.toList());
        }

        eventBus.postMessage(EventType.TurnEnded,"Turn " + turnCount + " ended");
    }

    public List<Character> getLivingCharacters() {
        return maze.getLivingCharacters();
    }


    public void play() {
        eventBus.postMessage(EventType.GameStart,"Game has begun");
        while (!isOver()) {
            logger.info(this.toString());
            playTurn();
            notifyObservers("turn finished, update game staus display");
            // TODO - EventBus update
        }
        logger.info("The game ended after {} turns.", turnCount);
        eventBus.postMessage(EventType.GameOver,"Game has ended");
        String eventDescription;
        if (hasLivingAdventurers()) {
            eventDescription = "The adventurers won! Left standing are:\n" + getAdventurerNames() + "\n";
        } else if (hasLivingCreatures()) {
            eventDescription = "The creatures won! Left standing are:\n" + getCreatureNames() + "\n";
        } else {
            eventDescription = "No team won! Everyone died!\n";
        }
        logger.info(eventDescription);
    }

    String getAdventurerNames() {
        return String.join("\n ", getLivingCharacters().stream().map(Object::toString).toList());
    }

    String getCreatureNames() {
        return String.join("\n ", getAliveCreatures().stream().map(Object::toString).toList());
    }

    public List<Creature> getAliveCreatures() {
        return maze.getLivingCreatures();
    }

    public Character getWinner() {
        if (!isOver() || !hasLivingCharacters()) {
            // No one has won yet or no one won -- all died
            return null;
        }
        return getLivingCharacters().getFirst();
    }

    private boolean hasLivingCharacters() {
        return !getLivingCharacters().isEmpty();
    }


    // IMazeSubject interface methods
    @Override
    public void attach(IMazeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String statusMessage) {
        for (IMazeObserver observer : observers) {
            observer.update(getMaze(), statusMessage);
        }
    }

    @Override
    public IMaze getMaze() {
        MazeAdaptor mazeAdaptor = new MazeAdaptor(maze);
        return mazeAdaptor;
    }
}