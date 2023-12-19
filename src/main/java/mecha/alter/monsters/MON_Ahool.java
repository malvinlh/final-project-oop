package mecha.alter.monsters;

import java.util.Random;

import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import mecha.alter.GamePanel;
import mecha.alter.entity.Entity;

public class MON_Ahool extends Entity 
{	
	// For the Battle Scene
	private List<BufferedImage> ahoolIdle;
	private List<BufferedImage> ahoolAttack;
    private int currentFrameIndex;
    private long lastFrame;
    private int ahoolHP;
	long frameDelay = 100;

	public MON_Ahool(GamePanel gp) {
		super(gp);
		type = 2;
		name = "Ahool";
		direction = "any";
		speed = 1;
		collideBox.x = 6;
		collideBox.y = 36;
		collideBox.width = 48;
		collideBox.height = 30;
		collideBoxDefaultX = collideBox.x;
		collideBoxDefaultY = collideBox.y;
		this.ahoolHP = 100;
		getImageForWorldMap();
		getImageForBattleScene();
	}

	public void getImageForWorldMap() {
		up1 = setup("/monsters/ahool/move-right/BatMovement-right_05");
		up2 = setup("/monsters/ahool/move-right/BatMovement-right_04");
		up3 = setup("/monsters/ahool/move-right/BatMovement-right_02");
		up4 = setup("/monsters/ahool/move-right/BatMovement-right_01");
		down1 = setup("/monsters/ahool/move-right/BatMovement-right_05");
		down2 = setup("/monsters/ahool/move-right/BatMovement-right_04");
		down3 = setup("/monsters/ahool/move-right/BatMovement-right_02");
		down4 = setup("/monsters/ahool/move-right/BatMovement-right_01");
		left1 = setup("/monsters/ahool/move-right/BatMovement-right_05");
		left2 = setup("/monsters/ahool/move-right/BatMovement-right_04");
		left3 = setup("/monsters/ahool/move-right/BatMovement-right_02");
		left4 = setup("/monsters/ahool/move-right/BatMovement-right_01");
		right1 = setup("/monsters/ahool/move-right/BatMovement-right_05");
		right2 = setup("/monsters/ahool/move-right/BatMovement-right_04");
		right3 = setup("/monsters/ahool/move-right/BatMovement-right_02");
		right4 = setup("/monsters/ahool/move-right/BatMovement-right_01");
	}

	public void setAction() {
		actionLockCounter++;

		if (actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;

			if (i <= 25) {
				direction = "up";
			}
			if (i > 25 && i <= 50) {
				direction = "down";
			}
			if (i > 50 && i <= 75) {
				direction = "left";
			}
			if (i > 75 && i <= 100) {
				direction = "right";
			}

			actionLockCounter = 0;
		}
	}
	
    private void getImageForBattleScene() 
    {
        ahoolIdle = new ArrayList<>();
        ahoolAttack = new ArrayList<>();
        currentFrameIndex = 0;
        lastFrame = System.currentTimeMillis();

        // Load each idle PNG frame
        for (int i = 1; i <= 6; i++) 
        {
            try 
            {
                String fileNameIdle = "/monsters/ahool/idle/idle-left_0" + i + ".png";
                BufferedImage frameIdle = ImageIO.read(getClass().getResourceAsStream(fileNameIdle));
                ahoolIdle.add(frameIdle);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        // Load each attack PNG frame
        for (int i = 1; i <= 7; i++)
        {
        	try
        	{
                String fileNameAttack = "/monsters/ahool/attack/attack_0" + i + ".png";
                BufferedImage frameAttack = ImageIO.read(getClass().getResourceAsStream(fileNameAttack));
                ahoolAttack.add(frameAttack);
        	}
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    public void updateBattleAnimationFrame() 
    {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrame > frameDelay) {
            currentFrameIndex = (currentFrameIndex + 1) % ahoolIdle.size();
            lastFrame = currentTime;
        }
    }
    
    public void drawIdleInBattleScene(Graphics g) 
    {
        if (!ahoolIdle.isEmpty()) {
            BufferedImage currentFrame = ahoolIdle.get(currentFrameIndex);
            g.drawImage(currentFrame, 1000, 300, null);
        }
    }
    
    public void drawAttackInBattleScene(Graphics g) 
    {
        if (!ahoolAttack.isEmpty()) {
            BufferedImage currentFrame = ahoolAttack.get(currentFrameIndex);
            g.drawImage(currentFrame, 1000, 300, null);
        }
    }
    
    public void reduceHP(int amount) {
        ahoolHP -= amount;
        if (ahoolHP < 0) {
            ahoolHP = 0;
        }
        System.out.println("Ahool HP reduced by " + amount + ". Current HP: " + ahoolHP);
    }
    
	public int getHP()
	{
		return ahoolHP;
	}
}