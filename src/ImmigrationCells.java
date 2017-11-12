/** Group of cells updated according to the Immigration model.*/
public class ImmigrationCells extends AbstractCells {

	public ImmigrationCells(int n, int m, int initialProbability, int nbState, int K) {
		super(n, m, initialProbability, nbState, K);
	}

	/** Computes the number of neighboors of the cell (i, j) that are in the
	* appropriate state (as demanded by the model).
	* Returns true if there are enough neighboors.
	* @param i Row of the cell.
	* @param j Column of the cell.
	* @return True if cell (i, j) has more than K neighboors in the state k+1 % `nbState` (where k is the current state).
	*/
	@Override
	public Boolean enoughNeighboors(int i, int j) {
		// count of the number of neighboors
		int count = 0;
		int targetState;

		for (int k = Math.max(0, i-1); k < Math.min(this.m, i+2); k++) {
			for (int l = Math.max(0, j-1); l < Math.min(this.n, j+2); l++) {

				targetState = (this.getState(i, j) + 1) % this.nbState;

				// if the current cell is one of the neighboors and is in the target state
				if ((k != i || l != j) && this.getState(k, l) == targetState) {
					count++;
				}
			}
		}
		return count >= this.K;
	}

	/** Computes and returns the next state of a cell according to the model.
	* @param currentState An int representing current state of the cell.
	* @param enoughNeighboors True if cell has more than K neighboors in the state k+1 % `nbState` (where k is the current state).
	* @return An int representing next state of the cell.
	*/
	@Override
	public int nextState(int currentState, Boolean enoughNeighboors) {
		// Computes and returns the next state of a cell
		int nextState;

		if (enoughNeighboors) {
			nextState = (currentState + 1) % this.nbState;
		} else {
			nextState = currentState;
		}

		return nextState;
	}
}
