import java.util.Queue;
import java.util.LinkedList;

public class GestorDeTurnos {
    private final Queue<Racer> colaEstrella = new LinkedList<>();
    private final Queue<Racer> colaNiquia = new LinkedList<>();

    public synchronized void registrarSalida(Racer r, String origen) {
        if (origen.equals("estrella")) {
            colaEstrella.add(r);
        } else if (origen.equals("niquia")) {
            colaNiquia.add(r);
        }
    }

    public synchronized void esperarTurno(Racer r, String origen) {
        Queue<Racer> cola = origen.equals("estrella") ? colaEstrella : colaNiquia;

        while (cola.peek() != r) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        cola.poll(); // Sale de la cola porque es su turno
    }

    public synchronized void notificarTurno() {
        notifyAll(); // Despierta a todos para revisar si ya es su turno
    }
}

