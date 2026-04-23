package juego.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hand implements Combinable{
	private List<Card> cards;
	private static final int MAX_CARDS_TURN = 8;
    private static final int NORMAL_HAND_SIZE = 7;
	
	public Hand() {
		cards = new ArrayList<>();
	}
	
	public void addCard(Card card) {
		if(cards.size()<MAX_CARDS_TURN) {
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
	public int calculateUncombinedPoints() {
		int sum;
		sum = cards
				.stream()
				.mapToInt(Card::getPoints)
				.sum();
		return sum;
	}
	public boolean checkChinchon() {
	    if (cards.size() < NORMAL_HAND_SIZE) {
	        return false;
	    }
	    return isSequence(cards);
	}
	
	public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

	@Override
	public boolean isSequence(List<Card> subSet) {
		Suit firstSuit;
		boolean sameSuit;
		List<Card> sortedSubSet;
		if(subSet.size()<3 || subSet == null) {
			return false;
		}
		firstSuit = subSet.getFirst().suit();
		sameSuit = subSet
				.stream()
				.allMatch(c->c.suit().equals(firstSuit));
		
		if(!sameSuit) {
			return false;
		}
		
		sortedSubSet = subSet
				.stream()
				.sorted(Comparator.comparingInt(Card::getPoints))
				.toList();
		
		for(int i= 0; i <sortedSubSet.size()-1;i++) {
			int currentRank = sortedSubSet.get(i).rank().getRank();
			int nextRank = sortedSubSet.get(i+1).rank().getRank();
			
			if(!isConsecutive(currentRank,nextRank)) {
				return false;
			}
		}
		return true;
	}
	private boolean isConsecutive(int current, int next) {
		if(current == 7 && next ==10) {
			return true;
		}
		return next == current +1;
	}

	@Override
	public boolean isGroup(List<Card> subSet) {
		Rank targetRank;
		
		if(subSet.size()<3 || subSet == null) {
			return false;
		}	
		targetRank = subSet.getFirst().rank();
		
		
		return subSet
				.stream()
				.allMatch(c->c.rank().equals(targetRank));
		
	}
	public boolean canClose(List<Card> group1, List<Card> group2,  int currentScore) {
		    // Valida que los grupos sean combinaciones reales
		    boolean g1Valid = isSequence(group1) || isGroup(group1);
		    boolean g2Valid = group2.isEmpty() || isSequence(group2) || isGroup(group2);

		    if (!g1Valid || !g2Valid) {
		    	return false;
		    }

		    // Calcula cuántas cartas se han combinado en total
		    int totalCombined = group1.size() + group2.size();

		    // Lógica de puntos según el PDF
		    int pointsThisRound = calculateClosingPoints(group1, group2, totalCombined);
		    
		    // No cierra si se pasa del límite total 
		    if (currentScore + pointsThisRound >= 100) {
		        return false;
		    }
		    
		    return switch (totalCombined) {
		        case 7 -> true; 
		        case 6 -> findRemainingCard(group1, group2).getPoints() <= 5;
		        default -> false; 
		    };
		}
	private Card findRemainingCard(List<Card> group1, List<Card> group2) {
	   
	    return cards.stream()
	        .filter(card -> !group1.contains(card))
	        .filter(card -> !group2.contains(card))
	        .findFirst() 
	        .orElseThrow(() -> new IllegalStateException("No remaining card found."));
	}
	public int calculateClosingPoints(List<Card> g1, List<Card> g2, int totalCombined) {
		Card remaining;
	  	    if (totalCombined == 7 && checkChinchon()) {
	        return -100; 
	    }

	   
	    if (totalCombined == 7) {
	        return -10;
	        }

	   
	    if (totalCombined == 6) {
	        
	        remaining = findRemainingCard(g1, g2);
	        return remaining.getPoints();
	    }

	    
	    return calculateUncombinedPoints();
	}
	

}
