package mecha.alter;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class EncyclopediaEntityDrawer implements MouseListener {
    private GamePanel gamePanel;
    private MainMenu mainMenu;
    private Image gifImage;
    private String entityBackground;
    private RoundRectangle2D.Double backButton;
    private Thread gifThread;
    private volatile boolean isRunning = false;

    public EncyclopediaEntityDrawer(GamePanel gamePanel, MainMenu mainMenu, Image gifImage, String entityBackground) {
        this.gamePanel = gamePanel;
        this.mainMenu = mainMenu;
        this.gifImage = gifImage;
        this.entityBackground = entityBackground;

        initializeBackButton();
        initializeThread();
    }

    private void initializeBackButton() {
        backButton = new RoundRectangle2D.Double(25, 25, 100, 40, 10, 10);
    }

    private void initializeThread() {
        gifThread = new Thread(new Runnable() {
            @Override
            public void run() {
                isRunning = true;

                long targetTime = 1000 / 60; // 60 FPS

                while (isRunning) {
                    long startTime = System.currentTimeMillis();

                    // Repaint the panel using the background image from mainMenu
                    if (gamePanel != null && mainMenu != null && mainMenu.getBackgroundImage() != null) {
                        gamePanel.repaint();
                    }

                    long elapsedTime = System.currentTimeMillis() - startTime;
                    long sleepTime = Math.max(0, targetTime - elapsedTime);

                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void startAnimation() {
        if (!gifThread.isAlive()) {
            initializeThread();
            gifThread.start();
        }
    }

    public void stopAnimation() {
        isRunning = false;
    }

    public void drawProfile(Graphics g, int x, int y) {
        // Draw the background image
        if (mainMenu != null && mainMenu.getBackgroundImage() != null) {
            g.drawImage(mainMenu.getBackgroundImage(), 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), gamePanel);
        }

        // Draw the animated GIF
        g.drawImage(gifImage, x, y, null);

        // Draw the entity background text
        g.setColor(Color.WHITE);
        g.drawString(entityBackground, x, y + gifImage.getHeight(null) + 20);

        // Draw the back button
        g.setColor(Color.WHITE);
        g.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
        g.fillRoundRect((int) backButton.x, (int) backButton.y, (int) backButton.width, (int) backButton.height, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("Back", (int) (backButton.x + 15), (int) (backButton.y + 29));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (backButton.contains(mx, my)) {
            System.out.println("Mouse press inside Back Button");
            stopAnimation();
            MainMenuEncyclopedia.encState = MainMenuEncyclopedia.ENCYCLOPEDIASTATE.BASE; // Go back to the base state
            MainMenu.mmState = MainMenu.MAINMENUSTATE.ENCYCLOPEDIA;
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