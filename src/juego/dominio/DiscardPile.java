package juego.dominio;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

public class DiscardPile {
	private Deque<Card> discardCards;
	
	public DiscardPile() {
		this.discardCards = new ArrayDeque<>();
	}
	public void push(Card card) {
		discardCards.add(card);
	}
	public Card pop() {
		if(discardCards.isEmpty()) {
			throw new NoSuchElementException("Discard pile is empty...");
		}
		return discardCards.pop();
	}
	public Card peek() {
		return discardCards.peek();
	}
	
	public List<Card> grabAllButLast(){
		Card topCard = discardCards.pop();
		List<Card> temporaryCards = new ArrayList<>(discardCards);
		discardCards.clear();
		discardCards.push(topCard);
		return temporaryCards;
	}

}
