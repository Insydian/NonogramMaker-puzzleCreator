package edu.ou.cs2334.project5.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project5.handlers.OpenHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;
import edu.ou.cs2334.project5.views.NonogramView;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * 
 * @author crlar
 * @author Discord for help with configurebuttons
 * @author Stackoverflow for lambda expression help
 *
 */
public class NonogramPresenter implements Openable {
	private NonogramView view;
	private NonogramModel model;
	private int cellLength;

	private static final String DEFAULT_PUZZLE = "C:\\Users\\crlar\\Documents\\GitHub\\project5-Insydian\\puzzles\\space-invader.txt";

	public NonogramPresenter(int cellLength) throws IOException {
		model = new NonogramModel(DEFAULT_PUZZLE);
		view = new NonogramView();
		this.cellLength = cellLength;
		initPresenter();
		configureButtons();
	}

	public void initPresenter() 
	{
		initializeView();
		bindCellViews();
		synchronize();
	}

	public void initializeView() {

		view.initialize(model.getRowClues(), model.getColClues(), cellLength);
		if (getWindow() != null) {
			getWindow().sizeToScene();
		}
		view.setVisible(true);

	}

	public void bindCellViews() {
		for (int i = 0; i < model.getNumRows(); ++i) {
			for (int j = 0; j < model.getNumCols(); ++j) {
				final int rowIdx = i;
				final int colIdx = j;
				view.getCellView(i, j).setOnMouseClicked(e -> {

					
					if (e.getButton().name().equals(MouseButton.PRIMARY.name())) {
						handleLeftClick(rowIdx, colIdx);
					}
					if (e.getButton().name().equals(MouseButton.SECONDARY.name())) {
						handleRightClick(rowIdx, colIdx);
					}

				});/*
					 * addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, handler);
					 */

			}
		}
	}

	public void handleLeftClick(int rowIdx, int colIdx) {
		System.out.println("Left clicked");
		if (model.getCellState(rowIdx, colIdx).name().equals(CellState.EMPTY.name())
				|| model.getCellState(rowIdx, colIdx).name().equals(CellState.MARKED.name())) {
			System.out.println(model.setCellState(rowIdx, colIdx, CellState.FILLED));
			synchronize();
		} else if (model.getCellState(rowIdx, colIdx).name().equals(CellState.FILLED.name())) {
			model.setCellState(rowIdx, colIdx, CellState.EMPTY);
			synchronize();
		}
		if (model.isSolved())
			processVictory();
	}

	public void handleRightClick(int rowIdx, int colIdx) {
		System.out.println("Right clicked");
		if (model.getCellState(rowIdx, colIdx).name().equals(CellState.EMPTY.name())
				|| model.getCellState(rowIdx, colIdx).name().equals(CellState.FILLED.name())) {
			model.setCellState(rowIdx, colIdx, CellState.MARKED);
			synchronize();
		} else if (model.getCellState(rowIdx, colIdx).name().equals(CellState.MARKED.name())) {
			model.setCellState(rowIdx, colIdx, CellState.EMPTY);
			synchronize();

		}
		if (model.isSolved())
			processVictory();
	}

	public void synchronize() {
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				updateCellState(row, col, model.getCellState(row, col));
			}
		}
		if (model.isSolved()) {
			processVictory();
		}
	}
	public void updateCellState(int rowIdx, int colIdx, CellState state)
	{
		view.setCellState(rowIdx, colIdx, state);
		if(model.isRowSolved(rowIdx))
			view.setRowClueState(rowIdx, true);
		if(model.isColSolved(colIdx))
		{
			view.setColClueState(colIdx, true);
		}
		if(!model.isRowSolved(rowIdx))
			view.setRowClueState(rowIdx, false);
		if(!model.isColSolved(colIdx))
		{
			view.setColClueState(colIdx, false);
		}
	}

	public void processVictory() {
		removeCellViewMarks();
		
		Alert alert = view.showVictoryAlert();
		alert.show();
	}

	public void removeCellViewMarks() {
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				if (model.getCellState(row, col) == CellState.MARKED) {
					view.setCellState(row, col, CellState.EMPTY);
				}
			}
		}
	}

	public void configureButtons() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open");
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fc.setInitialDirectory(new File("."));
		view.getLoadButton().setOnAction(new OpenHandler(getWindow(), fc, this));
		if (view.getResetButton() != null)
			view.getResetButton().setOnAction((ActionEvent event) -> {
				resetPuzzle();
			});
	}

	public void resetPuzzle() {
		model.resetCells();
		synchronize();
	}

	public Pane getPane() {
		return view;
	}

	@Override
	public void open(File file) throws IOException {
		this.model = new NonogramModel(file);
		initPresenter();

	}

	public Window getWindow() {
		try {
			return view.getScene().getWindow();
		} catch (NullPointerException e) {
			return null;
		}
	}

}
