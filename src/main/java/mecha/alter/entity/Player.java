package mecha.alter.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mecha.alter.GamePanel;
import mecha.alter.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	boolean idle = false;
	// public BufferedImage upIdle[], downIdle[], leftIdle[], rightIdle[];
	BufferedImage upIdle1, upIdle2, downIdle1, downIdle2, leftIdle1, leftIdle2, rightIdle1, rightIdle2;
	int walkSpriteCounter = 0;

	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		collideBox = new Rectangle();
		collideBox.x = 24;
		collideBox.y = 35;
		collideBox.width = 32;
		collideBox.height = 40;

		setDefaultValues();
		getPlayerImage();
	}

	private BufferedImage scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
		BufferedImage scaledImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics2D = scaledImage.createGraphics();

		graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);

		graphics2D.dispose();

		return scaledImage;
	}

	public void getPlayerImage() {
		try {

			up1 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/up1.png")), gp.tileSize, gp.tileSize);
			up2 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/up2.png")), gp.tileSize, gp.tileSize);
			up3 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/up3.png")), gp.tileSize, gp.tileSize);
			up4 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/up4.png")), gp.tileSize, gp.tileSize);
			down1 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/down1.png")), gp.tileSize,
					gp.tileSize);
			down2 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/down2.png")), gp.tileSize,
					gp.tileSize);
			down3 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/down3.png")), gp.tileSize,
					gp.tileSize);
			down4 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/down4.png")), gp.tileSize,
					gp.tileSize);
			left1 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/left1.png")), gp.tileSize,
					gp.tileSize);
			left2 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/left2.png")), gp.tileSize,
					gp.tileSize);
			left3 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/left3.png")), gp.tileSize,
					gp.tileSize);
			left4 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/left4.png")), gp.tileSize,
					gp.tileSize);
			right1 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/right1.png")), gp.tileSize,
					gp.tileSize);
			right2 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/right2.png")), gp.tileSize,
					gp.tileSize);
			right3 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/right3.png")), gp.tileSize,
					gp.tileSize);
			right4 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/right4.png")), gp.tileSize,
					gp.tileSize);

			upIdle1 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/upIdle1.png")), gp.tileSize,
					gp.tileSize);
			upIdle2 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/upIdle2.png")), gp.tileSize,
					gp.tileSize);
			downIdle1 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/downIdle1.png")), gp.tileSize,
					gp.tileSize);
			downIdle2 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/downIdle2.png")), gp.tileSize,
					gp.tileSize);
			leftIdle1 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/leftIdle1.png")), gp.tileSize,
					gp.tileSize);
			leftIdle2 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/leftIdle2.png")), gp.tileSize,
					gp.tileSize);
			rightIdle1 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/rightIdle1.png")), gp.tileSize,
					gp.tileSize);
			rightIdle2 = scaleImage(ImageIO.read(getClass().getResourceAsStream("/player/rightIdle2.png")), gp.tileSize,
					gp.tileSize);

			/*
			 * for (int i = 0; i < 4; i++) { up[i] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/" + "up" + (i + 1) +
			 * ".png")); } for (int i = 0; i < 4; i++) { down[i] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/" + "down" + (i + 1) +
			 * ".png")); } for (int i = 0; i < 4; i++) { left[i] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/" + "left" + (i + 1) +
			 * ".png")); } for (int i = 0; i < 4; i++) { right[i] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/" + "right" + (i + 1) +
			 * ".png")); } for (int i = 0; i < 2; i++) { upIdle[i] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/" + "uIdle" + (i + 1) +
			 * ".png")); } for (int i = 0; i < 2; i++) { downIdle[i] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/" + "dIdle" + (i + 1) +
			 * ".png")); } for (int i = 0; i < 2; i++) { leftIdle[i] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/" + "lIdle" + (i + 1) +
			 * ".png")); } for (int i = 0; i < 2; i++) { downIdle[i] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/" + "rIdle" + (i + 1) +
			 * ".png")); }
			 */
			/*
			 * up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png")); up2 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/up2.png")); up3 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/up3.png")); up4 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/up4.png")); down1 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/down1.png")); down2 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/down2.png")); down3 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/down3.png")); down4 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/down4.png")); left1 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/left1.png")); left2 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/left2.png")); left3 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/left3.png")); left4 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/left4.png")); right1 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/right1.png")); right2 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/right2.png")); right3 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/right3.png")); right4 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/right4.png"));
			 * 
			 * upIdle1 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/upIdle1.png")); upIdle2
			 * = ImageIO.read(getClass().getResourceAsStream("/player/upIdle2.png"));
			 * downIdle1 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/downIdle1.png"));
			 * downIdle2 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/downIdle2.png"));
			 * leftIdle1 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/leftIdle1.png"));
			 * leftIdle2 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/leftIdle2.png"));
			 * rightIdle1 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/rightIdle1.png"));
			 * rightIdle2 =
			 * ImageIO.read(getClass().getResourceAsStream("/player/rightIdle2.png"));
			 */
			/*
			 * upIdle[0] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/uIdle1.png")); upIdle[1]
			 * = ImageIO.read(getClass().getResourceAsStream("/player/uIdle2.png"));
			 * 
			 * downIdle[0] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/dIdle1.png"));
			 * downIdle[1] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/dIdle2.png"));
			 * 
			 * leftIdle[0] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/lIdle1.png"));
			 * leftIdle[1] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/lIdle2.png"));
			 * 
			 * downIdle[0] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/rIdle1.png"));
			 * downIdle[1] =
			 * ImageIO.read(getClass().getResourceAsStream("/player/rIdle2.png"));
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDefaultValues() {
		// Player default
		worldX = gp.tileSize * 20; // X pos
		worldY = gp.tileSize * 20; // Y pos
		speed = 5;
		direction = "left";
	}

	public void update() {
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
			gp.colDectection.checkTIle(this);

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

			walkSpriteCounter++;
			if (walkSpriteCounter > 15) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 3;
				} else if (spriteNum == 3) {
					spriteNum = 4;
				} else if (spriteNum == 4) {
					spriteNum = 1;
				}
				walkSpriteCounter = 0;
			}
		} else {
			idle = true;
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
}