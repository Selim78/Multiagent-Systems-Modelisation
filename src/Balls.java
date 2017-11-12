import java.util.ArrayList;

/** Many balls. */
public class Balls {
	private ArrayList<Ball> list;

	public Balls() {
		this.list = new ArrayList<Ball>();
	}

	/** Building a group of nbBalls
	* @param nbBalls An int representing the number of balls.
	*/
	public Balls(int nbBalls) {
		this.list = new ArrayList<Ball>();
		for (int i = 0; i < nbBalls; i++) {
			this.add();
		}
	}

	public Ball get(int i) {
		return this.list.get(i);
	}

	public int size() {
		return this.list.size();
	}

	public void add() {
		Ball b = new Ball();
		this.list.add(b);
	}

	public void add(int x, int y) {
		Ball b = new Ball(x, y);
		this.list.add(b);
	}

	public Ball remove(int index) {
		return this.list.remove(index);
	}

	public boolean remove(Ball b) {
		return this.list.remove(b);
	}

	/** Translates the balls according to their direction and velocity.
	* If the ball meets a wall, it bouces.
	* @param  width of the frame
	* @param  height of the frame
	*/
	public void translate(int width, int height) {
		for (Ball b : this.list) {
			if (b.x() > width || b.x() < 0) {
				b.changeDirection(-1, 1);
			}
			if (b.y() > height || b.y() < 0) {
				b.changeDirection(1, -1);
			}
			b.translate();
		}
	}

	public void reInit() {
		for (Ball b : this.list) {
			b.reInit();
		}
	}

	@Override
	public String toString() {
		String s = "[";
		int i;

		for (i = 0; i < this.list.size() - 1; i++) {
			Ball b = this.list.get(i);
			s += b.toString() + ", ";
		}

		Ball b = this.list.get(this.list.size() - 1);
		s += b.toString() + "]";

		return s;
	}
}
