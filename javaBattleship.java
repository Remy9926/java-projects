/**
 * Simulates a game of Battleship on a 5x5 board against a computer. As a player you are given
 * 2 ships each of length 3 and place them on the field at the beginning of the game. Then,
 * you and the computer each take turns attacking each other. The first player to sink both
 * of their opponents ships wins.
 */

import java.util.Scanner;

public class javaBattleship
{

	public static void main(String[] args)
	{
		String[][] player_grid = new String[5][5];
		String[][] player_view = new String[5][5];
		String[][] cpu_grid = new String[5][5];
		fillGrid(player_grid);
		fillGrid(player_view);
		fillGrid(cpu_grid);
		placeOpponentShips(cpu_grid);
		
		Scanner input = new Scanner(System.in);
		System.out.println("What is your first name?");
		String name = input.next();
		System.out.println("\nPlace your ships " + name + "!" + "\nYou have two cruisers that are both of length 3.\n"
				+ "Place ship number 1:");
		
		for (int i = 0; i < 3; i++) {
			placeShips(player_grid);
		}
		System.out.println("Place ship number 2:");
		for (int i = 0; i < 3; i++) {
			placeShips(player_grid);
		}
		printGrid(player_view);
		System.out.println("");
		printGrid(player_grid);
		
		int player_hits = 0;
		int cpu_hits = 0;
		while (player_hits != 6 && cpu_hits != 6) {
			System.out.println("\nCall your shot " + name + "!");
			player_hits = player_hits + hitOpponent(cpu_grid, player_view);
			printGrid(player_view);
			System.out.println("");
			printGrid(player_grid);
			
			if (player_hits == 6)
					System.out.print("You win " + name + " !!!");
			else {
				cpu_hits = cpu_hits + opponentAttack(player_grid);
				System.out.println("Press the enter key to see the computer's shot:");
				input = new Scanner(System.in);
				String empty = input.nextLine();
				printGrid(player_view);
				System.out.println("");
				printGrid(player_grid);
				if (cpu_hits == 6)
						System.out.print("You lose " + name + " !!!");
			}
		}
		
	}
	
	/**
	 * 
	 * @param grid a 2D string array
	 * 
	 * This method will make every index of the grid that is passed through it the █ character.
	 */
	public static void fillGrid(String[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = "█";
			}
		}
	}
	
	/**
	 * 
	 * @param grid a 2D string array
	 * 
	 * This method will print out the items in the array that is passed through it.
	 */
	public static void printGrid(String[][] grid) {
		for (String[] row: grid) {
			for (String col: row) {
				System.out.print(col);
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * 
	 * @param letter an uppercase character from A-E
	 * @return an integer from 0-4 depending on which character is passed through the method.
	 */
	public static int letterToInt(String letter) {
		if (letter.equals("A"))
			return 0;
		else if (letter.equals("B"))
			return 1;
		else if (letter.equals("C"))
			return 2;
		else if (letter.equals("D"))
			return 3;
		else
			return 4;
	}
	
	/**
	 * 
	 * @param grid a 2D string array
	 * 
	 * Prompts the user for input, which are the coordinate points where the user wants to
	 * place their ships on their grid.
	 */
	public static void placeShips(String[][] grid) {
		Scanner position = new Scanner(System.in);
		System.out.println("Enter the row and column (eg. B2):");
		String placement = position.next();
		String row = placement.substring(0, 1);
		String temp_col = placement.substring(1);
		int col = Integer.valueOf(temp_col);
		grid[letterToInt(row)][col] = "C";
	}
	
	/**
	 * 
	 * @param grid1 a 2D string array
	 * @param grid2 a 2D string array
	 * @return the integer 1 if an opponent's ship is hit and 0 if not.
	 * 
	 * Prompts the user to input a coordinate point to attack on the opponent's grid.
	 * Checks the opponents grid to see if there is a ship on that space and returns
	 * 1 if a ship is hit and 0 if a ship is not hit.
	 */
	public static int hitOpponent(String[][] grid1, String[][] grid2) {
		Scanner position = new Scanner(System.in);
		System.out.println("Enter the row and column (eg. B2):");
		String coordinate = position.next();
		String row = coordinate.substring(0, 1);
		String temp_col = coordinate.substring(1);
		int col = Integer.valueOf(temp_col);
		if (grid1[letterToInt(row)][col].equals("C")) {
			grid2[letterToInt(row)][col] = "H";
			grid1[letterToInt(row)][col] = "█";
			return 1;
		}
		else {
			grid2[letterToInt(row)][col] = "M";
			return 0;
		}
	}
	
	/**
	 * 
	 * @param grid a 2D string array
	 * 
	 * Randomly generates coordinates as well as a direction to place the opponent's ships.
	 * Picks a random coordinate and direction (horizontal or vertical) and checks to make
	 * sure that the parameters are valid. Then, places a ship twice.
	 */
	public static void placeOpponentShips(String[][] grid) {
		int ships_placed = 0;
		while (ships_placed != 2) {
			int dir = (int) (Math.random() * 2);
			int row = (int) (Math.random() * 5);
			int col = (int) (Math.random() * 5);
			
			if (dir == 0 && col != 0 && col != 4 && grid[row][col].equals("█") && grid[row][col - 1].equals("█") && grid[row][col + 1].equals("█")) {
				grid[row][col] = "C";
				grid[row][col - 1] = "C";
				grid[row][col + 1] = "C";
				ships_placed++;
			}
			
			else if (dir == 1 && row != 0 && row != 4 && grid[row][col].equals("█") && grid[row + 1][col].equals("█") && grid[row - 1][col].equals("█")) {
				grid[row][col] = "C";
				grid[row - 1][col] = "C";
				grid[row + 1][col] = "C";
				ships_placed++;
			}
			
			else
				;
		}
	}
	
	/**
	 * 
	 * @param grid a 2D string array
	 * @return the integer 1 if a user's ship is hit and 0 if not.
	 * 
	 * Simulates the opponent deciding a coordinate point to attack. Generates a random x and y
	 * coordinate and if the coordinate point has already been attacked, generates a new set of
	 * coordinates until a coordinate point has not been hit yet. Then, proceeds to hit that point
	 * and if a ship is hit, the user's board is changed to the character H. Otherwise, the
	 * point is changed to a M.
	 */
	public static int opponentAttack(String[][] grid) {
		int x = (int) (Math.random() * 5);
		int y = (int) (Math.random() * 5);
		while (grid[x][y] != "█" && grid[x][y] != "C") {
			x = (int) (Math.random() * 5);
			y = (int) (Math.random() * 5);
		}
		
		if (grid[x][y] == "█") {
			grid[x][y] = "M";
			return 0;
		}
		
		else {
			grid[x][y] = "H";
			return 1;
		}
	}
}
