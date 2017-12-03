import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collections;

/** Defines an Iterator that returns numbers between 0 and max in a random order.*/
public class ShuffleIterator implements Iterator<Integer> {
	private LinkedList<Integer> shuffledIndexes;
    private int max;

	/** @param max The values returned by the Iterator are in [0, max] */
    public ShuffleIterator(int max) {
        this.max = max;
		this.shuffledIndexes = new LinkedList<Integer>();
		for (int i = 0 ; i < max; i++) {
			this.shuffledIndexes.add(i);
		}
		Collections.shuffle(this.shuffledIndexes);
    }

    public boolean hasNext() {
        return this.shuffledIndexes.size() > 0;
    }

	public Integer next() {
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return this.shuffledIndexes.remove();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
