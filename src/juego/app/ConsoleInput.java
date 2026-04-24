package juego.app;

import java.util.List;
import java.util.Scanner;


import juego.dominio.Card;

public class ConsoleInput {
	private final Scanner kb;

	public ConsoleInput() {
		kb = new Scanner(System.in);
	}

	void cleanInput() {// Limpiar el buffer

		kb.nextLine();

	}

	public int readInt() {
		int numberInt = 0;
		boolean error = true;
		do {
			try {
				numberInt = Integer.parseInt(kb.nextLine());
				error = false;
			} catch (NumberFormatException e) {
				System.out.printf("Error: El valor debe ser un número entero entre %d y %d.\n",
						Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
		} while (error);
		return numberInt;
	}

	public int readIntLessThan(int upperBound) {
		int numberInt;
		do {
			numberInt = readInt();
			if (numberInt >= upperBound) {
				System.out.printf("El valor %d debe ser menor que %d.\n", numberInt, upperBound);
			}
		} while (numberInt >= upperBound);
		return numberInt;
	}

	public int readIntLessOrEqualThan(int upperBound) {
		int numberInt;
		do {
			numberInt = readInt();
			if (numberInt > upperBound) {
				System.out.printf("El valor %d debe ser menor o igual que %d.\n", numberInt, upperBound);
			}
		} while (numberInt > upperBound);
		return numberInt;
	}

	public int readIntGreaterThan(int lowerBound) {
		int numberInt;
		do {
			numberInt = readInt();
			if (numberInt <= lowerBound) {
				System.out.printf("El valor %d debe ser mayor que %d.\n", numberInt, lowerBound);
			}
		} while (numberInt <= lowerBound);
		return numberInt;
	}

	public int readIntGreaterOrEqualThan(int lowerBound) {
		int numberInt;
		do {
			numberInt = readInt();
			if (numberInt < lowerBound) {
				System.out.printf("El valor %d debe ser mayor o igual que %d.\n", numberInt, lowerBound);
			}
		} while (numberInt < lowerBound);
		return numberInt;
	}

	public int readIntInRange(int lowerBound, int upperBound) {
		int numberInt;
		do {
			System.out.printf("Introduce un número entre %d y %d: ", lowerBound, upperBound);
			numberInt = readInt();
			if (numberInt < lowerBound || numberInt > upperBound) {
				System.out.printf("Error: %d no está en el rango [%d, %d].\n", numberInt, lowerBound, upperBound);
			}
		} while (numberInt < lowerBound || numberInt > upperBound);
		return numberInt;
	}

	public double readDouble() {
		double numberDouble = 0.0;
		boolean error = true;
		do {
			try {
				numberDouble = Double.parseDouble(kb.nextLine());
				error = false;
			} catch (NumberFormatException e) {
				System.out.printf("Error: El valor debe ser un número decimal válido.\n");
			}
		} while (error);
		return numberDouble;
	}

	public double readDoubleLessThan(double upperBound) {
		double numberDouble;
		do {
			numberDouble = readDouble();
			if (numberDouble >= upperBound) {
				System.out.printf("El valor %f debe ser menor que %f.\n", numberDouble, upperBound);
			}
		} while (numberDouble >= upperBound);
		return numberDouble;
	}

	public double readDoubleLessOrEqualThan(double upperBound) {
		double numberDouble;
		do {
			numberDouble = readDouble();
			if (numberDouble > upperBound) {
				System.out.printf("El valor %f debe ser menor o igual que %f.\n", numberDouble, upperBound);
			}
		} while (numberDouble > upperBound);
		return numberDouble;
	}

	public double readDoubleGreaterThan(double lowerBound) {
		double numberDouble;
		do {
			numberDouble = readDouble();
			if (numberDouble <= lowerBound) {
				System.out.printf("El valor %f debe ser mayor que %f.\n", numberDouble, lowerBound);
			}
		} while (numberDouble <= lowerBound);
		return numberDouble;
	}

	public double readDoubleGreaterOrEqualThan(double lowerBound) {
		double numberDouble;
		do {
			numberDouble = readDouble();
			if (numberDouble < lowerBound) {
				System.out.printf("El valor %f debe ser mayor o igual que %f.\n", numberDouble, lowerBound);
			}
		} while (numberDouble < lowerBound);
		return numberDouble;
	}

	public double readDoubleInRange(double lowerBound, double upperBound) {
		double numberDouble;
		do {
			System.out.printf("Introduce un número decimal entre %f y %f: ", lowerBound, upperBound);
			numberDouble = readDouble();
			if (numberDouble < lowerBound || numberDouble > upperBound) {
				System.out.printf("Error: %f está fuera de rango.\n", numberDouble);
			}
		} while (numberDouble < lowerBound || numberDouble > upperBound);
		return numberDouble;
	}

	public String readString() {
		return kb.nextLine();
	}

	public String readStringNotEmpty() {
		String text;
		do {
			text = kb.nextLine();

			if (text.trim().isEmpty()) {
				System.out.println("No puedes ingresar una cadena vacía.");
			}
		} while (text.trim().isEmpty());
		return text;
	}

	public String readString(int maxLength) {
		String text;
		do {
			System.out.printf("Máximo %d caracteres: ", maxLength);
			text = kb.nextLine();

			if (text.length() > maxLength) {
				System.out.println("La cadena supera el máximo permitido.");
			}
		} while (text.length() > maxLength);
		return text;
	}

	public char readChar() {
		String text;

		do {
			text = kb.nextLine().trim();

			if (text.length() != 1) {
				System.out.println("Por favor, introduce un solo carácter.");
			}
		} while (text.length() != 1);
		return text.charAt(0);
	}

	public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue) {
		char input;
		boolean result = false;
		boolean error = true;

		char upperAffirmative = Character.toUpperCase(affirmativeValue);
		char upperNegative = Character.toUpperCase(negativeValue);

		do {
			System.out.printf("Introduce '%c' o '%c': ", upperAffirmative, upperNegative);
			input = Character.toUpperCase(readChar());

			if (input == upperAffirmative) {
				result = true;
				error = false;
			} else if (input == upperNegative) {
				result = false;
				error = false;
			} else {
				System.out.println("** Carácter inválido **");
			}
		} while (error);

		return result;
	}
	public void write(String texto) {
        System.out.print(texto);
    }

    public void writeLine(String texto) { 
        System.out.println(texto);
    }
	public void showHand(String playerName, List<Card>cards) {
		
	System.out.printf("--- MANO DE %s ---\n", playerName.toUpperCase());
    for (int i = 0; i < cards.size(); i++) {
       
        System.out.printf("[%d] %s\n", i + 1, cards.get(i).toString());
    }
    System.out.println("------------");
	}

	
	public int getDrawChoice(boolean isDiscardEmpty) {
    int choice = 0;
    do {
    	 System.out.print("¿Robar de [1] MAZO" + (isDiscardEmpty ? "" : " o [2] DESCARTE") + "?: ");
         try {
             choice = Integer.parseInt(kb.nextLine().trim()); 
             if (isDiscardEmpty && choice == 2) {
                 System.out.println("La pila de descarte está vacía. Debes robar del mazo.");
                 choice = 0;
             }
         } catch (NumberFormatException e) {
             System.out.println("Error: Introduce 1 o 2.");
         }
    } while (choice != 1 && choice != 2);
       
    return choice;
}
	public int getDiscardIndex(int handSize) {
    return readIntInRange(1, handSize) - 1;
	}
}

