/**
 * Inherits from the Plant class and is representative of a Flower in the garden.
 */

import java.util.ArrayList;

public class Flower extends Plant{

	public Flower(String type, String symbol) {
		super(type, symbol);
		this.plot[2][2] = this.symbol;
	}
	
	/**
	 * Blooms the flower each time that it is grown by iterating through the entire plot
	 * and keeping track of which coordinates are already filled. Then, iterates through each
	 * coordinate and checks above, below, left, and right of the coordinate and if possible, fills
	 * that coordinate.
	 */
	void editPlot() {
		ArrayList<Integer[]> filled = new ArrayList<>();
		for (int i = 0; i < this.plot.length; i++) {
			for (int j = 0; j < this.plot.length; j++) {
				if (this.plot[i][j].equals(this.symbol)) {
					Integer[] coords = new Integer[] {i,j};
					filled.add(coords);
				}
			}
		}
		for(Integer[] a: filled) {
			int x = a[0];
			int y = a[1];
			if (x - 1 >= 0) {
				this.plot[x-1][y] = this.symbol;
			}
			if (x + 1 <= 4) {
				this.plot[x + 1][y] = this.symbol;
			}
			if (y - 1 >= 0) {
				this.plot[x][y - 1] = this.symbol;
			}
			if (y + 1 <= 4) {
				this.plot[x][y + 1] = this.symbol;
			}
		}
		filled.clear();
	}
	
	/**
	 * Overrides the toString() method to return the Plant subclass of the object.
	 */
	public String toString() {
		return "flower";
	}
}