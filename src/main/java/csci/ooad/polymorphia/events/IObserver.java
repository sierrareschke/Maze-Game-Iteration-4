package csci.ooad.polymorphia.events;

import java.util.concurrent.Future;

public interface IObserver {

    Future<Void> update(String eventDescription);
}
