import kareltherobot.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
public class MiPrimerRobot implements Directions{
    public static volatile boolean cierre = false;
    public static void main(String [] args){
        //Configuraciones
        World.readWorld("MetroMed.kwld");
        World.setVisible(true);
        World.setDelay(10);
        ControlSalida control = new ControlSalida();

        //Robots
        Racer lineaA1 = new Racer(32, 14, East, 0, Color.BLUE, control, "niquia");
        Racer lineaA2 = new Racer(33, 14, South, 0, Color.BLUE,control, "estrella");
        Racer lineaB1 = new Racer(34, 14, East, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA3 = new Racer(34, 13, East, 0, Color.BLUE, control, "niquia");
        Racer lineaA4 = new Racer(34, 12, East, 0, Color.BLUE, control, "estrella");
        Racer lineaB2 = new Racer(34, 11, East, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA5 = new Racer(34, 10, East, 0, Color.BLUE,control, "niquia");
        Racer lineaA6 = new Racer(34, 9, East, 0, Color.BLUE,control, "estrella");
        Racer lineaB3 = new Racer(34, 8, East, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA7 = new Racer(34, 7, East, 0, Color.BLUE, control, "niquia");
        Racer lineaA8 = new Racer(34, 6, East, 0, Color.BLUE,control, "estrella");
        Racer lineaB4 = new Racer(34, 5, East, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA9 = new Racer(34, 4, East, 0, Color.BLUE, control, "niquia");
        Racer lineaA10 = new Racer(34, 3, East, 0, Color.BLUE, control, "estrella");
        Racer lineaB5 = new Racer(34, 2, East, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA11 = new Racer(34, 1, East, 0, Color.BLUE,control, "niquia");
        Racer lineaA12 = new Racer(35, 1, West, 0, Color.BLUE,control, "estrella");
        Racer lineaB6 = new Racer(35, 2, West, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA13 = new Racer(35, 3, West, 0, Color.BLUE,control, "niquia");
        Racer lineaA14 = new Racer(35, 4, West, 0, Color.BLUE,control, "estrella");
        Racer lineaB7 = new Racer(35, 5, West, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA15 = new Racer(35, 6, West, 0, Color.BLUE,control, "estrella");
        Racer lineaA16 = new Racer(35, 7, West, 0, Color.BLUE,control, "estrella");
        Racer lineaB8 = new Racer(35, 8, West, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA17 = new Racer(35, 9, West, 0, Color.BLUE,control, "estrella");
        Racer lineaA18 = new Racer(35, 10, West, 0, Color.BLUE,control, "estrella");
        Racer lineaB9 = new Racer(35, 11, West, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA19 = new Racer(35, 12, West, 0, Color.BLUE,control, "estrella");
        Racer lineaA20 = new Racer(35, 13, West, 0, Color.BLUE,control, "estrella");
        Racer lineaB10 = new Racer(35, 14, West, 0, Color.GREEN,control, "sanjavier");
        Racer lineaA21 = new Racer(35, 15, West, 0, Color.BLUE,control, "estrella");
        Racer lineaA22 = new Racer(34, 15, North, 0, Color.BLUE,control, "estrella");

        //Salida
        ArrayList<Racer> robots = new ArrayList<>();
        Collections.addAll(robots, lineaA1, lineaA2, lineaB1, lineaA3, lineaA4, lineaB2, lineaA5, lineaA6,lineaB3,lineaA7,lineaA8,lineaB4,lineaA9,lineaA10,lineaB5, lineaA11, lineaA12,lineaB6,lineaA13,lineaA14,lineaB7,lineaA15,lineaA16,lineaB8,lineaA17,lineaA18,lineaB9,lineaA19,lineaA20,lineaB10,lineaA21,lineaA22);
        TurnosSalida turnos = new TurnosSalida();
        for (int i = 0; i < robots.size(); i++) {
            Racer r = robots.get(i);
            int turno = i;
            new Thread(() -> iniciarViaje(r, turno, turnos)).start();
        }

        ArrayList<Racer> trenesDesdeSanAntonio = new ArrayList<>();
        Collections.addAll(trenesDesdeSanAntonio, lineaB1, lineaB2, lineaB3,lineaB4,lineaB5,lineaB6,lineaB7,lineaB8,lineaB9,lineaB10);
        ArrayList<Racer> treneslineaA = new ArrayList<>();
        Collections.addAll(treneslineaA,lineaA1,lineaA2,lineaA3,lineaA4,lineaA5,lineaA6,lineaA7,lineaA8,lineaA9,lineaA10,lineaA11,lineaA12,lineaA13,lineaA14,lineaA15,lineaA16,lineaA17,lineaA18,lineaA19,lineaA20,lineaA21,lineaA22);
        ZonaCritica cisneros = new ZonaCritica(1);
        ZonaCritica salidaSanJavier = new ZonaCritica(1);
        //ZonaCritica zonaLineaA = new ZonaCritica(1);
        ControlGeneral control1 = new ControlGeneral();
        Scanner scanner = new Scanner(System.in);
        GestorDeTurnos gestor = new GestorDeTurnos();
        
        System.out.println("Ingrese la hora");
        int hora = scanner.nextInt();
        scanner.nextLine();
        Thread lineaA;
        Thread lineaB;
        Cierre cierreTrenes = new Cierre();
        
        Thread cierreHilo = new Thread(cierreTrenes);
        cierreHilo.start();
        if (hora == 420) {
            lineaB = new Thread(() -> {
                for (Racer r : trenesDesdeSanAntonio) {
                    while (!(r.getAvenida() == 1 && r.getCalle() == 16)) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    new Thread(() -> recorridoLineaBLoop(r, salidaSanJavier, cisneros, control1)).start();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            });

            lineaA = new Thread(() -> {
                for (Racer r : treneslineaA) {
                    if (cierre) {
                    System.out.println("Hilo lineaA detenido por cierre");
                    tallerlineaA(r);
                    return;
                }
                    new Thread(() -> recorridoLineaALoop(r, control1, gestor, r.getDestino())).start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            // Lanza los hilos de los trenes
            lineaA.start();
            lineaB.start();
        }
    }
    private static void iniciarViaje(Racer r, int turno, TurnosSalida turnos) {
        turnos.esperarTurno(turno);
        r.getControl().esperarTurno();
        salida(r);
        r.getControl().liberarSalida();
        turnos.siguienteTurno();

        switch (r.getDestino()) {
            case "niquia":
                niquia(r);
                break;
            case "estrella":
                estrella(r);
                break;
            case "sanjavier":
                sanJavier(r);
                break;
        }
    }
    public static void salida(Racer r) {
        if(r.getCalle()==34 && r.getAvenida()==15){
            r.move();
            r.turnLeft();
            while (r.frontIsClear()) {
                r.move();
            }
            r.turnLeft();
            r.move();
            r.turnLeft();
            while (r.frontIsClear()) {
                r.move();
            }
            r.turnRight();
            r.move();
            r.move();
            r.turnLeft();
            r.move();
        }else{
            if(r.getCalle()==33 && r.getAvenida()==14){
                r.move();
                r.turnLeft();
                r.move();
            }else{
                if(r.getCalle()==32 && r.getAvenida()==14){
                    r.move();
                }else{
                    if(r.getCalle()==35){
                        while (r.frontIsClear()) {
                            r.move();
                        }
                        r.turnLeft();
                        r.move();
                        r.turnLeft();
                        while (r.frontIsClear()) {
                            r.move();
                        }
                        r.turnRight();
                        r.move();
                        r.move();
                        r.turnLeft();
                        r.move();
                    }else{
                        if(r.getCalle()==34){
                            while (r.frontIsClear()) {
                                r.move();
                            }
                            r.turnRight();
                            r.move();
                            r.move();
                            r.turnLeft();
                            r.move();                
                        }
                    }
            
                }
            }
        }
          
    }

    public static void niquia(Racer r){
        r.move();
        r.move();
        r.turnLeft();
        while (r.frontIsClear()) {
            r.move();
        }
        r.turnRight();
        while (r.frontIsClear()) {
            r.move();
        }
        r.turnLeft();
        r.move();
        r.turnLeft();
        r.move();
    }
    
    public static void estrella(Racer r){
        r.move();
        r.turnRight();
        int i = 0;
        while(i<3){
            if(r.frontIsClear()){
                r.move();
            }else{
                r.turnRight();
                while (r.frontIsClear()) {
                    r.move();
                }
                r.turnLeft();
                i = i+1;
            }
        }
        int j=0;
        while(j<5){
            r.move();
            j++;
        }
        r.turnLeft();
        while (r.frontIsClear()) {
            r.move();
        }
        r.turnRight();
        int k = 0;
        while(k<3){
            if(r.frontIsClear()){
                r.move();
            }else{
                r.turnRight();
                while (r.frontIsClear()) {
                    r.move();
                }
                r.turnLeft();
                k = k+1;
            }
        }
        r.move();
        r.turnLeft();
        r.move();
    }

    public static void sanJavier(Racer r){
        r.move();
        r.turnRight();
        int i = 0;
        while(i<3){
            if(r.frontIsClear()){
                r.move();
            }else{
                r.turnRight();
                while (r.frontIsClear()) {
                    r.move();
                }
                r.turnLeft();
                i++;
            }
        }
        int j=0;
        while(j<9){
            r.move();
            j++;
        }
        r.turnRight();
        int k = 0;
        while(k<2){
            if(r.frontIsClear()){
                r.move();
            }else{
                r.turnRight();
                while (r.frontIsClear()) {
                    r.move();
                }
                r.turnLeft();
                k++;
            }
        }
        r.move();
        r.turnLeft();
        r.move();
    }

    public static void niquiaToEstrella(Racer r) {
        int i = 0;
        while (i < 3) {
            if (r.frontIsClear()) {
                r.move();
                pausarSiBeeper(r);
            } else {
                r.turnLeft();
                while (r.frontIsClear()) {
                    r.move();
                    pausarSiBeeper(r);
                }
                r.turnRight();
                i = i + 1;
            }
        }
    
        r.move();
        pausarSiBeeper(r);
        r.move();
        pausarSiBeeper(r);
        r.turnLeft();
    
        while (!r.nextToABeeper()) {
            r.move();
            pausarSiBeeper(r);
        }
    
        r.move();
        pausarSiBeeper(r);
        r.move();
        pausarSiBeeper(r);
        r.turnLeft();
    
        while (r.frontIsClear()) {
            r.move();
            pausarSiBeeper(r);
        }
    
        r.turnRight();
        int j = 0;
        while (j < 2) {
            if (r.frontIsClear()) {
                r.move();
                pausarSiBeeper(r);
            } else {
                r.turnRight();
                while (r.frontIsClear()) {
                    r.move();
                    pausarSiBeeper(r);
                }
                r.turnLeft();
                j = j + 1;
            }
        }
    
        r.move();
        pausarSiBeeper(r);
        r.move();
        pausarSiBeeper(r);
        r.move();
        pausarSiBeeper(r);
        r.turnRight();
        r.move();
        pausarSiBeeper(r);
        r.move();
        r.turnLeft();
        r.move();
        r.turnLeft();
        r.move();
    }
    
    public static void estrellaToNiquia(Racer r) {
        int i = 0;
        while (i < 3) {
            if (r.frontIsClear()) {
                r.move();
                pausarSiBeeper(r);
            } else {
                r.turnLeft();
                while (r.frontIsClear()) {
                    r.move();
                    pausarSiBeeper(r);
                }
                r.turnRight();
                i = i + 1;
            }
        }
    
        r.turnLeft();
        r.turnLeft();
        for (int j = 0; j < 5; j++) {
            r.move();
            pausarSiBeeper(r);
        }
    
        r.turnRight();
        int k = 0;
        while (k < 4) {
            if (r.frontIsClear()) {
                r.move();
                pausarSiBeeper(r);
            } else {
                r.turnRight();
                while (r.frontIsClear()) {
                    r.move();
                    pausarSiBeeper(r);
                }
                r.turnLeft();
                k = k + 1;
            }
        }
        r.move();
        pausarSiBeeper(r);
        r.turnLeft();
        r.move();

    }
    
public static void sanJavierToSanAntonio(Racer r, ZonaCritica salidaSanJavier, ZonaCritica cisneros) {
    salidaSanJavier.block(); 
    int i = 0;
    try {
        r.move(); 
        r.move();
    } finally {
        salidaSanJavier.unblock();
    }

    while (i < 2) {
        if (r.frontIsClear()) {
            r.move();
            pausarSiBeeper(r);
        } else {
            r.turnLeft();
            while (r.frontIsClear()) {
                r.move();
                pausarSiBeeper(r);
            }
            r.turnRight();
            i++;
        }
    }
    cisneros.block();
    try {
        r.turnLeft(); r.turnLeft();
        r.move(); pausarSiBeeper(r);
        r.turnRight(); r.move(); pausarSiBeeper(r);
    } finally {
        cisneros.unblock();
    }
        
}

    public static void SanAntonioToSanJavier(Racer r, ZonaCritica zona) {
    zona.block();
    try {
        r.turnLeft();
        r.turnLeft();
        r.move();
        r.move();
    } finally {
        zona.unblock();
    }

    r.move();
    r.move();
    int j = 0;
    while (j < 2) {
        if (r.frontIsClear()) {
            r.move();
            pausarSiBeeper(r);
        } else {
            r.turnRight();
            while (r.frontIsClear()) {
                r.move();
                pausarSiBeeper(r);
            }
            r.turnLeft();
            j++;
        }
    }
    r.move();
    r.turnLeft();
    r.move();
}

    public static void recorridoLineaBLoop(Racer r, ZonaCritica salidaSanJavier, ZonaCritica cisneros, ControlGeneral control) {
        while (control.seguirEjecutando()) { 
            if(!cierre){
                sanJavierToSanAntonio(r,salidaSanJavier,cisneros);
                SanAntonioToSanJavier(r,cisneros);
            }else{
                tallerLineaB(r);
            }
        }
    }

    public static void recorridoLineaALoop(Racer r, ControlGeneral control, GestorDeTurnos gestor, String origenInicial) {
        String origen = origenInicial;
        String destino = origen.equals("estrella") ? "niquia" : "estrella";

        while (control.seguirEjecutando()) {
            gestor.registrarSalida(r, origen); 
            gestor.esperarTurno(r, origen); 

            
            if (origen.equals("estrella")) {
                if(!cierre){
                    estrellaToNiquia(r);
                }else{
                    tallerlineaA(r);
                }
            } else {
                if(!cierre){
                    niquiaToEstrella(r);
                }else{
                    tallerlineaA(r);
                }
                
            }

            String temp = origen;
            origen = destino;
            destino = temp;

            gestor.notificarTurno(); 
        }
    }

    public static void tallerlineaA(Racer r){
        if(r.getCalle()==1 && r.getAvenida()==11){
            int i = 0;
            while (i < 3) {
                if (r.frontIsClear()) {
                    r.move();
                } else {
                    r.turnLeft();
                    while (r.frontIsClear()) {
                        r.move();
                    }
                    r.turnRight();
                    i = i + 1;
                }
            }
            r.turnLeft();
            r.turnLeft();
            for (int j = 0; j < 5; j++) {
                r.move();
            }
            r.turnRight();
            int k = 0;
            while (k < 3) {
                if (r.frontIsClear()) {
                    r.move();
                } else {
                    r.turnRight();
                    while (r.frontIsClear()) {
                        r.move();
                    }
                    r.turnLeft();
                    k = k + 1;
                }
            }
            int s=0;
            while(s<5){
                r.move();
            }
            r.turnRight();
            r.move();
            r.move();
            r.turnLeft();
            r.move();
            r.move();
            r.turnLeft();
            while (r.frontIsClear()) {
                r.move();
            }

        }else{
            if(r.getCalle()==35 && r.getAvenida()==19){
                while(r.frontIsClear()) {
                    r.move();
                }
                r.turnLeft();
                r.move();
                r.move();
                r.turnRight();
                r.move();
                r.turnRight();

                r.move();
                r.move();
                r.turnLeft();
                while (r.frontIsClear()) {
                    r.move();
                }
                r.turnRight();
                while (r.frontIsClear()) {
                    r.move();
                }
                r.turnLeft();
                r.move();
                r.turnLeft();
                while (r.frontIsClear()) {
                    r.move();
                }
                r.turnLeft();

                while (r.frontIsClear()) {
                    r.move();
                }
                r.turnRight();
                r.move();
                r.move();
                r.turnLeft();
                r.move();
            }
        }
    }
    public static void tallerLineaB(Racer r){
        if(r.getCalle()==16 && r.getAvenida()==1){
            System.out.println("ENTRO");
            int i = 0;
            r.move(); 
            r.move();
            r.turnLeft();
            while (r.frontIsClear()) {
                r.move();
            }
            r.turnRight();
            r.move();
            r.turnLeft();
            while(i<5){
                r.move();
                i++;
            }
            r.turnLeft();
            int j=0;
            while(j<5){
                r.move();
                j++;
            }
            r.turnRight();
            r.move();
            r.turnLeft();
            int k = 0;
            while (k < 3) {
                if (r.frontIsClear()) {
                    r.move();
                } else {
                    r.turnRight();
                    while (r.frontIsClear()) {
                        r.move();
                    }
                    r.turnLeft();
                    k = k + 1;
                }
            }
            int f=0;
            while(f<5){
                r.move();
                f++;
            }
            r.turnLeft();
            r.move();
            r.move();
            r.turnRight();
            while (r.frontIsClear()) {
                r.move();
            }
            r.turnLeft();
            while (r.frontIsClear()) {
                r.move();
            }
            r.turnLeft();
            r.move();
            r.turnLeft();
            while (r.frontIsClear()) {
                r.move();
            }
            r.turnRight();
            r.move();


        }
    }
    
    
    private static void pausarSiBeeper(Racer r) {
        if (r.nextToABeeper()) {
            try {
                Thread.sleep(1000); // 3000 milisegundos = 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    
    
}