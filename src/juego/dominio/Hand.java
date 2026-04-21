package juego.dominio;

import java.util.ArrayList;
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
	public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

}
