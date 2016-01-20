package sid.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import sid.constant.PatternConstant;
import sid.model.Game;
import sid.view.MenuPanel;

/**
 * This class controls all user actions from MenuPanel.
 *
 * @author Siddharth Gupta
 */
public class MenuController implements ActionListener {
	private Game game;

	/**
	 * Constructor, sets game.
	 *
	 * @param game
	 *            Game to be set.
	 */
	public MenuController(Game game) {
		this.game = game;
	}

	/**
	 * Performs action after user pressed button.
	 *
	 * @param e
	 *            ActionEvent.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(PatternConstant.NEW))
			if (game.getType() == 0)
				game.newGame(10, 10);
			else
				game.newGame(game.getType(), game.getType());
		else if (e.getActionCommand().equals(PatternConstant.EXIT))
			System.exit(0);
		else if (e.getActionCommand().equals(PatternConstant.ABOUT))
			about();
		else if (e.getActionCommand().equals(PatternConstant.SOLVE))
			game.solve();
		else if (e.getActionCommand().equals(PatternConstant.CHECK))
			game.checkGame();
		else if (e.getActionCommand().equals(PatternConstant.RESTART))
			game.restart();
		else if (e.getActionCommand().equals(PatternConstant.X10))
			game.updateGame(10);
		else if (e.getActionCommand().equals(PatternConstant.X15))
			game.updateGame(15);
		else if (e.getActionCommand().equals(PatternConstant.X20))
			game.updateGame(20);
		else if (e.getActionCommand().equals(PatternConstant.X25))
			game.updateGame(25);
	}

	private void about() {
		JOptionPane.showMessageDialog(MenuPanel.getjRootPane(), PatternConstant.ABOUT_MESSAGE,
				PatternConstant.ABOUT_WINDOW_TITLE, 1); 
	}
}