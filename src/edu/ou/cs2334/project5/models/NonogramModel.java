package edu.ou.cs2334.project5.models;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/**
 * 
 * @author laru0009
 * @author Stackoverflow helped with the deepcopy method
 * @author The StackOverFlow helped convert List<Integer> to Integer[]
 *
 */
public class NonogramModel 
{
	
	private static final String DELIMITER = " ";
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_NUM_COLS = 1;

	private int[][] rowClues;
	private int[][] colClues;
	private CellState[][] cellStates;
	
	public NonogramModel(int[][] rowClues, int[][] colClues) {
		// This is simple, and you should not ask about this on Discord.
		this.rowClues = deepCopy(rowClues);
		this.colClues = deepCopy(colClues);

		cellStates = initCellStates(getNumRows(), getNumCols());
	}
	/**
	 * 
	 * @param file Takes in a file and creates a nonogram
	 * @throws IOException
	 */
	public NonogramModel(File file) throws IOException {
		// Number of rows and columns
//		BufferedReader reader = new BufferedReader(new FileReader(file));
//		String header = reader.readLine();
//		String[] fields = header.split(DELIMITER);
//		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
//		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);
//		
//		// TODO: Initialize cellStates.
//		// This is simple, and you should not ask about this on Discord.
//		cellStates = initCellStates(numRows, numCols);
//		// TODO: Read in row clues.
//		// This is simple, and you should not ask about this on Discord.
//		rowClues = new int[numRows][];
//		colClues = new int[numCols][];
//		rowClues = readClueLines(reader, numRows);
//		// TODO: Read in column clues.
//		// This is simple, and you should not ask about this on Discord.
//		colClues = readClueLines(reader, numCols);
//		// Close reader
//		reader.close();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String header = reader.readLine();
		String[] fields = header.split(DELIMITER);
		int numRows = Integer.parseInt(fields[IDX_NUM_ROWS]);
		int numCols = Integer.parseInt(fields[IDX_NUM_COLS]);

		// Initialize cellStates
		cellStates = initCellStates(numRows, numCols);
		
		// Row clues
		rowClues = readClueLines(reader, numRows);
		
		// Column clues
		colClues = readClueLines(reader, numCols);
		
		// Close reader
		reader.close();
	}

	public NonogramModel(String filename) throws IOException {
		this(new File(filename));
	}
	public int getNumRows()
	{
		return rowClues.length;
	}
	public int getNumCols()
	{
		return colClues.length;
	}
	
	/* Helper methods */
	public CellState getCellState(int rowIdx, int colIdx)
	{
		return cellStates[rowIdx][colIdx];
	}
	public boolean getCellStateAsBoolean(int rowIdx, int colIdx)
	{
		return CellState.toBoolean(cellStates[rowIdx][colIdx]);
	}
	/**
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @param state
	 * @return
	 */
	public boolean setCellState(int rowIdx, int colIdx, CellState state)
	{
		if(state == null || cellStates[rowIdx][colIdx].toString().equals(state.toString()) || isSolved())
		{
			return false;
		}
		else
		{
			cellStates[rowIdx][colIdx] = state;
			return true;
		}
	}
	public int[][] getRowClues()
	{
		return deepCopy(rowClues);
	}
	public int[][] getColClues()
	{
		return deepCopy(colClues);
	}
	public int[] getRowClue(int rowIdx)
	{
		return Arrays.copyOf(rowClues[rowIdx], rowClues[rowIdx].length);
	}
	public int[] getColClue(int colIdx)
	{
		return Arrays.copyOf(colClues[colIdx], colClues[colIdx].length);
	}
	public boolean isRowSolved(int rowIdx)
	{
		int count = 0;
		boolean[] boolArr = new boolean[cellStates[rowIdx].length];
		//i loops through the columns of a specific row
		for(int i = 0; i < cellStates[rowIdx].length; ++i)
		{
			boolArr[i] = CellState.toBoolean(cellStates[rowIdx][i]);
		}
		/*
		 * Integer[] projectedBool = project(boolArr).toArray();
		 */		Integer[] projectedBool = project(boolArr).toArray(new Integer[project(boolArr).size()]);
		Integer[] rowClue = IntStream.of( getRowClue(rowIdx) ).boxed().toArray( Integer[]::new );
		if(projectedBool.length != rowClue.length)
		{
			return false;
		}
		for(int i = 0; i < projectedBool.length; ++i)
		{
			if(projectedBool[i].equals(rowClue[i]))
			{
				count++;
			}

		}
		if(count == rowClue.length)
		{
			return true;
		}
		else 
			return false;
	}
	public boolean isColSolved(int colIdx)
	// TODO: There is an edge case that will break this. If the column does not exist
	//because the rowLength is dynamic it will throw an ArrayIndex out of bounds
	{
		int count = 0;
		boolean[] boolArr = new boolean[cellStates.length];
		for(int i = 0; i < cellStates.length; ++i)
		{
			boolArr[i] = CellState.toBoolean(cellStates[i][colIdx]);
		}
		Integer[] projectedBool = project(boolArr).toArray(new Integer[project(boolArr).size()]);
		Integer[] colClue = IntStream.of( getColClue(colIdx) ).boxed().toArray( Integer[]::new );
		for(int i = 0; i < projectedBool.length; ++i)
		{
			if(projectedBool[i].equals(colClue[i]))
			{
				count++;
			}

		}
		if(count == colClue.length)
		{
			return true;
		}
		else 
			return false;
	}
	public boolean isSolved()
	{
		int rowSolved = 0;
		for(int i = 0; i < cellStates.length; ++i)
		{
			if(isRowSolved(i))
			{
				rowSolved++;
			}
		}
		if(rowSolved == rowClues.length)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void resetCells()
	{
		for(int i = 0; i < cellStates.length; ++i)
		{
			for(int j = 0; j < cellStates[0].length; ++j)
			{
				cellStates[i][j] = CellState.EMPTY;
			}
		}
	}
	public List<Integer> project(boolean[] cells)
	{
		int count = 0;
		List<Integer> arl = new ArrayList<Integer>();
		for(int i = 0; i < cells.length; i++)
		{
			
			if(cells[i] == true)
			{
				count++;
			}
			if(cells[i] == false)
			{
				if(count > 0)
				{
					arl.add(count);
				}
				count = 0;
			}
		}
		if(count > 0)
			arl.add(count);
		if(arl.size() == 0)
			arl.add(0);
			return arl;
		
	}
	// This is implemented for you
	private static CellState[][] initCellStates(int numRows, int numCols) {
		// Create a 2D array to store numRows * numCols elements
		CellState[][] cellStates = new CellState[numRows][numCols];
		
		// Set each element of the array to empty
		for (int rowIdx = 0; rowIdx < numRows; ++rowIdx) {
			for (int colIdx = 0; colIdx < numCols; ++colIdx) {
				cellStates[rowIdx][colIdx] = CellState.EMPTY;
			}
		}
		
		// Return the result
		return cellStates;
	}
	
	// TODO: Implement this method
	private static int[][] deepCopy(int[][] array) {
		// You can do this in under 10 lines of code. If you ask the internet
		// "how do I do a deep copy of a 2d array in Java," be sure to cite
		// your source.
		// Note that if we used a 1-dimensional array to store our arrays,
		// we could simply use Arrays.copyOf directly without this helper
		// method.
		// Do not ask about this on Discord. You can do this on your own. :)
		int[][] copiedArray = new int[array.length][];
		
		for(int i = 0; i < array.length; ++i)
		{
			for(int j = 0; j < array[i].length; ++j)
			{
				copiedArray[i] = Arrays.copyOf(array[i], array[i].length);
			}
		}
		return copiedArray;
	}
	
	// This method is implemented for you. You need to figure out how it is useful.
	private static int[][] readClueLines(BufferedReader reader, int numLines)
			throws IOException {
		// Create a new 2D array to store the clues
		int[][] clueLines = new int[numLines][];
		
		// Read in clues line-by-line and add them to the array
		for (int lineNum = 0; lineNum < numLines; ++lineNum) {
			// Read in a line
			String line = reader.readLine();
			
			// Split the line according to the delimiter character
			String[] tokens = line.split(DELIMITER);
			
			// Create new int array to store the clues in
			int[] clues = new int[tokens.length];
			for (int idx = 0; idx < tokens.length; ++idx) {
				clues[idx] = Integer.parseInt(tokens[idx]);
			}
			
			// Store the processed clues in the resulting 2D array
			clueLines[lineNum] = clues;
		}
		
		// Return the result
		return clueLines;
	}
	
}
