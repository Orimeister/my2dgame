package org.main;

import object.Boot;
import object.Chest;
import object.Door;
import object.Key;

public class AssetInitializer {
    GamePanel gp;
    public AssetInitializer(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.objects[0] = new Key();
        gp.objects[0].worldX = 23*gp.tileSize;
        gp.objects[0].worldY =7*gp.tileSize;

        gp.objects[1] = new Key();
        gp.objects[1].worldX = 23*gp.tileSize;
        gp.objects[1].worldY = 40*gp.tileSize;

        gp.objects[2] = new Door();
        gp.objects[2].worldX = 10*gp.tileSize;
        gp.objects[2].worldY = 11*gp.tileSize;

        gp.objects[3] = new Chest();
        gp.objects[3].worldX = 10*gp.tileSize;
        gp.objects[3].worldY = 8*gp.tileSize;

        gp.objects[4] = new Door();
        gp.objects[4].worldX = 12*gp.tileSize;
        gp.objects[4].worldY = 22*gp.tileSize;

        gp.objects[5] = new Boot();
        gp.objects[5].worldX=37 * gp.tileSize;
        gp.objects[5].worldY=34 * gp.tileSize;




    }

}
