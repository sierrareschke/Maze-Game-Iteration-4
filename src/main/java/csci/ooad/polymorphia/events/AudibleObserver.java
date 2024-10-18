package csci.ooad.polymorphia.events;


import java.util.List;

public class AudibleObserver implements IObserver {

    private IObservable observableGame;
    private List<EventType> interestingEvents;
    private int delayInSeconds;

    public AudibleObserver(IObservable observableGame, List<EventType> interestingEvents, int delayInSeconds) {
        this.observableGame = observableGame;
        this.interestingEvents = interestingEvents;
        this.delayInSeconds = delayInSeconds;
    }

    public void update(String eventDescription){

    };
}
