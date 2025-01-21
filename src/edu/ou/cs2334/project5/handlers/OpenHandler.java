package edu.ou.cs2334.project5.handlers;

import edu.ou.cs2334.project5.handlers.AbstractBaseHandler;
import edu.ou.cs2334.project5.interfaces.Openable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class OpenHandler extends AbstractBaseHandler implements EventHandler<ActionEvent> {

	private Openable opener;
	
	public OpenHandler(Window window, FileChooser fileChooser, Openable opener) {
		super(window, fileChooser);
		this.opener = opener;
	}
	
	@Override
	public void handle(ActionEvent event) {
		File file = fileChooser.showOpenDialog(window);
		if (file != null) {
			try {
				opener.open(file);
			} catch (IOException e) {
				Alert alert = new Alert(AlertType.ERROR, "File could not be saved.");
				alert.showAndWait();
			}
		}
	}
	
}
