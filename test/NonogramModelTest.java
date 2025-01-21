import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import edu.ou.cs2334.project5.models.CellState;
import edu.ou.cs2334.project5.models.NonogramModel;

class NonogramModelTest {

	@Test
	void testArrayConstructor() {
		int[][] rowClues = new int[][] {{1, 1, 3}, {1, 1, 1}, {3, 3}, {1, 1},
				{1, 3}};
		int[][] colClues = new int[][] {{3}, {1}, {5}, {0}, {1, 3}, {1, 1, 1},
				{3, 1}};
		NonogramModel model = new NonogramModel(rowClues, colClues);
		assertEquals(rowClues.length, model.getNumRows());
		assertEquals(colClues.length, model.getNumCols());
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			assertArrayEquals(rowClues[rowIdx], model.getRowClue(rowIdx));
		}
		for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
			assertArrayEquals(colClues[colIdx], model.getColClue(colIdx));
		}
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
				CellState state = model.getCellState(rowIdx, colIdx);
				assertEquals(CellState.EMPTY, state);
			}
		}

		rowClues = new int[][] {{1, 1, 3, 1}, {1, 1, 1, 2}, {3, 3, 1},
				{1, 1, 1}, {1, 3, 3}};
		colClues = new int[][] {{3}, {1}, {5}, {0}, {3, 1}, {1, 1, 1}, {1, 3},
				{0}, {1, 1}, {5}, {1}};
		model = new NonogramModel(rowClues, colClues);
		assertEquals(rowClues.length, model.getNumRows());
		assertEquals(colClues.length, model.getNumCols());
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			assertArrayEquals(rowClues[rowIdx], model.getRowClue(rowIdx));
		}
		for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
			assertArrayEquals(colClues[colIdx], model.getColClue(colIdx));
		}
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
				CellState state = model.getCellState(rowIdx, colIdx);
				assertEquals(CellState.EMPTY, state);
			}
		}
	}
	
	@Test
	void testFileConstructor() throws IOException {
		String filename = "./test/test-bunny.txt";
		NonogramModel model = new NonogramModel(new File(filename));
		int[][] rowClues = {{1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1},
				{1, 3, 1}, {1, 1, 1, 1}, {1, 1, 1}, {1, 1}, {3, 2, 1},
				{1, 2, 1}, {3, 4}};
		int[][] colClues = {{4}, {1, 1}, {3, 3}, {1, 2, 1, 1}, {3, 1}, {1, 1},
				{4, 2}, {1, 1, 1}, {1, 1, 1}, {1, 1}, {1, 1}, {1, 3}, {1}};
		assertEquals(rowClues.length, model.getNumRows());
		assertEquals(colClues.length, model.getNumCols());
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			assertArrayEquals(rowClues[rowIdx], model.getRowClue(rowIdx));
		}
		for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
			assertArrayEquals(colClues[colIdx], model.getColClue(colIdx));
		}
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
				CellState state = model.getCellState(rowIdx, colIdx);
				assertEquals(CellState.EMPTY, state);
			}
		}

		filename = "./test/test-pig.txt";
		model = new NonogramModel(new File(filename));
		rowClues = new int[][] {{1, 1}, {9}, {1, 1, 1}, {1, 1, 1}, {2, 1, 3},
				{5, 2}, {1, 1, 1, 1, 1}, {5, 3, 1}, {1, 1, 1, 1}, {10},
				{1, 1, 1, 1}};
		colClues = new int[][] {{3}, {3, 2}, {3, 4, 1}, {1, 1, 1, 2},
				{1, 3, 1}, {1, 1, 2}, {1, 2}, {2, 1, 1}, {2, 1, 1},
				{1, 1, 1, 2}, {1, 1, 2}, {1, 1, 2}, {6}, {1}, {1}};
		assertEquals(rowClues.length, model.getNumRows());
		assertEquals(colClues.length, model.getNumCols());
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			assertArrayEquals(rowClues[rowIdx], model.getRowClue(rowIdx));
		}
		for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
			assertArrayEquals(colClues[colIdx], model.getColClue(colIdx));
		}
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
				CellState state = model.getCellState(rowIdx, colIdx);
				assertEquals(CellState.EMPTY, state);
			}
		}
	}

	@Test
	void testStringConstructor() throws IOException {
		String filename = "./test/test-bunny.txt";
		NonogramModel model = new NonogramModel(filename);
		int[][] rowClues = {{1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1},
				{1, 3, 1}, {1, 1, 1, 1}, {1, 1, 1}, {1, 1}, {3, 2, 1},
				{1, 2, 1}, {3, 4}};
		int[][] colClues = {{4}, {1, 1}, {3, 3}, {1, 2, 1, 1}, {3, 1}, {1, 1},
				{4, 2}, {1, 1, 1}, {1, 1, 1}, {1, 1}, {1, 1}, {1, 3}, {1}};
		assertEquals(rowClues.length, model.getNumRows());
		assertEquals(colClues.length, model.getNumCols());
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			assertArrayEquals(rowClues[rowIdx], model.getRowClue(rowIdx));
		}
		for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
			assertArrayEquals(colClues[colIdx], model.getColClue(colIdx));
		}
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
				CellState state = model.getCellState(rowIdx, colIdx);
				assertEquals(CellState.EMPTY, state);
			}
		}

		filename = "./test/test-pig.txt";
		model = new NonogramModel(filename);
		rowClues = new int[][] {{1, 1}, {9}, {1, 1, 1}, {1, 1, 1}, {2, 1, 3},
				{5, 2}, {1, 1, 1, 1, 1}, {5, 3, 1}, {1, 1, 1, 1}, {10},
				{1, 1, 1, 1}};
		colClues = new int[][] {{3}, {3, 2}, {3, 4, 1}, {1, 1, 1, 2},
				{1, 3, 1}, {1, 1, 2}, {1, 2}, {2, 1, 1}, {2, 1, 1},
				{1, 1, 1, 2}, {1, 1, 2}, {1, 1, 2}, {6}, {1}, {1}};
		assertEquals(rowClues.length, model.getNumRows());
		assertEquals(colClues.length, model.getNumCols());
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			assertArrayEquals(rowClues[rowIdx], model.getRowClue(rowIdx));
		}
		for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
			assertArrayEquals(colClues[colIdx], model.getColClue(colIdx));
		}
		for (int rowIdx = 0; rowIdx < rowClues.length; ++rowIdx) {
			for (int colIdx = 0; colIdx < colClues.length; ++colIdx) {
				CellState state = model.getCellState(rowIdx, colIdx);
				assertEquals(CellState.EMPTY, state);
			}
		}
	}

	@Test
	void testEncapsulation() {
		int[][] rowClues = {{2}, {0}};
		int[][] colClues = {{1}, {1}};
		NonogramModel model = new NonogramModel(rowClues, colClues);
		assertEquals(2, model.getNumRows());
		assertEquals(2, model.getNumCols());
		assertArrayEquals(new int[] {2}, model.getRowClue(0));
		assertArrayEquals(new int[] {0}, model.getRowClue(1));
		assertArrayEquals(new int[] {1}, model.getColClue(0));
		assertArrayEquals(new int[] {1}, model.getColClue(1));

		rowClues[0] = null;
		rowClues[1] = null;
		colClues[0] = null;
		colClues[1] = null;
		assertArrayEquals(new int[] {2}, model.getRowClue(0));
		assertArrayEquals(new int[] {0}, model.getRowClue(1));
		assertArrayEquals(new int[] {1}, model.getColClue(0));
		assertArrayEquals(new int[] {1}, model.getColClue(1));

		model.getRowClue(0)[0] = 2334;
		model.getRowClue(1)[0] = 2334;
		model.getColClue(0)[0] = 2334;
		model.getColClue(1)[0] = 2334;
		assertArrayEquals(new int[] {2}, model.getRowClue(0));
		assertArrayEquals(new int[] {0}, model.getRowClue(1));
		assertArrayEquals(new int[] {1}, model.getColClue(0));
		assertArrayEquals(new int[] {1}, model.getColClue(1));
	}

	@Test
	void testSetCellState() {
		int[][] rowClues = {{0}, {1}};
		int[][] colClues = {{0}, {1}};
		NonogramModel model = new NonogramModel(rowClues, colClues);
		assertEquals(CellState.EMPTY, model.getCellState(0, 0));

		assertFalse(model.setCellState(0, 0, CellState.EMPTY));
		assertEquals(CellState.EMPTY, model.getCellState(0, 0));

		assertFalse(model.setCellState(0, 0, null));
		assertEquals(CellState.EMPTY, model.getCellState(0, 0));

		assertTrue(model.setCellState(0, 0, CellState.FILLED));
		assertEquals(CellState.FILLED, model.getCellState(0, 0));

		assertFalse(model.setCellState(0, 0, CellState.FILLED));
		assertEquals(CellState.FILLED, model.getCellState(0, 0));

		assertFalse(model.setCellState(0, 0, null));
		assertEquals(CellState.FILLED, model.getCellState(0, 0));

		assertTrue(model.setCellState(0, 0, CellState.MARKED));
		assertEquals(CellState.MARKED, model.getCellState(0, 0));

		assertFalse(model.setCellState(0, 0, CellState.MARKED));
		assertEquals(CellState.MARKED, model.getCellState(0, 0));

		assertFalse(model.setCellState(0, 0, null));
		assertEquals(CellState.MARKED, model.getCellState(0, 0));
	}

	@Test
	void testIsRowColumnSolved() {
		int[][] rowClues = {{0}, {2}};
		int[][] colClues = {{1}, {1}};
		NonogramModel model = new NonogramModel(rowClues, colClues);
		assertTrue(model.isRowSolved(0));
		assertFalse(model.isRowSolved(1));
		assertFalse(model.isColSolved(0));
		assertFalse(model.isColSolved(1));

		model.setCellState(0, 0, CellState.MARKED);
		assertTrue(model.isRowSolved(0));
		assertFalse(model.isRowSolved(1));
		assertFalse(model.isColSolved(0));
		assertFalse(model.isColSolved(1));

		model.setCellState(0, 0, CellState.FILLED);
		assertFalse(model.isRowSolved(0));
		assertFalse(model.isRowSolved(1));
		assertTrue(model.isColSolved(0));
		assertFalse(model.isColSolved(1));

		model.setCellState(0, 0, CellState.EMPTY);
		assertTrue(model.isRowSolved(0));
		assertFalse(model.isRowSolved(1));
		assertFalse(model.isColSolved(0));
		assertFalse(model.isColSolved(1));

		model.setCellState(0, 1, CellState.MARKED);
		assertTrue(model.isRowSolved(0));
		assertFalse(model.isRowSolved(1));
		assertFalse(model.isColSolved(0));
		assertFalse(model.isColSolved(1));

		model.setCellState(1, 1, CellState.FILLED);
		assertTrue(model.isRowSolved(0));
		assertFalse(model.isRowSolved(1));
		assertFalse(model.isColSolved(0));
		assertTrue(model.isColSolved(1));

		model.setCellState(1, 0, CellState.FILLED);
		assertTrue(model.isRowSolved(0));
		assertTrue(model.isRowSolved(1));
		assertTrue(model.isColSolved(0));
		assertTrue(model.isColSolved(1));
	}

	@Test
	void testEmptyPuzzle() {
		int[][] rowClues = {{0}};
		int[][] colClues = {{0}};
		NonogramModel model = new NonogramModel(rowClues, colClues);
		assertTrue(model.isRowSolved(0));
		assertTrue(model.isColSolved(0));
		assertTrue(model.isSolved());

		assertFalse(model.setCellState(0, 0, CellState.FILLED));
		assertEquals(CellState.EMPTY, model.getCellState(0, 0));
		assertTrue(model.isRowSolved(0));
		assertTrue(model.isColSolved(0));
		assertTrue(model.isSolved());

		assertFalse(model.setCellState(0, 0, CellState.MARKED));
		assertEquals(CellState.EMPTY, model.getCellState(0, 0));
		assertTrue(model.isRowSolved(0));
		assertTrue(model.isColSolved(0));
		assertTrue(model.isSolved());

		rowClues = new int[][] {{0}, {0}, {0}, {0}, {0}};
		colClues = new int[][] {{0}, {0}, {0}, {0}, {0}};
		model = new NonogramModel(rowClues, colClues);
		boolean[] rowsSolved = {true, true, true, true, true};
		boolean[] colsSolved = {true, true, true, true, true};
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertTrue(model.isSolved());

		assertFalse(model.setCellState(0, 0, CellState.MARKED));
		assertEquals(CellState.EMPTY, model.getCellState(0, 0));
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertTrue(model.isSolved());
	}

	@Test
	void testSolvePuzzle() {
		int[][] rowClues = {{3}, {1, 1}, {0}, {3}};
		int[][] colClues = {{2, 1}, {1, 1}, {2, 1}};
		NonogramModel model = new NonogramModel(rowClues, colClues);

		CellState[][] cellStates = {
				{CellState.EMPTY, CellState.EMPTY, CellState.EMPTY},
				{CellState.EMPTY, CellState.EMPTY, CellState.EMPTY},
				{CellState.EMPTY, CellState.EMPTY, CellState.EMPTY},
				{CellState.EMPTY, CellState.EMPTY, CellState.EMPTY}};
		boolean[] rowsSolved = {false, false, true, false};
		boolean[] colsSolved = {false, false, false};
		boolean puzzleSolved = false;
		checkCellStates(cellStates, model);
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertEquals(puzzleSolved, model.isSolved());

		assertTrue(model.setCellState(1, 0, CellState.FILLED));
		assertTrue(model.setCellState(1, 1, CellState.MARKED));
		assertTrue(model.setCellState(1, 2, CellState.FILLED));
		cellStates[1][0] = CellState.FILLED;
		cellStates[1][1] = CellState.MARKED;
		cellStates[1][2] = CellState.FILLED;
		rowsSolved[1] = true;
		checkCellStates(cellStates, model);
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertEquals(puzzleSolved, model.isSolved());

		assertTrue(model.setCellState(0, 1, CellState.FILLED));
		assertTrue(model.setCellState(3, 1, CellState.FILLED));
		cellStates[0][1] = CellState.FILLED;
		cellStates[3][1] = CellState.FILLED;
		colsSolved[1] = true;
		checkCellStates(cellStates, model);
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertEquals(puzzleSolved, model.isSolved());

		assertTrue(model.setCellState(3, 0, CellState.FILLED));
		assertTrue(model.setCellState(3, 2, CellState.FILLED));
		cellStates[3][0] = CellState.FILLED;
		cellStates[3][2] = CellState.FILLED;
		rowsSolved[3] = true;
		checkCellStates(cellStates, model);
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertEquals(puzzleSolved, model.isSolved());

		assertTrue(model.setCellState(0, 0, CellState.FILLED));
		assertTrue(model.setCellState(0, 1, CellState.MARKED));
		assertTrue(model.setCellState(0, 2, CellState.FILLED));
		cellStates[0][0] = CellState.FILLED;
		cellStates[0][1] = CellState.MARKED;
		cellStates[0][2] = CellState.FILLED;
		colsSolved[0] = true;
		colsSolved[1] = false;
		colsSolved[2] = true;
		checkCellStates(cellStates, model);
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertEquals(puzzleSolved, model.isSolved());

		assertTrue(model.setCellState(0, 1, CellState.FILLED));
		cellStates[0][1] = CellState.FILLED;
		rowsSolved[0] = true;
		colsSolved[1] = true;
		puzzleSolved = true;
		checkCellStates(cellStates, model);
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertEquals(puzzleSolved, model.isSolved());

		assertFalse(model.setCellState(0, 1, CellState.EMPTY));
		assertFalse(model.setCellState(1, 1, CellState.MARKED));
		assertFalse(model.setCellState(2, 1, CellState.FILLED));
		assertFalse(model.setCellState(3, 1, CellState.MARKED));
		checkCellStates(cellStates, model);
		checkRowsSolved(rowsSolved, model);
		checkColsSolved(colsSolved, model);
		assertEquals(puzzleSolved, model.isSolved());
	}
	
	@Test
	void testResetCells() {
		int[][] rowClues = {{3}, {1, 1}, {0}, {3}};
		int[][] colClues = {{2, 1}, {1, 1}, {2, 1}};
		NonogramModel model = new NonogramModel(rowClues, colClues);
		
		// Set all cells to FILLED (setCellState should be implemented first)
		for (int row = 0; row < rowClues.length; ++row) {
			for (int col = 0; col < colClues.length; ++col) {
				assertTrue(model.setCellState(row, col, CellState.FILLED));
			}
		}
		
		// Reset cells and make sure they all become EMPTY
		model.resetCells();
		for (int row = 0; row < rowClues.length; ++row) {
			for (int col = 0; col < colClues.length; ++col) {
				assertEquals(CellState.EMPTY, model.getCellState(row, col));
			}
		}
	}

	private static void checkCellStates(CellState[][] states, NonogramModel model) {
		for (int rowIdx = 0; rowIdx < states.length; ++rowIdx) {
			for (int colIdx = 0; colIdx < states[0].length; ++colIdx) {
				assertEquals(states[rowIdx][colIdx],
						model.getCellState(rowIdx, colIdx));
			}
		}
	}

	private static void checkRowsSolved(boolean[] rowsSolved, NonogramModel model) {
		for (int rowIdx = 0; rowIdx < rowsSolved.length; ++rowIdx) {
			assertEquals(rowsSolved[rowIdx], model.isRowSolved(rowIdx));
		}
	}

	private static void checkColsSolved(boolean[] colsSolved, NonogramModel model) {
		for (int colIdx = 0; colIdx < colsSolved.length; ++colIdx) {
			assertEquals(colsSolved[colIdx], model.isColSolved(colIdx));
		}
	}
}
