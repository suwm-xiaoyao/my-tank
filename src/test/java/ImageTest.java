import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageTest {
    @Test
    public void test(){
        try {
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            int a =1;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
