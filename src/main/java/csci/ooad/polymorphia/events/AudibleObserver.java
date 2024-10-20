package csci.ooad.polymorphia.events;


import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AudibleObserver implements IObserver {

    private int delayInSeconds;
    // Static lock object to synchronize all speech executions
    private static final Object speechLock = new Object();
    private ExecutorService executorService;

    public AudibleObserver(int delayInSeconds) {
        this.delayInSeconds = delayInSeconds;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public Future<Void> update(String eventDescription) {
        // Submit the task to the executor service and return a Future
        return executorService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                try {
                    // Synchronized block to ensure only one thread runs the "say" command at a time
                    synchronized (speechLock) {
                        String[] cmd = {"say", eventDescription};
                        Process process = Runtime.getRuntime().exec(cmd);

                        // Wait for the "say" command to finish before allowing the next one
                        process.waitFor();
                    }

                } catch (IOException | InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    e.printStackTrace();
                }
                return null;  // Void return type
            }
        });
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
