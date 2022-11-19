package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class O_Key extends SuperObject{
	File o1 = new File("src/res/objects/key.png");

	public O_Key () throws IOException {
		name = "Ключ";
		try {
			image = ImageIO.read(o1);
		} catch (IOException e){
			e.printStackTrace();
		}


	}

}
