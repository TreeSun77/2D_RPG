package main;

import object.O_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
	GamePanel gp;
	Font arial_40;
	Font arial_70B;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message ="";
	int messageCounter = 0;
	public boolean gameFinished = false;


	public UI (GamePanel gp) throws IOException {
		this.gp=gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_70B = new Font("Arial", Font.BOLD, 70);
		O_Key key = new O_Key();
		keyImage = key.image;

	}
	public void showMessage (String text){
		message = text;
		messageOn = true;
	}
    public void draw (Graphics2D g2){
		//GAME OVER
		if (gameFinished){
			g2.setFont(arial_40);
			g2.setColor(Color.white);

			String text;
			int textLength;
			int x;
			int y;

			text = "Найден сундук";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);

			g2.setFont(arial_70B);
			g2.setColor(Color.RED);
			text = "Уровень пройден!!!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);

			// STOP GAME
			gp.gameThread = null;

		}
		else {

		g2.setFont(arial_40);
		g2.setColor(Color.WHITE);
		g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
		g2.drawString("x " + gp.player.hasKey, 70,66);

	    //MESSAGE
	    if (messageOn){
			g2.drawString(message, gp.tileSize/6, gp.tileSize*10);
			g2.setFont(g2.getFont().deriveFont(18F));

			messageCounter++;
			if (messageCounter > 90) {
				messageCounter = 0;
				messageOn = false;
			}
			}
	    }

    }


}
