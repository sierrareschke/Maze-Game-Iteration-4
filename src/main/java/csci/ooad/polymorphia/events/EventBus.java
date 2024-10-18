package csci.ooad.polymorphia.events;

import java.util.List;
import java.util.Map;
import java.util.Observer;

public class EventBus {

    // Need to be able to map event type to those that are observing it
    private Map<EventType, List<Observer>> eventObservers ;

    /*
    *  This function registers an observer’s interest in a particular event
    * */
    public void attach(IObserver observer, EventType eventType) {
        // If observer already subscribed to event notification, raise warning

        // If observer not already subscribed, add observer to eventType's list of observers
    }

    /*
    *  This function is called by game elements when an event occurs
    * */
    public void postMessage(EventType eventType, String eventDescription) {
        List<Observer> toBeNotifiedObservers = eventObservers.get(eventType);
        for(Observer observer : toBeNotifiedObservers) {
            observer.update() // TODO : UNSURE OF WHAT TO DO HERE
        }
    }
}
