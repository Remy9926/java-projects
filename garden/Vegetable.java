/**
 * Inherits from the Plant class and is a Vegetable in the garden.
 */

public class Vegetable extends Plant{

	public Vegetable(String type, String symbol) {
		super(type, symbol);
		this.plot[0][2] = this.symbol;
	}
	
	/**
	 * Changes the plot of the Vegetable object so that it is growing from top to bottom.
	 */
	void editPlot() {
		for (int i = 0; i < this.level; i++) {
			this.plot[i][2] = this.symbol;
		}
	}
	
	/**
	 * Overrides the toString() method to return the Plant subclass of the object.
	 */
	public String toString() {
		return "vegetable";
	}
}