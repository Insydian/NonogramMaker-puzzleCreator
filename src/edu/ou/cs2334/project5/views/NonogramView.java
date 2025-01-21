package edu.ou.cs2334.project5.views;

import java.awt.Button;
import java.util.Arrays;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.views.clues.AbstractGroupCluesView;
import edu.ou.cs2334.project5.views.clues.AbstractOrientedClueView;
import edu.ou.cs2334.project5.views.clues.LeftCluesView;
import edu.ou.cs2334.project5.views.clues.TopCluesView;
import javafx.css.StyleClass;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
/**
 * 
 * @author crlar
 *
 */
public class NonogramView extends BorderPane
{
	private final String STYLE_CLASS = "nonogram-view";
	private final String SOLVED_STYLE_CLASS = "nonogram-view-solved";
	private LeftCluesView leftCluesView;
	private TopCluesView topCluesView;
	private CellGridView cellGridView;
	private HBox bottomHbox;
	private javafx.scene.control.Button LoadBtn = new javafx.scene.control.Button("Load");
	private javafx.scene.control.Button resetBtn = new javafx.scene.control.Button("Reset");
	public NonogramView()
	{
		getStyleClass().add(STYLE_CLASS);
	}
	public void initialize(int[][] rowClues, int[][] colClues, int cellLength)
	{
		leftCluesView = new LeftCluesView(rowClues, cellLength, calcMaxClueLength(rowClues));
		topCluesView = new TopCluesView(colClues, cellLength, calcMaxClueLength(colClues));
		cellGridView = new CellGridView(rowClues.length, colClues.length, cellLength);
		setLeft(leftCluesView);
		BorderPane.setAlignment(leftCluesView, Pos.BOTTOM_RIGHT);
		setTop(topCluesView);
		BorderPane.setAlignment(topCluesView, Pos.BOTTOM_RIGHT);
		setCenter(cellGridView);
		BorderPane.setAlignment(cellGridView, Pos.BOTTOM_RIGHT);
		bottomHbox = new HBox(LoadBtn, resetBtn);
		setBottom(bottomHbox);
		
	}
	private static int calcMaxClueLength(int[][] clues) {
		return Arrays.stream(clues)
				.map(a -> a.length)
				.max(Integer::compareTo)
				.get();
	}

	/**
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @return
	 */
	
	public CellView getCellView(int rowIdx, int colIdx)
	{
		return cellGridView.getCellView(rowIdx, colIdx);
	}
	public void setCellState(int rowIdx, int colIdx, CellState state) 
	{
		cellGridView.setCellState(rowIdx, colIdx, state);
	}
	/**
	 * 
	 * @param rowIdx
	 * @param solved
	 */
	public void setRowClueState(int rowIdx, boolean solved)
	{
		leftCluesView.setState(rowIdx, solved);

	}
	public void setColClueState(int colIdx, boolean solved)
	{
		topCluesView.setState(colIdx, solved);
	}
	/**
	 * 
	 * @param solved
	 */
	public void setPuzzleState(boolean solved)
	{
		if(solved)
			getStyleClass().add(SOLVED_STYLE_CLASS);
	}
	public javafx.scene.control.Button getLoadButton()
	{
		return LoadBtn;
	}
	public javafx.scene.control.Button getResetButton()
	{
		return resetBtn;
	}
	public Alert showVictoryAlert()
	{
		Alert a = new Alert(AlertType.INFORMATION, "Yay you won!!");
		a.setTitle("VictoryAlert");
		a.setContentText("Well Done!");
		return a;
	}
	
}
