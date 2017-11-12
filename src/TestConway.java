import gui.GUISimulator;
import java.awt.Color;

/** Initialises the parameters and launches the simulation.*/
public class TestConway {
	public static void main(String[] args) {

		// Parameters of the simulation
		int n = 200; // number of cells vertically
		int m = 200; // number of cells horizontally
		int initialProbability = 6; // probability (percentage) that a cell will begin the simulation alive

		ConwayCells cells = new ConwayCells(n, m, initialProbability);

		GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
		gui.setSimulable(new CellsSimulator(gui, cells));
	}
}
