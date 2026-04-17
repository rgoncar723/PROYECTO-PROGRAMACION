package juego.dominio;

public enum Rank {
	ACE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),SOTA(10),KNIGHT(11),KING(12);
	
	private final int value;
	Rank(int value){
		this.value=value;
	}
	public int getRank() {
		return value;
	}
}
