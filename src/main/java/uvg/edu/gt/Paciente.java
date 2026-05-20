package uvg.edu.gt;

/**
 * Hospital patient with name, symptom, and emergency code (A = highest priority, E = lowest).
 */
public class Paciente implements Comparable<Paciente> {

    private final String nombre;
    private final String sintoma;
    private final char   codigo;

    /**
     * @param codigo must be A–E
     * @throws IllegalArgumentException if code is outside A–E
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

    /** @return emergency code (A–E) */
    public char   getCodigo()  { return codigo; }

    /** A sorts before E (higher urgency = smaller value). */
    @Override
    public int compareTo(Paciente other) {
        return Character.compare(this.codigo, other.codigo);
    }

    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + codigo;
    }
}
