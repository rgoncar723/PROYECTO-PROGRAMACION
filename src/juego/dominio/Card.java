package juego.dominio;

public class Card implements Comparable<Card>{
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

	@Override
	public int compareTo(Card o) {
		if(suit != o.suit) {
			return suit.compareTo(o.suit);
		}
		return Integer.compare(rank.getRank(), o.rank.getRank());
	}
	
}
