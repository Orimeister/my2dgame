package org.main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTile = 16;
    final int scale = 3;
    public final int tileSize = originalTile*scale;
    public final int maxScreenCol =16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol=50;
    public final int maxWorldRow =50;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight =tileSize*maxWorldRow;
    KeyHandler keyHandler = new KeyHandler(this);
    final int FPS =60;
    public UI ui = new UI(this);
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    public CollisionController collisionChecker = new CollisionController(this);
    public SuperObject[] objects = new SuperObject[10];
    public AssetInitializer assetInitializer = new AssetInitializer(this);

    public boolean isPaused = false;

    Thread gameThread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void setupGame(){

        assetInitializer.setObject();
        this.playMusic(3);
    }
    public void startGameThread(){
        gameThread  = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;

            if (!isPaused) {
                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for(int i=0; i<objects.length;i++){
            if(!(objects[i] ==null)){
                objects[i].draw(g2,this);
            }
        }

        player.draw(g2);


        ui.draw(g2);
        if (isPaused) {
            g2.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black overlay
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            String pauseText = "Game Paused";
            int textWidth = g2.getFontMetrics().stringWidth(pauseText);
            g2.drawString(pauseText, (getWidth() - textWidth) / 2, getHeight() / 2);
        }
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFiles(i);
        music.play();
        music.loop();
        music.setVolume(-10.0f);
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        soundEffect.setFiles(i);
        soundEffect.play();
    }

}
