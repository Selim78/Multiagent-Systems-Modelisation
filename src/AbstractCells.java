import java.util.ArrayList;
import java.util.Random;

/** Group of cells of undertemined model.*/
public abstract class AbstractCells {
	protected ArrayList<Cell> list; // list of cells
	protected int n; // height in cells
	protected int m; // width in cells
	protected int nbState; // number of possible states for a cell
	protected int K; // number of neighboors threshold
	protected int initialProbability; // probability that each state will be activated initially

	public AbstractCells(int n, int m, int initialProbability, int nbState, int K) {
		this.n = n;
		this.m = m;
		this.nbState = nbState;
		this.K = K;
		this.initialProbability = initialProbability;
		this.list = new ArrayList<Cell>();
		Random rand = new Random();
		for (int i = 0; i < n*m; i++) {
			this.addCell(rand);
		}
	}

	public int n() {
		return this.n;
	}

	public int m() {
		return this.m;
	}

	public Cell getCell(int i, int j) {
		return this.list.get(i * this.m + j);
	}

	public int getState(int i, int j) {
		return this.getCell(i, j).getState();
	}

	public int getNbState() {
		return this.nbState;
	}

	/** Adds a cell in a state superior strictly to 0 with a probability `initialProbability`.
	* @param rand We give the same Random iterator to all the cells to ensure randomness.
	*/
	public void addCell(Random rand) {

		Cell c = new Cell(0, this.nbState);

		if (rand.nextInt(100) <= this.initialProbability) {
			int s = 1 + rand.nextInt(this.nbState - 1); // s in [1..(nbState - 1)]
			c = new Cell(s, this.nbState);
		}

		this.list.add(c);
	}

	public void updateCurrentStates(){

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				Cell c = this.getCell(i, j);
				c.setState(c.getNextState());
			}
		}
	}


	/** Comuptes the next states of all the cells, and
	* assigns them to their nextState attribute
	*/
	public void updateNextStates() {

		Random rand = new Random();
		ShuffleIterator itr = new ShuffleIterator(this.n * this.m);

		while(itr.hasNext()){
			int randomIndex = itr.next();
			int i = randomIndex/this.m;
			int j = randomIndex%this.m;

			Cell c = this.getCell(i, j);

			int currentState = c.getState();
			int nextState = nextState(currentState, enoughNeighboors(i, j));
			c.setNextState(nextState);
		}
	}


	/** Computes the number of neighboors of the cell (i, j) that are in the
	* appropriate state (as demanded by the model).
	* Returns true if there are enough neighboors.
	* @param i Row of the cell.
	* @param j Column of the cell.
	* @return True if cell (i, j) has more than K neighboors of the appropriate type (as defined by the model).
	*/
	public abstract Boolean enoughNeighboors(int i, int j);

	/** Computes and returns the next state of a cell according to the model.
	* @param currentState An int representing current state of the cell.
	* @param enoughNeighboors True if cell has more than K neighboors of the appropriate type (as defined by the model).
	* @return An int representing next state of the cell.
	*/
	public abstract int nextState(int currentState, Boolean enoughNeighboors);



	public void reInit() {
		this.list = new ArrayList<Cell>();
		Random rand = new Random();
		for (int i = 0; i < n*m; i++) {
			this.addCell(rand);
		}
	}

	@Override
	public String toString() {
		String s = "\n[";

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				Cell c = this.getCell(i, j);
				s += c.toString();
				if (i != this.n - 1 || j != this.m - 1) {s += ", ";}
			}
			if (i == this.n - 1) {s += "\n";}
		}
		s += "] \n";

		return s;
	}
}
