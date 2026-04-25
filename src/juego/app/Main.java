package juego.app;

import java.util.List;

import juego.dominio.Player;

public class Main {
	public void show() {
		ConsoleInput ui = new ConsoleInput();
		int numPlayers, pointLimit, numDecks;
		List<Player> players;
		GameController game;

        ui.writeLine("========================================");
        ui.writeLine("       BIENVENIDO A CHINCHÓN 2026       ");
        ui.writeLine("========================================\n");

        // 1. Configuración inicial de la partida
        // Usamos los métodos de ConsoleInput para validar la entrada
        ui.writeLine("¿Cuántos jugadores participarán? ");
        numPlayers = ui.readInt();
        ui.writeLine("¿Límite de puntos para eliminación? (Estandar: 100): ");
        pointLimit = ui.readInt();
        ui.writeLine("¿Con cuántas barajas jugaran? (1-2): ");
        numDecks = ui.readInt();

        // 2. Creación de jugadores mediante el PlayerBuilder
        // Pasamos 'ui' para que el Builder pueda preguntar nombres y dificultades
        ui.writeLine("\n--- CONFIGURACIÓN DE JUGADORES ---");
        players = PlayerBuilder.buildPlayers(numPlayers, ui);

        // 3. Inicialización del GameController
        // Le entregamos el límite, el número de barajas y la interfaz de consola
        game = new GameController(pointLimit, numDecks, ui);
        
        for (Player p : players) {
            game.addPlayer(p);
        }

        // 4. Ejecución del bucle de juego
        ui.writeLine("¡Todo listo! Que comience la partida...");
        try {
            game.startGame();
        } catch (Exception e) {
            ui.writeLine("Se ha producido un error crítico: " + e.getMessage());
        }
    }
	

	public static void main(String[] args) {
		new Main().show();
	}

}
