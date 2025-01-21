package edu.ou.cs2334.project5.views;
import edu.ou.cs2334.project5.models.CellState;
import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CellView extends StackPane {

	private static final String STYLE_CLASS = "cell-view";
	private static final String EMPTY_STYLE_CLASS = "cell-view-empty";
	private static final String FILLED_STYLE_CLASS = "cell-view-filled";
	private static final String MARKED_STYLE_CLASS = "cell-view-marked";
	private static final double X_LENGTH_SCALE = 1.0 / 2.0;

	private Rectangle background = new Rectangle();
	private Line xLeftLeg = new Line();
	private Line xRightLeg = new Line();

	public CellView(int sideLength) {
		getStyleClass().add(STYLE_CLASS);
		setState(CellState.EMPTY);
		setSize(sideLength);
		getChildren().addAll(background, xLeftLeg, xRightLeg);
	}

	public void setState(CellState state) {
		ObservableList<String> styleClasses = getStyleClass();
		styleClasses.removeAll(
				EMPTY_STYLE_CLASS, FILLED_STYLE_CLASS, MARKED_STYLE_CLASS);
		switch (state) {
			case EMPTY:
				styleClasses.add(EMPTY_STYLE_CLASS);
				break;
			case FILLED:
				styleClasses.add(FILLED_STYLE_CLASS);
				break;
			case MARKED:
				styleClasses.add(MARKED_STYLE_CLASS);
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

	public void setSize(int sideLength) {
		background.setWidth(sideLength);
		background.setHeight(sideLength);

		// Set the size of the X.
		double legLength = X_LENGTH_SCALE * sideLength;
		double xWidth = legLength / Math.sqrt(2);
		double xHeight = xWidth;
		xLeftLeg.setStartX(0);
		xLeftLeg.setStartY(0);
		xLeftLeg.setEndX(xWidth);
		xLeftLeg.setEndY(xHeight);
		xRightLeg.setStartX(0);
		xRightLeg.setStartY(xHeight);
		xRightLeg.setEndX(xWidth);
		xRightLeg.setEndY(0);
	}
}
