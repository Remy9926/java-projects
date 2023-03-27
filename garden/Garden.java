/**
 * This public class Garden is meant to be a representation of the garden. The way it does so
 * is by utilizing an ArrayList that stores arrays of Plant objects. It also contains all the
 * methods to respond to the commands within the given input file.
 */

import java.util.ArrayList;

public class Garden {
	
	ArrayList<Plant[]> field = new ArrayList<Plant[]>();
	ArrayList<String> trees = new ArrayList<String>();
    ArrayList<String> flowers = new ArrayList<String>();
    ArrayList<String> vegetables = new ArrayList<String>();
					
	public Garden(int rows, int cols) {
		for (int i = 0; i < rows; i++) {
			this.field.add(new Plant[cols]);
		}
		String[] treeArray = new String[] {"oak", "willow", "banana", "coconut", "pine"};
		String[] flowerArray = new String[] {"iris", "lily", "rose", "daisy", "tulip", "sunflower"};
		String[] vegetableArray = new String[] {"garlic", "zucchini", "tomato", "yam", "lettuce"};
		for (int i = 0; i < treeArray.length; i++) {
			trees.add(treeArray[i]);
		}
		for (int i = 0; i < flowerArray.length; i++) {
			flowers.add(flowerArray[i]);
		}
		for (int i = 0; i < vegetableArray.length; i++) {
			vegetables.add(vegetableArray[i]);
		}
	}
	
	/**
	 * 
	 * @param x the x coordinate that is going to be planted at
	 * @param y the y coordinate that is going to be planted at
	 * @param type the type of Plant that is going to be planted
	 * @param symbol how the Plant is going to be represented in the garden
	 * 
	 * This method plants a Plant object within the garden at the specified coordinates.
	 */
	void plant(int x, int y, String type, String symbol) {
		if (x >= 0 && y >= 0 && x <= field.size() - 1 && y <= field.get(0).length - 1) {
			if (trees.contains(type)) {
				field.get(x)[y] = new Tree(type, symbol);
			}
			else if (flowers.contains(type)) {
				field.get(x)[y] = new Flower(type, symbol);
	
			}
			else if (vegetables.contains(type)) {
				field.get(x)[y] = new Vegetable(type, symbol);
			}
		}
	}
	
	/**
	 * Prints out the entire garden.
	 */
	void print() {
		String row1 = "";
		String row2 = "";
		String row3 = "";
		String row4 = "";
		String row5 = "";
		for (Plant[] row: field) {
			for (Plant p: row) {
				if (p != null) {
    				for (int i = 0; i < p.plot.length; i++) {	
						row1 = row1 + p.plot[0][i];
    					row2 = row2 + p.plot[1][i];
    					row3 = row3 + p.plot[2][i];
    					row4 = row4 + p.plot[3][i];
    					row5 = row5 + p.plot[4][i];
    				}
				}
				else {
					row1 = row1 + ".....";
					row2 = row2 + ".....";
					row3 = row3 + ".....";
					row4 = row4 + ".....";
					row5 = row5 + ".....";
				}
			}
			System.out.println(row1);
			System.out.println(row2);
			System.out.println(row3);
			System.out.println(row4);
			System.out.println(row5);
			row1 = "";
			row2 = "";
			row3 = "";
			row4 = "";
			row5 = "";
		}
		System.out.println();
	}
	
	/**
	 * 
	 * @param count the number of times to grow each Plant in the garden
	 * 
	 * This method iterates through each plot in the garden and if possible, grows it by
	 * the specified amount.
	 */
	void grow(int count) {
		for (Plant[] row: field) {
			for(Plant p: row) {
				if (p != null) {
					p.growPlant(count);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param count the number of times to grow the Plant in the garden
	 * @param x the x coordinate of the Plant that you want to be grown
	 * @param y the y coordinate of the Plant that you want to be grown
	 * 
	 * Grows the Plant object at the specified coordinates by the specified
	 * number of times.
	 */
	void grow(int count, int x, int y) {
		if (x >= 0 && y >= 0 && x <= field.size() - 1 && y <= field.get(0).length - 1 && field.get(x)[y] != null) {
			field.get(x)[y].growPlant(count);
		}
		
		else {
			System.out.println("Can't grow there.\n");
		}
	}
	
	/**
	 * 
	 * @param count the number of times to grow each Plant of that specific type
	 * @param family the subclass of Plant that you want to grow in the garden
	 * 
	 * Grows each Plant in the garden the specified number of times if it is part of the
	 * specified Plant subclass.
	 */
	void grow(int count, String family) {
		for (Plant[] row: field) {
			for (Plant p: row) {
				if (p!= null && p.toString().equals(family)) {
					p.growPlant(count);
				}
			}
		}
	}
	
	/**
	 * Harvests all the plants in the garden.
	 */
	void harvest() {
		for (int i = 0; i < field.size(); i++) {
			for (int j = 0; j < field.get(i).length; j++) {
				if (field.get(i)[j] != null && field.get(i)[j] instanceof Vegetable) {
					field.get(i)[j] = null;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param x the x coordinate of the Vegetable you want to harvest
	 * @param y the y coordinate of the Vegetable you want to harvest
	 * 
	 * Harvests the Vegetable at the specified coordinates. If the Plant object
	 * is not a Vegetable or the plot is empty, an error message is displayed instead.
	 */
	void harvest(int x, int y) {
		if (x >= 0 && y >= 0 && x <= field.size() - 1 && y <= field.get(0).length - 1 && field.get(x)[y] instanceof Vegetable) {
			field.get(x)[y] = null;
		}
		else {
			System.out.println("Can't harvest there.\n");
		}
	}
	
	/**
	 * 
	 * @param type the type of Vegetable that is to be harvested
	 * 
	 * Harvests all Vegetables in the garden that are of the specified type.
	 */
	void harvest(String type) {
		for (int i = 0; i < field.size(); i++) {
			for (int j = 0; j < field.get(i).length; j++) {
				if (field.get(i)[j] != null && field.get(i)[j].type.equals(type)) {
					field.get(i)[j] = null;
				}
			}
		}
	}
	
	/**
	 * Picks all Flowers in the garden.
	 */
	void pick() {
		for (int i = 0; i < field.size(); i++) {
			for (int j = 0; j < field.get(i).length; j++) {
				if (field.get(i)[j] != null && field.get(i)[j] instanceof Flower) {
					field.get(i)[j] = null;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param x the x coordinate of the Plant you want to pick
	 * @param y the y coordinate of the Plant you want to pick
	 * 
	 * Picks the Flower at the specified coordinates. If the Plant object
	 * is not a Flower or if the plot is empty, an error message is displayed.
	 */
	void pick(int x, int y) {
		if (x >= 0 && y >= 0 && x <= field.size() - 1 && y <= field.get(0).length - 1 && field.get(x)[y] instanceof Flower) {
			field.get(x)[y] = null;
		}
		else {
			System.out.println("Can't pick there.\n");
		}
	}
	
	/**
	 * 
	 * @param type the type of Flower you want to pick from the garden
	 * 
	 * Picks all the Flowers of the specified type in the garden.
	 */
	void pick(String type) {
		for (int i = 0; i < field.size(); i++) {
			for (int j = 0; j < field.get(i).length; j++) {
				if (field.get(i)[j] != null && field.get(i)[j].type.equals(type)) {
					field.get(i)[j] = null;
				}
			}
		}
	}
	
	/**
	 * Cuts all Trees in the garden.
	 */
	void cut() {
		for (int i = 0; i < field.size(); i++) {
			for (int j = 0; j < field.get(i).length; j++) {
				if (field.get(i)[j] != null && field.get(i)[j] instanceof Tree) {
					field.get(i)[j] = null;
				}
			}
		}
	}
	
	/**
	 * 
	 * @param x the x coordinate of the Tree you want to cut
	 * @param y the y coordinate of the Tree you want to cut
	 * 
	 * Cuts the Tree at the specified coordinates. If the Plant object is not a Tree
	 * or if the coordinates are empty, an error messaged is displayed.
	 */
	void cut(int x, int y) {
		if (x >= 0 && y >= 0 && x <= field.size() - 1 && y <= field.get(0).length - 1 && field.get(x)[y] instanceof Tree) {
			field.get(x)[y] = null;
		}
		else {
			System.out.println("Can't cut there.\n");
		}
	}
	
	/**
	 * 
	 * @param type the type of Flower you want to cut
	 * 
	 * Cuts all Flowers of the specified type in the garden.
	 */
	void cut(String type) {
		for (int i = 0; i < field.size(); i++) {
			for (int j = 0; j < field.get(i).length; j++) {
				if (field.get(i)[j] != null && field.get(i)[j].type.equals(type)) {
					field.get(i)[j] = null;
				}
			}
		}
	}
}