package entity;

import org.main.GamePanel;
import org.main.KeyHandler;
import org.main.UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    public int keyPossessed;

   public Player(GamePanel gp, KeyHandler keyHandler){
       super(gp);
       this.keyHandler = keyHandler;
       screenX =gp.screenWidth/2-gp.tileSize/2;
       screenY = gp.screenHeight/2-gp.tileSize/2;
       solidArea = new Rectangle();
       solidArea.x =8;
       solidArea.y=16;
       solidAreaDefaultX=solidArea.x;
       solidAreaDefaultY=solidArea.y;
       solidArea.width=18;
       solidArea.height=24;
       setDefaultValue();
       getPlayerImage();
       keyPossessed =0;
   }
   public void setDefaultValue(){
       worldX  = gp.tileSize*23;
       worldY = gp.tileSize*21;
       speed= 4;
       direction = "down";
   }
   public void getPlayerImage(){
       try{
           up1 = ImageIO.read(getClass().getResourceAsStream("/player/runningUp.png"));
           up2 = ImageIO.read(getClass().getResourceAsStream("/player/runningUp2.png"));
           down1 = ImageIO.read(getClass().getResourceAsStream("/player/runningDown.png"));
           down2 = ImageIO.read(getClass().getResourceAsStream("/player/runningDown2.png"));
           left1 = ImageIO.read(getClass().getResourceAsStream("/player/runningLeft.png"));
           left2 = ImageIO.read(getClass().getResourceAsStream("/player/runningLeft2.png"));
           right1 = ImageIO.read(getClass().getResourceAsStream("/player/runningRight.png"));
           right2 = ImageIO.read(getClass().getResourceAsStream("/player/runningRight2.png"));
           facingDown = ImageIO.read(getClass().getResourceAsStream("/player/idle.png"));
           facingLeft = ImageIO.read(getClass().getResourceAsStream("/player/facingLeft.png"));
           facingRight = ImageIO.read(getClass().getResourceAsStream("/player/facingRight.png"));
           facingUp = ImageIO.read(getClass().getResourceAsStream("/player/facingUp.png"));
       }
       catch(IOException e){
           e.printStackTrace();
       }
   }
   public void update(){
       boolean isMovingNow = false;
       if(keyHandler.upPressed){
           direction = "up";
           isMovingNow=true;
       }
       else if(keyHandler.downPressed){
           direction = "down";
           isMovingNow=true;
       }
       else if(keyHandler.leftPressed){
           direction= "left";
           isMovingNow=true;
       }
       else if(keyHandler.rightPressed){
           direction = "right";
           isMovingNow=true;
       }
       collisionOn =false;
       gp.collisionChecker.checkTile(this);
       int objectIndex = gp.collisionChecker.checkObject(this, true);
       pickObject(objectIndex);

       if (!collisionOn && isMovingNow) {
           if (keyHandler.upPressed && keyHandler.rightPressed) {
               worldY -= speed / Math.sqrt(2);
               worldX += speed / Math.sqrt(2);
           } else if (keyHandler.upPressed && keyHandler.leftPressed) {
               worldY -= speed / Math.sqrt(2);
               worldX -= speed / Math.sqrt(2);
           } else if (keyHandler.downPressed && keyHandler.rightPressed) {
               worldY += speed / Math.sqrt(2);
               worldX += speed / Math.sqrt(2);
           } else if (keyHandler.downPressed && keyHandler.leftPressed) {
               worldY += speed / Math.sqrt(2);
               worldX -= speed / Math.sqrt(2);
           } else if (keyHandler.upPressed) {
               worldY -= speed;
           } else if (keyHandler.downPressed) {
               worldY += speed;
           } else if (keyHandler.leftPressed) {
               worldX -= speed;
           } else if (keyHandler.rightPressed) {
               worldX += speed;
           }
       }


       moving= isMovingNow;
       if (moving) {
           spriteCounter++;
           if (spriteCounter > 10) {
               spriteNum++;
               if (spriteNum > 4) {
                   spriteNum = 1;
               }
               spriteCounter = 0;
           }
       }
   }
   public void pickObject(int index){
       if(index!=999){
           String objectName =gp.objects[index].name;
           switch(objectName){
               case "Key":
                   gp.playSE(0);
                   keyPossessed++;
                   gp.objects[index]=null;
                   if(keyPossessed==1){
                       gp.ui.showText("You got a key");
                   } else if (keyPossessed>1) {
                       gp.ui.showText("You got another key");
                   }
                   break;
               case "Door":
                   if(keyPossessed>0){
                       gp.playSE(1);
                       gp.objects[index]=null;
                       keyPossessed--;
                       gp.ui.showText("Door opened");
                   } else{
                       gp.ui.showText("You need a key");
                   }
                   break;
               case "Boot":
                   gp.playSE(2);
                   speed+=2;
                   gp.objects[index]= null;
                   gp.ui.showText("420 blaze it");
                   break;
               case "Chest":
                   gp.ui.gameFinished = true;
                   gp.playSE(2);
                   gp.objects[index]=null;
                   gp.ui.showText("Hooray you beat the game");
                   break;
           }
       }

   }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // If the player is moving, alternate between running and idle sprites
        if (moving) {
            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up1;
                    } else if (spriteNum == 2) {
                        image = facingUp;
                    } else if (spriteNum == 3) {
                        image = up2;
                    } else if (spriteNum == 4) {
                        image = facingUp;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down1;
                    } else if (spriteNum == 2) {
                        image = facingDown;
                    } else if (spriteNum == 3) {
                        image = down2;
                    } else if (spriteNum == 4) {
                        image = facingDown;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    } else if (spriteNum == 2) {
                        image = facingLeft;
                    } else if (spriteNum == 3) {
                        image = left2;
                    } else if (spriteNum == 4) {
                        image = facingLeft;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    } else if (spriteNum == 2) {
                        image = facingRight;
                    } else if (spriteNum == 3) {
                        image = right2;
                    } else if (spriteNum == 4) {
                        image = facingRight;
                    }
                }
            }
        } else {
            // Player is idle, display idle sprite
            switch (direction) {
                case "up" -> image = facingUp;
                case "down" -> image = facingDown;
                case "left" -> image = facingLeft;
                case "right" -> image = facingRight;
            }
        }

        if (image != null) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

}
