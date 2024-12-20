package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Boot extends SuperObject{
    public Boot(){
        name = "Boot";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/object/boots.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
