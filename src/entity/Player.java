package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Entity{
	GamePanel gp;

	KeyHandler keyH;

	public final int screenX;
	public  final  int screenY;
	public int hasKey = 0;
	int standCounter = 0;
	boolean moving = false;
	int pixelCounter = 0;


	File p1 = new File("src/res/player/1.png");
	File p2 = new File("src/res/player/2.png");
	File p3 = new File("src/res/player/3.png");
	File p4 = new File("src/res/player/1d.png");
	File p5 = new File("src/res/player/2d.png");
	File p6 = new File("src/res/player/3d.png");
	File p7 = new File("src/res/player/1L.png");
	File p8 = new File("src/res/player/2L.png");
	File p9 = new File("src/res/player/3L.png");
	File p10 = new File("src/res/player/1r.png");
	File p11 = new File("src/res/player/2r.png");
	File p12 = new File("src/res/player/3r.png");

	public Player (GamePanel gp, KeyHandler keyH) throws IOException {

		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight /2 - (gp.tileSize/2);

		solidArea = new Rectangle();
		solidArea.x = 1;
		solidArea.y = 1;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 44;
		solidArea.height = 45;

		//да я знаю это опечатка. Будет fix но не сайчас 23:04 18.11.2022
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues(){
		worldX = gp.tileSize * 10;
		worldY = gp.tileSize * 15;
		speed = 4;
		direction = "up";
	}


	public void getPlayerImage() throws IOException {
		up1 = ImageIO.read(p1);
		up2 = ImageIO.read(p2);
		up3 = ImageIO.read(p3);
		down1 = ImageIO.read(p4);
		down2 = ImageIO.read(p5);
		down3 = ImageIO.read(p6);
		left1 = ImageIO.read(p7);
		left2 = ImageIO.read(p8);
		left3 = ImageIO.read(p9);
		right1 = ImageIO.read(p10);
		right2 = ImageIO.read(p11);
		ringht3 = ImageIO.read(p12);
	}


	public void update(){
		if (!moving) {

			if (keyH.upPressed || keyH.downPressed

					|| keyH.rightPressed || keyH.leftPressed) {


				if (keyH.upPressed) {
					direction = "up";

				} else if (keyH.downPressed) {
					direction = "down";

				} else if (keyH.leftPressed) {
					direction = "left";

				} else if (keyH.rightPressed) {
					direction = "right";
				}

				moving = true;

				//CHECK TILE COLLISION
				collisionOn = false;
				// on\OFF CHECK COLLISION
				gp.cChecker.checkTile(this);

				//CHECK OBJ COLLISION
				int objIndex = gp.cChecker.checkObject(this, true);
				puckUpObject(objIndex);

			}
			// ANIMATION bug freeze moving FIX
			else {
				standCounter++;
				if (standCounter == 20) {
					spriteNum = 1;
					standCounter = 0;
				}
			}
		}
			if (moving){

				// IF COLLISION FALSE
				if (!collisionOn ){
					switch (direction){
						case "up":
							worldY -= speed;
							break;
						case "down":
							worldY += speed;
							break;
						case  "left":
							worldX -= speed;
							break;
						case "right":
							worldX += speed;
							break;


					}
				}


				spriteCounter++;
				if (spriteCounter > 11) {
					if (spriteNum == 1) {
						spriteNum = 2;
					} else if (spriteNum == 2) {
						spriteNum = 3;
					} else if (spriteNum == 3) {
						spriteNum = 4;

					} else if (spriteNum == 4) {
						spriteNum = 1;

					}
					spriteCounter = 0;

		}
		pixelCounter += speed;

		if (pixelCounter == 48){
			moving = false;
			pixelCounter = 0;
		}
			}




	}
	 // TAKE OBJ item
	public void puckUpObject (int i){
		if (i != 888){
			String objectName = gp.obj[i].name;

			switch (objectName){
				case "Ключ":
					gp.playSE(1);
					hasKey++;
					gp.obj[i] = null;
					gp.ui.showMessage("Получен ключ");
					//System.out.println("Keys: " +hasKey);
					break;

				case "Дверь":

					if (hasKey > 0){
						gp.playSE(3);
						gp.obj[i] = null;
						hasKey--;
						gp.ui.showMessage("Дверь открыта");
					}
					else {
						gp.ui.showMessage("Нужен ключ. Закрыто!");
					}
					//System.out.println("Keys: " +hasKey);
					break;
				case "Ускорение":
					gp.playSE(2);
					speed += 2;
					gp.obj[i] = null;
					gp.ui.showMessage("Зелье скорости. Speed + 2");
					break;


				case "Сундук":
					gp.ui.gameFinished = true;
					gp.stopMusic();
					gp.playSE(4);
					break;
			}

		}

	}

	public void draw (Graphics2D g2){
		BufferedImage image = null;
		switch (direction) {
			case "up":
				if (spriteNum == 1){
					image = up1;
				}
				if(spriteNum == 2) {
					image = up2;
					break;
				}
				if (spriteNum == 3){
					image = up1;
				}
				if (spriteNum == 4){
					image = up3;
				}
			case "down":
				if (spriteNum == 1){
					image = down1;
				}
				if(spriteNum == 2) {
					image = down2;
					break;
				}
				if (spriteNum == 3){
					image = down1;
				}
				if (spriteNum == 4){
					image = down3;
				}
				break;
			case "left":
				if (spriteNum == 1){
					image = left1;
				}
				if(spriteNum == 2) {
					image = left2;
					break;
				}
				if (spriteNum == 3){
					image = left1;
				}
				if (spriteNum == 4){
					image = left3;
				}
				break;
			case "right":
				if (spriteNum == 1){
					image = right1;
				}
				if(spriteNum == 2) {
					image = right2;
					break;
				}
				if (spriteNum == 3){
					image = right1;
				}
				if (spriteNum == 4){
					image = ringht3;
				}
				break;

		}
			int x = screenX;
			int y = screenY;

			if (screenX > worldX){
				x = worldX;

			}
			if(screenY > worldY){
				y = worldY;
			}
		int rightOffset = gp.screenWidth - screenX;
		if (rightOffset > gp.worldWidth - worldX){
			x = gp.screenWidth - (gp.worldWidth - worldX);
		}
		int bottomOffset = gp.screenHeight - screenY;
		if (bottomOffset > gp.worldHeight - worldY){
			y = gp.screenHeight - (gp.worldHeight - worldY);
		}
			g2.drawImage(image, x , y ,gp.tileSize, gp.tileSize, null);
	}
}
