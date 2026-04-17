package juego.dominio;

public class Card {
	private Rank rank;
	private Suit suit;
	
	public Card (Rank rank, Suit suit) {
		this.rank=rank;
		this.suit=suit;
	}
	
	public String getCardRank() {
		return rank.toString();
	}
	public int getPoints() {
		return rank.getRank();
	}
	public String getSuit() {
		return suit.getDescription();
	}
	
	@Override
	public String toString() {
		return String.format("%s%s", rank,suit);
	}
	
}
