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
	private GameManager gameManager;
    private GamePanel gamePanel;
    private MainMenuEncyclopedia mmEn;
    private MainMenuCredits mmCr;
    private BufferedImage backgroundImage;
    public RoundRectangle2D.Double storyModeButton, optionButton, encyclopediaButton, creditsButton;
    private int buttonSpacing = 40;
    
    Font imprisha;
    
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
        gameManager = new GameManager(gamePanel, this);
        storyModeButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        optionButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        encyclopediaButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        creditsButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);

        // Load the background image
        try 
        {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/mainmenu/main_menu_background.png"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
        sceneSetup();
    }
    
    public void sceneSetup()
    {
    	playMusic(0);
    }
    
    public BufferedImage getBackgroundImage() 
    {
        return this.backgroundImage;
    }
    
    public Font getImprisha()
    {
        if (imprisha == null) 
        {
            try 
            {
                InputStream imp = getClass().getResourceAsStream("/fonts/IMPRISHA.TTF");
                imprisha = Font.createFont(Font.TRUETYPE_FONT, imp);
            } 
            catch (FontFormatException | IOException e) 
            {
                e.printStackTrace();
            }
        }

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
	        int yTitle = (gamePanel.getHeight() - titleHeight) / 8 + g.getFontMetrics().getAscent();
	
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
	        g2d.drawString("Encyclopedia", (int) (xTitle + 21 + (encyclopediaButton.width - encyclopediaTextWidth) / 2), (int) (optionButton.y + 25 + optionButton.height + buttonSpacing + encyclopediaTextHeight / 2));
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
    
    public void playMusic(int i) 
    {
        gameManager.setSounds(i, true);
        gameManager.playSounds();
    }
    
    public void playSE(int i) 
    {
        gameManager.setSounds(i, false);
        gameManager.playSounds();
    }

    public void stopMusic() 
    {
        System.out.println("Stop Music");
        gameManager.stopMusic();
    }

	@Override
	public void mouseClicked(MouseEvent e) 
	{
	    if (mmState != MAINMENUSTATE.MAINMENU || GamePanel.state != GamePanel.GAMESTATE.MAINMENU) 
	    {
	        return;
	    }
	    
		int mx = e.getX();
		int my = e.getY();
		
		if (storyModeButton.contains(mx, my)) 
		{
		    System.out.println("Mouse click inside Story Mode Button");
		    playSE(1);
		    stopMusic();
		    GamePanel.state = GamePanel.GAMESTATE.GAME;
		} 
		else if (optionButton.contains(mx, my)) 
		{
			playSE(1);
		    System.out.println("Mouse click inside Option Button");
		} 
		else if (encyclopediaButton.contains(mx, my)) 
		{
			playSE(1);
		    System.out.println("Mouse click inside Encyclopedia Button");
		    mmState = MAINMENUSTATE.ENCYCLOPEDIA;
		} 
		else if (creditsButton.contains(mx, my)) 
		{
			playSE(1);
		    System.out.println("Mouse click inside Credits Button");
		    mmState = MAINMENUSTATE.CREDITS;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
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