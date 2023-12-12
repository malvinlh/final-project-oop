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

	// Display setting
	final int originalTileSize = 32;
	final int scale = 2;
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 25;
	public final int maxScreenRow = 15;
	public final int screenWidth = tileSize * maxScreenCol; // 1600px
	public final int screenHeight = tileSize * maxScreenRow; // 960px

	// World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	// FPS

	int FPS = 60;

	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this);
	public UI ui = new UI(this);
	Thread gameThread;
	public CollisionDetection colDectection = new CollisionDetection(this);
	public Player player = new Player(this, keyH);
	MainMenu mainMenu = new MainMenu(this);

	public static enum GAMESTATE {
		GAME, MAINMENU
	};

	public static GAMESTATE state = GAMESTATE.MAINMENU;

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
		if (state == GAMESTATE.GAME) {
			player.update();
		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		if (state == GAMESTATE.GAME) {

			// debug
			long drawStart = 0;
			drawStart = System.nanoTime();

			if (keyH.drawTimeInfo) {
				drawStart = System.nanoTime();
			}

			tileM.draw(g2);
			player.draw(g2);

			if (keyH.drawTimeInfo) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd - drawStart;
				g2.setColor(Color.WHITE);
				g2.drawString("draw time: " + passed, 10, 400);
				System.out.println("draw time: " + passed);
			}

		} else if (state == GAMESTATE.MAINMENU) {
			mainMenu.draw(g2);
		}

		g2.dispose();
	}
}