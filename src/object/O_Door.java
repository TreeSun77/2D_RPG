package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class O_Door extends SuperObject {

    File o1 = new File("src/res/objects/door.png");

    public O_Door () throws IOException {
        name = "Дверь";
        try {
            image = ImageIO.read(o1);
        } catch (IOException e){
            e.printStackTrace();
        }


    }

}


