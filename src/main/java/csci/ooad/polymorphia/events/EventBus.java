package csci.ooad.polymorphia.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observer;

public class EventBus {

    // Need to be able to map event type to those that are observing it
    private Map<EventType, List<Observer>> eventObservers ;

    /*
    *  This function registers an observer’s interest in a particular event
    * */
    // TODO : Change to a list
    public void attach(Observer observer, EventType eventType) {

        if (!eventObservers.containsKey(eventType)) {
            eventObservers.put(eventType, new ArrayList<>());
        }

        List<Observer> observers = eventObservers.get(eventType);

        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }


    /*
     *  This function is called by game elements when an event occurs
     */
    public void postMessage(EventType eventType, String eventDescription) {
        // Get the list of observers subscribed to the event type
        List<Observer> toBeNotifiedObservers = eventObservers.get(eventType);

        // Check if there are any observers to notify
        if (toBeNotifiedObservers != null) {
            // Notify each observer
            for (Observer observer : toBeNotifiedObservers) {
                observer.update(eventType, eventDescription);
            }
        }
    }

}
