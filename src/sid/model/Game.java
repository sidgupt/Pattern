package sid.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javax.swing.JOptionPane;

import sid.constant.PatternConstant;
import sid.view.Field;
import sid.view.MenuPanel;
import sid.view.Pattern;
import sid.view.PatternPanel;

/**
 * This class represents a Sudoku game. It contains the solution, the user
 * input, the selected number and methods to check the validation of the user
 * input.
 *
 * @author Siddharth Gupta
 */
public class Game extends Observable {
	private int[][] game; // Generated game with user input.
	private ArrayList<Integer> negCol = new ArrayList<Integer>(); // Holds left
																	// hand side
																	// values of
																	// the
																	// pattern
	private ArrayList<Integer> negRow = new ArrayList<Integer>(); // Holds top
																	// side
																	// values of
																	// the
																	// pattern
	private int maxCol; // Max column in left side data.
	private int maxRow; // Max row in top side data.
	private int type; // User changeable matrix size type*type
	PatternPanel patternPanel;

	/**
	 * Constructor
	 */
	public Game(int type) {
		newGame(type, type);
	}

	/**
	 * Generates a new Pattern game.<br />
	 * All observers will be notified, update action: new game.
	 */
	public void newGame(int x, int y) {
		int[][] gamedummy = new int[x][y];
		setType(x);
		game = gamedummy;
		game = generatePatternGame(x);
		setChanged();
		notifyObservers(UpdateAction.NEW_GAME);
	}

	int[][] generatePatternGame(int x) {
		int count = 0;
		int col, row;
		Random rand = new Random();
		for (int i = 0; i < x; i++) {
			col = rand.nextInt(x);
			if (game[i][col] == 0) {
				game[i][col] = 1;
			} else {
				i--;
			}
		}
		for (int i = 0; i < x; i++) {
			row = rand.nextInt(x);
			if (game[row][i] == 0) {
				game[row][i] = 1;
			} else {
				i--;
			}
		}
		int compare = ((x * x) / 2) - 2 * x + type / 2;
		while (count < compare) {
			col = rand.nextInt(x);
			row = rand.nextInt(x);
			if (game[row][col] == 0) {
				game[row][col] = 1;
				count++;
			}
		}
		extractProblem(game, x);
		return game;
	}

	public void extractProblem(int[][] game, int x) {
		// TODO Auto-generated method stub
		int count;
		maxCol = 0;
		int col;
		maxRow = 0;
		int row;
		negRow.clear();
		negCol.clear();

		for (int i = 0; i < x; i++) {
			col = 0;
			count = 0;
			negRow.add(-i - 1);
			for (int j = x - 1; j >= 0; j--) {
				if (game[i][j] == 1) {
					count++;
				} else {
					if (count != 0) {
						negRow.add(count);
						col++;
						count = 0;
					}
				}
			}
			if (count != 0) {
				negRow.add(count);
				col++;
			}
			if (col > maxCol) {
				maxCol = col;
			}
		}
		for (int j = 0; j < x; j++) {
			row = 0;
			count = 0;
			negCol.add(-j - 1);
			for (int i = x - 1; i >= 0; i--) {
				if (game[i][j] == 1) {
					count++;
				} else {
					if (count != 0) {
						negCol.add(count);
						row++;
						count = 0;
					}
				}
			}
			if (count != 0) {
				negCol.add(count);
				row++;
			}
			if (row > maxRow) {
				maxRow = row;
			}
		}
	}

	/**
	 * Checks user input against the solution.<br />
	 */
	public void checkGame() {
		Field[][] fields = getPatternPanel().getFields();
		int start = getPatternPanel().getStart();
		int type = this.getType();
		int[][] game = this.getGame();
		int[][] fieldArray = new int[type][type];
		for (int i = 0; i < type; i++) {
			for (int j = 0; j < type; j++) {
				if (fields[i + start][j + start].getBackground() == Color.GRAY) {
					JOptionPane.showMessageDialog(MenuPanel.getjRootPane(), PatternConstant.INCOMPLETE_GAME,
							PatternConstant.RESULT_WINDOW_TITLE, 1);
					return;
				}
				if (fields[i + start][j + start].getBackground() == Color.BLACK) {
					fieldArray[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < type; i++) {
			for (int j = 0; j < type; j++) {
				if (fieldArray[i][j] != game[i][j]) {
					JOptionPane.showMessageDialog(MenuPanel.getjRootPane(), PatternConstant.UNSOLVED_GAME,
							PatternConstant.RESULT_WINDOW_TITLE, 1);
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(MenuPanel.getjRootPane(), PatternConstant.SOLVED_GAME,
				PatternConstant.RESULT_WINDOW_TITLE, 1);

	}

	/**
	 * Sets given number on given position in the game.
	 *
	 * @param x
	 *            The x position in the game.
	 * @param y
	 *            The y position in the game.
	 * @param number
	 *            The number to be set.
	 */
	public void setNumber(int x, int y, int number) {
		game[y][x] = number;
	}

	/**
	 * Returns number of given position.
	 *
	 * @param x
	 *            X position in game.
	 * @param y
	 *            Y position in game.
	 * @return Number of given position.
	 */
	public int getNumber(int x, int y) {
		return game[y][x];
	}

	public ArrayList<Integer> getNegCol() {
		return negCol;
	}

	public ArrayList<Integer> getNegRow() {
		return negRow;
	}

	public void solve() {
		// TODO Auto-generated method stub
		getPatternPanel().solveGame(this);

	}

	public int[][] getGame() {
		return game;
	}

	public int getMaxCol() {
		return maxCol;
	}

	public int getMaxRow() {
		return maxRow;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void updateGame(int i) {
		// TODO Auto-generated method stub
		new Pattern(i);
	}

	public PatternPanel getPatternPanel() {
		return patternPanel;
	}

	public void setPatternPanel(PatternPanel patternPanel) {
		this.patternPanel = patternPanel;
	}

	public void restart() {
		// TODO Auto-generated method stub
		getPatternPanel().setRestartGame(this);

	}
}