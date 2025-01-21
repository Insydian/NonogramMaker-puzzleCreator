package edu.ou.cs2334.project5.views.clues;

import javafx.geometry.Orientation;

/**
 * Represents a view containing all row clues displayed to the left of the grid.
 * 
 * @author laru0009
 * @version 0.1
 */
public class LeftCluesView extends AbstractGroupCluesView {

	/**
	 * The style class for this view.
	 */
	private static final String STYLE_CLASS = "left-clues-view";
	
	/**
	 * Constructs a LeftCluesView given a set of clues, cell length, and width.
	 * 
	 * @param rowClues a set of horizontal row clues
	 * @param cellLength the length of a cell
	 * @param width the maximum number of numbered clues among all rows
	 */
	public LeftCluesView(int[][] rowClues, int cellLength, int width) {
		super(Orientation.VERTICAL, STYLE_CLASS, rowClues, cellLength, width);
		setMaxHeight(rowClues.length * cellLength);
		
		// TODO: Possibly add something here. Do not directly discuss your
		// solution on Discord.
	}
	
	@Override
	protected AbstractOrientedClueView makeClue(int[] clue, int cellLength, int numClueUnits) {
		// TODO Auto-generated method stub
		
		return new HorizontalClueView(clue, cellLength, numClueUnits);
	}

}
