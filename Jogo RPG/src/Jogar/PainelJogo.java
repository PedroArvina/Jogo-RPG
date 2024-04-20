package Jogar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

import Entity.Player;



public class PainelJogo extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    //WORD SETTINGS
    
    public final int maxWordCol = 50;
    public final int maxWordRow = 50;
    public final int wordWidht = tileSize * maxWordCol;
    public final int wordHeight = tileSize * maxWordRow;
    
    
    
    // fps
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    ComandosTeclado keyH = new ComandosTeclado();
    private Thread gameThread;
    public Player player = new Player(this,keyH);
    
    // Posições iniciais do jogador
    int playerX = 100;
    int playerY = 100;	
    int playerSpeed = 4;

    public PainelJogo() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // Tempo esperado entre desenhos
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while (gameThread != null) {
            update();
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // Convertendo para milissegundos
                
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void update() {
        player.update();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        tileM.draw(g2);
        
       player.draw(g2);
        g2.dispose();
    }
}
