package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class MainMenuCredits implements MouseListener 
{
	  private GamePanel gamePanel;
	  private MainMenu mainMenu;
	  private RoundRectangle2D.Double backButton;
	  
	  public MainMenuCredits(GamePanel gamePanel, MainMenu mainMenu) 
	  {
	      this.gamePanel = gamePanel;
	      this.mainMenu = mainMenu;
	
	      gamePanel.addMouseListener(this);
	      backButton = new RoundRectangle2D.Double(25, 25, 100, 40, 10, 10);
	  }
	  
	  public void draw(Graphics g)
	  {
		  if (mainMenu.getBackgroundImage() != null) 
		  {
              g.drawImage(mainMenu.getBackgroundImage(), 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), gamePanel);
          } 
		  else 
		  {
              g.setColor(Color.BLACK);
              g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
          }
          
	      // Font settings
	      Font font30 = mainMenu.getImprisha().deriveFont(Font.BOLD, 30);
	      Font font20 = mainMenu.getImprisha().deriveFont(Font.BOLD, 20);
	
	      // Set font and color for the first two strings
	      g.setFont(font30);
	      g.setColor(Color.WHITE);
	      
	      // Strings to be centered
	      String text1 = "Malvin Leonardo Hartanto (5025221033)";
	      String text2 = "Surya Fadli Alamsyah (5025221059)";
	
	      // Calculate x-coordinate for centering
	      int x1 = (1525 - g.getFontMetrics(font30).stringWidth(text1)) / 2;
	      int x2 = (1525 - g.getFontMetrics(font30).stringWidth(text2)) / 2;
	
	      // Draw the centered strings
	      g.drawString(text1, x1, 100);
	      g.drawString(text2, x2, 150);
	
	      // Set font and color for the third string
	      g.setFont(font20);
	      g.setColor(Color.WHITE);
	
	      // String to be centered
	      String text3 = "Special thanks to:";
	
	      // Calculate x-coordinate for centering
	      int x3 = (1525 - g.getFontMetrics(font20).stringWidth(text3)) / 2;
	
	      // Draw the centered string
	      g.drawString(text3, x3, 225);

	      
	      // Draw a back button
	      g.setFont(font30);
	      g.fillRoundRect((int) backButton.x, (int) backButton.y, (int) backButton.width, (int) backButton.height, 10, 10);
          g.setColor(Color.BLACK);
          g.drawString("Back", (int) (backButton.x + 15), (int) (backButton.y + 29));
	  }

	@Override
	public void mouseClicked(MouseEvent e) 
	{   
		if(MainMenu.mmState != MainMenu.MAINMENUSTATE.CREDITS)
		{
			return;
		}
		
        int mx = e.getX();
        int my = e.getY();

        if (backButton.contains(mx, my)) 
        {
        	mainMenu.playSE(1);
        	System.out.println("Mouse click inside Credits Back Button");
            MainMenu.mmState = MainMenu.MAINMENUSTATE.MAINMENU;
        }		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	  
}