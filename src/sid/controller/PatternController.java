package sid.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import sid.model.Game;
import sid.view.Field;
import sid.view.PatternPanel;

/**
 * This class controls all user actions from PatternPanel.
 *
 * @author Siddharth Gupta
 */
public class PatternController implements MouseListener {
	private PatternPanel patternPanel; // Panel to control.
	private Game game; // Current Sudoku game.

	/**
	 * Constructor, sets game. 
	 * 
	 * @param game
	 *            Game to be set.
	 */
	public PatternController(PatternPanel patternPanel, Game game) {
		this.patternPanel = patternPanel;
		this.game = game;
	}

	/**
	 * Recovers if user clicked field in game. If so it sets the selected number
	 * at clicked position in game and updates clicked field. If user clicked a
	 * field and used left mouse button, number at clicked position will be
	 * cleared in game and clicked field will be updated.
	 *
	 * @param e
	 *            MouseEvent.
	 */
	public void mousePressed(MouseEvent e) {
		JPanel panel = (JPanel) e.getSource();
		Component component = panel.getComponentAt(e.getPoint());
		if (component instanceof Field) {
			Field field = (Field) component;
			int x = field.getFieldX();
			int y = field.getFieldY();
			Field[][] fields = patternPanel.getFields();

			if (e.getButton() == MouseEvent.BUTTON1) {
				if (field.getBackground() == Color.GRAY) {
					fields[y][x].setBackground(Color.BLACK);
				} else if (field.getBackground() == Color.WHITE) {
					fields[y][x].setBackground(Color.GRAY);
				} else {
					fields[y][x].setBackground(Color.WHITE);
				}
			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				if (field.getBackground() == Color.GRAY) {
					fields[y][x].setBackground(Color.WHITE);
				} else if (field.getBackground() == Color.WHITE) {
					fields[y][x].setBackground(Color.BLACK);
				} else {
					fields[y][x].setBackground(Color.GRAY);
				}
			}
			patternPanel.setFields(fields);
			game.setPatternPanel(patternPanel);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}