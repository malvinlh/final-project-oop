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

public class MainMenuEncyclopedia implements MouseListener 
{

    private GamePanel gamePanel;
    private MainMenu mainMenu;
    private RoundRectangle2D.Double backButton;
    private List<String> entityNames;
    private List<RoundRectangle2D.Double> boundingBoxes;
    private List<EncyclopediaEntityDrawer> profileDrawers;
    private List<String> entityBackgrounds;
    private List<String> entityGifFileNames;

    public static enum ENCYCLOPEDIASTATE {
        BASE,
        AHOOL,
        BANASPATI,
        GAJAHMADA,
        JERANGKONG
    }

    public static ENCYCLOPEDIASTATE encState = ENCYCLOPEDIASTATE.BASE;

    public MainMenuEncyclopedia(GamePanel gamePanel, MainMenu mainMenu) 
    {
        this.gamePanel = gamePanel;
        this.mainMenu = mainMenu;

        gamePanel.addMouseListener(this);
        backButton = new RoundRectangle2D.Double(25, 25, 100, 40, 10, 10);

        entityNames = new ArrayList<>();
        boundingBoxes = new ArrayList<>();
        entityBackgrounds = new ArrayList<>();
        entityGifFileNames = new ArrayList<>();

        int y = 125;

        String[] names = {"Ahool", "Banaspati", "Gajah Mada", "Jerangkong"};
        String[] gifFileNames = {"ahool_idle.gif", "banaspati_idle.gif", "gajah_mada_idle.gif", "jerangkong_idle.gif"};

        for (String name : names) 
        {
            entityNames.add(name);
            int textWidth = calculateTextWidth(name, mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
            boundingBoxes.add(new RoundRectangle2D.Double(100, y, textWidth + 20, 40, 10, 10));
            y += 60;
        }

        // Initialize background text and GIF file names for each entity
        entityBackgrounds.add("Ahool dikatakan berbentuk seperti kelelawar dan dideskripsikan\n"
        		+ "sebagai ukuran anak berumur satu tahun dengan rentang sayap raksasa\n"
        		+ "kira-kira 12 kaki. Ahool juga dideskripsikan berbulu pendek, abu-abu gelap,\n"
        		+ "memiliki mata hitam yang besar, lengan pendukung yang rata, sayap dengan\n"
        		+ "muka yang rata. Satu laporan mengenai Ahool terjadi pada 1925 ketika naturalis\n"
        		+ "Dr. Ernest Bartels, ahli ilmu burung mencatat Bartels tengah menjajaki air\n"
        		+ "terjun di lereng Pegunungan Salak ketika kelelawar raksasa tidak dikenal,\n"
        		+ "Ahool, terbang tepat di atas kepalanya. Dua tahun kemudian pada tahun 1927,\n"
        		+ "sekitar pukul 11.30 WIB, Dr. Ernest Bartels ditemui Ahool lagi, kali ini dia\n"
        		+ "berbaring di tempat tidur, di dalam rumah jerami dekat ke Sungai Tjidjenkol\n"
        		+ "di Jawa Barat. Ia tiba-tiba mendengar suara yang datang langsung dari\n"
        		+ "atas gubuknya, seperti menangis keras dan jelas tampak mengucapkan A Hool!");
        entityBackgrounds.add("Banaspati merupakan legenda atau cerita di masyarakat mengenai sosok makhluk\n"
        		+ "halus yang menjadi bagian dari budaya Indonesia. Sosok Banaspati sering kali\n"
        		+ "ditemukan dalam arsitektur candi di pulau Jawa. Mengutip dari jurnal Universitas\n"
        		+ "Udayana, pada candi di daerah Jawa Timur, sosok Banaspati atau yang disebut sebagai\n"
        		+ "Kala di Jawa Tengah, umumnya dapat ditemukan terpahat pada bagian atas lubang\n"
        		+ "pintu masuk di ruang sucinya masing-masing. Sementara itu, dikutip dari repository\n"
        		+ "Universitas Katolik Soegijapranata (Unika) Semarang, dalam kepercayaan masyarakat,\n"
        		+ "Banaspati dikenal sebagai hantu yang mempunyai elemen api. Banaspati dikenal\n"
        		+ "sebagai makhluk mistis yang memiliki kekuatan besar dan biasa ditemukan di dalam\n"
        		+ "hutan ataupun digunakan oleh dukun sebagai sarana ilmu hitam.");
        entityBackgrounds.add("Gajah Mada adalah seorang panglima perang dan tokoh yang sangat\n"
        		+ "berpengaruh pada zaman kerajaan Majapahit. Menurut berbagai sumber mitologi,\n"
        		+ "kitab, dan prasasti dari zaman Jawa Kuno, ia memulai kariernya tahun 1313, dan\n"
        		+ "semakin menanjak setelah peristiwa pemberontakan Ra Kuti pada masa\n"
        		+ "pemerintahan Sri Jayanagara, yang mengangkatnya sebagai Patih. Ia menjadi\n"
        		+ "Mahapatih (Menteri Besar) pada masa Ratu Tribhuwanatunggadewi, dan\n"
        		+ "kemudian sebagai Amangkubhumi (Perdana Menteri) yang mengantarkan\n"
        		+ "Majapahit ke puncak kejayaannya. Gajah Mada terkenal dengan sumpahnya\n"
        		+ "yaitu Sumpah Palapa, yang tercatat di dalam Kitab Pararaton. Sumpah Gajah\n"
        		+ "Mada adalah inspirasi dan ”bukti” bahwa bangsa ini dapat bersatu, meskipun\n"
        		+ "meliputi wilayah yang luas dan budaya yang berbeda-beda. Dengan demikian,\n"
        		+ "Gajah Mada adalah inspirasi bagi revolusi nasional Indonesia dan rasa persatuan\n"
        		+ "se-nusantara. Saat ini, Indonesia telah menetapkan Gajah Mada sebagai salah satu\n"
        		+ "Pahlawan Nasional dan merupakan simbol nasionalisme dan persatuan Nusantara.");
        entityBackgrounds.add("Jerangkong merupakan salah satu hantu yang berasal dari tanah Jawa. Masyarakat\n"
        		+ "Jawa percaya kalau jerangkong adalah arwah orang yang sudah meninggal saat dia\n"
        		+ "masih hidup suka mencuri telur. Hingga mati pun ia akan tetap mencuri telur. dan telur\n"
        		+ "siapapun bisa diambil. Namun tidak ada cerita jelas mengapa hantu jerangkong bisa\n"
        		+ "gentayangan. Hantu jerangkong digambarkan sebagai hantu yang berbentuk tengkorak,\n"
        		+ "tanpa ada kulit ataupun daging. Tidak ada penjelasan pasti mengapa hantu jerangkong\n"
        		+ "bisa berwujud tulang belulang saja tanpa adanya daging dan kulit. Pencurian yang\n"
        		+ "dilakukan oleh hantu jerangkong bukan seperti tuyul yang mencuri uang dengan membawa\n"
        		+ "pergi uangnya. Hantu jerangkong yang sudah mencuri telur tersebut akan memakan telurnya\n"
        		+ "di tempat. hantu jerangkong pun memakan telur tersebut bukan dengan cara memecahkan\n"
        		+ "telur, namun dia hanya akan memakan isinya tanpa memecahkan telurnya. Sehingga ketika\n"
        		+ "kalian mendapati telur yang kalian miliki di rumah tidak memiliki isinya, bisa jadi telur\n"
        		+ "kalian sudah dimakan oleh hantu jerangkong.\n");

        for (String fileName : gifFileNames) 
        {
            entityGifFileNames.add(fileName); // Add each GIF file name to the list
        }

        profileDrawers = new ArrayList<>();
        for (int i = 0; i < entityBackgrounds.size(); i++) 
        {
            Image entityGif = loadEntityGif(entityGifFileNames.get(i));
            profileDrawers.add(new EncyclopediaEntityDrawer(gamePanel, mainMenu, entityGif, entityBackgrounds.get(i)));
        }
    }

    private Image loadEntityGif(String fileName) 
    {
        try 
        {
            ImageIcon icon = new ImageIcon(getClass().getResource("/entities/" + fileName));
            return icon.getImage();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            return null;
        }
    }

    private int calculateTextWidth(String text, Font font) 
    {
        FontMetrics fontMetrics = gamePanel.getFontMetrics(font);
        return fontMetrics.stringWidth(text);
    }

    public void draw(Graphics g) 
    {
        if (encState == ENCYCLOPEDIASTATE.BASE) 
        {
            // Draw the background image
            if (mainMenu.getBackgroundImage() != null) 
            {
                g.drawImage(mainMenu.getBackgroundImage(), 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), gamePanel);
            } 
            else 
            {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
            }

            // Draw entities as a clickable list
            g.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
            g.setColor(Color.WHITE);

            for (int i = 0; i < entityNames.size(); i++) 
            {
                g.drawString(entityNames.get(i), (int) boundingBoxes.get(i).x, (int) boundingBoxes.get(i).y + 30);
            }

            // Draw a back button
            g.setColor(Color.WHITE);
            g.fillRoundRect((int) backButton.x, (int) backButton.y, (int) backButton.width, (int) backButton.height, 10, 10);
            g.setColor(Color.BLACK);
            g.drawString("Back", (int) (backButton.x + 15), (int) (backButton.y + 29));
        } 
        else
        {
            drawEntityProfiles(g);
        }
    }

    private void drawEntityProfiles(Graphics g) 
    {
        switch (encState) 
        {
            case AHOOL:
                profileDrawers.get(0).drawProfile(g, 90, 210, 500, 400, 580, 65);
                break;
            case BANASPATI:
                profileDrawers.get(1).drawProfile(g, 120, 230, 400, 400, 470, 75);
                break;
            case GAJAHMADA:
                profileDrawers.get(2).drawProfile(g, 225, 160, 650, 550, 395, 90);
                break;
            case JERANGKONG:
                profileDrawers.get(3).drawProfile(g, 250, 200, 550, 450, 330, 75);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if (MainMenu.mmState != MainMenu.MAINMENUSTATE.ENCYCLOPEDIA || encState != ENCYCLOPEDIASTATE.BASE) 
        {
            return;
        }
        
        int mx = e.getX();
        int my = e.getY();

        if (backButton.contains(mx, my)) 
        {
        	mainMenu.playSE(1);
            System.out.println("Mouse click inside Encyclopedia Back Button");
            encState = ENCYCLOPEDIASTATE.BASE;
            MainMenu.mmState = MainMenu.MAINMENUSTATE.MAINMENU;
        } 
        else 
        {
            for (int i = 0; i < boundingBoxes.size(); i++) 
            {
                if (boundingBoxes.get(i).contains(mx, my)) 
                {
                    switch (i) 
                    {
                        case 0:
                        	System.out.println("Mouse click inside Entity Ahool");
                        	mainMenu.playSE(1);
                            encState = ENCYCLOPEDIASTATE.AHOOL;
                            break;
                        case 1:
                        	System.out.println("Mouse click inside Entity Banaspati");
                        	mainMenu.playSE(1);
                            encState = ENCYCLOPEDIASTATE.BANASPATI;
                            break;
                        case 2:
                        	System.out.println("Mouse click inside Entity Gajah Mada");
                        	mainMenu.playSE(1);
                            encState = ENCYCLOPEDIASTATE.GAJAHMADA;
                            break;
                        case 3:
                        	System.out.println("Mouse click inside Entity Jerangkong");
                        	mainMenu.playSE(1);
                            encState = ENCYCLOPEDIASTATE.JERANGKONG;
                            break;
                        default:
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