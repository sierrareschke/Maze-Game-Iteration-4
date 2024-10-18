package csci.ooad.polymorphia.events;

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

    public void attach(IObserver observer, EventType eventType) {
        if(!eventsAndObservers.containsKey(eventType)) {
            eventsAndObservers.put(eventType, new HashSet<IObserver>() {
            });
        }

        if(!eventsAndObservers.get(eventType).contains(observer)) {
            eventsAndObservers.get(eventType).add(observer);
        }
    }

    public void postMessage(EventType eventType,String eventDescription) {
        Set<IObserver> subscribersToEvent = eventsAndObservers.get(eventType);
        Set<IObserver> subscribersToAllEvents = eventsAndObservers.get(EventType.All);

        if (subscribersToAllEvents != null) {
            // Use addAll to merge subscribers, ensuring no duplicates
            subscribersToEvent.addAll(subscribersToAllEvents);
        }

        for(IObserver observer : subscribersToEvent) {
            observer.update(eventDescription);
        }
    }
}
