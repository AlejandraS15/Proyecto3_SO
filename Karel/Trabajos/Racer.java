import kareltherobot.*;
import java.awt.Color;
class Racer extends Robot implements Runnable{
    private int calleActual;
    private int avenidaActual;
    private ControlSalida control;
    private String destino;

    public Racer(int calle, int avenida, Direction direccion, int beepers, ControlSalida c, String destino) {
        super(calle, avenida, direccion, beepers);
        this.calleActual = calle;
        this.avenidaActual = avenida;
        this.control = c;
        this.destino = destino;
        World.setupThread(this);
    }
    public Racer(int street, int avenue, Direction direction, int beepers, Color badge,ControlSalida c, String destino) {
        super(street, avenue, direction, beepers, badge);
        this.calleActual = street;
        this.avenidaActual = avenue;
        this.control = c;
        this.destino = destino;
        World.setupThread(this);
    }
    
    @Override
    public void move() {
        if (!frontIsClear()) {
            return;
        }
        int nextCalle = calleActual;
        int nextAvenida = avenidaActual;

        // Calcular la próxima posición basada en la dirección actual
        if (facingNorth())
            nextCalle++;
        else if (facingSouth())
            nextCalle--;
        else if (facingEast())
            nextAvenida++;
        else if (facingWest())
            nextAvenida--;

        try {
            // Esperar hasta que la posición esté disponible y ocuparla
            PosicionesCompartidas.esperarYOcupar(nextCalle, nextAvenida);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return; // termina si el hilo fue interrumpido
        }

        // Liberar la posición actual antes de moverse físicamente
        PosicionesCompartidas.liberar(calleActual, avenidaActual);

        // Mover el robot físicamente
        
        super.move();

        // Actualizar la posición actual del robot
        calleActual = nextCalle;
        avenidaActual = nextAvenida;
    }


    public int getCalle() {
        return calleActual;
    }

    public int getAvenida() {
        return avenidaActual;
    }
    public ControlSalida getControl() {
        return control;
    }
    public String getDestino(){
        return destino;
    }
    public void race(){
        while(! nextToABeeper())
        move();
        pickBeeper();
        turnOff();
    }
    @Override
    public void run() {
        control.esperarTurno();         // Espera su turno para salir (sin colisiones)
        
        MiPrimerRobot.salida(this);     // Se ubica en la salida (debe usar PosicionesCompartidas)
        
        control.liberarSalida();        // Libera para que otro pueda salir

        // Luego hace su recorrido a destino
        if (destino.equals("niquia")) {
            MiPrimerRobot.niquia(this);
        } else if (destino.equals("estrella")) {
            MiPrimerRobot.estrella(this);
        } else if (destino.equals("sanjavier")) {
            MiPrimerRobot.sanJavier(this);
        }
    }

    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }
}
