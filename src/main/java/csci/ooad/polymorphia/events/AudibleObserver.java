package csci.ooad.polymorphia.events;


import java.util.List;
import java.io.IOException;

public class AudibleObserver implements IObserver {
    static int delayInSeconds;
    public AudibleObserver(IObservable observableGame, List<EventType> interestingEvents, int delayInSeconds) {}
//    public AudibleObserver(int seconds) { delayInSeconds = seconds; }

    @Override
    public void update(String eventDescription) {
        String[] cmd = {"say", eventDescription};
        try {
            Thread.sleep(delayInSeconds);
            Runtime.getRuntime().exec(cmd);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
