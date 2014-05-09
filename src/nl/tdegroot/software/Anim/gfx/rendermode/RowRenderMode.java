package nl.tdegroot.software.Anim.gfx.rendermode;

import nl.tdegroot.software.Anim.PreferencesWindow;
import nl.tdegroot.software.Anim.gfx.Screen;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

public class RowRenderMode extends RenderMode {

    public RowRenderMode(int startColumn, int startRow, int columns, int rows, PreferencesWindow preferences) {
        super(startColumn, startRow, columns, rows, preferences);
    }

    int column, row;

    public void tick(int delta, SpriteSheet sheet) {
        time++;
        if (sheet != null) {
            if (time % loopSpeed == 0) {
                column++;
                if (column >= columns) {
                    column = 0;
                    row++;
                    if (row >= rows) {
                        row = 0;
                    }
                }
            }
        }
    }

    public void previousFrame(SpriteSheet sheet) {
        column--;
        if (column < 0) {
            column = startColumn + columns - 1;
            row--;
            if (row < 0) {
                row = startRow + rows - 1;
            }
        }
    }

    public void nextFrame(SpriteSheet sheet) {
        column++;
        if (column >= columns) {
            column = 0;
            row++;
            if (row >= rows) {
                row = 0;
            }
        }
    }

    public void render(int x, int y, SpriteSheet sheet, Screen screen) {
        int c = (startColumn + column) % columns;
        int r = (startRow + row);
        if (r >= sheet.yy) {
            row = 0;
            r = startRow;
        }
        preferences.setCurrentColumn(c);
        preferences.setCurrentRow(r);
        sheet.render(x, y, c, r, screen);
    }

}
