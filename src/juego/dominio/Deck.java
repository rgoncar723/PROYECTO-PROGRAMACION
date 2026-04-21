package juego.dominio;

import java.util.Deque;
import java.util.NoSuchElementException;

public class Deck   {
	private Deque<Card> cards;
	
	public Deck(int numberOfDecks) {
		
		this.cards = DeckFactory.createCustomDeck(numberOfDecks);
	}
	
	
	public Card drawCard() {
		if(cards.isEmpty()) {
			
			//Se llama a la lista de descarte...
		}
		
		return cards.pop();
	}
	public int sizeDeck() {
		return cards.size();
	}
}
