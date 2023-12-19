package mecha.alter;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class MainMenuEncyclopedia implements MouseListener {

    private GamePanel gamePanel;
    private MainMenu mainMenu;
    private RoundRectangle2D.Double backButton;
    private List<String> entityNames;
    private List<RoundRectangle2D.Double> boundingBoxes;
    private List<String> entityBackgrounds;
    private String[] entityFilePath;
    private List<List<BufferedImage>> entityIdleFrames; 
    private List<EncyclopediaEntityDrawer> profileDrawers;

    public static enum ENCYCLOPEDIASTATE {
        BASE,
        AHOOL,
        BANASPATI,
        GAJAHMADA,
        JERANGKONG
    }

    public static ENCYCLOPEDIASTATE encState = ENCYCLOPEDIASTATE.BASE;

    public MainMenuEncyclopedia(GamePanel gamePanel, MainMenu mainMenu) {
        this.gamePanel = gamePanel;
        this.mainMenu = mainMenu;

        gamePanel.addMouseListener(this);
        backButton = new RoundRectangle2D.Double(25, 25, 100, 40, 10, 10);

        entityNames = new ArrayList<>();
        boundingBoxes = new ArrayList<>();
        entityBackgrounds = new ArrayList<>();
        entityIdleFrames = new ArrayList<>();
        entityFilePath = new String[4];

        int y = 125;

        String[] names = {"Ahool", "Banaspati", "Gajah Mada", "Jerangkong"};

        for (String name : names) {
            entityNames.add(name);
            int textWidth = calculateTextWidth(name, mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
            boundingBoxes.add(new RoundRectangle2D.Double(100, y, textWidth + 20, 40, 10, 10));
            y += 60;
        }

        // Initialize background text and GIF file names for each entity
        entityBackgrounds.add("Ahool dikatakan berbentuk seperti kelelawar dan dideskripsikan "
        		+ "sebagai ukuran anak berumur satu tahun dengan rentang sayap raksasa "
        		+ "kira-kira 12 kaki. Ahool juga dideskripsikan berbulu pendek, abu-abu gelap, "
        		+ "memiliki mata hitam yang besar, lengan pendukung yang rata, dan sayap dengan "
        		+ "muka yang rata. Satu laporan mengenai Ahool terjadi pada 1925 ketika naturalis "
        		+ "Dr. Ernest Bartels, ahli ilmu burung, mencatat bahwa ia tengah menjajaki air "
        		+ "terjun di lereng Pegunungan Salak ketika kelelawar raksasa tidak dikenal, "
        		+ "Ahool, terbang tepat di atas kepalanya. Dua tahun kemudian pada tahun 1927, "
        		+ "sekitar pukul 11.30 WIB, Dr. Ernest Bartels ditemui Ahool lagi. Kali ini dia "
        		+ "berbaring di tempat tidur, di dalam rumah jerami dekat ke Sungai Tjidjenkol "
        		+ "di Jawa Barat. Ia tiba-tiba mendengar suara yang datang langsung dari "
        		+ "atas gubuknya, seperti menangis keras dan jelas tampak mengucapkan A Hool!");
        
        entityBackgrounds.add("Banaspati merupakan legenda atau cerita di masyarakat mengenai sosok makhluk "
        		+ "halus yang menjadi bagian dari budaya Indonesia. Sosok Banaspati sering kali "
        		+ "ditemukan dalam arsitektur candi di pulau Jawa. Mengutip dari jurnal Universitas "
        		+ "Udayana, pada candi di daerah Jawa Timur, sosok Banaspati atau yang disebut sebagai "
        		+ "Kala di Jawa Tengah, umumnya dapat ditemukan terpahat pada bagian atas lubang "
        		+ "pintu masuk di ruang sucinya masing-masing. Sementara itu, dikutip dari repositori "
        		+ "Universitas Katolik Soegijapranata (Unika) Semarang, dalam kepercayaan masyarakat, "
        		+ "Banaspati dikenal sebagai hantu yang mempunyai elemen api. Banaspati dikenal "
        		+ "sebagai makhluk mistis yang memiliki kekuatan besar dan biasa ditemukan di dalam "
        		+ "hutan ataupun digunakan oleh dukun sebagai sarana ilmu hitam.");
        
        entityBackgrounds.add("Gajah Mada adalah seorang panglima perang dan tokoh yang sangat "
        		+ "berpengaruh pada zaman kerajaan Majapahit. Menurut berbagai sumber mitologi, "
        		+ "kitab, dan prasasti dari zaman Jawa Kuno, ia memulai kariernya tahun 1313, dan "
        		+ "semakin menanjak setelah peristiwa pemberontakan Ra Kuti pada masa "
        		+ "pemerintahan Sri Jayanagara, yang mengangkatnya sebagai Patih. Ia menjadi "
        		+ "Mahapatih (Menteri Besar) pada masa Ratu Tribhuwanatunggadewi, dan "
        		+ "kemudian sebagai Amangkubhumi (Perdana Menteri) yang mengantarkan "
        		+ "Majapahit ke puncak kejayaannya. Gajah Mada terkenal dengan sumpahnya, "
        		+ "yaitu Sumpah Palapa, yang tercatat di dalam Kitab Pararaton. Sumpah Gajah "
        		+ "Mada adalah inspirasi dan ”bukti” bahwa bangsa ini dapat bersatu, meskipun "
        		+ "meliputi wilayah yang luas dan budaya yang berbeda-beda. Dengan demikian, "
        		+ "Gajah Mada adalah inspirasi bagi revolusi nasional Indonesia dan rasa persatuan "
        		+ "se-nusantara. Saat ini, Indonesia telah menetapkan Gajah Mada sebagai salah satu "
        		+ "Pahlawan Nasional dan merupakan simbol nasionalisme dan persatuan Nusantara.");
        
        entityBackgrounds.add("Jerangkong merupakan salah satu hantu yang berasal dari tanah Jawa. Masyarakat "
        		+ "Jawa percaya kalau jerangkong adalah arwah orang yang sudah meninggal yang saat "
        		+ "hidup suka mencuri telur. Hingga mati pun, ia akan tetap mencuri telur, dan telur "
        		+ "siapapun bisa diambil. Namun, tidak ada cerita jelas mengapa hantu jerangkong bisa "
        		+ "gentayangan. Hantu jerangkong digambarkan sebagai hantu yang berbentuk tengkorak, "
        		+ "tanpa ada kulit ataupun daging. Tidak ada penjelasan pasti mengapa hantu jerangkong "
        		+ "bisa berwujud tulang belulang saja tanpa adanya daging dan kulit. Pencurian yang "
        		+ "dilakukan oleh hantu jerangkong bukan seperti tuyul yang mencuri uang dengan membawa "
        		+ "pergi uangnya. Hantu jerangkong yang sudah mencuri telur akan memakan telur tersebut "
        		+ "di tempat. Hantu jerangkong memakan telur tersebut bukan dengan cara memecahkannya, "
        		+ "tetapi dia hanya akan memakan isinya tanpa memecahkan telurnya.");
        
        entityFilePath[0] = "/monsters/ahool/move-right/BatMovement-right_0";
        entityFilePath[1] = "/monsters/banaspati/idle/idle";
        entityFilePath[2] = "/player/gajahmada/idle/idle_0";
        entityFilePath[3] = "/monsters/jerangkong/idleRight/idle_0";
        

        // Load each entity's idle frames
        loadEntityFrames(entityFilePath[0], 6, entityIdleFrames);
        loadEntityFrames(entityFilePath[1], 11, entityIdleFrames);
        loadEntityFrames(entityFilePath[2], 7, entityIdleFrames);
        loadEntityFrames(entityFilePath[3], 4, entityIdleFrames);

        profileDrawers = new ArrayList<>();
        for (int i = 0; i < entityBackgrounds.size(); i++) {
            profileDrawers.add(new EncyclopediaEntityDrawer(gamePanel, mainMenu, entityIdleFrames.get(i), entityBackgrounds.get(i)));
        }
    }

    private void loadEntityFrames(String pathPrefix, int numFrames, List<List<BufferedImage>> framesList) {
        List<BufferedImage> frames = new ArrayList<>();
        for (int i = 1; i <= numFrames; i++) {
            try {
                String fileName = pathPrefix + i + ".png";
                BufferedImage frame = ImageIO.read(getClass().getResourceAsStream(fileName));
                frames.add(frame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        framesList.add(frames);
    }

    private int calculateTextWidth(String text, Font font) {
        FontMetrics fontMetrics = gamePanel.getFontMetrics(font);
        return fontMetrics.stringWidth(text);
    }

    public void draw(Graphics g) {
        if (encState == ENCYCLOPEDIASTATE.BASE) {
            if (mainMenu.getBackgroundImage() != null) {
                g.drawImage(mainMenu.getBackgroundImage(), 0, 0, gamePanel.getWidth(), gamePanel.getHeight(), gamePanel);
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
            }

            g.setFont(mainMenu.getImprisha().deriveFont(Font.BOLD, 30));
            g.setColor(Color.WHITE);

            for (int i = 0; i < entityNames.size(); i++) {
                g.drawString(entityNames.get(i), (int) boundingBoxes.get(i).x, (int) boundingBoxes.get(i).y + 30);
            }

            g.setColor(Color.WHITE);
            g.fillRoundRect((int) backButton.x, (int) backButton.y, (int) backButton.width, (int) backButton.height, 10, 10);
            g.setColor(Color.BLACK);
            g.drawString("Back", (int) (backButton.x + 15), (int) (backButton.y + 29));
        } else {
            drawEntityProfiles(g);
        }
    }

    private void drawEntityProfiles(Graphics g) {
        switch (encState) {
            case AHOOL:
                profileDrawers.get(0).drawProfile(g, 85, 260, 500, 400, 1300, 575);
                break;
            case BANASPATI:
                profileDrawers.get(1).drawProfile(g, 180, 260, 370, 370, 1280, 600);
                break;
            case GAJAHMADA:
                profileDrawers.get(2).drawProfile(g, 225, 200, 300, 500, 1300, 530);
                break;
            case JERANGKONG:
                profileDrawers.get(3).drawProfile(g, 120, 150, 550, 550, 1300, 575);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (MainMenu.mmState != MainMenu.MAINMENUSTATE.ENCYCLOPEDIA || encState != ENCYCLOPEDIASTATE.BASE) {
            return;
        }

        int mx = e.getX();
        int my = e.getY();

        if (backButton.contains(mx, my)) {
            mainMenu.playSE(1);
            System.out.println("Mouse click inside Encyclopedia Back Button");
            encState = ENCYCLOPEDIASTATE.BASE;
            MainMenu.mmState = MainMenu.MAINMENUSTATE.MAINMENU;
        } else {
            for (int i = 0; i < boundingBoxes.size(); i++) {
                if (boundingBoxes.get(i).contains(mx, my)) {
                    switch (i) {
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
