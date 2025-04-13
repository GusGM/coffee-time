package com.coffeetime;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("To enable: 'enable <workingMinutes> <breakMinutes>");
            System.out.println("To disable: 'disable'");
            return;
        }

        if (!Arrays.asList("enable","disable").contains(args[0])){
            System.out.println("Please use the correct arguments!");
            System.out.println("To enable: 'enable <workingMinutes> <breakMinutes>");
            return;
        }
        if (args[0].equals("enable")) {
            int workMin = Integer.parseInt(args[1]);
            int breakMin = Integer.parseInt(args[2]);
            CoffeeTimeManager manager = new CoffeeTimeManager(workMin,breakMin);
            manager.start();
            try {
                Thread.sleep((workMin + breakMin) * 60 * 1000L);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted while waiting.");
            }
        }
        if (args[0].equals("disable")) {
            CoffeeTimeManager manager = new CoffeeTimeManager(0,0);
            manager.stop();
            System.out.println("Is the sistem running: " + manager.isRunning());
        }
        return;
    }
}
