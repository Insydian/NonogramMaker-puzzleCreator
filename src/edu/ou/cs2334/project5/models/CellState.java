package edu.ou.cs2334.project5.models;

/**
 * 
 * @author laru0009
 *
 */
public enum CellState {
	EMPTY, FILLED, MARKED;

	public static boolean toBoolean(CellState cell) {
		if (cell.toString().equals(CellState.FILLED.toString())) {
			return true;
		} else {
			return false;
		}
	}
}
