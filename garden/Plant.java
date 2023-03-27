/**
 * An abstract class that is meant to be a model for Trees, Flowers, and Vegetables. All
 * Trees, Flowers, and Vegetables inherit from this class. Each Plant object will represent
 * its plot of land within the garden using a 2D array.
 */
abstract class Plant {
	
	protected String type, symbol;
	protected String[][] plot;
	protected int level = 1;
	
	public Plant(String type, String symbol) {
		this.type = type;
		this.symbol = symbol;
		this.plot = new String[5][5];
		fillPlot();
	}
	
	/**
	 * Fills the Plant object's plots with the character ".".
	 */
	void fillPlot() {
		for (int i = 0; i < this.plot.length; i++) {
			for (int j = 0; j < this.plot[i].length; j++) {
				this.plot[i][j] = ".";
			}
		}
	}
	 /**
	  * 
	  * @param level the number of times to grow the plant
	  * 
	  * Grows the plant by the specified number of times, making sure to change the Plant's
	  * plot to correspond with its growth level as well.
	  */
	void growPlant(int level) {
		for (int i = 0; i < level; i++) {
			if (this.level < 5) {
				this.level++;
			}
			editPlot();
		}
	}
	
	// A method to represent that a Plant object has grown.
	abstract void editPlot();
}