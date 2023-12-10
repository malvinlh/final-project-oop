package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class MainMenuEncyclopedia implements MouseListener {

    private GamePanel gp;
    private MainMenu mainMenu;
    private RoundRectangle2D.Double backButton;
    private List<String> entityNames;
    private List<RoundRectangle2D.Double> boundingBoxes;
    private List<EncyclopediaEntityDrawer> profileDrawers;
    private List<String> entityBackgrounds;
    private List<String> entityGifFileNames; // Add this list for storing GIF file names

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

        entityNames = new ArrayList<>();
        boundingBoxes = new ArrayList<>();
        entityBackgrounds = new ArrayList<>();
        entityGifFileNames = new ArrayList<>(); // Initialize the list

        int y = 125;

        String[] names = {"Ahool", "Banaspati", "Gajah Mada", "Jerangkong"};
        String[] gifFileNames = {"ahool_idle.gif", "banaspati_idle.gif", "gajah_mada_idle.gif", "jerangkong_idle.gif"};

        for (String name : names) {
            entityNames.add(name);
            int textWidth = calculateTextWidth(name, mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
            boundingBoxes.add(new RoundRectangle2D.Double(100, y, textWidth + 20, 40, 10, 10));
            y += 60;
        }

        // Initialize background text and GIF file names for each entity
        entityBackgrounds.add("Ahool dikatakan berbentuk seperti kelelawar dan dideskripsikan\r\n"
        		+ "sebagai ukuran anak berumur satu tahun dengan rentang sayap raksasa\r\n"
        		+ "kira-kira 12 kaki. Ahool juga dideskripsikan berbulu pendek, abu-abu gelap,\r\n"
        		+ "memiliki mata hitam yang besar, lengan pendukung yang rata, sayap dengan\r\n"
        		+ "muka yang rata. Satu laporan mengenai Ahool terjadi pada 1925 ketika naturalis\r\n"
        		+ "Dr. Ernest Bartels, ahli ilmu burung mencatat Bartels tengah menjajaki air\r\n"
        		+ "terjun di lereng Pegunungan Salak ketika kelelawar raksasa tidak dikenal,\r\n"
        		+ "Ahool, terbang tepat di atas kepalanya. Dua tahun kemudian pada tahun 1927,\r\n"
        		+ "sekitar pukul 11.30 WIB, Dr. Ernest Bartels ditemui Ahool lagi, kali ini dia\r\n"
        		+ "berbaring di tempat tidur, di dalam rumah jerami dekat ke Sungai Tjidjenkol\r\n"
        		+ "di Jawa Barat. Ia tiba-tiba mendengar suara yang datang langsung dari\r\n"
        		+ "di atas gubuknya, seperti menangis keras dan jelas tampak mengucapkan A Hool!");
        entityBackgrounds.add("Banaspati merupakan legenda atau cerita di masyarakat mengenai sosok makhluk\r\n"
        		+ "halus yang menjadi bagian dari budaya Indonesia. Sosok Banaspati sering kali\r\n"
        		+ "ditemukan dalam arsitektur candi di pulau Jawa. Mengutip dari jurnal Universitas\r\n"
        		+ "Udayana, pada candi di daerah Jawa Timur, sosok Banaspati atau yang disebut sebagai\r\n"
        		+ "Kala di Jawa Tengah, umumnya dapat ditemukan terpahat pada bagian atas lubang\r\n"
        		+ "pintu masuk di ruang sucinya masing-masing. Sementara itu, dikutip dari repository\r\n"
        		+ "Universitas Katolik Soegijapranata (Unika) Semarang, dalam kepercayaan masyarakat,\r\n"
        		+ "Banaspati dikenal sebagai hantu yang mempunyai elemen api. Banaspati dikenal\r\n"
        		+ "sebagai makhluk mistis yang memiliki kekuatan besar dan biasa ditemukan di dalam\r\n"
        		+ "hutan ataupun digunakan oleh dukun sebagai sarana ilmu hitam.");
        entityBackgrounds.add("Gajah Mada adalah seorang panglima perang dan tokoh yang sangat\r\n"
        		+ "berpengaruh pada zaman kerajaan Majapahit. Menurut berbagai sumber mitologi,\r\n"
        		+ "kitab, dan prasasti dari zaman Jawa Kuno, ia memulai kariernya tahun 1313, dan\r\n"
        		+ "semakin menanjak setelah peristiwa pemberontakan Ra Kuti pada masa\r\n"
        		+ "pemerintahan Sri Jayanagara, yang mengangkatnya sebagai Patih. Ia menjadi\r\n"
        		+ "Mahapatih (Menteri Besar) pada masa Ratu Tribhuwanatunggadewi, dan\r\n"
        		+ "kemudian sebagai Amangkubhumi (Perdana Menteri) yang mengantarkan\r\n"
        		+ "Majapahit ke puncak kejayaannya. Gajah Mada terkenal dengan sumpahnya\r\n"
        		+ "yaitu Sumpah Palapa, yang tercatat di dalam Kitab Pararaton. Sumpah Gajah\r\n"
        		+ "Mada adalah inspirasi dan ”bukti” bahwa bangsa ini dapat bersatu, meskipun\r\n"
        		+ "meliputi wilayah yang luas dan budaya yang berbeda-beda. Dengan demikian,\r\n"
        		+ "Gajah Mada adalah inspirasi bagi revolusi nasional Indonesia dan rasa persatuan\r\n"
        		+ "se-nusantara. Saat ini, Indonesia telah menetapkan Gajah Mada sebagai salah satu\r\n"
        		+ "Pahlawan Nasional dan merupakan simbol nasionalisme dan persatuan Nusantara.");
        entityBackgrounds.add("Background text for Jerangkong...");

        for (String fileName : gifFileNames) {
            entityGifFileNames.add(fileName); // Add each GIF file name to the list
        }

        profileDrawers = new ArrayList<>();
        for (int i = 0; i < entityBackgrounds.size(); i++) {
            Image entityGif = loadEntityGif(entityGifFileNames.get(i));
            profileDrawers.add(new EncyclopediaEntityDrawer(gp, mainMenu, entityGif, entityBackgrounds.get(i)));
        }
    }

    private Image loadEntityGif(String fileName) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/entities/" + fileName));
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
        if (encState == ENCYCLOPEDIASTATE.BASE) {
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
        } else {
            // Draw entity profiles based on the current state
            drawEntityProfiles(g);
        }
    }

    private void drawEntityProfiles(Graphics g) {
        switch (encState) {
            case AHOOL:
                // Customize layout for AHOOL
                profileDrawers.get(0).drawProfile(g, 65, 250, 390, 290, 430, 20);
                break;
            case BANASPATI:
                // Customize layout for BANASPATI
                profileDrawers.get(1).drawProfile(g, -75, 150, 600, 600, 500, 150);
                break;
            case GAJAHMADA:
                // Customize layout for GAJAHMADA
                profileDrawers.get(2).drawProfile(g, 110, 160, 650, 550, 360, 90);
                break;
            case JERANGKONG:
                // Customize layout for JERANGKONG
                profileDrawers.get(3).drawProfile(g, 300, 200, 250, 150, 50, 80);
                break;
            default:
                // Draw a default profile or nothing
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (encState != ENCYCLOPEDIASTATE.BASE) {
            return;
        }
        int mx = e.getX();
        int my = e.getY();

        if (backButton.contains(mx, my)) {
            System.out.println("Mouse press inside Back Button");
            encState = ENCYCLOPEDIASTATE.BASE;
            MainMenu.mmState = MainMenu.MAINMENUSTATE.MAINMENU;
        } else {
            for (int i = 0; i < boundingBoxes.size(); i++) {
                if (boundingBoxes.get(i).contains(mx, my)) {
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

    // Other mouse event methods...
}