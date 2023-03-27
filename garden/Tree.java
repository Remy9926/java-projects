/**
 * Inherits from the Plant class and represents a Tree in the garden.
 */

public class Tree extends Plant{
	
	public Tree(String type, String symbol) {
		super(type, symbol);
		this.plot[4][2] = this.symbol;
	}
	
	/**
	 * Changes the plot of the Tree object to show that it is growing from the ground up.
	 */
	void editPlot() {
		for (int i = 4; i >= 5 - this.level; i--) {
			this.plot[i][2] = this.symbol;
		}
	}
	
	/**
	 * Overrides the toString() method to return the Plant subclass of the object.
	 */
	public String toString() {
		return "tree";
	}
}
