package nl.tdegroot.software.Anim.gfx;

public class Screen {

    public int width, height;
    public int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void render(int x, int y, Sprite sprite) {
        for (int yy = 0; yy < sprite.height; yy++) {
            int ya = yy + y;
            for (int xx = 0; xx < sprite.width; xx++) {
                int xa = xx + x;
                if (xa < -sprite.width || xa >= width || ya >= height) break;

                if (xa < 0)
                    xa = 0;

                int col = sprite.pixels[xx + yy * sprite.width];
                int ignoreCol = 0xFFFF00FF;
//                if (col == ignoreCol) continue;
                pixels[xa + ya * width] = col;
            }
        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0xFFFFFFFF;
//            pixels[i] = 0;
        }
    }
}
