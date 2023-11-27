package mecha.alter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import mecha.alter.entity.Player;
import mecha.alter.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 2968997093426263095L;
	final int originalTileSize = 16;
	final int scale = 5;

	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 1280px
	public final int screenHeight = tileSize * maxScreenRow; // 960px

	// FPS

	int FPS = 60;

	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this);
	Thread gameThread;
	Player player = new Player(this, keyH);
	MainMenu mainMenu = new MainMenu(this);
	
	public static enum STATE 
	{
		GAME,
		MENU
	};

	public static STATE state = STATE.MENU;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;

		while (gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);

			lastTime = currentTime;

			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;

			}

			if (timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
		}

	}

	public void update() {
		if(state == STATE.GAME)
		{
			player.update();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		if(state == STATE.GAME)
		{
			tileM.draw(g2);
			player.draw(g2);
		}
		else if (state == STATE.MENU) 
		{
	        mainMenu.draw(g2);
	    }

		g2.dispose();
	}
}