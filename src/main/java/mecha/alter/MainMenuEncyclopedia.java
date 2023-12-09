package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MainMenuEncyclopedia implements MouseListener {

    private GamePanel gp;
    private MainMenu mainMenu;
    private RoundRectangle2D.Double backButton;
    private List<String> entityNames;
    private List<RoundRectangle2D.Double> boundingBoxes;
    private List<EncyclopediaEntityDrawer> profileDrawers;

    public static enum ENCYCLOPEDIASTATE {
        BASE,
        AHOOL,
        BANASPATI,
        GAJAHMADA,
        JERANGKONG
    }

    public static ENCYCLOPEDIASTATE encState = ENCYCLOPEDIASTATE.BASE;

    public MainMenuEncyclopedia(GamePanel gp, MainMenu mainMenu) {
        this.gp = gp;
        this.mainMenu = mainMenu;

        gp.addMouseListener(this);
        backButton = new RoundRectangle2D.Double(25, 25, 100, 40, 10, 10);

        // Initialize entities with dynamically calculated bounding boxes
        entityNames = new ArrayList<>();
        boundingBoxes = new ArrayList<>();
        int y = 125; // Adjust the starting Y coordinate for the list

        String[] names = {"Ahool", "Banaspati", "Gajah Mada", "Jerangkong"};

        for (String name : names) {
            entityNames.add(name);
            int textWidth = calculateTextWidth(name, mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
            boundingBoxes.add(new RoundRectangle2D.Double(100, y, textWidth + 20, 40, 10, 10));
            y += 60; // Adjust the spacing between entities
        }
        
        profileDrawers = new ArrayList<>();
        Image entityGifGajahMada = loadEntityGif("gajah_mada.gif");
        // Load other entity GIFs...
        
        String entityBackgroundGajahMada = "Explanation for Gajah Mada..."; // Adjust the explanation
        // Provide other entity backgrounds...

        profileDrawers.add(new EncyclopediaEntityDrawer(gp, mainMenu, entityGifGajahMada, entityBackgroundGajahMada));
    }
    
    private Image loadEntityGif(String fileName) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/entities/gajah_mada.gif"));
            return icon.getImage();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    
    private int calculateTextWidth(String text, Font font) {
        FontMetrics fontMetrics = gp.getFontMetrics(font);
        return fontMetrics.stringWidth(text);
    }

    public void draw(Graphics g) {
    	if(encState == ENCYCLOPEDIASTATE.BASE)
    	{
	        // Draw the background image
	        if (mainMenu.getBackgroundImage() != null) {
	            g.drawImage(mainMenu.getBackgroundImage(), 0, 0, gp.getWidth(), gp.getHeight(), gp);
	        } else {
	            g.setColor(Color.BLACK);
	            g.fillRect(0, 0, gp.getWidth(), gp.getHeight());
	        }
	
	        // Draw entities as a clickable list
	        g.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
	        g.setColor(Color.WHITE);
	
	        for (int i = 0; i < entityNames.size(); i++) {
	            g.drawString(entityNames.get(i), (int) boundingBoxes.get(i).x, (int) boundingBoxes.get(i).y + 30);
	        }
	
	        // Draw a back button
	        g.setColor(Color.WHITE);
	        g.fillRoundRect((int) backButton.x, (int) backButton.y, (int) backButton.width, (int) backButton.height, 10, 10);
	        g.setColor(Color.BLACK);
	        g.drawString("Back", (int) (backButton.x + 15), (int) (backButton.y + 29));
    	}
    	else
        // Draw entity profiles based on the current state
    		drawEntityProfiles(g);
    }
    
    private void drawEntityProfiles(Graphics g) {
        switch (encState) {
//            case AHOOL:
//                profileDrawers.get(0).drawProfile(g, 300, 100); // Adjust coordinates as needed
//                break;
//            case BANASPATI:
//                profileDrawers.get(1).drawProfile(g, 300, 100); // Adjust coordinates as needed
//                break;
            case GAJAHMADA:
            	profileDrawers.get(0).drawProfile(g, 300, 100);
            	break;
            // Add cases for other entities if needed
            default:
                // Draw a default profile or nothing
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (backButton.contains(mx, my)) {
            System.out.println("Mouse press inside Back Button");
            encState = ENCYCLOPEDIASTATE.BASE; // Go back to the base state
            MainMenu.mmState = MainMenu.MAINMENUSTATE.MAINMENU;
        } else {
            // Handle entity clicks if needed
            for (int i = 0; i < boundingBoxes.size(); i++) {
                if (boundingBoxes.get(i).contains(mx, my)) {
                    // Set the state based on the clicked entity
                    switch (i) {
                        case 0:
                            encState = ENCYCLOPEDIASTATE.AHOOL;
                            break;
                        case 1:
                            encState = ENCYCLOPEDIASTATE.BANASPATI;
                            break;
                        case 2:
                            encState = ENCYCLOPEDIASTATE.GAJAHMADA;
                            break;
                        case 3:
                            encState = ENCYCLOPEDIASTATE.JERANGKONG;
                            break;
                        // Add cases for other entities if needed
                        default:
                            encState = ENCYCLOPEDIASTATE.BASE;
                            break;
                    }
                }
            } 
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

    // Implement other mouse event methods as needed
}