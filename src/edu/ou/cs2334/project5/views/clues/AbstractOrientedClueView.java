package edu.ou.cs2334.project5.views.clues;

import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;

/**
 * This class provides a basic implementation of an AbstractOrientedClueView. It
 * stores a collection of ClueItemView references. The elements are stored in a
 * List and displayed using a FlowPane the layout of the FlowPane is specified
 * by child classes.
 * 
 * @author laru0009
 * @version 0.1
 */
public abstract class AbstractOrientedClueView extends FlowPane {

	/**
	 * A list of ClueItemView elements.
	 */
	private List<ClueItemView> clueViews = new LinkedList<>();

	/**
	 * Initializes an AbstractOrientedClueView using the given parameter values.
	 * 
	 * @param orientation the orientation of the view (HORIZONTAL or VERTICAL)
	 * @param styleClass the name of a styling class for the view
	 * @param clues an array of clues
	 * @param cellLength the length of a cell
	 * @param numClueUnits the maximum number of numbered clues overall
	 */
	protected AbstractOrientedClueView(
			Orientation orientation, String styleClass, 
			int[] clues, int cellLength, int numClueUnits) {
		// Set the orientation -------------------------------------------------
		setOrientation(orientation);
		
		// Add styling class ---------------------------------------------------
		getStyleClass().add(styleClass);
		
		// Add clues -----------------------------------------------------------
		
		// Add clues from the clues list
		for (int clue : clues) {
			clueViews.add(new ClueItemView(clue, cellLength));
		}
		
		// Prepend the list with blank ClueViews to pad the front
		// (e.g., say there are 2 clues [1, 2] but a maximum of
		// 4 clues overall (since another ClueView may have more
		// clues) so we display something like "   1 2"
		while (clueViews.size() < numClueUnits) {
			clueViews.add(0, new ClueItemView(cellLength));
		}
		
		// Add these elements to the pane --------------------------------------
		getChildren().addAll(clueViews);
		
		// Use preferred dimensions --------------------------------------------
		setMaxWidth(USE_PREF_SIZE);
		setMaxHeight(USE_PREF_SIZE);
		
		// BUG: javafx flow panel seems to wrap at size 400 even though we don't
		// want it to wrap at all
		// Try searching to see if flowpanel has a default wrap length and see
		// if you can set it to a very large, maximum value
		setPrefWrapLength(Double.MAX_VALUE);
	}

	/**
	 * Update the solved status of each ClueItemView.
	 * 
	 * @param solved whether or not the clue is solved
	 */
	public void setState(boolean solved) {
		for (ClueItemView clueView : clueViews) {
			clueView.setState(solved);
		}
	}

}
