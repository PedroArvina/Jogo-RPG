package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import Jogar.PainelJogo;
import Jogar.ComandosTeclado;

public class Player extends entity {
    
    PainelJogo gp;
    ComandosTeclado keyH;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public final int screenX;
    public final int screenY;
	
	

    public Player(PainelJogo gp, ComandosTeclado keyH) {
        this.gp = gp;
        this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        wordX = gp.tileSize * 23;
        wordY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        // Initialize sprite variables
        spriteCounter = 0;
        spriteNum = 1;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
    	
    	if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed) {
    	
        if (keyH.upPressed) {
            direction = "up";
            wordY -= speed;
        } else if (keyH.downPressed) {
            direction = "down";
            wordY += speed;
        } else if (keyH.leftPressed) {
            direction = "left";
            wordX -= speed;
        } else if (keyH.rightPressed) {
            direction = "right";
            wordX += speed;
        }
        
        // Animation logic
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Select the correct image based on the direction and sprite number
        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
        }
        if (image != null) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
