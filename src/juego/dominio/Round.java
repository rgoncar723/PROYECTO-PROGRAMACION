package juego.dominio;

import java.util.List;

public class Round {
    private final List<Player> players;
    private final Deck deck;
    private final DiscardPile pile;
    private int currentPlayerIndex;
    private boolean roundOver;

    public Round(List<Player> players, int numDecks) { 
        this.players = players;
        this.deck = new Deck(numDecks); 
        this.pile = new DiscardPile();
        this.currentPlayerIndex = 0;
        this.roundOver = false;
    }

   
    public void dealCards() {
        for (Player p : players) {
            
            p.getHand().reset(); 
        }

        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.getHand().addCard(deck.drawCard(pile));
            }
        }
    }


    public void execute() {
        dealCards();
        while (!isRoundOver()) {
            start(); 
            if (!roundOver) {
                nextTurn();
            }
        }
    }

    
    public void start() {
        Player currentPlayer = players.get(currentPlayerIndex);
        
        
        currentPlayer.playTurn(deck, pile);

       
        if (currentPlayer.getHand().canClose(currentPlayer.getScore())) {
            this.roundOver = true;
        }
    }

    
    public void nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

  

    public boolean isRoundOver() {
        return roundOver;
    }

    
    public Player getWinner() {
        return players.get(currentPlayerIndex);
    }

    
    public Deck getDeck() {
        return deck;
    }

    
    public DiscardPile getPile() {
        return pile;
    }
}