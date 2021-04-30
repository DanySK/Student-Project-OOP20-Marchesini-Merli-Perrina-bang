package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

import java.util.ArrayList;

import libs.observe.ObservableElement;
import view.View;
import view.ViewFactory;

public class Controller {

    private ViewFactory factory;
    private ObservableElement<Integer> numberOfPlayers = new ObservableElement<>();
    private ObservableElement<String> changeSceneObs;
    private List<String> winners = new ArrayList<>();

    private Map<String, IViewController> controllers = new HashMap<String, IViewController>(
        Map.ofEntries(entry("start", (fct) -> {
            View v = fct.getMenuView(numberOfPlayers);
            v.show();
        }), entry("rules", (fct) -> {
            View v = fct.getRulesView();
            v.show();
        }), entry("game", (fct) -> {
            GameController gmc = new GameController(numberOfPlayers.get(), winners);
            gmc.setup(fct);
        }), entry("end", (fct) -> {
            View v = fct.getEndGameView(winners);
            v.show();
        })));

    public Controller(ViewFactory factory) {
        this.changeSceneObs = factory.getChangeSceneObservable();
        this.changeSceneObs.addObserver(() -> {
            controllers.get(changeSceneObs.get()).setup(this.factory);
        });

        this.factory = factory;
    }

    public void start() {
        controllers.get("start").setup(factory);
    }
}
