package uvg.edu.gt;

import java.util.ArrayList;

/**
 * Min-heap implementation of {@link PriorityQueue} backed by an {@link ArrayList}.
 * The element with the lowest natural order (highest priority) is always at the root.
 *
 * @param <E> element type, must implement {@link Comparable}
 */
public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    private final ArrayList<E> data;

    /** Constructs an empty heap. */
    public VectorHeap() {
        data = new ArrayList<>();
    }

    // ── index helpers ──────────────────────────────────────────────────────────

    private static int parent(int i)      { return (i - 1) / 2; }
    private static int leftChild(int i)   { return 2 * i + 1; }
    private static int rightChild(int i)  { return 2 * i + 2; }

    // ── heap operations ────────────────────────────────────────────────────────

    /**
     * Moves the element at {@code index} up the tree until the heap property is restored.
     * Called after an insertion at the last position.
     */
    private void percolateUp(int index) {
        while (index > 0) {
            int p = parent(index);
            if (data.get(index).compareTo(data.get(p)) < 0) {
                swap(index, p);
                index = p;
            } else {
                break;
            }
        }
    }

    /**
     * Moves the element at {@code index} down the tree until the heap property is restored.
     * Called after removing the root and placing the last element there.
     */
    private void pushDown(int index) {
        int size = data.size();
        while (true) {
            int smallest = index;
            int left  = leftChild(index);
            int right = rightChild(index);

            if (left < size && data.get(left).compareTo(data.get(smallest)) < 0) {
                smallest = left;
            }
            if (right < size && data.get(right).compareTo(data.get(smallest)) < 0) {
                smallest = right;
            }

            if (smallest == index) break;

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        E tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }

    // ── PriorityQueue interface ────────────────────────────────────────────────

    /**
     * {@inheritDoc}
     * Inserts the element at the end and percolates it up. O(log n).
     */
    @Override
    public void add(E value) {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    /**
     * {@inheritDoc}
     * Returns the root element without removing it. O(1).
     *
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    @Override
    public E peek() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Priority queue is empty");
        return data.get(0);
    }

    /**
     * {@inheritDoc}
     * Swaps the root with the last element, removes the last, then pushes the
     * new root down. O(log n).
     *
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    @Override
    public E remove() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Priority queue is empty");
        E min = data.get(0);
        int last = data.size() - 1;
        data.set(0, data.get(last));
        data.remove(last);
        if (!isEmpty()) pushDown(0);
        return min;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        return data.size();
    }
}
