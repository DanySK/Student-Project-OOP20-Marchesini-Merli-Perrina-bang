package model.effects;
import model.Table;

public class GeneralStore implements Effect {

    @Override
    public void useEffect(Table table) {
        table.getPlayers().forEach(p -> {
            p.addCard(table.getDeck().nextCard());
        });
    }
}
