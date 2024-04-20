package tile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Jogar.PainelJogo;

public class TileManager {

    PainelJogo gp;
    Tile[] tile; // Assuming Tile is a class that has at least a BufferedImage called image
    int mapTileNum[][];
    
    
    public TileManager(PainelJogo gp) {
        this.gp = gp;
        tile = new Tile[10]; // Initialize your tile array
        mapTileNum = new int [gp.maxWordCol][gp.maxWordRow];
        getTileImage(); // Get the images for your tiles
        loadMap("/res/maps/word.01.txt"); 
        
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            // ... add more tiles if necessary
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
         
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
         
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath) {
    	
    	try {
    	InputStream is = getClass().getResourceAsStream(filePath);
    	BufferedReader br = new BufferedReader(new InputStreamReader(is));
    	
    	int col = 0;
    	int row = 0;
    	
    	while(col < gp.maxWordCol && row < gp.maxWordRow) {
    		
    		String line =br.readLine();
    		
    		while (col < gp.maxWordCol) {
    			
    			String numbers[] = line.split(" ");
    			
    			int num = Integer.parseInt(numbers[col]);
    			
    			mapTileNum[col][row] = num;
    			col++;
    			
    		}
    		
    		if(col == gp.maxWordCol) {
    			col = 0;
    			row++;
    		}
    		if(col == gp.maxWordCol) {
    			col = 0;
    			row ++;
    		}
    		
    	}
    	br.close();
    	
    	
    }catch(Exception e) {
    	
    }	
    }

    public void draw(Graphics2D g2) {
        int wordCol = 0;
        int wordRow = 0;
        

        while(wordCol < gp.maxWordCol && wordRow < gp.maxWordRow) {
        	
        	int tileNum = mapTileNum[wordCol][wordRow];
        	
        	int wordX = wordCol * gp.tileSize;
        	int wordY = wordRow * gp.tileSize;
        	int screenX = wordX - gp.player.wordX + gp.player.screenX;
        	int screenY = wordY - gp.player.wordY + gp.player.screenY;
        
            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            wordCol++;
            

            // Move to the next row after the last column
            if (wordCol == gp.maxWordCol) {
            	wordCol = 0;
               
            	wordRow++;
               
            }
        }
    }
}

