package mecha.alter.monsters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import mecha.alter.GamePanel;
import mecha.alter.entity.Entity;

public class MON_Banaspati extends Entity 
{
    int currentFrame;
	long lastFrameTime;
	
	// For the Battle Scene
	private List<BufferedImage> banaspatiIdle;
	private List<BufferedImage> banaspatiAttack;
    private int currentFrameIndex;
    private long lastFrame;
    private int banaspatiHP;

	long frameDelay = 100; // Adjust this value to control the animation speed

	public MON_Banaspati(GamePanel gp) {
		super(gp);
		type = 2;
		name = "Banaspati";
		direction = "any";
		speed = 1;
		collideBox.x = 6;
		collideBox.y = 36;
		collideBox.width = 48;
		collideBox.height = 30;
		collideBoxDefaultX = collideBox.x;
		collideBoxDefaultY = collideBox.y;
		
		this.banaspatiHP = 100;
				
		getImageForWorldMap();
		getImageForBattleScene();
	}
	
    private void getImageForBattleScene() {
        banaspatiIdle = new ArrayList<>();
        banaspatiAttack = new ArrayList<>();
        currentFrameIndex = 0;
        lastFrame = System.currentTimeMillis();

        // Load each idle PNG frame
        for (int i = 1; i <= 4; i++) 
        {
            try 
            {
                String fileNameIdle = "/monsters/banaspati/idle/idle" + i + ".png";
                BufferedImage frameIdle = ImageIO.read(getClass().getResourceAsStream(fileNameIdle));
                banaspatiIdle.add(frameIdle);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }

        // Load each attack PNG frame
        for (int i = 1; i <= 4; i++)
        {
        	try
        	{
                String fileNameAttack = "/monsters/banaspati/idle/idle" + i + ".png";
                BufferedImage frameAttack = ImageIO.read(getClass().getResourceAsStream(fileNameAttack));
                banaspatiAttack.add(frameAttack);
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
            currentFrameIndex = (currentFrameIndex + 1) % banaspatiIdle.size();
            lastFrame = currentTime;
        }
    }
    
    public void drawIdleInBattleScene(Graphics g) 
    {
        if (!banaspatiIdle.isEmpty()) {
            BufferedImage currentFrame = banaspatiIdle.get(currentFrameIndex);
            g.drawImage(currentFrame, 1000, 270, null);
        }
    }
    
    public void drawAttackInBattleScene(Graphics g) 
    {
        if (!banaspatiAttack.isEmpty()) {
            BufferedImage currentFrame = banaspatiAttack.get(currentFrameIndex);
            g.drawImage(currentFrame, 1000, 270, null);
        }
    }


	public void getImageForWorldMap() 
	{
			up1 = setup("/monsters/banaspati/idle/idle1");
			up2 = setup("/monsters/banaspati/idle/idle2");
			up3 = setup("/monsters/banaspati/idle/idle3");
			up4 = setup("/monsters/banaspati/idle/idle4");
			down1 = setup("/monsters/banaspati/idle/idle1");
			down2 = setup("/monsters/banaspati/idle/idle2");
			down3 = setup("/monsters/banaspati/idle/idle3");
			down4 = setup("/monsters/banaspati/idle/idle4");
			left1 = setup("/monsters/banaspati/idle/idle1");
			left2 = setup("/monsters/banaspati/idle/idle2");
			left3 = setup("/monsters/banaspati/idle/idle3");
			left4 = setup("/monsters/banaspati/idle/idle4");
			right1 = setup("/monsters/banaspati/idle/idle1");
			right2 = setup("/monsters/banaspati/idle/idle2");
			right3 = setup("/monsters/banaspati/idle/idle3");
			right4 = setup("/monsters/banaspati/idle/idle4");
	}

	public void setAction() {
		actionLockCounter++;
		
		if (actionLockCounter == 120) {
			Random random = new Random();
	    	int i = random.nextInt(100)+1;
	    	
	    	if(i <= 25) {
	    		direction = "up";
	    	}
	    	if(i > 25 && i <= 50) {
	    		direction = "down";
	    	}
	    	if(i > 50 && i <= 75) {
	    		direction = "left";
	    	}
	    	if(i > 75 && i <= 100) {
	    		direction = "right";
	    	}
	    	
	    	actionLockCounter = 0;
		}
    }
	

    public void reduceHP(int amount) {
        banaspatiHP -= amount;
        if (banaspatiHP < 0) {
            banaspatiHP = 0;
        }
        System.out.println("Banaspati HP reduced by " + amount + ". Current HP: " + banaspatiHP);
    }
    
	public int getHP()
	{
		return banaspatiHP;
	}
}