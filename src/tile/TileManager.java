package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    int mapTileNumDetails[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[1240];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        mapTileNumDetails = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        // Stores the tiled and their corresponding number in an array
        try {
            for(int i = 0; i < tile.length; i++) {
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream("/"+(i+1) +".png"));
            }
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/1.png"));
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
        // Loads the txt file (the map) that contains the numbers of where to place the tiles;
        try {
            InputStream is = getClass().getResourceAsStream("/map_base.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String[] numbers = line.split(",");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row ++;
                }
            }
            br.close();
        }
        catch (Exception e ) {

        }
    }
    public void draw(Graphics g2) {
        // Draws the correct tile the correct location;
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
