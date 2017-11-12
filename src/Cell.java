/** A unique cell.*/
public class Cell {
	private int state; // current state of the cell at the step t
	private int nextState; // state the cell will be in at the step t+1
	private int nbState; // number of possible states 0 .. nbState-1

	public Cell(int state, int nbState) {
		this.state = state;
		this.nbState = nbState;
	}

	/** Gets current state of the cell.
	 * @return An int representing current state of the cell.
	*/
	public int getState() {
		return this.state;
	}

	/** Gets next state of the cell.
	 * @return An int representing next state of the cell.
	*/
	public int getNextState() {
		return this.nextState;
	}

	/** Sets the current state of a cell
	 * @param state An int representing current state of the cell.
	*/
	public void setState(int state) {
		this.state = state;
	}

	/** Sets the next state of a cell
	 * @param nextState An int representing next state of the cell.
	*/
	public void setNextState(int nextState) {
		this.nextState = nextState;
	}

	/** Sets the number of state the cell can take.
	 * @return An int representing the number of state the cell can take.
	*/
	public int getNbState() {
		return this.nbState;
	}

	@Override
	public String toString() {
		return "Cell[state=" + this.state + "]";
	}
}
