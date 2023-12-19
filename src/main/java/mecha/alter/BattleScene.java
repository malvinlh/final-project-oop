package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import mecha.alter.entity.Entity;
import mecha.alter.entity.Player;
import mecha.alter.monsters.MON_Ahool;
import mecha.alter.monsters.MON_Banaspati;
import mecha.alter.monsters.MON_Jerangkong;

public class BattleScene implements MouseListener 
{
    private BufferedImage backgroundImage, playerStatsPanel, playerMovesPanel, enemyStatsPanel;
    private GamePanel gamePanel;
    private MainMenu mainMenu;
    private List<String> moveNames;
    private List<RoundRectangle2D.Double> boundingBoxes;
    private boolean playerTurn, playerAttack, enemyAttack;
    private long attackStartTime;
    
    private Player playerChar;
    private Entity currentMonster;

    public BattleScene(GamePanel gamePanel, MainMenu mainMenu) 
    {
        this.gamePanel = gamePanel;
        this.mainMenu = mainMenu;
        gamePanel.addMouseListener(this);
        this.playerTurn = true;
        this.playerAttack = false;
        this.enemyAttack = false;
        this.attackStartTime = 0;

        getImage();

        moveNames = new ArrayList<>();
        boundingBoxes = new ArrayList<>();
        playerChar = new Player(gamePanel, null);
        
        int randomMonster = (int) (Math.random() * 3);
        if(randomMonster == 0)
        {
        	currentMonster = new MON_Ahool(gamePanel);
        }
        else if(randomMonster == 1)
        {
        	currentMonster = new MON_Jerangkong(gamePanel);
        }
        else
        {
        	currentMonster = new MON_Banaspati(gamePanel);
        }

        int y = 690;

        String[] names = {"Attack", "Summon"};

        for (String name : names) {
            moveNames.add(name);
            int textWidth = calculateTextWidth(name, mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
            boundingBoxes.add(new RoundRectangle2D.Double(560, y, textWidth - 25, 30, 0, 0));
            y += 35;
        }
    }

    private void getImage() 
    {
        try 
        {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/battle/battle-bg-1-pixelated.png"));
            playerStatsPanel = ImageIO.read(getClass().getResourceAsStream("/battle/panel.png"));
            playerMovesPanel = ImageIO.read(getClass().getResourceAsStream("/battle/panel.png"));
            enemyStatsPanel = ImageIO.read(getClass().getResourceAsStream("/battle/panel.png"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private int calculateTextWidth(String text, Font font)
    {
        FontMetrics fontMetrics = gamePanel.getFontMetrics(font);
        return fontMetrics.stringWidth(text);
    }

    public void draw(Graphics g) 
    {
    	if(GamePanel.state == GamePanel.GAMESTATE.BATTLE)
    	{
	        if (backgroundImage != null) 
	        {
	            g.drawImage(backgroundImage, 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), gamePanel);
	        } 
	        else 
	        {
	            g.setColor(Color.BLACK);
	            g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
	        }
	
	        Font font20 = mainMenu.getImprisha().deriveFont(Font.BOLD, 20);
	
	        int panelWidth = 560;
	        int panelHeight = 300;
	        int xPosition = -20;
	        int yPosition = gamePanel.getHeight() - 265;
	
	        g.drawImage(playerMovesPanel, xPosition, yPosition, panelWidth, panelHeight, gamePanel);
	        g.drawImage(playerStatsPanel, xPosition + 505, yPosition, panelWidth, panelHeight, gamePanel);
	        g.drawImage(enemyStatsPanel, xPosition + 1010, yPosition, panelWidth, panelHeight, gamePanel);
	
	        g.setFont(font20);
	
	        g.drawString("PLAYER MOVES", 680, 680);
	
	        g.setColor(Color.BLACK);
	        for (int i = 0; i < moveNames.size(); i++) {
	            g.drawString(moveNames.get(i), (int) boundingBoxes.get(i).x + 3, (int) boundingBoxes.get(i).y + 22);
	        }
	        
	        if (playerChar != null && playerAttack == false && playerChar.getHP() != 0) 
	        {
	            playerChar.drawIdleInBattleScene(g);
	            playerChar.updateBattleAnimationFrame();
	        }
	        
	        if(playerChar != null && playerAttack == true && playerChar.getHP() != 0)
	        {
	        	playerChar.drawAttackInBattleScene(g);
	        	playerChar.updateBattleAnimationFrame();
	        	
	            if (System.currentTimeMillis() - attackStartTime > 1000) {
	                playerAttack = false;
		            startEnemyTurn();
	            }
	        }
	        
	        if (currentMonster != null && enemyAttack == false && currentMonster.getHP() != 0) 
	        {
	        	currentMonster.drawIdleInBattleScene(g);
	        	currentMonster.updateBattleAnimationFrame();
	        }
	        
	        if (currentMonster != null && enemyAttack == true && currentMonster.getHP() != 0) 
	        {
	        	currentMonster.drawAttackInBattleScene(g);
	        	currentMonster.updateBattleAnimationFrame();
	        }
	
	        g.drawString("PLAYER STATS", 180, 680);
	        int playerHP = playerChar.getHP();
	        int playerMana = playerChar.getMana();
	        g.drawString("HP", 60, 710);
	        g.drawString(playerHP + "/100", 60, 730);
	        g.drawString("MANA", 60, 760);
	        g.drawString(playerMana + "/100", 60, 780);
	
	        int monsterHP = currentMonster.getHP();
	        g.drawString("ENEMY STATS", 1195, 680);
	        g.drawString("HP", 1070, 710);
	        g.drawString(monsterHP + "/100", 1070, 730);
	        
	        if(playerChar.getHP() == 0 || currentMonster.getHP() == 0)
	        {
	        	GamePanel.state = GamePanel.GAMESTATE.GAME;
	        }
    	}
    }
    
    private void startEnemyTurn() {
        enemyAttack = true;
        attackStartTime = System.currentTimeMillis();
        
        playerChar.reduceHP(10);
        
        new Thread(new Runnable() {
			@Override
			public void run() {
			    try {
			        Thread.sleep(1000);
			    } catch (InterruptedException e) {
			        e.printStackTrace();
			    }
			    
			    enemyAttack = false;
			    playerTurn = true;
			}
		}).start();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (GamePanel.state != GamePanel.GAMESTATE.BATTLE)
            return;

        int mx = e.getX();
        int my = e.getY();

        if (playerTurn) {
            for (int i = 0; i < boundingBoxes.size(); i++) {
                if (boundingBoxes.get(i).contains(mx, my)) {
                    switch (i) {
                        case 0:
                            System.out.println("Mouse click inside attack");
                            playerAttack = true;
                            attackStartTime = System.currentTimeMillis();
                            playerChar.setAttackType("attack");
                            mainMenu.playSE(1);
                            currentMonster.reduceHP(10);
                            playerTurn = false;
                            break;
                        case 1:
                            if (playerChar.getMana() != 0) {
                                System.out.println("Mouse click inside summon");
                                playerAttack = true;
                                attackStartTime = System.currentTimeMillis();
                                playerChar.setAttackType("summon");
                                mainMenu.playSE(1);
                                currentMonster.reduceHP(15);
                                playerChar.reduceMana(25);
                                playerTurn = false;
                            } else {
                                mainMenu.playSE(1);
                                playerTurn = true;
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        } else {
            playerTurn = true;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // ... mousePressed implementation ...
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // ... mouseReleased implementation ...
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // ... mouseEntered implementation ...
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // ... mouseExited implementation ...
    }
}