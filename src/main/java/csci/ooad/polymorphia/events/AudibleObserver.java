package csci.ooad.polymorphia.events;


import java.util.List;
import java.io.IOException;

public class AudibleObserver implements IObserver {

    public AudibleObserver(IObservable observableGame, List<EventType> interestingEvents, int delayInSeconds) {}

    @Override
    public void update(String eventDescription) {
        String[] cmd = {"say", eventDescription};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
