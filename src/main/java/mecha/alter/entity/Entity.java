package mecha.alter.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import mecha.alter.GamePanel;
import mecha.alter.UtilityTools;

public abstract class Entity {
	GamePanel gp;
	public int worldX, worldY;
	public String name;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, up3, up4, down3, down4, left3, left4,
			right3, right4;;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public int collideBoxDefaultX, collideBoxDefaultY;
	public boolean collide = false;
	public int maxLife;
	public int life;
	public int actionLockCounter = 0;
	public Rectangle collideBox = new Rectangle(0, 0, 48, 48);
	public int type;


	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	public void update() {
		setAction();
		collide = false;
		gp.colDetection.checkTile(this);
		gp.colDetection.checkEntity(this, gp.monster);
		boolean touchPlayer = gp.colDetection.checkPlayer(this);
		
		if(this.type == 2 & touchPlayer == true) {
			if(GamePanel.state != GamePanel.GAMESTATE.BATTLE)
			{
				GamePanel.state = GamePanel.GAMESTATE.BATTLE;
			}
			gp.player.life -= 1;
		}
		
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

		spriteCounter++;
		if (spriteCounter > 15) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	public void draw(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

			BufferedImage image = null;

			switch (direction) {
			case "up":
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
				break;
			case "down":
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
				break;
			case "left":

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
				break;
			case "right":
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
				break;
			}
			g2.drawImage(image, screenX, screenY, null);

		}
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTools uTool = new UtilityTools();
		BufferedImage scaledImage = null;

		try {
			scaledImage = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return scaledImage;
	}
	
	public abstract void drawIdleInBattleScene(Graphics g);
	public abstract void drawAttackInBattleScene(Graphics g);
	public abstract void updateBattleAnimationFrame();
	public abstract int getHP();
	public abstract void reduceHP(int amount);
}