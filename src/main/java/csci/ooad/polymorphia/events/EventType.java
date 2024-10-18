package csci.ooad.polymorphia.events;

public enum EventType {
    FightOutcome, // Character.fight()
    AteSomething, // Adventurer.eatFood()
    TurnEnded, // Polymorphia.playTurn()
    GameOver, // Polymorphia.play()
    GameStart, // Polymorphia.play()
    Death, // Character.loseHealth()
    All // Handled in EventBus
}
