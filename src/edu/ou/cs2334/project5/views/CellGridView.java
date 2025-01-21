package edu.ou.cs2334.project5.views;

import edu.ou.cs2334.project5.models.CellState;
import javafx.scene.layout.GridPane;

public class CellGridView extends GridPane
{
	private static final String STYLE_CLASS = "cell-grid-view";
	private CellView[][] cellViews;
	public CellGridView(int numRows, int numCols, int cellLength)
	{
		cellViews = new CellView[numRows][numCols];
		getStyleClass().add(STYLE_CLASS);
		this.getChildren().clear();

		cellViews = new CellView[numRows][numCols];
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				CellView cellView = new CellView(cellLength);
				cellViews[rowIdx][colIdx] = cellView;
			}
			addRow(rowIdx, cellViews[rowIdx]);

		}
	}
	
	public CellView getCellView(int rowIdx, int colIdx)
	{
		return cellViews[rowIdx][colIdx];
	}
	public void setCellState(int rowIdx, int colIdx, CellState state)
	{
		cellViews[rowIdx][colIdx].setState(state);
	}
	
}
