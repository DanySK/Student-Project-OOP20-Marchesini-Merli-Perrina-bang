package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.card.Card;
import model.deck.IDeck;
import model.effects.Effect;
import libs.CircularList;
import libs.observe.IObservable;

enum Message{
	 CHOOSE_PLAYER, CHOOSE_PLAYER_WITH_DISTANCE, CHOOSE_CARD
}

public class SimpleTable implements Table{
    
    private IDeck deck;
    private CircularList<Player> players;
    private Player currentPlayer;
    private boolean sheriffIsDead = false;
    private int countOutlaws = 0;
    private List<String> usedCards = new ArrayList<>();
    
    private TurnObservable<List<Player>> choosePlayersObservable = new TurnObservable<>();
    private TurnObservable<Map<Card, Player>> chooseCardsObservable = new TurnObservable<>();
	private int howMany;
	private Message message;
	private int distance;
	private List<Card> cardsToChoose;
	private List<Player> choosers;
	private int howManyPerPlayer;
    
    public SimpleTable(final IDeck deck, final CircularList<Player> players) {
        this.deck = deck;
        this.players = players;
        this.currentPlayer = players.getCurrentElement();
        this.players.forEach(p -> {
            if(p.getRole().equals(Role.OUTLAW) || p.getRole().equals(Role.RENEGADE)) {
                this.countOutlaws++;
            }
        });
    }

    @Override
    public IDeck getDeck() {
        return this.deck;
    }

    @Override
    public CircularList<Player> getPlayers() {
        return this.players;
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    
    @Override
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    @Override
    public void drawCards(int nCards) {
        
    }

    @Override
    public void action(Effect effects, Set<Player> targets) {
        // TODO Auto-generated method stub
    }

    @Override
    public Player getNextPlayer() {
        this.usedCards.clear();
        return players.getNext();
    }

    @Override
    public boolean isOver() {
        return this.sheriffIsDead || this.countOutlaws == 0;
    }

    @Override
    public TurnObservable<List<Player>> getChoosePlayersObservable() {
        return this.choosePlayersObservable;
    }

    @Override
    public TurnObservable<Map<Card, Player>> getChooseCardsObservable() {
        return this.chooseCardsObservable;
    }

    @Override
    public void choosePlayers(int howMany) {
    	this.howMany = howMany;
    	this.message = Message.CHOOSE_PLAYER;
    }

    @Override
    public void choosePlayers(int howMany, int distance) {
    	this.howMany = howMany;
    	this.distance = distance;
    	this.message = Message.CHOOSE_PLAYER_WITH_DISTANCE;
    }

    @Override
    public void chooseCards(List<Card> cardsToChoose, List<Player> choosers, int howManyPerPlayer) {
    	this.cardsToChoose = cardsToChoose;
    	this.choosers = choosers;
    	this.howManyPerPlayer = howManyPerPlayer;
    	this.message = Message.CHOOSE_CARD;
    }

    @Override
    public void playerUsedCard(String cardName) {
        this.usedCards.add(cardName);
    }

    @Override
    public List<String> getPlayerUsedCards() {
        return this.usedCards;
    }
}
