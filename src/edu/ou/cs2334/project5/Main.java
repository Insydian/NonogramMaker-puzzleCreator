package edu.ou.cs2334.project5;

import java.io.IOException;
import java.util.List;

import edu.ou.cs2334.project5.presenters.NonogramPresenter;
import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author laru00009
 *
 */
public class Main extends Application
{
	private static final int IDX_CELL_SIZE = 4;
	private static final int DEFAULT_CELL_SIZE = 32;
	public static void main(String[] args)
	{
		launch(args);
		// TODO Auto-generated constructor stub
	}
	public void start(Stage primaryStage) throws Exception
	{
		Parameters params = getParameters();
		List<String> args = params.getRaw();
		int cellSize = DEFAULT_CELL_SIZE;
		try {
			cellSize = Integer.parseInt(args.get(IDX_CELL_SIZE));
		} catch (IndexOutOfBoundsException e) {e.printStackTrace();}
		NonogramPresenter np = new NonogramPresenter(cellSize);
		Scene scene = new Scene(np.getPane());
		primaryStage.setScene(scene);
		scene.getStylesheets().add("style.css");
		primaryStage.setTitle("Game");
		primaryStage.show();
	}

}
