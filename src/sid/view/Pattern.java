package sid.view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.UIManager;

import sid.controller.MenuController;
import sid.controller.PatternController;
import sid.model.Game;

/**
 * Main class of program.
 *
 * @author Siddharth Gupta
 */
 
@SuppressWarnings("serial")
public class Pattern extends JFrame implements Observer {

	public Pattern(int type) {
		super("Pattern");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		Game game = new Game(type);
		MenuController menuController = new MenuController(game);
		MenuPanel menuPanel = new MenuPanel(getRootPane());
		menuPanel.setController(menuController);

		PatternPanel patternPanel = new PatternPanel(game);
		PatternController patternController = new PatternController(patternPanel, game);
		patternPanel.setGame(game);
		patternPanel.setController(patternController, game);
		add(patternPanel, BorderLayout.CENTER);

		game.addObserver(patternPanel);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Main entry point of program.
	 * 
	 * @param args
	 *            Command line arguments.
	 */
	public static void main(String[] args) {
		// Use System Look and Feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		new Pattern(10);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}