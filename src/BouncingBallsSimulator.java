import gui.Simulable;
import java.util.ArrayList;

import java.awt.Color;
import gui.Oval;
import gui.GUISimulator;

/** A simulable group of bouncing balls.*/
public class BouncingBallsSimulator implements Simulable {
	private BouncingBalls balls;
	private GUISimulator gui;

	public BouncingBallsSimulator(GUISimulator gui, int nbBouncingBalls) {
		this.gui = gui;
		this.balls = new BouncingBalls(nbBouncingBalls);
	}

	public int size() {
		return this.balls.size();
	}

	public BouncingBall get(int i) {
		return this.balls.get(i);
	}

	@Override
	public void next(){
		this.gui.reset();

		this.balls.translate(gui.getPanelWidth(), gui.getPanelHeight());

		// trace
		//System.out.println(this.balls);

		for (int i = 0; i < this.balls.size(); i++) {
			BouncingBall b = this.balls.get(i);
			Color color = b.color();
			int size = b.radius();
			this.gui.addGraphicalElement(new Oval(b.x(), b.y(), color, color, size));
		}

	}

	@Override
	public void restart(){
		this.balls.reInit();
		this.gui.reset();
	}

}
