package edu.ou.cs2334.project5.views.clues;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represents an individual clue cell.
 * 
 * @author laru0009
 * @author 0.1
 */
public class ClueItemView extends StackPane {

	/**
	 * The base style class.
	 */
	private static final String STYLE_CLASS = "clue-item-view";
	
	/**
	 * The style class used to for an unsolved clue.
	 */
	private static final String UNSOLVED_STYLE_CLASS = "clue-item-view-unsolved";
	
	/**
	 * The style class for a solved clue.
	 */
	private static final String SOLVED_STYLE_CLASS = "clue-item-view-solved";
	
	/**
	 * The scaling factor of the font size relative to the cell length.
	 */
	private static final double FONT_SIZE_SCALE = 1.0 / 2.0;

	/**
	 * A Rectangle used to color the clue background.
	 */
	private Rectangle background = new Rectangle();
	
	/**
	 * A Text entry used to display the clue.
	 */
	private Text text = new Text();

	/**
	 * Constructs a ClueItemView with no clue and a side length.
	 * 
	 * @param sideLength the side length (width/height) 
	 */
	public ClueItemView(int sideLength) {
		this("", sideLength);
	}

	/**
	 * Constructs a ClueItemView with a clue and a side length.
	 * 
	 * @param clue the clue integer value
	 * @param sideLength the side length (width/height) 
	 */
	public ClueItemView(int clue, int sideLength) {
		this(Integer.toString(clue), sideLength);
	}

	/**
	 * Constructs a ClueItemView given a clue and side length.
	 * 
	 * @param clue the String representation of a clue
	 * @param sideLength the side length (width/height)
	 */
	private ClueItemView(String clue, int sideLength) {
		getStyleClass().add(STYLE_CLASS);
		text.setText(clue);
		setState(false);
		setSize(sideLength);
		getChildren().addAll(background, text);
	}

	/**
	 * Updates the state of the ClueItemView by updating style classes.
	 * 
	 * When unsolved, this the clue uses the unsolved styling. When solve, this
	 * clue uses the solved styling.
	 * 
	 * @param solved the solved status
	 */
	public void setState(boolean solved) {
		ObservableList<String> styleClasses = getStyleClass();
		styleClasses.removeAll(SOLVED_STYLE_CLASS, UNSOLVED_STYLE_CLASS);
		if (solved) {
			styleClasses.add(SOLVED_STYLE_CLASS);
		} else {
			styleClasses.add(UNSOLVED_STYLE_CLASS);
		}
	}

	/**
	 * Updates the size of this ClueItemView given a side length.
	 * 
	 * @param sideLength the new side length
	 */
	public void setSize(int sideLength) {
		background.setWidth(sideLength);
		background.setHeight(sideLength);
		text.setFont(new Font(FONT_SIZE_SCALE * sideLength));
	}
}
