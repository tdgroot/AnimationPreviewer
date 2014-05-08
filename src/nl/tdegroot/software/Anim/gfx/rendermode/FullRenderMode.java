package nl.tdegroot.software.Anim.gfx.rendermode;

import nl.tdegroot.software.Anim.gfx.Screen;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

public class FullRenderMode extends RenderMode {

    public FullRenderMode() {
        super(0, 0, 0, 0);
    }

    public void tick(int delta, SpriteSheet sheet) {
        time++;
        if (sheet != null) {
            if (time % loopSpeed == 0)
                animIndex = (animIndex + 1) % sheet.size;
        }
    }

    public void render(int x, int y, SpriteSheet sheet, Screen screen) {
        sheet.render(16, 0, animIndex % sheet.xx, animIndex / sheet.xx, screen);
    }
}
