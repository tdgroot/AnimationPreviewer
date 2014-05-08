package nl.tdegroot.software.Anim.gfx.rendermode;

import nl.tdegroot.software.Anim.gfx.Screen;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

public class LineRenderMode extends RenderMode {

    private int limit;

    public LineRenderMode(int startColumn, int startRow, int columns, int rows) {
        super(startColumn, startRow, columns, rows);
        limit = columns * rows;
    }

    public void tick(int delta, SpriteSheet sheet) {
        time++;
        if (sheet != null) {
            if (time % loopSpeed == 0)
                animIndex = (animIndex + 1) % limit;
        }
    }

    public void render(int x, int y, SpriteSheet sheet, Screen screen) {
        sheet.render(16, 0, (startColumn + animIndex) % sheet.xx, (startRow + animIndex) / sheet.xx, screen);
    }

}
