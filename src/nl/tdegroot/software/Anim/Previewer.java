package nl.tdegroot.software.Anim;

import nl.tdegroot.software.Anim.gfx.rendermode.RenderMode;
import nl.tdegroot.software.Anim.gfx.Screen;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Previewer extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    private int width = 256;
    private int height = 128;
    private int scale = 4;
    private Dimension size;
    private boolean running = false;

    private Thread thread;
    private Screen screen;

    private RenderMode renderMode;

    private SpriteSheet sheet;
    private int frameWidth, frameHeight;
    private String path;

    private BufferedImage img;
    private int[] pixels;
    private int loopSpeed = 32;
    private boolean paused = false;

    public Previewer() {

    }

    public synchronized void init() {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
        size = new Dimension(width * scale, height * scale);
        screen = new Screen(width, height);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        addKeyListener(new Keyboard());
    }

    public synchronized void start() {
        thread = new Thread(this, "Game");
        thread.start();
        running = true;
    }

    public void pause() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    long last = System.currentTimeMillis();

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
                long currentTime = System.currentTimeMillis();
                int delta = (int) (currentTime - last);
                last = currentTime;
                ticks++;
                tick(delta);
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

    public void tick(int delta) {
        if (renderMode != null && !paused)
            renderMode.tick(delta, sheet);
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        if (renderMode != null)
            renderMode.render(50, 50, sheet, screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);

        g.dispose();
        bs.show();
    }

    public void setPainting(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public synchronized void setSheet(int frameWidth, int frameHeight, String path, SpriteSheet sheet) {
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.path = path;
        this.sheet = sheet;
    }

    public RenderMode getRenderMode() {
        return renderMode;
    }

    public synchronized void setRenderMode(RenderMode renderMode) {
        this.renderMode = renderMode;
    }
}

