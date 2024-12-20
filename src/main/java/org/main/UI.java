package org.main;

import entity.Player;
import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_40;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;

    private int messageCounter =0;
    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD,40);
        Key key = new Key();
        keyImage = key.image;
    }
    public void showText(String text){
        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){

            g2.setFont(arial_40);
            g2.setColor(Color.black);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize,gp.tileSize, null);
            g2.drawString(": "+gp.player.keyPossessed,74, 65);
            if(messageOn){
                g2.drawString(message, gp.tileSize/2, gp.tileSize*3);
                messageCounter++;
                if(messageCounter>120){
                    messageCounter=0;
                    messageOn = false;
                }

            }
        }

    }


