package tile;

import org.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public int[][] mapTileNum;
    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum=new int[gp.maxWorldCol][gp.maxWorldRow];
        GetTileImage();
        loadMap();
    }
    public void GetTileImage(){
        try{
            tiles[0]= new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));

            tiles[1]= new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall.png"));
            tiles[1].collision=true;

            tiles[2]= new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/water.png"));
            tiles[2].collision=true;

            tiles[3]= new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tile/earth.png"));
            tiles[3].collision=false;

            tiles[4]= new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tile/tree.png"));
            tiles[4].collision=true;

            tiles[5]= new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tile/sand.png"));

        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/map/world01.txt");
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line = br.readLine();
                while(col<gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col=0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while (worldRow < gp.maxWorldRow) {
            while (worldCol < gp.maxWorldCol) {
                int tileNum = mapTileNum[worldCol][worldRow];
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                // Only draw tiles within the screen bounds
                if (screenX + gp.tileSize > 0 && screenX < gp.screenWidth &&
                        screenY + gp.tileSize > 0 && screenY < gp.screenHeight) {
                    g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }

                worldCol++;
            }

            worldCol = 0;
            worldRow++;
        }
    }

}
