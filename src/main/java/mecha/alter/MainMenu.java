package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MainMenu implements MouseListener
{
    private GamePanel gp;
    private BufferedImage backgroundImage;
    public RoundRectangle2D.Double storyModeButton, optionButton, encyclopediaButton, creditsButton;
    private int buttonSpacing = 40;

    public MainMenu(GamePanel gp) 
    {
        this.gp = gp;
        gp.addMouseListener(this);
        storyModeButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        optionButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        encyclopediaButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);
        creditsButton = new RoundRectangle2D.Double(0, 0, 340, 75, 20, 20);

        // Load the background image
        try 
        {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/mainmenu/bg2.png"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) 
    {
        Graphics2D g2d = (Graphics2D) g;

        // Draw the background image
        if (backgroundImage != null) 
        {
            g.drawImage(backgroundImage, 0, 0, gp.getWidth(), gp.getHeight(), gp);
        } 
        else 
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        }

        Font fontTitle = new Font("Serif", Font.BOLD, 100);
        g.setFont(fontTitle);
        g.setColor(Color.WHITE);

        FontMetrics fontMetrics = g.getFontMetrics(fontTitle);
        int titleWidth = fontMetrics.stringWidth("ALTER");
        int titleHeight = fontMetrics.getHeight();

        int xTitle = (gp.getWidth() - titleWidth) / 2;
        int yTitle = (gp.getHeight() - titleHeight) / 8 + fontMetrics.getAscent(); // Adjusted the division factor

        g.drawString("ALTER", xTitle, yTitle);
        
        g2d.setColor(Color.WHITE);
        
        Font fontStory = new Font("Serif", Font.BOLD, 45);
        g.setFont(fontStory);
        FontMetrics fontMetricsStory = g.getFontMetrics(fontStory);
        int storyModeTextWidth = fontMetricsStory.stringWidth("Story Mode");
        int storyModeTextHeight = fontMetricsStory.getHeight();
        g2d.drawString("Story Mode", (int) (xTitle + (storyModeButton.width - storyModeTextWidth) / 2), (int) (yTitle + 30 + titleHeight + buttonSpacing - storyModeTextHeight / 2));
        storyModeButton.setFrame(xTitle, yTitle + titleHeight + buttonSpacing - 50, storyModeButton.width, storyModeButton.height);
        g2d.draw(storyModeButton);
        
        Font fontOption = new Font("Serif", Font.BOLD, 45);
        g.setFont(fontOption);
        FontMetrics fontMetricsOption = g.getFontMetrics(fontOption);
        int optionTextWidth = fontMetricsOption.stringWidth("Option");
        int optionTextHeight = fontMetricsOption.getHeight();
        g2d.drawString("Option", (int) (xTitle - 3 + (optionButton.width - optionTextWidth) / 2), (int) (storyModeButton.y + 19 + storyModeButton.height + buttonSpacing + optionTextHeight / 2));
        optionButton.setFrame(xTitle, storyModeButton.y + storyModeButton.height + buttonSpacing, optionButton.width, optionButton.height);
        g2d.draw(optionButton);

        Font fontEncyclopedia = new Font("Serif", Font.BOLD, 45);
        g.setFont(fontEncyclopedia);
        FontMetrics fontMetricsEncyclopedia = g.getFontMetrics(fontEncyclopedia);
        int encyclopediaTextWidth = fontMetricsEncyclopedia.stringWidth("Encyclopedia");
        int encyclopediaTextHeight = fontMetricsEncyclopedia.getHeight();
        g2d.drawString("Encyclopedia", (int) (xTitle + 2 + (encyclopediaButton.width - encyclopediaTextWidth) / 2), (int) (optionButton.y + 20 + optionButton.height + buttonSpacing + encyclopediaTextHeight / 2));
        encyclopediaButton.setFrame(xTitle, optionButton.y + optionButton.height + buttonSpacing, encyclopediaButton.width, encyclopediaButton.height);
        g2d.draw(encyclopediaButton);

        Font fontCredits = new Font("Serif", Font.BOLD, 45);
        g.setFont(fontCredits);
        FontMetrics fontMetricsCredits = g.getFontMetrics(fontCredits);
        int creditsTextWidth = fontMetricsCredits.stringWidth("Credits");
        int creditsTextHeight = fontMetricsCredits.getHeight();
        g2d.drawString("Credits", (int) (xTitle + (creditsButton.width - creditsTextWidth) / 2), (int) (encyclopediaButton.y + 20 + encyclopediaButton.height + buttonSpacing + creditsTextHeight / 2));
        creditsButton.setFrame(xTitle, encyclopediaButton.y + encyclopediaButton.height + buttonSpacing, creditsButton.width, creditsButton.height);
        g2d.draw(creditsButton);
    }

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		int mx = e.getX();
		int my = e.getY();
		
		if (storyModeButton.contains(mx, my)) 
		{
		    System.out.println("Mouse press inside Story Mode Button");
		    GamePanel.state = GamePanel.STATE.GAME;
		} 
		else if (optionButton.contains(mx, my)) 
		{
		    System.out.println("Mouse press inside Option Button");
		    // ... rest of the code
		} 
		else if (encyclopediaButton.contains(mx, my)) 
		{
		    System.out.println("Mouse press inside Encyclopedia Button");
		    // ... rest of the code
		} 
		else if (creditsButton.contains(mx, my)) 
		{
		    System.out.println("Mouse press inside Credits Button");
		    // ... rest of the code
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