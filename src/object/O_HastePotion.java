package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class O_HastePotion extends SuperObject{

    File o1 = new File("src/res/objects/potion.png");

    public O_HastePotion () throws IOException {
        name = "Ускорение";
        try {
            image = ImageIO.read(o1);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
