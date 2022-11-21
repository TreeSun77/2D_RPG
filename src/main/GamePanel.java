package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{


	//SCREEN SETTING

	public  final int originalTitleSize = 16;
	public final int scale = 3;

	public final int tileSize = originalTitleSize * scale;

	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768
	public final int screenHeight = tileSize * maxScreenRow; //576


  // WORLD SETTING

	public  final int maxWorldCol = 90;
	public  final int maxWorldRow = 50;




	int FPS = 60;
	TileManager tileM = new TileManager(this);


	KeyHandler keyH = new KeyHandler(this);
	Sound sound = new Sound();



	public CollisionChecker cChecker = new CollisionChecker( this);
	public AssetSetter aSetter = new AssetSetter(this);

	// GAME THREAD
	Thread gameThread;

	// ENTITY AND OBJ
	public  Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[100];


	public GamePanel () throws IOException {
		this.setPreferredSize(new Dimension(screenWidth , screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);


	}
	public  void setupGame() throws IOException{
		aSetter.setObject();
		playMusic(0);

	}

	public void startGameThred() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null){

		update();
		repaint();


			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				if (remainingTime < 0 ){
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public void update(){
	player.update();
	}

	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Call TILE
		tileM.draw(g2);

		//OBJECT
		for (int i = 0; i < obj.length; i++ ){
			if (obj[i] != null){
				obj[i].draw(g2, this);
			}
		}


		//Call PLAYER
		player.draw(g2);
		g2.dispose();

	}
	public void playMusic (int i){
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	public void stopMusic(){
		sound.stop();
	}
	// SOUND EFFECT ITEMS
	public void playSE (int i){
		sound.setFile(i);
		sound.play();
	}
}
