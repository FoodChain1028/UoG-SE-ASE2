package src;
import java.util.ArrayList;
import java.util.List;

/**
 * Observable class for managing and notifying observers.
 */
class Observable {
    private List<Observer> observers = new ArrayList<>();

    /**
     * Adds an observer to the list.
     * @param observer The observer to add.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list.
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers of a change.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}