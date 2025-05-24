import java.util.concurrent.*;
public class ZonaCritica {
    private final Semaphore sem;

    public ZonaCritica(int permisos) {
        this.sem = new Semaphore(permisos); 
    }

    public void block() {
        try {
            sem.acquire(); // espera a que se libere la zona
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void unblock() {
        sem.release(); // libera la zona
    }
}


