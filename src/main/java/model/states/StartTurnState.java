package model.states;

import model.GameStateMachine;
import model.Player;
import model.Table;

public class StartTurnState implements State {

    @Override
    public void handle(GameStateMachine gsMachine) {
        Table table = gsMachine.getTable();
        Player current = table.getCurrentPlayer();
        
        if(current.getCardsByName("prison").size() == 0) {
            table.getDeck().nextCards(2).forEach(c -> current.addCard(c));
            gsMachine.setCurrentState(new ChooseActionState());
        }
        else {
            current.removeCard(current.getCardsByName("prison").get(0));
            gsMachine.setCurrentState(new EndTurnState());
        }
    }

}