package nl.tdegroot.software.Anim.gfx.rendermode;

import nl.tdegroot.software.Anim.PreferencesWindow;
import nl.tdegroot.software.Anim.gfx.Screen;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

public abstract class RenderMode {

    protected PreferencesWindow preferences;

    protected int startColumn, startRow;
    protected int columns, rows;
    protected int loopSpeed = 8;

    protected int time, animIndex;

    public RenderMode(int startColumn, int startRow, int columns, int rows, PreferencesWindow preferences) {
        this.startColumn = startColumn;
        this.startRow = startRow;
        this.columns = columns;
        this.rows = rows;
        this.preferences = preferences;
    }

    public abstract void tick(int delta, SpriteSheet sheet);

    public abstract void previousFrame(SpriteSheet sheet);

    public abstract void nextFrame(SpriteSheet sheet);

    public abstract void render(int x, int y, SpriteSheet sheet, Screen screen);

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getLoopSpeed() {
        return loopSpeed;
    }

    public void setLoopSpeed(int loopSpeed) {
        this.loopSpeed = loopSpeed;
    }

}
