package csci.ooad.polymorphia.events;

import csci.ooad.polymorphia.events.EventType;
import csci.ooad.polymorphia.events.IObserver;

import java.util.*;

public class EventBus {
    private static EventBus instance;
    private final Map<EventType, Set<IObserver>> eventsAndObservers = new HashMap<>();

    private EventBus() {}

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    // Attach method: properly adds observers to specific event types
    public void attach(IObserver observer, EventType eventType) {
        // Initialize the set if the eventType doesn't have any observers yet
        eventsAndObservers.computeIfAbsent(eventType, k -> new HashSet<>());

        // Add the observer to the set, ensuring no duplicates
        eventsAndObservers.get(eventType).add(observer);
    }

    // Post message to observers for the specific event type and all-event observers
    public void postMessage(EventType eventType, String eventDescription) {
        // Retrieve the list of observers for the specific event type
        Set<IObserver> subscribersToEvent = eventsAndObservers.get(eventType);
        // Retrieve the list of observers for all events
        Set<IObserver> subscribersToAllEvents = eventsAndObservers.get(EventType.All);

        // If no observers for the specific event type, initialize the set
        if (subscribersToEvent == null) {
            subscribersToEvent = new HashSet<>();
            eventsAndObservers.put(eventType, subscribersToEvent);
        }

        // If there are observers for all events, merge them into the event-specific set
        if (subscribersToAllEvents != null) {
            subscribersToEvent.addAll(subscribersToAllEvents);
        }

        // Notify all observers for this event type
        for (IObserver observer : subscribersToEvent) {
            observer.update(eventDescription);
        }
    }
}