package juego.dominio;

public record Card(Rank rank, Suit suit) implements Comparable<Card>{
	
	
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
