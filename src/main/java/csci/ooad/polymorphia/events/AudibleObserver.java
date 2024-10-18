package csci.ooad.polymorphia.events;


import java.util.List;
import java.io.IOException;

public class AudibleObserver implements IObserver {

    private int delayInSeconds;
    // Static lock object to synchronize all speech executions
    private static final Object speechLock = new Object();

    public AudibleObserver(int delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
    }

    @Override
    public void update(String eventDescription) {
        // Run the speaking task in a separate thread to avoid blocking the main thread
        new Thread(() -> {
            try {
                // Add the delay before speaking
                Thread.sleep(10);

                // Synchronized block to ensure only one thread runs the "say" command at a time
                synchronized (speechLock) {
                    String[] cmd = {"say", eventDescription};
                    Process process = Runtime.getRuntime().exec(cmd);

                    // Wait for the "say" command to finish before allowing the next one
                    process.waitFor();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                e.printStackTrace();
            }
        }).start();
    }
}
