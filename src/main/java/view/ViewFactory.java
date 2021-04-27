package view;

import java.util.List;

import libs.observe.ObservableElement;

public interface ViewFactory {
    
    /**
     * @return a view containing the main menu of the game
     */
    View getMenuView(final ObservableElement<Integer> obs);
    
    /**
     * @return a view containing the game rules
     */
    View getRulesView();
    
    /**
     * @param playerNum the number of players
     * @return the main game view
     */
    View getGameView(final GameViewObservables observables);
    
    /**
     * @param the names of players who won the game
     * @return a view containing the names of the winners
     */
    View getEndGameView(final List<String> winners);

    /**
     * @return an {@link} @ObservableElement
     */
    ObservableElement<String> getChangeScreenObservable();
    
    /**
     * Shows the specified view
     * @param s the identifier of the view to show 
     */
    void changeView(final String s);

}
