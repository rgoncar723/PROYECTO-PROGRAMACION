package juego.dominio;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public final class AIPlayer extends Player {
    private final String difficulty;

    public AIPlayer(String name, String difficulty) {
        super(name);
        this.difficulty = difficulty.toUpperCase();
    }

    @Override
    public void playTurn(Deck deck, DiscardPile discardPile) {
       
        Optional<Card> topCard = discardPile.peek();
        Card toDiscard;
        
        if (topCard.isPresent() && shouldIDrawFromDiscard(topCard.get())) {
           
            discardPile.pop().ifPresent(c -> hand.addCard(c));
        } else {
            hand.addCard(deck.drawCard(discardPile));
        }


         toDiscard = switch (difficulty) {
            case "EASY" -> discardRadomnly();
            case "HARD" -> evaluateOptimalDiscard();
            case "MEDIUM"    -> evaluateByPoints();
            default -> discardRadomnly();
        };

        hand.removeCard(toDiscard);
        discardPile.push(toDiscard);
    }

    private boolean shouldIDrawFromDiscard(Card card) {
    	List<Card> simulatedHand;
        if (difficulty.equals("EASY")) {
            return false;
        }
        
        simulatedHand = new ArrayList<>(hand.getCards());
        simulatedHand.add(card);
        return isPartOfCombination(card, simulatedHand);
    }

    private Card discardRadomnly() {
        return hand.getCards().getFirst();
    }

    private Card evaluateByPoints() {
        return hand.getCards().stream()
                .max(Comparator.comparingInt(Card::getPoints))
                .orElse(hand.getCards().getFirst());
    }

    private Card evaluateOptimalDiscard() {
        List<Card> currentCards = hand.getCards();

        List<Card> looseCards = currentCards.stream()
                .filter(c -> !isPartOfCombination(c, currentCards))
                .toList();

        if (looseCards.isEmpty()) {
            return evaluateByPoints();
        }

        return looseCards.stream()
                .max(Comparator.comparingInt(Card::getPoints))
                .orElse(looseCards.getFirst());
    }

    private boolean isPartOfCombination(Card card, List<Card> set) {
        List <Card> sameSuitOrdered;
    	long rankCount = set.stream()
                .filter(c -> c.rank().equals(card.rank()))
                .count();
        if (rankCount >= 3) {
        	return true;
        }

        sameSuitOrdered = set.stream()
                .filter(c -> c.suit().equals(card.suit()))
                .sorted(Comparator.comparingInt(Card::getPoints))
                .toList();

        return isCardInSequence(card, sameSuitOrdered);
    }

    private boolean isCardInSequence(Card target, List<Card> orderedSuitCards) {
    	int index;
    	long backCount, forwardCount;
        if (orderedSuitCards.size() < 3) {
        	return false;
        }

         index = orderedSuitCards.indexOf(target);
        
        backCount = countConsecutive(orderedSuitCards, index, -1);
        forwardCount = countConsecutive(orderedSuitCards, index, 1);

        return (backCount + forwardCount + 1) >= 3;
    }

    private long countConsecutive(List<Card> cards, int startIndex, int direction) {
        long count = 0;
        int currentIdx = startIndex;
        boolean keepCounting = true;

        while (keepCounting && currentIdx + direction >= 0 && currentIdx + direction < cards.size()) {
            int currentVal = cards.get(currentIdx).rank().getRank();
            int nextVal = cards.get(currentIdx + direction).rank().getRank();

            if (isConsecutive(currentVal, nextVal, direction)) {
                count++;
                currentIdx += direction;
            } else {
                keepCounting = false;
            }
        }
        return count;
    }

    private boolean isConsecutive(int current, int next, int direction) {
        if (direction == 1) {
            return next == current + 1 || (current == 7 && next == 10);
        } else {
            return next == current - 1 || (current == 10 && next == 7);
        }
    }
}