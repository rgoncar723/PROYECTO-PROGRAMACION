package juego.dominio;

public enum Suit {
	GOLD("🪙"),COPS("🍷"),SWORDS("⚔️"),CLUBS("🦯");
	
	private final String description;
	Suit(String description) {
		this.description=description;
	}
	public String getDescription() {
		return description;
	}
}
