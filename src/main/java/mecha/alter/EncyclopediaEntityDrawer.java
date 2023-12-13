package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;

public class EncyclopediaEntityDrawer implements MouseListener 
{
    private GamePanel gamePanel;
    private MainMenu mainMenu;
    private Image gifImage;
    private String entityBackground;
    private RoundRectangle2D.Double backButton;
    private Thread gifThread;
    private volatile boolean isRunning = false;

    public EncyclopediaEntityDrawer(GamePanel gamePanel, MainMenu mainMenu, Image gifImage, String entityBackground) 
    {
        this.gamePanel = gamePanel;
        this.mainMenu = mainMenu;
        this.gifImage = gifImage;
        this.entityBackground = entityBackground;
        gamePanel.addMouseListener(this);

        initializeBackButton();
        initializeThread();
    }

    private void initializeBackButton() 
    {
        backButton = new RoundRectangle2D.Double(25, 25, 100, 40, 10, 10);
    }

    private void initializeThread() 
    {
        gifThread = new Thread(new Runnable() 
        {
            @Override
            public void run() 
            {
                isRunning = true;

                long targetTime = 1000 / 60; // 60 FPS

                while (isRunning) 
                {
                    long startTime = System.currentTimeMillis();

                    if (gamePanel != null && mainMenu != null && mainMenu.getBackgroundImage() != null) 
                    {
                        gamePanel.repaint();
                    }

                    long elapsedTime = System.currentTimeMillis() - startTime;
                    long sleepTime = Math.max(0, targetTime - elapsedTime);

                    try 
                    {
                        Thread.sleep(sleepTime);
                    } 
                    catch (InterruptedException e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void startAnimation() 
    {
        if (!gifThread.isAlive()) 
        {
            initializeThread();
            gifThread.start();
        }
    }

    public void stopAnimation() 
    {
        isRunning = false;
    }

    public void drawProfile(Graphics g, int x, int y, int targetWidth, int targetHeight, int xOffset, int yOffset) 
    {
        // Draw the background image
        if (mainMenu != null && mainMenu.getBackgroundImage() != null) 
        {
            g.drawImage(mainMenu.getBackgroundImage(), 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), gamePanel);
        }

        // Scale the animated GIF
        if (gifImage != null) 
        {
            int scaledWidth = targetWidth;
            int scaledHeight = targetHeight;

            // Calculate the aspect ratio to maintain proportions
            double aspectRatio = (double) gifImage.getWidth(null) / gifImage.getHeight(null);

            if (aspectRatio > 1) 
            {
                scaledHeight = (int) (scaledWidth / aspectRatio);
            } 
            else 
            {
                scaledWidth = (int) (scaledHeight * aspectRatio);
            }

            g.drawImage(gifImage, x, y, scaledWidth, scaledHeight, null);
        }

        // Create a JLabel for displaying justified HTML text
        JLabel htmlLabel = new JLabel("<html><div style='text-align: justify; margin-left: 490px'>" + entityBackground + "</div></html>");
        htmlLabel.setBounds(x + xOffset, y + yOffset, xOffset, yOffset);
        htmlLabel.setForeground(Color.WHITE);
        htmlLabel.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 20));

        // Draw the JLabel
        htmlLabel.print(g);

        // Draw the back button
        g.setColor(Color.WHITE);
        g.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
        g.fillRoundRect((int) backButton.x, (int) backButton.y, (int) backButton.width, (int) backButton.height, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("Back", (int) (backButton.x + 15), (int) (backButton.y + 29));
    }


    private void drawMultilineText(Graphics g, String text, int x, int y, int lineSpacing) 
    {
        String[] lines = text.split("\n");
        FontMetrics fontMetrics = g.getFontMetrics();

        for (int i = 0; i < lines.length; i++) 
        {
            int lineHeight = fontMetrics.getHeight() + lineSpacing;
            int lineY = y + i * lineHeight;
            g.drawString(lines[i], x, lineY);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
    	if(MainMenu.mmState != MainMenu.MAINMENUSTATE.ENCYCLOPEDIA || MainMenuEncyclopedia.encState == MainMenuEncyclopedia.ENCYCLOPEDIASTATE.BASE)
    	{
    		return;
    	}
    	
        int mx = e.getX();
        int my = e.getY();

        if (backButton.contains(mx, my)) 
        {
        	mainMenu.playSE(1);
            System.out.println("Mouse click inside Encylopedia Entity Drawer Back Button");
            stopAnimation();
            MainMenuEncyclopedia.encState = MainMenuEncyclopedia.ENCYCLOPEDIASTATE.BASE;
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