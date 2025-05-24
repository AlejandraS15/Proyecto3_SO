class ControlSalida {
    private boolean salidaOcupada = false;

    public synchronized void esperarTurno() {
        while (salidaOcupada) {
            try {
                wait();  // Esperar hasta que esté libre
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        salidaOcupada = true;  // Marcar la salida como ocupada
    }

    public synchronized void liberarSalida() {
        salidaOcupada = false;  // Marcar la salida como libre
        notifyAll();            // Despertar a los otros trenes
    }
}
