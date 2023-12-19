package mecha.alter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import mecha.alter.entity.Entity;
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
	public int maxWorldCol = 50;
	public int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	// FPS

	int FPS = 60;

	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	public CollisionDetection colDetection = new CollisionDetection(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	public Player player = new Player(this, keyH);
	MainMenu mainMenu = new MainMenu(this);
	SoundManager sManager = new SoundManager();
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	BattleScene battleScene = new BattleScene(this, mainMenu);

	public static enum GAMESTATE {
		GAME, MAINMENU, PAUSED, BATTLE
	};

	public static GAMESTATE state = GAMESTATE.MAINMENU;

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}

	public void setupGame() {
		aSetter.setMonsters();
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
			if (!sManager.isMusicPlaying()) {
				sManager.setSounds(2, true);
				sManager.playSounds();
			}
			player.update();

			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					monster[i].update();
				}
			}
		}
		if (state == GAMESTATE.PAUSED) {

		}

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		if (state == GAMESTATE.GAME || state == GAMESTATE.PAUSED) {

			// debug
			long drawStart = 0;
			drawStart = System.nanoTime();

			if (keyH.drawTimeInfo) {
				drawStart = System.nanoTime();
			}

			tileM.draw(g2);
			entityList.add(player);

			for (int i = 0; i < monster.length; i++) {
				if (monster[i] != null) {
					entityList.add(monster[i]);
				}
			}

			Collections.sort(entityList, new Comparator<Entity>() {
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});

			for (int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}

			for (int i = 0; i < entityList.size(); i++) {
				entityList.remove(i);
			}

			ui.draw(g2);

			if (keyH.drawTimeInfo) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd - drawStart;
				g2.setColor(Color.WHITE);
				g2.drawString("draw time: " + passed, 10, 400);
				System.out.println("draw time: " + passed);
			}
		}
		else if(state == GAMESTATE.BATTLE)
		{
			battleScene.draw(g2);
		}
		else if (state == GAMESTATE.MAINMENU) 
		{
			mainMenu.draw(g2);
		}

		g2.dispose();
	}
}