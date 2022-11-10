package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNum [][];

	File t =new File("src/res/player/bg/grass.png");
	File t1 =new File("src/res/player/bg/water.png");
	File t2 =new File("src/res/player/bg/wall.png");

	File t3 =new File("src/res/player/bg/sand.png");
	File t4 =new File("src/res/player/bg/tree.png");
	File t5 =new File("src/res/player/bg/tree1.png");

	public TileManager(GamePanel gp){
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/res/map/world01.txt");

	}
	public void getTileImage() {

		try {
			// GRASS
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(t);
		    tile [0].collision = false;
			// WATER
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(t1);
			tile [1].collision = true;

			// WALL

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(t2);
			tile [2].collision = true;

			/// Sand tile 3
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(t3);
			tile [3].collision = true;


			///  tree tile 4
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(t4);
			tile [4].collision = true;



			///  tree1 tile 5
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(t5);
			tile [5].collision = true;

		}catch (IOException e){
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

	}

	public void  loadMap ( String filepath ){
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;
			while (col < gp.maxWorldCol && row < gp.maxWorldRow){

				String line = br.readLine();

				while (col < gp.maxWorldCol){
					String numbers [] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum [col][row] = num;
					col++;
				}
				if (col== gp.maxWorldCol){
					col = 0;
					row++;

				}

			}
			br.close();

		}catch (Exception e){

		}


	}

	public void draw (Graphics2D g2){
		int worldCol = 0;
		int worldRow = 0;



		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
 			int tileNum = mapTileNum [worldCol][worldRow];
							// тайл расположения
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;

			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
			    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize , null);

			}




		worldCol++;


			if (worldCol == gp.maxWorldCol){
				worldCol = 0;
				worldRow++;

			}
		}

	}


}


