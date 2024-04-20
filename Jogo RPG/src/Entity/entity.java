package Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage; // Importação corrigida para BufferedImage
import java.io.IOException; // Importação corrigida para IOException
import javax.imageio.ImageIO; // Importação corrigida para ImageIO

import Jogar.PainelJogo;
import Jogar.ComandosTeclado;
public class entity {
	
	
public int wordX, wordY;
public int speed;

public BufferedImage up1, up2, down1, down2, left1, left2, righ1, right2;
public String direction;

public int spriteCouner = 0;
public int spriteNum = 1;

}