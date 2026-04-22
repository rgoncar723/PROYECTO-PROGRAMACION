package juego.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hand {
	private List<Card> cards;
	private static final int HAND_SIZE = 7;
	
	public Hand() {
		cards = new ArrayList<>();
	}
	
	public void addCard(Card card) {
		if(cards.size()<8) {
			cards.add(card);
		}
	}
	public void removeCard(Card card) {
		cards.remove(card);
	}
	public void sortBySuit() {
		cards.
		sort(Comparator.comparing(Card::getSuit)
				.thenComparingInt(Card::getPoints));
	}
	public void sortByRank() {
		cards
		.sort(Comparator.comparingInt(Card::getPoints));
	}
	public boolean canClose() {
		return false;
	}
	public Card getBestDiscard() {
		Card bestCard = null;
		int minPoints = Integer.MAX_VALUE;
		for(Card candidate : cards) {
			
		}
	}
	public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}
