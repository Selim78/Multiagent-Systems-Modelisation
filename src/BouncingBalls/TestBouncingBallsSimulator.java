import gui.GUISimulator;
import java.awt.Color;

/** Initialises the parameters and launches the simulation.*/
public class TestBouncingBallsSimulator {
	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.WHITE);

		// Parameters of the simulation
		int nbBouncingBalls = 50; // number of balls

		gui.setSimulable(new BouncingBallsSimulator(gui, nbBouncingBalls));
	}
}
