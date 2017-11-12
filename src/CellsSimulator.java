import gui.Simulable;
import gui.Rectangle;
import gui.GUISimulator;
import java.util.ArrayList;
import java.awt.Color;

/** A simulable group of cells.*/
public class CellsSimulator implements Simulable {
	private AbstractCells cells;
	private GUISimulator gui;

	public CellsSimulator(GUISimulator gui, AbstractCells cells) {
		this.cells = cells;
		this.gui = gui;
	}

	/** Displays all the cells with the appropriate colors. This function is called by next.*/
	public void display(){
		// trace
		// System.out.println(this.cells);

		// we compute the size of a cell
		int size = Math.min(this.gui.getPanelWidth(), this.gui.getPanelHeight())
				   /Math.max(this.cells.n(), this.cells.m());

		// for each cell
		for (int i = 0; i < this.cells.n(); i++) {
			for (int j = 0; j < this.cells.m(); j++) {

				// we choose the color the current cell will take
				int value = this.cells.getState(i, j) * 255 / (this.cells.getNbState()-1);

				// colors will range from yellow to magenta to cyan, with varying opacities
				Color color = new Color(255, 255 - value, value, value);

				// Whichever model is used, if there are only 2 states,
				// the colors will be white and cyan. Because it's more beautiful.
				if(this.cells.getNbState() == 2) {
					color = new Color(255 - value, 255, 255);
				}

				// we add the cell to the gui
				this.gui.addGraphicalElement(new Rectangle(i*size + size/2, j*size + size/2, new Color(0, 255, 255, 10), color, size));
			}
		}
	}

	@Override
	public void next(){
		this.gui.reset();

		display();

		this.cells.updateNextStates();
		this.cells.updateCurrentStates();
	}

	@Override
	public void restart(){
		this.cells.reInit();
		this.gui.reset();
	}

}
