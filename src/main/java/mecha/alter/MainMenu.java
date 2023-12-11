package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class MainMenu implements MouseListener
{
    private GamePanel gamePanel;
    private MainMenuEncyclopedia mmEn;
    private MainMenuCredits mmCr;
    Font imprisha, maruMonica;
    private BufferedImage backgroundImage;
    public RoundRectangle2D.Double storyModeButton, optionButton, encyclopediaButton, creditsButton;
    private int buttonSpacing = 40;
    
    public static enum MAINMENUSTATE
    {
    	MAINMENU,
    	OPTIONS,
    	ENCYCLOPEDIA,
    	CREDITS
    };
    
    public static MAINMENUSTATE mmState = MAINMENUSTATE.MAINMENU;

    public MainMenu(GamePanel gamePanel) 
    {
        this.gamePanel = gamePanel;
        gamePanel.addMouseListener(this);
        mmEn = new MainMenuEncyclopedia(gamePanel, this);
        mmCr = new MainMenuCredits(gamePanel, this);
        storyModeButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        optionButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        encyclopediaButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        creditsButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);

        // Load the background image
        try 
        {
        	InputStream maruMon = getClass().getResourceAsStream("/fonts/x12y16pxMaruMonica.ttf");
        	maruMonica = Font.createFont(Font.TRUETYPE_FONT, maruMon);
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/mainmenu/bg2.png"));
        } 
        catch (FontFormatException e)
        {
        	e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getBackgroundImage() 
    {
        return this.backgroundImage;
    }
    
    public Font getImprisha()
    {
        if (imprisha == null) {
            // Load the Imprisha font if it hasn't been loaded yet.
            try {
                InputStream imp = getClass().getResourceAsStream("/fonts/IMPRISHA.TTF");
                imprisha = Font.createFont(Font.TRUETYPE_FONT, imp);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
                // Handle the exception as needed (e.g., log the error, use a default font, etc.).
            }
        }

        // Return the Imprisha font or a default font if loading failed.
        return imprisha;
    }

    public void draw(Graphics g) 
    {
    	if(mmState == MAINMENUSTATE.MAINMENU)
    	{
	        Graphics2D g2d = (Graphics2D) g;
	
	        // Draw the background image
	        if (backgroundImage != null) 
	        {
	            g.drawImage(backgroundImage, 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), gamePanel);
	        } 
	        else 
	        {
	            g.setColor(Color.BLACK);
	            g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
	        }
	
	        g.setFont(imprisha.deriveFont(Font.BOLD, 100));
	        g.setColor(Color.WHITE);
	
	        int titleWidth = g.getFontMetrics().stringWidth("ALTER");
	        int titleHeight = g.getFontMetrics().getHeight();
	
	        int xTitle = (gamePanel.getWidth() - titleWidth) / 2;
	        int yTitle = (gamePanel.getHeight() - titleHeight) / 8 + g.getFontMetrics().getAscent(); // Adjusted the division factor
	
	        g.drawString("ALTER", xTitle, yTitle);
	        
	        g2d.setColor(Color.WHITE);
	        
	        Font fontStory = new Font("Arial", Font.BOLD, 45);
	        g.setFont(fontStory);
	        FontMetrics fontMetricsStory = g.getFontMetrics(fontStory);
	        int storyModeTextWidth = fontMetricsStory.stringWidth("Story Mode");
	        int storyModeTextHeight = fontMetricsStory.getHeight();
	        g2d.drawString("Story Mode", (int) (xTitle + 20 + (storyModeButton.width - storyModeTextWidth) / 2), (int) (yTitle + 30 + titleHeight + buttonSpacing - storyModeTextHeight / 2));
	        storyModeButton.setFrame(xTitle + 20, yTitle + titleHeight + buttonSpacing - 50, storyModeButton.width, storyModeButton.height);
	        g2d.draw(storyModeButton);
	        
	        Font fontOption = new Font("Arial", Font.BOLD, 45);
	        g.setFont(fontOption);
	        FontMetrics fontMetricsOption = g.getFontMetrics(fontOption);
	        int optionTextWidth = fontMetricsOption.stringWidth("Option");
	        int optionTextHeight = fontMetricsOption.getHeight();
	        g2d.drawString("Options", (int) (xTitle + 8 + (optionButton.width - optionTextWidth) / 2), (int) (storyModeButton.y + 24 + storyModeButton.height + buttonSpacing + optionTextHeight / 2));
	        optionButton.setFrame(xTitle + 20, storyModeButton.y + storyModeButton.height + buttonSpacing, optionButton.width, optionButton.height);
	        g2d.draw(optionButton);
	
	        Font fontEncyclopedia = new Font("Arial", Font.BOLD, 45);
	        g.setFont(fontEncyclopedia);
	        FontMetrics fontMetricsEncyclopedia = g.getFontMetrics(fontEncyclopedia);
	        int encyclopediaTextWidth = fontMetricsEncyclopedia.stringWidth("Encyclopedia");
	        int encyclopediaTextHeight = fontMetricsEncyclopedia.getHeight();
	        g2d.drawString("Encyclopedia", (int) (xTitle + 19 + (encyclopediaButton.width - encyclopediaTextWidth) / 2), (int) (optionButton.y + 25 + optionButton.height + buttonSpacing + encyclopediaTextHeight / 2));
	        encyclopediaButton.setFrame(xTitle + 20, optionButton.y + optionButton.height + buttonSpacing, encyclopediaButton.width, encyclopediaButton.height);
	        g2d.draw(encyclopediaButton);
	
	        Font fontCredits = new Font("Arial", Font.BOLD, 45);
	        g.setFont(fontCredits);
	        FontMetrics fontMetricsCredits = g.getFontMetrics(fontCredits);
	        int creditsTextWidth = fontMetricsCredits.stringWidth("Credits");
	        int creditsTextHeight = fontMetricsCredits.getHeight();
	        g2d.drawString("Credits", (int) (xTitle + 19 + (creditsButton.width - creditsTextWidth) / 2), (int) (encyclopediaButton.y + 25 + encyclopediaButton.height + buttonSpacing + creditsTextHeight / 2));
	        creditsButton.setFrame(xTitle + 20, encyclopediaButton.y + encyclopediaButton.height + buttonSpacing, creditsButton.width, creditsButton.height);
	        g2d.draw(creditsButton);
    	}
    	else if(mmState == MAINMENUSTATE.ENCYCLOPEDIA)
    	{
    		mmEn.draw(g);
    	}
    	else if(mmState == MAINMENUSTATE.CREDITS)
    	{
    		mmCr.draw(g);
    	}
    }

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
	    if (mmState != MAINMENUSTATE.MAINMENU) {
	        // If the state is in Encyclopedia, return without processing the mouse event
	        return;
	    }
	    
		int mx = e.getX();
		int my = e.getY();
		
		if (storyModeButton.contains(mx, my)) 
		{
		    System.out.println("Mouse press inside Story Mode Button");
		    GamePanel.state = GamePanel.GAMESTATE.GAME;
		} 
		else if (optionButton.contains(mx, my)) 
		{
		    System.out.println("Mouse press inside Option Button");

		} 
		else if (encyclopediaButton.contains(mx, my)) 
		{
		    System.out.println("Mouse press inside Encyclopedia Button");
		    if(mmState == MAINMENUSTATE.MAINMENU)
		    {
		    	mmState = MAINMENUSTATE.ENCYCLOPEDIA;
		    }		
		} 
		else if (creditsButton.contains(mx, my)) 
		{
		    System.out.println("Mouse press inside Credits Button");
		    if(mmState == MAINMENUSTATE.MAINMENU)
		    {
		    	mmState = MAINMENUSTATE.CREDITS;
		    }
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}