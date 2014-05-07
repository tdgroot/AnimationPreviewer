package nl.tdegroot.software.Anim.gfx;

public class SpriteSheet {

    public int xx;
    private int yy;
    public int width, height;
    public int sheetWidth, sheetHeight;
    private Sprite sheet;
    public int size;
    private Sprite[] sprites;

    public SpriteSheet(int clipWidth, int clipHeight, String ref) {
        this(clipWidth, clipHeight, new Sprite(ref));
    }

    public SpriteSheet(int clipWidth, int clipHeight, Sprite sheet) {
        this.sheet = sheet;
        this.width = clipWidth;
        this.height = clipHeight;
        this.sheetWidth = sheet.width;
        this.sheetHeight = sheet.height;
        load();
    }

    private void load() {
        xx = sheetWidth / width;
        yy = sheetHeight / height;
        size = xx * yy;
        sprites = new Sprite[size];
        System.out.println("Sprites: " + sprites.length);
        for (int i = 0; i < sprites.length; i++) {
            int[] pixels = new int[width * height];
            int xo = (i % xx) * width;
            int yo = (i / xx) * height;
            for (int y = 0; y < height; y++) {
                int ya = yo + y;
                for (int x = 0; x < width; x++) {
                    int xa = xo + x;
                    int col = sheet.pixels[xa + ya * sheetWidth];
                    pixels[x + y * width] = col;
                }
            }
            sprites[i] = new Sprite(width, height, pixels);
        }
    }

    public void render(int x, int y, int sx, int sy, Screen screen) {
        screen.render(x, y, sprites[sx + sy * xx]);
    }

}
