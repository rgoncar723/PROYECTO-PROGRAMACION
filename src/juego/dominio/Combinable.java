package juego.dominio;

import java.util.List;

public interface Combinable {
	boolean isSequence(List<Card> cards);
	boolean isGroup(List<Card>cards);
}
