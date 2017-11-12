import gui.GUISimulator;
import java.awt.Color;

/** Initialises the parameters and launches the simulation.*/
public class TestSchelling {
	public static void main(String[] args) {

		// Parameters of the simulation
		int n = 200; // number of cells vertically
		int m = 200; // number of cells horizontally
		int nbState = 3; // number of possible states for a cell (empty not included)
		int K = 3; // number of neighboors threshold
		int initialProbability = 50; // probability (percentage) for a cell not to start empty

		SchellingCells cells = new SchellingCells(n, m, initialProbability, nbState, K);

		GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);
		gui.setSimulable(new CellsSimulator(gui, cells));
	}
}
