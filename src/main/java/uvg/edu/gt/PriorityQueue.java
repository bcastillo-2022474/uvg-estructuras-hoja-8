package uvg.edu.gt;

/**
 * ADT PriorityQueue: a collection where elements are removed in priority order
 * (smallest/highest-priority element first).
 *
 * @param <E> element type, must be Comparable
 */
public interface PriorityQueue<E extends Comparable<E>> {

    /**
     * Adds an element to the priority queue.
     *
     * @param value element to insert
     */
    void add(E value);

    /**
     * Returns (but does not remove) the element with highest priority.
     *
     * @return the minimum element
     */
    E peek();

    /**
     * Removes and returns the element with highest priority.
     *
     * @return the minimum element
     */
    E remove();

    /**
     * Returns {@code true} if the queue contains no elements.
     *
     * @return {@code true} when empty
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     *
     * @return size
     */
    int size();
}
