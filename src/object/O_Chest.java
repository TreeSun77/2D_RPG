package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class O_Chest extends SuperObject{
    File o1 = new File("src/res/objects/chest.png");

    public O_Chest () throws IOException {
        name = "Сундук";
        try {
            image = ImageIO.read(o1);
        } catch (IOException e){
            e.printStackTrace();
        }


    }


}
