package nl.tdegroot.software.Anim.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Sprite {

    public int width, height;
    public int[] pixels;

    public Sprite(String ref) {
        try {
            BufferedImage image = ImageIO.read(new File(ref));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            pixels = image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

}
