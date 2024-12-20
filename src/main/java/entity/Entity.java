package entity;

import org.main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public int worldX,worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, facingUp, facingDown, facingLeft, facingRight;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    boolean moving;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn =false;
    public Entity(GamePanel gp){
        this.gp = gp;
    }
}
