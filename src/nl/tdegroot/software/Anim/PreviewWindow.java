package nl.tdegroot.software.Anim;

import nl.tdegroot.software.Anim.gfx.Screen;
import nl.tdegroot.software.Anim.gfx.Sprite;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class PreviewWindow extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static int width = 64;
    public static int height = 32;
    private static int scale = 16;
    private static Dimension size;
    private boolean running = false;

    private Thread thread;
    private Screen screen;
    private Sprite sprite;
    private SpriteSheet spriteSheet;

    private BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    private int[] pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

    public PreviewWindow() {
        size = new Dimension(width * scale, height * scale);
        screen = new Screen(width, height);
        sprite = new Sprite("res/sprite.png");
        spriteSheet = new SpriteSheet(16, 16, "res/sheet.png");
        init();
    }

    public void init() {
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        addKeyListener(new Keyboard());
    }

    public static int getWindowWidth() {
        return width * scale;
    }

    public static int getWindowHeight() {
        return height * scale;
    }

    public synchronized void start() {
        thread = new Thread(this, "Game");
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        running = false;
        System.exit(0);
    }

    public void run() {
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        int frames = 0;
        int ticks = 0;
        long lastTimer1 = System.currentTimeMillis();
        requestFocus();
        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (unprocessed >= 1) {
                ticks++;
                tick();
                unprocessed -= 1;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                System.out.println(ticks + " ticks, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }
        }
    }

    int time;
    int animIndex;

    public void tick() {
        time++;
        if (time % 16 == 0)
            animIndex = (animIndex + 1) % spriteSheet.size;
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        screen.render(0, 0, sprite);

        spriteSheet.render(16, 0, animIndex % spriteSheet.xx, animIndex / spriteSheet.xx, screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);

        g.dispose();
        bs.show();
    }
}

