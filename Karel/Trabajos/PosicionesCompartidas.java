import java.util.HashSet;
import java.util.Set;

public class PosicionesCompartidas {
    private static final Set<String> posicionesOcupadas = new HashSet<>();

    private static String clave(int calle, int avenida) {
        return calle + "-" + avenida;
    }

    // Intenta ocupar una posición: solo lo logra si no está ocupada
    public synchronized static boolean ocupar(int calle, int avenida) {
        String key = clave(calle, avenida);
        if (!posicionesOcupadas.contains(key)) {
            posicionesOcupadas.add(key);
            return true;
        }
        return false;
    }

    // Espera hasta poder ocupar una posición
    public synchronized static void esperarYOcupar(int calle, int avenida) throws InterruptedException {
        String key = clave(calle, avenida);
        while (posicionesOcupadas.contains(key)) {
            PosicionesCompartidas.class.wait(); // espera pasiva hasta que se libere
        }
        posicionesOcupadas.add(key); // ahora sí la ocupa
    }

    // Libera una posición y notifica a los que esperan
    public synchronized static void liberar(int calle, int avenida) {
        String key = clave(calle, avenida);
        posicionesOcupadas.remove(key);
        PosicionesCompartidas.class.notifyAll(); // notifica a todos los que esperan
    }
}
