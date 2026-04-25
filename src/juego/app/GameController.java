package juego.app;

import juego.dominio.*;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private List<Player> players;
    private int pointLimit;
    private List<Round> rounds;
    private ConsoleInput ci;
    private int numberOfDecks;

    public GameController(int pointLimit, int numberOfDecks, ConsoleInput ui) {
        this.players = new ArrayList<>();
        this.rounds = new ArrayList<>();
        this.pointLimit = pointLimit;
        this.numberOfDecks = numberOfDecks;
        this.ci = ui;
    }

    /**
     * Punto de entrada principal tras configurar los jugadores.
     */
    public void startGame() {
        ci.write("¡Bienvenidos al Chinchón!");
        startGameLoop();
    }

    /**
     * Bucle principal: Una iteración = Una ronda completa.
     */
    public void startGameLoop() {
    	do {
    		 nextRound();      // Crea y ejecutar la ronda
             updateScores();    // Suma puntos de cartas sueltas
             eliminatePlayers(); //Expulsa a los que superan el límite
             
             ci.displayScores(players); // Muestra cómo va la tabla
    	}
        while (!isGameOver()); 
        
        declareWinner();
    }

    /**
     * Crea una nueva instancia de Round y la ejecuta.
     */
    public void nextRound() {
        // En cada ronda el mazo se regenera 
        Round currentRound = new Round(players, numberOfDecks);
        rounds.add(currentRound);
        
        ci.write("--- Iniciando nueva ronda ---");
        currentRound.execute(); 
    }

    /**
     * Al finalizar una ronda, se penaliza a los jugadores.
     */
    public void updateScores() {
        for (Player p : players) {
            // Obtenemos los puntos de las cartas que no pudo combinar
            int pointsToAdd = p.getHand().calculateUncombinedPoints();
            p.addPoints(pointsToAdd);
            
            ci.write(p.getName() + " suma " + pointsToAdd + " puntos.");
        }
    }

    /**
     * Filtra la lista de jugadores eliminando a los que perdieron.
     */
    public void eliminatePlayers() {
        // Usamos removeIf para limpiar la lista de forma eficiente
        players.removeIf(p -> {
            boolean eliminated = p.isEliminated(pointLimit);
            if (eliminated) {
                ci.write("El jugador " + p.getName() + " ha sido eliminado.");
            }
            return eliminated;
        });
    }

    /**
     * El juego termina si solo queda un jugador vivo (o ninguno por empate técnico).
     */
    public boolean isGameOver() {
        return players.size() <= 1;
    }

    /**
     * Muestra quién es el campeón.
     */
    public Player declareWinner() {
        if (players.isEmpty()) {
            ci.write("No hay ganadores, todos han sido eliminados.");
            return null;
        }
        Player winner = players.get(0); // El ganador
        ci.write("¡EL GANADOR ES " + winner.getName().toUpperCase() + "!");
        return winner;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
