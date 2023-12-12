package mecha.alter.tile;

import java.io.IOException;

import javax.imageio.ImageIO;

public class TileLoader 
{
    public static Tile[] loadTiles() 
    {
        Tile[] tiles = new Tile[5];

        try {
            for (int i = 0; i < 2; i++) {
                tiles[i] = loadTile("/tiles/" + i + ".png");
                tiles[i].collision = true;
            }
            for (int i = 2; i < 5; i++) {
                tiles[i] = loadTile("/tiles/" + i + ".png");
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