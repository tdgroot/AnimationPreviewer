package nl.tdegroot.software.Anim.gfx;

public class SpriteSheet {

    public int size;
    public int xx, yy;
    public int frameWidth, frameHeight;
    public int sheetWidth, sheetHeight;
    private Sprite sheet;
    private Sprite[] sprites;

    public SpriteSheet(int frameWidth, int frameHeight, String ref) {
        this(frameWidth, frameHeight, new Sprite(ref));
    }

    public SpriteSheet(int frameWidth, int frameHeight, Sprite sheet) {
        this.sheet = sheet;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.sheetWidth = sheet.width;
        this.sheetHeight = sheet.height;
        load();
    }

    private void load() {
        xx = sheetWidth / frameWidth;
        yy = sheetHeight / frameHeight;
        size = xx * yy;
        sprites = new Sprite[size];
        System.out.println("Sprites: " + sprites.length);
        for (int i = 0; i < sprites.length; i++) {
            int[] pixels = new int[frameWidth * frameHeight];
            int xo = (i % xx) * frameWidth;
            int yo = (i / xx) * frameHeight;
            for (int y = 0; y < frameHeight; y++) {
                int ya = yo + y;
                for (int x = 0; x < frameWidth; x++) {
                    int xa = xo + x;
                    int col = sheet.pixels[xa + ya * sheetWidth];
                    pixels[x + y * frameWidth] = col;
                }
            }
            sprites[i] = new Sprite(frameWidth, frameHeight, pixels);
        }
    }

    public void render(int x, int y, int sx, int sy, Screen screen) {
        screen.render(x, y, sprites[sx + sy * xx]);
    }

}
