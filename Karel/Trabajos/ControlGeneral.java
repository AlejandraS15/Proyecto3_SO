import java.util.Scanner;

public class ControlGeneral {
    private volatile boolean ejecutando = true;

    public boolean seguirEjecutando() {
        return ejecutando;
    }

    public void detener() {
        ejecutando = false;
        
    }
    public static void escucharEntrada(ControlGeneral control) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("11")) {
                System.out.println("Deteniendo robots...");
                control.detener(); // Cambia la bandera
                break;
            }
        }
    }

}

