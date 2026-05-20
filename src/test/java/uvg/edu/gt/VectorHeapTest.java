package uvg.edu.gt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link VectorHeap}.
 */
class VectorHeapTest {

    private VectorHeap<Paciente> heap;

    @BeforeEach
    void setUp() {
        heap = new VectorHeap<>();
    }

    // ── isEmpty / size ─────────────────────────────────────────────────────────

    @Test
    void newHeapIsEmpty() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void notEmptyAfterInsert() {
        heap.add(new Paciente("A", "s", 'C'));
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
    }

    // ── insertion ─────────────────────────────────────────────────────────────

    @Test
    void singleInsertPeekEqualsInserted() {
        Paciente p = new Paciente("Juan", "fiebre", 'B');
        heap.add(p);
        assertSame(p, heap.peek());
    }

    @Test
    void insertOrderAscendingCodesMinAtRoot() {
        heap.add(new Paciente("p1", "s1", 'C'));
        heap.add(new Paciente("p2", "s2", 'A'));
        heap.add(new Paciente("p3", "s3", 'E'));

        assertEquals('A', heap.peek().getCodigo());
    }

    @Test
    void insertOrderDescendingCodesMinAtRoot() {
        heap.add(new Paciente("p1", "s1", 'E'));
        heap.add(new Paciente("p2", "s2", 'D'));
        heap.add(new Paciente("p3", "s3", 'A'));

        assertEquals('A', heap.peek().getCodigo());
    }

    // ── removal ───────────────────────────────────────────────────────────────

    @Test
    void removeReturnsPriorityOrder() {
        heap.add(new Paciente("Juan",    "fractura",     'C'));
        heap.add(new Paciente("Maria",   "apendicitis",  'A'));
        heap.add(new Paciente("Lorenzo", "chikunguya",   'E'));
        heap.add(new Paciente("Carmen",  "parto",        'B'));

        assertEquals('A', heap.remove().getCodigo());
        assertEquals('B', heap.remove().getCodigo());
        assertEquals('C', heap.remove().getCodigo());
        assertEquals('E', heap.remove().getCodigo());
        assertTrue(heap.isEmpty());
    }

    @Test
    void removeDecrementsSize() {
        heap.add(new Paciente("p1", "s1", 'B'));
        heap.add(new Paciente("p2", "s2", 'A'));
        heap.remove();
        assertEquals(1, heap.size());
    }

    @Test
    void removeFromEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> heap.remove());
    }

    @Test
    void peekFromEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> heap.peek());
    }

    // ── duplicate priorities ──────────────────────────────────────────────────

    @Test
    void duplicatePrioritiesAllDequeued() {
        heap.add(new Paciente("p1", "s1", 'B'));
        heap.add(new Paciente("p2", "s2", 'B'));
        heap.add(new Paciente("p3", "s3", 'B'));

        assertEquals('B', heap.remove().getCodigo());
        assertEquals('B', heap.remove().getCodigo());
        assertEquals('B', heap.remove().getCodigo());
        assertTrue(heap.isEmpty());
    }

    // ── Paciente.compareTo ────────────────────────────────────────────────────

    @Test
    void pacienteCompareToHigherPriorityIsLess() {
        Paciente a = new Paciente("x", "y", 'A');
        Paciente c = new Paciente("x", "y", 'C');
        assertTrue(a.compareTo(c) < 0);
        assertTrue(c.compareTo(a) > 0);
        assertEquals(0, a.compareTo(new Paciente("z", "w", 'A')));
    }

    @Test
    void pacienteInvalidCodeThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Paciente("x", "y", 'Z'));
    }
}
