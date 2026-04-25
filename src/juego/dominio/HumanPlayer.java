package juego.dominio;

import java.util.Optional;

import juego.app.ConsoleInput;

public final class HumanPlayer extends Player {
	private ConsoleInput ci;

	public HumanPlayer(String name, ConsoleInput ci) {
		super(name);
		this.ci=ci;
		
	}

	@Override
	public void playTurn(Deck deck, DiscardPile discardPile) {
	    int drawChoice, discardIndex;
	    Card cardToDiscard;
	    Card drawnCard = null; 
	    Optional<Card> optCard;

	    // Muestra el estado actual al jugador
	    ci.displayBoard(this, discardPile);

	    // Fase de robo
	    ci.writeLine("¿Robar de (1) Mazo o (2) Descarte?: ");
	    drawChoice = ci.readIntInRange(1, 2);         
	    
	    if (drawChoice == 1) {
	        // deck.drawCard devuelve Card, asignación directa
	        drawnCard = deck.drawCard(discardPile); 
	    } else {
	        // discardPile.pop() devuelve Optional<Card>
	       optCard = discardPile.pop();
	        
	        if (optCard.isPresent()) {
	            drawnCard = optCard.get();
	        } else {
	            ci.writeLine("Pila vacía. Robando del mazo...");
	            drawnCard = deck.drawCard(discardPile);
	        }
	    }

	    // Añadir la carta a la mano si logramos obtener una
	    if (drawnCard != null) {
	        hand.addCard(drawnCard);
	    }

	    // 3. Mostrar mano actualizada (ahora tiene 8 cartas)
	    ci.displayHand(hand);

	    // 4. FASE DE DESCARTE
	    ci.writeLine("Elige el índice de la carta a descartar (1-8): ");
	    discardIndex = ci.readIntInRange(1, 8);
	    
	    // Obtenemos la carta de la lista (índice - 1)
	    cardToDiscard = hand.getCards().get(discardIndex - 1);
	    
	    hand.removeCard(cardToDiscard);
	    discardPile.push(cardToDiscard);
	    
	    ci.writeLine("Has descartado: " + cardToDiscard);
	}

		
}


