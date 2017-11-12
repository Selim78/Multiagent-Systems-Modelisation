/** Group of cells updated according to the Conway model.*/
public class ConwayCells extends AbstractCells {

	public ConwayCells(int n, int m, int initialProbability) {
		super(n, m, initialProbability, 2, 3);
	}


	/** Computes the number of neighboors of the cell (i, j) that are in the
	* appropriate state (as demanded by the model).
	* Returns true if there are enough neighboors.
	* @param i Row of the cell.
	* @param j Column of the cell.
	* @return True if cell (i, j) has more than K neighboors alive.
	*/
	@Override
	public Boolean enoughNeighboors(int i, int j) {

		// count of the number of neighboors
		int count = 0;
		int targetState;

		for (int k = Math.max(0, i-1); k < Math.min(this.m, i+2); k++) {
			for (int l = Math.max(0, j-1); l < Math.min(this.n, j+2); l++) {

				targetState = 1;

				// if the current cell is one of the neighboors and is in the target state
				if ((k != i || l != j) && this.getState(k, l) == targetState) {
					count++;
				}
			}
		}
		return count >= this.K;
	}

	@Override
	public int nextState(int currentState, Boolean enoughNeighboors) {

		int nextState;

		if (enoughNeighboors) {
			nextState = 1;
		} else {
			nextState = 0;
		}

		return nextState;
	}
}
