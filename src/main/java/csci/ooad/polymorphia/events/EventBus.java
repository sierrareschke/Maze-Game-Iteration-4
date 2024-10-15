package csci.ooad.polymorphia.events;

public class EventBus {

    /*
    *  This function registers an observer’s interest in a particular event
    * */
    public void attach(IObserver observer, EventType eventType) {}

    /*
    *  This function is called by game elements when an event occurs
    * */
    public void postMessage(EventType eventType, String eventDescription) {}
}
