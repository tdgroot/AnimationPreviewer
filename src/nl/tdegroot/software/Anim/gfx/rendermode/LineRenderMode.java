package nl.tdegroot.software.Anim.gfx.rendermode;

import nl.tdegroot.software.Anim.gfx.Screen;
import nl.tdegroot.software.Anim.gfx.SpriteSheet;

public class LineRenderMode extends RenderMode {


	public LineRenderMode(int startColumn, int startRow, int columns, int rows) {
		super(startColumn, startRow, columns, rows);
	}

	int column, row;

	public void tick(int delta, SpriteSheet sheet) {
		time++;
		if (sheet != null) {
			if (time % loopSpeed == 0) {
				column = (column + 1);
				if (column % columns == 0) {
					column = column % columns;
					row = (row + 1);
					if (row % rows == 0) {
						row = row % rows;
					}
				}
			}
		}
	}

	public void render(int x, int y, SpriteSheet sheet, Screen screen) {
		int c = (startColumn + column) % columns;
		int r = (startRow + row);
		sheet.render(x, y, c, r, screen);
	}

}
