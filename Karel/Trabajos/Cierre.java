import java.util.Scanner;
class Cierre implements Runnable {
    private int enterCount = 0;
    public Cierre(){

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Ingrese 1 para detener la simulación:");
            String entrada = scanner.nextLine();
            System.out.println("-----------------------------");
                MiPrimerRobot.cierre = true;
                System.out.println("¡Se activó el cierre con interrupción!");
                break;
        }
    }
}