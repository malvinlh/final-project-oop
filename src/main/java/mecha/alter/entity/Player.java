package mecha.alter.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mecha.alter.GamePanel;
import mecha.alter.GamePanel.STATE;
import mecha.alter.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		setDefaultValues();
		
		//Added this
		if(gp.state == STATE.GAME)
		{
			getPlayerImage();
		}
		else
		{
			//nothing
		}
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
			
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void setDefaultValues() {
		// Player default
		x = 100; // X pos
		y = 100; // Y pos
		speed = 5;
		direction = "left";
	}

	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if (keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			} else if (keyH.downPressed == true) {
				direction = "down";
				y += speed;
			} else if (keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			} else if (keyH.rightPressed == true) {
				direction = "right";
				x += speed;	
			}
			
			spriteCounter++;
			if(spriteCounter > 15) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}

	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}