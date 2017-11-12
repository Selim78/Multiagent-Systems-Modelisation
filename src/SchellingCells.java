import java.util.ArrayList;
import java.util.Random;

/** Group of cells updated according to the Schelling model.*/
public class SchellingCells extends AbstractCells {
	private ArrayList<Integer> empty; // empty cells (for schelling model)
								 // note: a cell is empty when state == 0

	public SchellingCells(int n, int m, int initialProbability, int nbState, int K) {
		super(n, m, initialProbability, ++nbState, K);
	}

	/** The superclass method is Overriden to take empty states into account */
	@Override
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

			// we update the set of empty cells
			if (nextState == 0 && currentState !=0) {
				// we move the cell that was previously here to another position
				// we select randomly a new position in among the empty cells
				if (this.empty.size() > 0) {
					int index = rand.nextInt(this.empty.size());
					int newPosition = this.empty.get(index);
					this.empty.remove(index);
					Cell newCell = this.getCell(newPosition / this.m, newPosition % this.m);

					newCell.setState(currentState);
					newCell.setNextState(currentState);
				} else {
					c.setNextState(currentState);
				}
			}
		}
	}


	/** Computes the number of neighboors of the cell (i, j) that are in the
	* appropriate state (as demanded by the model).
	* Returns true if there are enough neighboors.
	* @param i Row of the cell.
	* @param j Column of the cell.
	* @return True if the cell (i, j) has too much neighboors in different states than its one.
	*/
	@Override
	public Boolean enoughNeighboors(int i, int j) {
		// count of the number of neighboors
		int count = 0;
		int targetState;

		for (int k = Math.max(0, i-1); k < Math.min(this.m, i+2); k++) {
			for (int l = Math.max(0, j-1); l < Math.min(this.n, j+2); l++) {

					targetState = this.getState(i, j);

				// if the current cell is one of the neighboors and is not in the state of the cell (i, j) and is not null
				if (this.getState(i, j) != 0 && (k != i || l != j) && this.getState(k, l) != targetState && this.getState(k, l) != 0) {
					count++;
				}
			}
		}
		return count >= this.K;
	}


	/** Computes and returns the next state of a cell according to the model.
	* @param currentState An int representing current state of the cell.
	* @param enoughNeighboors True if the cell has too much neighboors in different states than its one.
	* @return An int representing next state of the cell.
	*/
	@Override
	public int nextState(int currentState, Boolean enoughNeighboors) {
		int nextState;

		if (enoughNeighboors) {
			nextState = 0;
		} else {
			nextState = currentState;
		}

		return nextState;
	}

	/** The superclass method is Overriden to take empty states into account */
	@Override
	public void addCell(Random rand) {


		if (this.empty == null) {
			this.empty = new ArrayList<Integer>();
		}

		Cell c = new Cell(0, this.nbState);

		if (rand.nextInt(100) <= this.initialProbability) {
			int s = 1 + rand.nextInt(this.nbState - 1); // s in [1..(nbState - 1)]
			c = new Cell(s, this.nbState);
		} else {
			int index = this.list.size();
			this.empty.add(index);
		}

		this.list.add(c);
	}

	/** The superclass method is Overriden to take empty states into account */
	@Override
	public void updateCurrentStates(){
		this.empty = new ArrayList<Integer>();

		for (int i = 0; i < this.n; i++) {
			for (int j = 0; j < this.m; j++) {
				Cell c = this.getCell(i, j);
				c.setState(c.getNextState());
				if (c.getState() == 0) {
					this.empty.add(i * this.m + j);
				}
			}
		}
	}

	/** The superclass method is Overriden to take empty states into account */
	@Override
	public void reInit() {
		this.list = new ArrayList<Cell>();
		this.empty = new ArrayList<Integer>();
		Random rand = new Random();
		for (int i = 0; i < n*m; i++) {
			this.addCell(rand);
		}
	}
}
