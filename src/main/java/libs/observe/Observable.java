package libs.observe;

public interface Observable {
    
    /**
     * Adds an observer to a list
     * 
     * @param observer
     */
    void addObserver(final Observer observer);

    /**
     * Removes an observer from a list
     * 
     * @param observer
     */
    void removeObserver(final Observer observer);

    /**
     * Removes all observers from a list
     */
    void removeAllObservers();

    /**
     * Notifies all observers
     */
    void notifyObservers();
}
