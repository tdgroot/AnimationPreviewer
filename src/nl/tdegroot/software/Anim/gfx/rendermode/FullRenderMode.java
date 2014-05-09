package nl.tdegroot.software.Anim.gfx.rendermode;

import nl.tdegroot.software.Anim.PreferencesWindow;
import nl.tdegroot.software.Anim.gfx.Screen;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

public class FullRenderMode extends RenderMode {

    public FullRenderMode(PreferencesWindow preferences) {
        super(0, 0, 0, 0, preferences);
    }

    public void tick(int delta, SpriteSheet sheet) {
        time++;
        if (sheet != null) {
            if (time % loopSpeed == 0)
                nextFrame(sheet);
        }
    }

    public void previousFrame(SpriteSheet sheet) {
        animIndex = (animIndex - 1);
        if (animIndex < 0) {
            animIndex = sheet.size - 1;
        }
    }

    public void nextFrame(SpriteSheet sheet) {
        animIndex = (animIndex + 1) % sheet.size;
    }

    public void render(int x, int y, SpriteSheet sheet, Screen screen) {
        preferences.setCurrentColumn(animIndex % sheet.xx);
        preferences.setCurrentRow(animIndex / sheet.xx);
        sheet.render(x, y, animIndex % sheet.xx, animIndex / sheet.xx, screen);
    }

}
