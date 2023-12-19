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

public class MON_Jerangkong extends Entity 
{
    int currentFrame;
	long lastFrameTime;
	
	// For the Battle Scene
	private List<BufferedImage> jerangkongIdle;
	private List<BufferedImage> jerangkongAttack;
    private int currentFrameIndex;
    private long lastFrame;
    private int jerangkongHP;

	long frameDelay = 100; // Adjust this value to control the animation speed

	public MON_Jerangkong(GamePanel gp) {
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
		
		this.jerangkongHP = 100;
				
		getImageForWorldMap();
		getImageForBattleScene();
	}
	
    private void getImageForBattleScene() {
        jerangkongIdle = new ArrayList<>();
        jerangkongAttack = new ArrayList<>();
        currentFrameIndex = 0;
        lastFrame = System.currentTimeMillis();

        // Load each idle PNG frame
        for (int i = 1; i <= 4; i++) 
        {
            try 
            {
                String fileNameIdle = "/monsters/jerangkong/idleLeft/idle_0" + i + ".png";
                BufferedImage frameIdle = ImageIO.read(getClass().getResourceAsStream(fileNameIdle));
                jerangkongIdle.add(frameIdle);
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
                String fileNameAttack = "/monsters/jerangkong/attack/attack_0" + i + ".png";
                BufferedImage frameAttack = ImageIO.read(getClass().getResourceAsStream(fileNameAttack));
                jerangkongAttack.add(frameAttack);
        	}
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    public void updateBattleAnimationFrame() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrame > frameDelay) {
            currentFrameIndex = (currentFrameIndex + 1) % jerangkongIdle.size();
            lastFrame = currentTime;
        }
    }
    
    public void drawIdleInBattleScene(Graphics g) 
    {
        if (!jerangkongIdle.isEmpty()) {
            BufferedImage currentFrame = jerangkongIdle.get(currentFrameIndex);
            g.drawImage(currentFrame, 1000, 270, null);
        }
    }
    
    public void drawAttackInBattleScene(Graphics g) 
    {
        if (!jerangkongAttack.isEmpty()) {
            BufferedImage currentFrame = jerangkongAttack.get(currentFrameIndex);
            g.drawImage(currentFrame, 1000, 270, null);
        }
    }


	public void getImageForWorldMap() {
			up1 = setup("/monsters/jerangkong/idleRight/idle_01");
			up2 = setup("/monsters/jerangkong/idleRight/idle_02");
			up3 = setup("/monsters/jerangkong/idleRight/idle_03");
			up4 = setup("/monsters/jerangkong/idleRight/idle_04");
			down1 = setup("/monsters/jerangkong/idleRight/idle_01");
			down2 = setup("/monsters/jerangkong/idleRight/idle_02");
			down3 = setup("/monsters/jerangkong/idleRight/idle_03");
			down4 = setup("/monsters/jerangkong/idleRight/idle_04");
			left1 = setup("/monsters/jerangkong/idleRight/idle_01");
			left2 =  setup("/monsters/jerangkong/idleRight/idle_02");
			left3 =  setup("/monsters/jerangkong/idleRight/idle_03");
			left4 =  setup("/monsters/jerangkong/idleRight/idle_04");
			right1 = setup("/monsters/jerangkong/idleRight/idle_01");
			right2 = setup("/monsters/jerangkong/idleRight/idle_02");
			right3 = setup("/monsters/jerangkong/idleRight/idle_03");
			right4 = setup("/monsters/jerangkong/idleRight/idle_04");
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
        jerangkongHP -= amount;
        if (jerangkongHP < 0) {
            jerangkongHP = 0;
        }
        System.out.println("Jerankong HP reduced by " + amount + ". Current HP: " + jerangkongHP);
    }
    
	public int getHP()
	{
		return jerangkongHP;
	}

}