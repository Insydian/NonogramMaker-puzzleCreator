package edu.ou.cs2334.project5.handlers;
import javafx.stage.*;
/**
 * 
 * @author laru0009
 *
 */
public class AbstractBaseHandler 
{
	protected Window window;
	protected FileChooser fileChooser;
	public AbstractBaseHandler(Window window, FileChooser fileChooser) {
		// TODO Auto-generated constructor stub
		this.window = window;
		this.fileChooser = fileChooser;
	}

}
