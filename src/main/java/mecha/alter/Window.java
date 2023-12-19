package mecha.alter;

import javax.swing.JFrame;

public class Window extends JFrame {

	private static final long serialVersionUID = 1829289440268248188L;
	
	public Window(String title) {		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(title);
		
		GamePanel gamepanel = new GamePanel();
		
		this.add(gamepanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		gamepanel.setupGame();
		gamepanel.startGameThread();
	}
}