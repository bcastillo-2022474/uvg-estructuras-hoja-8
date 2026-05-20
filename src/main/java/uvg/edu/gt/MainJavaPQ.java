package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Entry point for the patient-attention system using {@link java.util.PriorityQueue}
 * from the Java Collections Framework.
 *
 * <p>Behaviour is identical to {@link MainVectorHeap} but delegates heap management
 * to the JDK's built-in implementation.
 */
public class MainJavaPQ {

    public static void main(String[] args) {
        java.util.PriorityQueue<Paciente> queue = new java.util.PriorityQueue<>();

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

        System.out.println("=== Orden de atención (java.util.PriorityQueue) ===");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
