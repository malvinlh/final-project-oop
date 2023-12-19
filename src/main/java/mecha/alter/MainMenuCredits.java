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
	      Font font30 = new Font("Arial", Font.BOLD, 30);
	      Font font20 = new Font("Arial", Font.BOLD, 20);;
	
	      g.setFont(font30);
	      g.setColor(Color.WHITE);
	      String text1 = "Malvin Leonardo Hartanto (5025221033)";
	      String text2 = "Surya Fadli Alamsyah (5025221059)";
	      int x1 = (1525 - g.getFontMetrics(font30).stringWidth(text1)) / 2;
	      int x2 = (1525 - g.getFontMetrics(font30).stringWidth(text2)) / 2;
	      g.drawString(text1, x1, 100);
	      g.drawString(text2, x2, 150);
	
	      g.setFont(font20);
	      g.setColor(Color.WHITE);
	      String text3 = "SPECIAL THANKS TO:";
	      int x3 = (1525 - g.getFontMetrics(font20).stringWidth(text3)) / 2;
	      g.drawString(text3, x3, 220);
	      
	      String text4 = "ART AND ANIMATION";
	      int x4 = (1525 - g.getFontMetrics(font20).stringWidth(text4)) / 2;
	      g.drawString(text4, x4, 265);
	      
	      // Ahool Sprites
	      String text5 = "https://rentro-ghost.itch.io/bat-sprites";
	      int x5 = (1525 - g.getFontMetrics(font20).stringWidth(text5)) / 2;
	      g.drawString(text5, x5, 290);
	      
	      // Banaspati Sprites
	      String text6 = "https://msfrantz.itch.io/free-fire-ball-pixel-art";
	      int x6 = (1525 - g.getFontMetrics(font20).stringWidth(text6)) / 2;
	      g.drawString(text6, x6, 315);
	      
	      // Jerangkong Sprites
	      String text7 = "https://yggdrazzil.itch.io/skeleton";
	      int x7 = (1525 - g.getFontMetrics(font20).stringWidth(text7)) / 2;
	      g.drawString(text7, x7, 340);
	      
	      // MC Sprites
	      String text8 = "https://cuddle-bug.itch.io/apocalypse";
	      int x8 = (1525 - g.getFontMetrics(font20).stringWidth(text8)) / 2;
	      g.drawString(text8, x8, 365);
	      
	      // Battle Panel
	      String text9 = "https://karwisch.itch.io/pxui-basic";
	      int x9 = (1525 - g.getFontMetrics(font20).stringWidth(text9)) / 2;
	      g.drawString(text9, x9, 390);
	      
	      // Gajah Mada Sprites
	      String text10 = "https://www.deviantart.com/irzaqi/art/Gajah-Mada-Standing-Sprite-141609451";
	      int x10 = (1525 - g.getFontMetrics(font20).stringWidth(text10)) / 2;
	      g.drawString(text10, x10, 415);
	      
	      // Tileset
	      String text11 = "https://schwarnhild.itch.io/volcanoe-tileset-and-asset-pack-32x32-pixels";
	      int x11 = (1525 - g.getFontMetrics(font20).stringWidth(text11)) / 2;
	      g.drawString(text11, x11, 440);
	      
	      // Battle BG
	      String text12 = "https://i.pinimg.com/originals/c8/a1/b2/c8a1b2793cb2b0f4e5a120cbe22024eb.png";
	      int x12 = (1525 - g.getFontMetrics(font20).stringWidth(text12)) / 2;
	      g.drawString(text12, x12, 465);
	      
	      // Main Menu BG
	      String text13 = "https://www.hdwallpapers.in/unique_red_and_black_background_hd_red_aesthetic-wallpapers.html";
	      int x13 = (1525 - g.getFontMetrics(font20).stringWidth(text13)) / 2;
	      g.drawString(text13, x13, 490);
	      
	      String text14 = "MUSIC AND SOUND EFFECT";
	      int x14 = (1525 - g.getFontMetrics(font20).stringWidth(text14)) / 2;
	      g.drawString(text14, x14, 540);
	      
	      // World Map BGM
	      String text15 = "https://sonatina.itch.io/letsadventure" ;
	      int x15 = (1525 - g.getFontMetrics(font20).stringWidth(text15)) / 2;
	      g.drawString(text15, x15, 570);

	      // Main Menu BGM
	      String text16 = "https://youtu.be/YndrZcmKK6o?si=Cvh9i_ZzPziJ7VM7";
	      int x16 = (1525 - g.getFontMetrics(font20).stringWidth(text16)) / 2;
	      g.drawString(text16, x16, 600);
	      
	      String text17 = "OUR LECTURER";
	      int x17 = (1525 - g.getFontMetrics(font20).stringWidth(text17)) / 2;
	      g.drawString(text17, x17, 650);
	      
	      String text18 = "Rizky Januar Akbar, S.Kom., M.Eng.";
	      int x18 = (1525 - g.getFontMetrics(font20).stringWidth(text18)) / 2;
	      g.drawString(text18, x18, 680);
	      
	      // Draw a back button
	      g.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
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