package uvg.edu.gt;

/**
 * Represents a hospital patient with a name, symptom description, and emergency
 * priority code (A = highest … E = lowest).
 *
 * <p>The natural ordering is defined so that {@code A &lt; B &lt; C &lt; D &lt; E},
 * meaning a patient with code {@code A} is dequeued first from a min-heap.
 */
public class Paciente implements Comparable<Paciente> {

    private final String nombre;
    private final String sintoma;
    private final char   codigo;   // 'A'..'E'

    /**
     * Creates a new patient.
     *
     * @param nombre  full name
     * @param sintoma symptom description
     * @param codigo  emergency code, must be one of {@code A–E}
     * @throws IllegalArgumentException if {@code codigo} is outside {@code A–E}
     */
    public Paciente(String nombre, String sintoma, char codigo) {
        if (codigo < 'A' || codigo > 'E') {
            throw new IllegalArgumentException("Invalid emergency code: " + codigo);
        }
        this.nombre  = nombre.trim();
        this.sintoma = sintoma.trim();
        this.codigo  = Character.toUpperCase(codigo);
    }

    /** @return patient's full name */
    public String getNombre()  { return nombre; }

    /** @return symptom description */
    public String getSintoma() { return sintoma; }

    /** @return emergency priority code (A–E) */
    public char   getCodigo()  { return codigo; }

    /**
     * Compares patients by emergency code so that {@code A} (highest urgency)
     * sorts before {@code E} (lowest urgency).
     *
     * @param other the other patient
     * @return negative if {@code this} has higher priority, positive if lower
     */
    @Override
    public int compareTo(Paciente other) {
        return Character.compare(this.codigo, other.codigo);
    }

    /** Returns a human-readable representation matching the expected output format. */
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigo;
    }
}
