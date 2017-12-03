import java.util.ArrayList;

/** Many balls. */
public class BouncingBalls {
	private ArrayList<BouncingBall> list;

	public BouncingBalls() {
		this.list = new ArrayList<BouncingBall>();
	}

	/** Building a group of nbBouncingBalls
	* @param nbBouncingBalls An int representing the number of balls.
	*/
	public BouncingBalls(int nbBouncingBalls) {
		this.list = new ArrayList<BouncingBall>();
		for (int i = 0; i < nbBouncingBalls; i++) {
			this.add();
		}
	}

	public BouncingBall get(int i) {
		return this.list.get(i);
	}

	public int size() {
		return this.list.size();
	}

	public void add() {
		BouncingBall b = new BouncingBall();
		this.list.add(b);
	}

	public void add(int x, int y) {
		BouncingBall b = new BouncingBall(x, y);
		this.list.add(b);
	}

	public BouncingBall remove(int index) {
		return this.list.remove(index);
	}

	public boolean remove(BouncingBall b) {
		return this.list.remove(b);
	}

	/** Translates the balls according to their direction and velocity.
	* If the ball meets a wall, it bouces.
	* @param  width of the frame
	* @param  height of the frame
	*/
	public void translate(int width, int height) {
		for (BouncingBall b : this.list) {
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
		for (BouncingBall b : this.list) {
			b.reInit();
		}
	}

	@Override
	public String toString() {
		String s = "[";
		int i;

		for (i = 0; i < this.list.size() - 1; i++) {
			BouncingBall b = this.list.get(i);
			s += b.toString() + ", ";
		}

		BouncingBall b = this.list.get(this.list.size() - 1);
		s += b.toString() + "]";

		return s;
	}
}
