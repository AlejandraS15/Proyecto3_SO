public class TurnosSalida {
    private int turnoActual = 0;

    public synchronized void esperarTurno(int miTurno) {
        while (turnoActual != miTurno) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void siguienteTurno() {
        turnoActual++;
        notifyAll();
    }
}
