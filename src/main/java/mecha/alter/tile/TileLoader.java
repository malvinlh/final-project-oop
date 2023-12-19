package mecha.alter.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mecha.alter.GamePanel;

public class TileLoader 
{
    public static Tile[] loadTiles(GamePanel gp) 
    {
        Tile[] tiles = new Tile[5];

        try {
            for (int i = 0; i < 2; i++) {
                tiles[i] = loadTile("/tiles/" + i + ".png");
                BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = scaledImage.createGraphics();
                g2.drawImage(tiles[i].image, 0, 0, gp.tileSize, gp.tileSize, null);
                tiles[i].image = scaledImage;
                g2.dispose();
                tiles[i].collision = true;
            }
            for (int i = 2; i < 5; i++) {
                tiles[i] = loadTile("/tiles/" + i + ".png");
                BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = scaledImage.createGraphics();
                g2.drawImage(tiles[i].image, 0, 0, gp.tileSize, gp.tileSize, null);
                tiles[i].image = scaledImage;
                g2.dispose();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tiles;
    }

    private static Tile loadTile(String path) throws IOException 
    {
        Tile tile = new Tile();
        tile.image = ImageIO.read(TileLoader.class.getResourceAsStream(path));
        return tile;
    }
}
