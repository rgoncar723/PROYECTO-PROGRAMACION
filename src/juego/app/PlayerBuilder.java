package juego.app;

import java.util.ArrayList;
import java.util.List;

import juego.dominio.AIPlayer;
import juego.dominio.HumanPlayer;
import juego.dominio.Player;

public class PlayerBuilder {
	public static List<Player> buildPlayers(int count, ConsoleInput ui) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            players.add(askPlayerType(i + 1, ui));
        }
        return players;
    }
	public static Player askPlayerType(int index, ConsoleInput ci) {
		String difficulty;
		int diffChoice;
		ci.writeLine("Nombre del jugador " + index + ": ");
        String name = ci.readString();
        ci.writeLine("Selecciona tipo: (1) Humano | (2) IA");
        int type = ci.readIntInRange(1, 2);

        if (type == 1) {
            return new HumanPlayer(name, ci);
        } else {
            // Selección de dificultad para la IA
            ci.writeLine("Dificultad para " + name + ": (1) Fácil | (2) Media | (3) Difícil");
            diffChoice = ci.readIntInRange(1, 3);
            
            difficulty = switch (diffChoice) {
                case 1 -> "EASY";
                case 3 -> "HARD";
                case 2 -> "MEDIUM";
                default -> "MEDIUM";
            };
            
            return new AIPlayer(name, difficulty);
        }
    }

}
