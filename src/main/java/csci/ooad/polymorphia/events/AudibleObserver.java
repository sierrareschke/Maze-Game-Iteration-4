package csci.ooad.polymorphia.events;


import java.util.List;

public class AudibleObserver implements IObserver {

    public AudibleObserver(IObservable observableGame, List<EventType> interestingEvents, int delayInSeconds) {}

    public void update(String eventDescription){};
}
