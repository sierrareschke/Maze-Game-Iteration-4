package csci.ooad.polymorphia.events;

import java.util.*;

public class EventBus {
    private static EventBus instance;
    private final Map<EventType, List<IObserver>> observers = new HashMap<>();

    private EventBus() {
        for (EventType eventType : EventType.values()) {
            observers.put(eventType, new ArrayList<>());
        }
    }

    public static EventBus getInstance() {
        if (instance == null) {
            instance = new EventBus();
        }
        return instance;
    }

    public void attach(IObserver observer, EventType eventType) {
        observers.get(eventType).add(observer);
    }

    public void postMessage(EventType eventType, String eventDescription) {
        for (IObserver observer : observers.get(eventType)) {
            observer.update(eventDescription);
        }
    }
}