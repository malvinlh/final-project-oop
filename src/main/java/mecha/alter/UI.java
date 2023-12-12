package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI 
{
	
	GamePanel gp;
	Font arial_40;
	public String message = "";
	public boolean messageOn = false;
	int messageCounter = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		g2.setFont(arial_40);
		g2.setColor(Color.WHITE);
		g2.drawString("Key = " + gp.player.hasKey, 50, 50);
		
		if(messageOn == true) {
			
			
			
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.drawString(message, gp.tileSize/2, gp.tileSize *5);
			
			messageCounter++;
			
			if(messageCounter > 180) {
				messageCounter = 0;
				messageOn = false;
			}
		}
	}
}