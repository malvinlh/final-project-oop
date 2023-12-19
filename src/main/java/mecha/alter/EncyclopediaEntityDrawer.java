package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JLabel;

public class EncyclopediaEntityDrawer implements MouseListener {
    private GamePanel gamePanel;
    private MainMenu mainMenu;
    private List<BufferedImage> frames;
    private String entityBackground;
    private RoundRectangle2D.Double backButton;
    private Thread gifThread;
    private volatile boolean isRunning = false;
    private long frameDelay = 150; 

    public EncyclopediaEntityDrawer(GamePanel gamePanel, MainMenu mainMenu, List<BufferedImage> frames, String entityBackground) {
        this.gamePanel = gamePanel;
        this.mainMenu = mainMenu;
        this.frames = frames;
        this.entityBackground = entityBackground;
        gamePanel.addMouseListener(this);

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
        gifThread.interrupt();
    }

    public void drawProfile(Graphics g, int x, int y, int targetWidth, int targetHeight, int xOffset, int yOffset) {
        if (mainMenu != null && mainMenu.getBackgroundImage() != null) {
            g.drawImage(mainMenu.getBackgroundImage(), 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), gamePanel);
        }

        if (frames != null && !frames.isEmpty()) {
            int frameIndex = (int) (System.currentTimeMillis() / frameDelay) % frames.size();
            
            frameIndex = (frameIndex + frames.size()) % frames.size();

            BufferedImage currentFrame = frames.get(frameIndex);
            g.drawImage(currentFrame, x, y, targetWidth, targetHeight, null);
        }

        JLabel htmlLabel = new JLabel("<html><div style='text-align: justify; margin-left: 490px'>" + entityBackground + "</div></html>");
        htmlLabel.setBounds(x + xOffset, y + yOffset, xOffset, yOffset);
        htmlLabel.setForeground(Color.WHITE);
        htmlLabel.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 20));

        htmlLabel.print(g);

        g.setColor(Color.WHITE);
        g.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
        g.fillRoundRect((int) backButton.x, (int) backButton.y, (int) backButton.width, (int) backButton.height, 10, 10);
        g.setColor(Color.BLACK);
        g.drawString("Back", (int) (backButton.x + 15), (int) (backButton.y + 29));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MainMenu.mmState != MainMenu.MAINMENUSTATE.ENCYCLOPEDIA || MainMenuEncyclopedia.encState == MainMenuEncyclopedia.ENCYCLOPEDIASTATE.BASE) {
            return;
        }

        int mx = e.getX();
        int my = e.getY();

        if (backButton.contains(mx, my)) {
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