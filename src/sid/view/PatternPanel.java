package sid.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import sid.controller.PatternController;
import sid.model.Game;
import sid.model.UpdateAction;

/**
 * This class draws the Pattern panel and reacts to updates from the model.
 *
 * @author Siddharth Gupta
 */
@SuppressWarnings("serial")
public class PatternPanel extends JPanel implements Observer {
	// Color constant for candidates.

	private Field[][] fields; // Array of fields.
	private JPanel[][] panels; // Panels holding the fields.
	private int start;
	private int boundry;

	private static double max(double x, double y) {
		double maxy = y;
		if (x >= y) {
			maxy = x;
		}
		return maxy;
	}

	public PatternPanel(Game game) {
		super(new GridLayout(
				(int) Math.ceil(
						max((game.getType() + game.getMaxRow()) / 5.0, (game.getType() + game.getMaxCol()) / 5.0)),
				(int) Math.ceil(
						max((game.getType() + game.getMaxRow()) / 5.0, (game.getType() + game.getMaxCol()) / 5.0))));
		int maxRow = game.getMaxRow();
		int maxCol = game.getMaxCol();
		int panel = (int) Math.ceil(max((game.getType() + maxRow) / 5.0, (game.getType() + maxCol) / 5.0));

		panels = new JPanel[panel][panel];
		for (int y = 0; y < panel; y++) {
			for (int x = 0; x < panel; x++) {
				panels[y][x] = new JPanel(new GridLayout(5, 5));
				panels[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
				panels[y][x].setBackground(Color.GRAY);
				add(panels[y][x]);
			}
		}
		boundry = panel * 5;
		fields = new Field[boundry][boundry];
		int type = game.getType();
		start = boundry - type;

		for (int y = start; y < boundry; y++) {
			for (int x = start; x < boundry; x++) {
				fields[y][x] = new Field(x, y);
				fields[y][x].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				panels[y / 5][x / 5].add(fields[y][x]);
			}
		}
		for (int y = start; y < boundry; y++) {
			for (int x = 0; x < start; x++) {
				fields[y][x] = new Field(x, y);
				fields[y][x].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				panels[y / 5][x / 5].add(fields[y][x]);
				fields[y][x].setBackground(Color.LIGHT_GRAY);
			}
		}
		for (int y = 0; y < start; y++) {
			for (int x = start; x < boundry; x++) {
				fields[y][x] = new Field(x, y);
				fields[y][x].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				panels[y / 5][x / 5].add(fields[y][x]);
				fields[y][x].setBackground(Color.LIGHT_GRAY);
			}
		}
		for (int y = 0; y < start; y++) {
			for (int x = 0; x < start; x++) {
				fields[y][x] = new Field(x, y);
				fields[y][x].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				panels[y / 5][x / 5].add(fields[y][x]);
				fields[y][x].setBackground(Color.LIGHT_GRAY);
			}
		}
	}

	/**
	 * Method called when model sends update notification.
	 *
	 * @param o
	 *            The model.
	 * @param arg
	 *            The UpdateAction.
	 */
	public void update(Observable o, Object arg) {
		switch ((UpdateAction) arg) {
		case NEW_GAME:
			Game game = (Game) o;
			updatePatternPanel(game);
			setGame((Game) o);
			break;
		default:
			break;
		}
	}

	private void updatePatternPanel(Game game) {
		// TODO Auto-generated method stub
		new PatternPanel(game);
	}

	/**
	 * Sets the fields corresponding to given game.
	 *
	 * @param game
	 *            Game to be set.
	 */
	private void setTopPannel(Game game, int[][] toppattern) {
		ArrayList<Integer> col = game.getNegCol();
		int type = game.getType();
		int count = 1;
		int column;
		for (int j = 0; j < type; j++) {
			for (int i = start - 1; i >= 0 && count <= col.size() - 1; i--) {
				column = col.get(count);
				if (column > 0) {
					toppattern[i][j] = column;
					count++;
				} else {
					count++;
					break;
				}
				if (i == 0) {
					count++;
				}
			}
		}
	}

	private void setLeftPannel(Game game, int[][] leftpattern) {
		ArrayList<Integer> row = game.getNegRow();
		int type = game.getType();
		int count = 1;
		int rowVal;
		for (int i = 0; i < type; i++) {
			for (int j = start - 1; j >= 0 && count <= row.size() - 1; j--) {
				rowVal = row.get(count);
				if (rowVal > 0) {
					leftpattern[i][j] = rowVal;
					count++;
				} else {
					count++;
					break;
				}
				if (j == 0) {
					count++;
				}
			}
		}
	}

	public void setRestartGame(Game game) {
		for (int y = start; y < boundry; y++) {
			for (int x = start; x < boundry; x++) {
				fields[y][x].setBackground(Color.GRAY);
			}
		}
	}

	public void solveGame(Game game) {
		for (int y = start; y < boundry; y++) {
			for (int x = start; x < boundry; x++) {
				if (game.getNumber(x - start, y - start) == 1) {
					fields[y][x].setBackground(Color.BLACK);
				} else {
					fields[y][x].setBackground(Color.WHITE);
				}
			}
		}
	}

	public void setGame(Game game) {
		setRestartGame(game);
		int[][] toppattern = new int[start][game.getType()];
		setTopPannel(game, toppattern);
		for (int y = 0; y < start; y++) {
			for (int x = start; x < boundry; x++) {
				fields[y][x].setNumber(toppattern[y][x - start], false);
			}
		}
		int[][] leftpattern = new int[game.getType()][start];
		setLeftPannel(game, leftpattern);
		for (int y = start; y < boundry; y++) {
			for (int x = 0; x < start; x++) {
				fields[y][x].setNumber(leftpattern[y - start][x], false);
			}
		}
		game.setPatternPanel(this);
	}

	/**
	 * Adds controller to all sub panels.
	 * 
	 * @param patternController
	 * @param game
	 */
	public void setController(PatternController patternController, Game game) {
		int start = getStart() / 5;
		int end = start + game.getType() / 5;
		for (int y = start; y < end; y++) {
			for (int x = start; x < end; x++) { 
				panels[y][x].addMouseListener(patternController);
			}
		}
	}

	public int getStart() {
		return start;
	}

	public Field[][] getFields() {
		return fields;
	}

	public void setFields(Field[][] fields) {
		this.fields = fields;
	}
}