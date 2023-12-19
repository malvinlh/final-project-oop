package mecha.alter.entity;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import mecha.alter.GamePanel;
import mecha.alter.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	boolean idle = false;
	BufferedImage upIdle1, upIdle2, downIdle1, downIdle2, leftIdle1, leftIdle2, rightIdle1, rightIdle2;
	
	// For the Battle Scene
	private List<BufferedImage> playerIdle;
	private List<BufferedImage> playerAttack;
	private List<BufferedImage> playerGMIdle;
    private int currentFrameIndex;
    private long lastFrame;
    long frameDelay = 400;
    private int playerHP;
    private int playerMana;
    private String attackType;

	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		this.playerHP = 100;
		this.playerMana = 100;
		this.attackType = "attack";
		setDefaultValues();
		getPlayerImage();
		getImageForBattleScene();
		collideBox = new Rectangle();
		collideBox.x = 16;
		collideBox.y = 32;
		collideBox.width = 32;
		collideBox.height = 32;
		collideBoxDefaultX = collideBox.x;
		collideBoxDefaultY = collideBox.y;
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
	}

	public void getPlayerImage() {

		up1 = setup("/player/up1");
		up2 = setup("/player/up2");
		up3 = setup("/player/up3");
		up4 = setup("/player/up4");
		down1 = setup("/player/down1");
		down2 = setup("/player/down2");
		down3 = setup("/player/down3");
		down4 = setup("/player/down4");
		left1 = setup("/player/left1");
		left2 = setup("/player/left2");
		left3 = setup("/player/left3");
		left4 = setup("/player/left4");
		right1 = setup("/player/right1");
		right2 = setup("/player/right2");
		right3 = setup("/player/right3");
		right4 = setup("/player/right4");

		upIdle1 = setup("/player/upIdle1");
		upIdle2 = setup("/player/upIdle2");
		downIdle1 = setup("/player/downIdle1");
		downIdle2 = setup("/player/downIdle2");
		leftIdle1 = setup("/player/leftIdle1");
		leftIdle2 = setup("/player/leftIdle2");
		rightIdle1 = setup("/player/rightIdle1");
		rightIdle2 = setup("/player/rightIdle2");

	}

	public void setDefaultValues() {
		// Player default
		worldX = gp.tileSize * 12; // X pos
		worldY = gp.tileSize * 40; // Y pos
		speed = 4;
		direction = "left";
		maxLife = 100;
		life = maxLife;
	}

	public void touchMonster(int i) {
		if(i != 999) {
			if(GamePanel.state != GamePanel.GAMESTATE.BATTLE)
			{
				GamePanel.state = GamePanel.GAMESTATE.BATTLE;
			}
			System.out.println("monster");
		}
		
	}
	
	public void update() {
		idle = true;
		spriteCounter++;
		if (spriteCounter > 15) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 3;
			} else if (spriteNum == 3) {
				spriteNum = 4;
			} else if (spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {
			idle = false;
			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			collide = false;
			gp.colDetection.checkTile(this);
			
			int monsterIndex = gp.colDetection.checkEntity(this, gp.monster);
			touchMonster(monsterIndex);

			if (collide == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
		}

	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (idle) {
				if (spriteNum % 2 == 0) {
					image = upIdle1;
				} else {
					image = upIdle2;
				}
			} else {
				if (spriteNum == 1) {
					image = up1;
				}
				if (spriteNum == 2) {
					image = up2;
				}
				if (spriteNum == 3) {
					image = up3;
				}
				if (spriteNum == 4) {
					image = up4;
				}
			}
			break;
		case "down":
			if (idle) {
				if (spriteNum % 2 == 0) {
					image = downIdle1;
				} else {
					image = downIdle2;
				}
			} else {
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = down2;
				}
				if (spriteNum == 3) {
					image = down3;
				}
				if (spriteNum == 4) {
					image = down4;
				}
			}
			break;
		case "left":
			if (idle) {
				if (spriteNum % 2 == 0) {
					image = leftIdle1;
				} else {
					image = leftIdle2;
				}
			} else {
				if (spriteNum == 1) {
					image = left1;
				}
				if (spriteNum == 2) {
					image = left2;
				}
				if (spriteNum == 3) {
					image = left3;
				}
				if (spriteNum == 4) {
					image = left4;
				}
			}
			break;
		case "right":
			if (idle) {
				if (spriteNum % 2 == 0) {
					image = rightIdle1;
				} else {
					image = rightIdle2;
				}
			} else {
				if (spriteNum == 1) {
					image = right1;
				}
				if (spriteNum == 2) {
					image = right2;
				}
				if (spriteNum == 3) {
					image = right3;
				}
				if (spriteNum == 4) {
					image = right4;
				}
			}
			break;
		}

		g2.drawImage(image, screenX, screenY, null);
	}
	
    private void getImageForBattleScene() {
        playerIdle = new ArrayList<>();
        playerAttack = new ArrayList<>();
        playerGMIdle = new ArrayList<>();
        currentFrameIndex = 0;
        lastFrame = System.currentTimeMillis();

        // Load each player idle PNG frame
        for (int i = 1; i <= 2; i++) {
            try {
                String fileNameIdle = "/player/rightIdle" + i + ".png";
                BufferedImage frameIdle = ImageIO.read(getClass().getResourceAsStream(fileNameIdle));
                playerIdle.add(frameIdle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Load each player attack PNG frame
        for (int i = 1; i <= 4; i++) {
            try {
                String fileNameAttack = "/player/attack/stab_0" + i + ".png";
                BufferedImage frameAttack = ImageIO.read(getClass().getResourceAsStream(fileNameAttack));
                playerAttack.add(frameAttack);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Load each Gajah Mada idle PNG frame
        for (int i = 1; i <= 7; i++)
        {
        	try
        	{
        		String fileNameGMIdle = "/player/gajahmada/idle/idle_0" + i + ".png";
        		BufferedImage frameIdleGM = ImageIO.read(getClass().getResourceAsStream(fileNameGMIdle));
        		playerGMIdle.add(frameIdleGM);
        	}
        	catch (IOException e) 
        	{
                e.printStackTrace();
            }
        }
    }
    
    public void setAttackType(String type) {
        attackType = type;
    }
    
    public String getAttackType()
    {
    	return this.attackType;
    }
    
    public void updateBattleAnimationFrame() {
        // Update the frame only if enough time has passed
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrame > frameDelay) {
            currentFrameIndex = (currentFrameIndex + 1) % playerIdle.size();
            lastFrame = currentTime;
        }
    }
    
    public void drawIdleInBattleScene(Graphics g) 
    {
        if (!playerIdle.isEmpty()) {
            BufferedImage currentFrame = playerIdle.get(currentFrameIndex);
            
            // Adjust the scale factor based on your desired size
            double scaleX = 5.0; // Example: Increase width by a factor of 2
            double scaleY = 5.0; // Example: Increase height by a factor of 2

            int scaledWidth = (int) (currentFrame.getWidth() * scaleX);
            int scaledHeight = (int) (currentFrame.getHeight() * scaleY);

            // Draw the scaled image
            g.drawImage(currentFrame, 250, 400, scaledWidth, scaledHeight, null);
        }
    }
    
    public void drawAttackInBattleScene(Graphics g)
    {
        if (!playerAttack.isEmpty() && attackType == "attack") 
        {
            BufferedImage currentFrame = playerAttack.get(currentFrameIndex);
            
            // Adjust the scale factor based on your desired size
            double scaleX = 5.0; // Example: Increase width by a factor of 2
            double scaleY = 5.0; // Example: Increase height by a factor of 2

            int scaledWidth = (int) (currentFrame.getWidth() * scaleX);
            int scaledHeight = (int) (currentFrame.getHeight() * scaleY);

            // Draw the scaled image
            g.drawImage(currentFrame, 250, 400, scaledWidth, scaledHeight, null);
        }
        else if(!playerAttack.isEmpty() && attackType == "summon")
        {
        	BufferedImage currentFrameGM = playerGMIdle.get(currentFrameIndex);
        	BufferedImage currentFramePlayer = playerIdle.get(currentFrameIndex);
            
            // Adjust the scale factor based on your desired size
            double scaleXPlayer = 5.0;
            double scaleYPlayer = 5.0;

            int scaledWidthPlayer = (int) (currentFramePlayer.getWidth() * scaleXPlayer);
            int scaledHeightPlayer = (int) (currentFramePlayer.getHeight() * scaleYPlayer);

            // Draw the scaled image
            g.drawImage(currentFrameGM, 400, 200,  null);
            g.drawImage(currentFramePlayer, 250, 400, scaledWidthPlayer, scaledHeightPlayer, null);
        }
    }
    
	public void reduceHP(int amount)
	{
        playerHP -= amount;
        if (playerHP < 0) {
            playerHP = 0;
        }
        System.out.println("Player HP reduced by " + amount + ". Current HP: " + playerHP);
	}
	
	public void reduceMana(int amount)
	{
        playerMana -= amount;
        if (playerMana < 0) {
            playerMana = 0;
        }
        System.out.println("Player Mana reduced by " + amount + ". Current Mana: " + playerMana);
	}
	
	public int getHP()
	{
		return playerHP;
	}
	
	public int getMana()
	{
		return playerMana;
	}
}