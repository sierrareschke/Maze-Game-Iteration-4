package csci.ooad.polymorphia.events;


import java.util.List;
import java.io.IOException;

public class AudibleObserver implements IObserver {

    private int delayInSeconds;

    public AudibleObserver(int delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
    }

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
