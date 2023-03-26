/** 
 * Reads commands through a file and creates a 2D Array that represents a garden based on the
 * specified number of rows and columns. Then reads in the commands from the file and appropriately
 * changes the garden according to the command. If the specified file cannot be found, an error
 * message will appear. Otherwise, the file will be parsed and the garden will be affected
 * according to the commands within the file.
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GardenMain {
	static int NUM_ROWS;
  	static int NUM_COLS;

	public static void main(String[] args) throws FileNotFoundException{
		try {
			Scanner rfile = new Scanner(new File(args[0]));
	        NUM_ROWS = Integer.valueOf(rfile.nextLine().split(" ")[1]);
	        NUM_COLS = Integer.valueOf(rfile.nextLine().split(" ")[1]);
	        
	        if (NUM_COLS > 16) {
	        	System.out.println("Too many plot columns.");
	        }
	        
	        else {
				Garden garden = new Garden(NUM_ROWS, NUM_COLS);
				while (rfile.hasNextLine()) {
					String line = rfile.nextLine();
			        String[] splitString = line.split(" ");
			        
			        if (!splitString[0].equals("")) {
			        	
			        	if (splitString[0].toLowerCase().equals("plant")) {
			        		int[] coords = getCoords(splitString[1]);
		        			garden.plant(coords[0], coords[1], splitString[2].toLowerCase(), splitString[2].substring(0,1).toLowerCase());
			        	}
			        	
			        	else if (splitString[0].toLowerCase().equals("print")) {
			        		System.out.println("> " + line.toUpperCase());
			        		garden.print();
			        	}
			        	
			        	else if (splitString[0].toLowerCase().equals("grow")) {
			        		int count = Integer.valueOf(splitString[1]);
			        		
			        		if (splitString.length == 2) {
				        		System.out.println("> " + line.toUpperCase() + "\n");
				        		garden.grow(count);
			        		}
			        		
			        		else {
			        			String[] coordOrPlant = splitString[2].split(",");
			        			if (coordOrPlant.length == 2) {
					        		System.out.println("> " + line.toUpperCase() + "\n");
					        		int[] coords = getCoords(splitString[2]);
			        				garden.grow(count, coords[0], coords[1]);
			        			}
			        			else {
					        		System.out.println("> " + splitString[0].toUpperCase() + " " + splitString[1] + " " + splitString[2] + "\n");
			        				garden.grow(count, coordOrPlant[0].toLowerCase());
			        			}
			        		}
			        	}
			        	
			        	else if (splitString[0].toLowerCase().equals("harvest")) {
			        		if (splitString.length == 1) {
				        		System.out.println("> " + line.toUpperCase() + "\n");
			        			garden.harvest();
			        		}
			        		
			        		else {
			        			String[] coordOrType = splitString[1].split(",");	
			        			if (coordOrType.length == 2) {
					        		System.out.println("> " + line.toUpperCase() + "\n");
					        		int[] coords = getCoords(splitString[1]);
					        		garden.harvest(coords[0], coords[1]);
			        			}
			        			else {
					        		System.out.println("> " + splitString[0].toUpperCase() + " " + splitString[1] + "\n");
			        				garden.harvest(splitString[1].toLowerCase());
			        			}
			        		}
			        	}
			        	
			        	else if (splitString[0].toLowerCase().equals("pick")) {
			        		if (splitString.length == 1) {
				        		System.out.println("> " + line.toUpperCase() + "\n");
			        			garden.pick();
			        		}
			        		
			        		else {
			        			String[] coordOrType = splitString[1].split(",");
			        			if (coordOrType.length == 2) {
					        		System.out.println("> " + line.toUpperCase() + "\n");
					        		int[] coords = getCoords(splitString[1]);
					        		garden.pick(coords[0], coords[1]);
			        			}
			        			else {
					        		System.out.println("> " + splitString[0].toUpperCase() + " " + splitString[1] + "\n");
			        				garden.pick(splitString[1].toLowerCase());

			        			}
			        		}
			        	}
			        	
			        	else if (splitString[0].toLowerCase().equals("cut")) {
			        		if (splitString.length == 1) {
				        		System.out.println("> " + line.toUpperCase() + "\n");
			        			garden.cut();
			        		}
			        		
			        		else {
			        			String[] coordOrType = splitString[1].split(",");
			        			if (coordOrType.length == 2) {
					        		System.out.println("> " + line.toUpperCase() + "\n");
					        		int[] coords = getCoords(splitString[1]);
					        		garden.cut(coords[0], coords[1]);
			        			}
			        			else {
					        		System.out.println("> " + splitString[0].toUpperCase() + " " + splitString[1] + "\n");
			        				garden.cut(splitString[1].toLowerCase());
			        			}
			        		}
			        	}
			        }
			    }
	        }
		}
		
		catch(java.io.FileNotFoundException e){
			System.out.println("The specified file could not be found!");
		}
	}
	
	/**
	 * 
	 * @param coordinates a string representation of coordinates
	 * @return an array of integers of size 2
	 * 
	 * The function takes a string representation of coordinates and returns an integer array
	 * such that the integer at index 0 is the x-coordinate and the integer at index 1 is the
	 * y-coordinate.
	 */
	public static int[] getCoords(String coordinates) {
		String[] coords = coordinates.split(",");
		int[] int_coords = new int[] {Integer.valueOf(coords[0].substring(1)), Integer.valueOf(coords[1].substring(0, coords[1].length() - 1))};
		return int_coords;
	}
}
