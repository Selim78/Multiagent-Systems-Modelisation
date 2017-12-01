import java.awt.Point;
import java.awt.Color;
import java.util.Random;

/**
* A ball that has a speed a size and a color
*/
public class BouncingBall {
	private Point ball;
	// we save the initial position
	private Point init;
	private Point speed;
	private Color color;
	private int radius;

	// constructors
	public BouncingBall() {
		Random rand = new Random();
		this.ball = new Point();
		this.init = new Point();
		this.speed = new Point(rand.nextInt(30), rand.nextInt(30));
		this.color = new Color(rand.nextFloat(),
							   rand.nextFloat(),
							   rand.nextFloat(),
							   (float) 0.5);
		this.radius = rand.nextInt(50);
	}

	public BouncingBall(int x, int y) {
		Random rand = new Random();
		this.ball = new Point(x, y);
		this.init = new Point(x, y);
		this.speed = new Point(rand.nextInt(50), rand.nextInt(50));
		this.color = new Color(rand.nextFloat(),
							   rand.nextFloat(),
							   rand.nextFloat());
		this.radius = rand.nextInt(50);
	}

	/** Changing direction of the ball. This is useful when the ball meets a wall.
	* @param dx The new direction along `x` will be `x*dx`
	* @param dy The new direction along `y` will be `y*dy`
	*/
	public void changeDirection(int dx, int dy) {
		this.speed.x *= dx;
		this.speed.y *= dy;
	}

	public int x() {
		return this.ball.x;
	}

	public int y() {
		return this.ball.y;
	}

	public int radius(){
		return this.radius;
	}

	public Color color(){
		return this.color;
	}

	public void translate() {
		this.ball.translate(this.speed.x, this.speed.y);
	}

	public void reInit() {
		this.ball = new Point(this.init);
	}

	@Override
	public String toString() {
		return "BouncingBall[x=" + ball.x + ", y=" + ball.y + "]";
	}
}
