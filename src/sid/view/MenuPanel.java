package sid.view;

import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import sid.constant.PatternConstant;
import sid.controller.MenuController;

/**
 * This class draws the Menu panel.
 *
 * @author Siddharth Gupta
 */

@SuppressWarnings("serial")
public class MenuPanel extends JPanel implements Observer {

	JMenuItem New;
	JMenuItem Exit;
	JMenuItem Restart;
	JMenuItem Solve;
	JMenuItem Check;
	JMenuItem About;
	JMenuItem x10;
	JMenuItem x15;
	JMenuItem x20;
	JMenuItem x25;
	static JRootPane jRootPane;

	public MenuPanel(JRootPane jRootPane) {
		setjRootPane(jRootPane);
		jRootPane.setJMenuBar(createMenuBar());
	}

	public void setController(MenuController menuController) {
		New.addActionListener(menuController);
		Exit.addActionListener(menuController);
		Restart.addActionListener(menuController);
		Solve.addActionListener(menuController);
		About.addActionListener(menuController);
		Check.addActionListener(menuController);
		x10.addActionListener(menuController);
		x15.addActionListener(menuController);
		x20.addActionListener(menuController);
		x25.addActionListener(menuController);
	}

	public JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu;

		// Create the menu bar.
		menuBar = new JMenuBar();

		// Build the first menu.
		menu = new JMenu(PatternConstant.GAME);
		menu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(menu);

		// a group of JMenuItems
		New = new JMenuItem(PatternConstant.NEW, KeyEvent.VK_N);
		menu.add(New);

		Restart = new JMenuItem(PatternConstant.RESTART, KeyEvent.VK_R);
		menu.add(Restart);

		Check = new JMenuItem(PatternConstant.CHECK, KeyEvent.VK_C);
		menu.add(Check);

		Solve = new JMenuItem(PatternConstant.SOLVE, KeyEvent.VK_S);
		menu.add(Solve);

		Exit = new JMenuItem(PatternConstant.EXIT, KeyEvent.VK_E);
		menu.add(Exit);

		menu = new JMenu(PatternConstant.TYPE);
		menu.setMnemonic(KeyEvent.VK_T);
		menuBar.add(menu);

		x10 = new JMenuItem(PatternConstant.X10);
		menu.add(x10);

		x15 = new JMenuItem(PatternConstant.X15);
		menu.add(x15);

		x20 = new JMenuItem(PatternConstant.X20);
		menu.add(x20);

		x25 = new JMenuItem(PatternConstant.X25);
		menu.add(x25);

		menu = new JMenu(PatternConstant.HELP);
		menu.setMnemonic(KeyEvent.VK_H);
		About = new JMenuItem(PatternConstant.ABOUT);
		menu.add(About);

		menuBar.add(menu);

		return menuBar;
	}

	public static JRootPane getjRootPane() {
		return jRootPane;
	}

	public void setjRootPane(JRootPane jRootPane) {
		MenuPanel.jRootPane = jRootPane;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
