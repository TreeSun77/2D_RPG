package main;

import object.O_Chest;
import object.O_Door;
import object.O_Key;

import java.io.IOException;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter (GamePanel gp) {
        this.gp=gp;

    }
    public  void setObject() throws IOException {
        int i = 0;
        //KEY 1
        gp.obj[i] = new O_Key();
        gp.obj[i].worldX = 19 * gp.tileSize;
        gp.obj[i].worldY = 36 * gp.tileSize;
        i ++;
        // KEY 2
        gp.obj[i] = new O_Key();
        gp.obj[i].worldX = 14 * gp.tileSize;
        gp.obj[i].worldY = 28 * gp.tileSize;
        i++;

        //DOOR
        gp.obj[i] = new O_Door();
        gp.obj[i].worldX = 28 * gp.tileSize;
        gp.obj[i].worldY = 41 * gp.tileSize;
        i++;

        //CHEST
        gp.obj[i] = new O_Chest();
        gp.obj[i].worldX = 28 * gp.tileSize;
        gp.obj[i].worldY = 44 * gp.tileSize;
        i++;
    }

}
