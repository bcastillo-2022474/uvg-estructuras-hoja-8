package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Entry point for the patient-attention system using the custom {@link VectorHeap}.
 *
 * <p>Reads patients from {@code pacientes.txt} (same directory as the JAR / working
 * directory), inserts them into a {@link VectorHeap}, then removes and prints them
 * in priority order.
 */
public class MainVectorHeap {

    public static void main(String[] args) {
        VectorHeap<Paciente> queue = new VectorHeap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.err.println("Skipping malformed line: " + line);
                    continue;
                }
                String nombre  = parts[0].trim();
                String sintoma = parts[1].trim();
                char   codigo  = parts[2].trim().charAt(0);
                queue.add(new Paciente(nombre, sintoma, codigo));
            }
        } catch (IOException e) {
            System.err.println("Error reading pacientes.txt: " + e.getMessage());
            return;
        }

        System.out.println("=== Orden de atención (VectorHeap) ===");
        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}
