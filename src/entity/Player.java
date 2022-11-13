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
		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues(){
		worldX = gp.tileSize * 2;
		worldY = gp.tileSize * 4;
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
		if (keyH.upPressed == true || keyH.downPressed== true || keyH.rightPressed == true || keyH.leftPressed == true ) {


			if (keyH.upPressed == true) {
				direction = "up";
				worldY -= speed;
			} else if (keyH.downPressed == true) {
				direction = "down";
				worldY += speed;
			} else if (keyH.leftPressed == true) {
				direction = "left";
				worldX -= speed;
			} else if (keyH.rightPressed == true) {
				direction = "right";
				worldX += speed;
			}

			spriteCounter++;
			if (spriteCounter > 15) {
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
	g2.drawImage(image, screenX , screenY ,gp.tileSize, gp.tileSize, null);
	}
}
