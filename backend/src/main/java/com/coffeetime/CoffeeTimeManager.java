package com.coffeetime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CoffeeTimeManager {
    private int workingMinutes;
    private int breakMinutes;
    private boolean running;
    private ScheduledExecutorService scheduler;

    public CoffeeTimeManager(int workingMinutes, int breakMinutes){
        this.workingMinutes = workingMinutes;
        this.breakMinutes = breakMinutes;
    }
    public void changeTimer(boolean status, int workingMin, int breakMin){
        running = status;
        workingMinutes = workingMin;
        breakMinutes = breakMin;
    }
    public void start(){
        System.out.println("Point Start");
        running = true;
        scheduler = Executors.newSingleThreadScheduledExecutor();
        System.out.println("The system will take a break of " + breakMinutes + " every " + workingMinutes + " minutes");
        scheduler.schedule(() -> {
            System.out.println("Time for a break, take " + breakMinutes + " minutes off.");
            scheduler.schedule(() -> {
                System.out.println("Break is over. Time to get your ass back to work");
                stop();
            }, breakMinutes, TimeUnit.MINUTES);
        }, workingMinutes, TimeUnit.MINUTES);
    }
    public void stop(){
        running = false;
        if (scheduler != null && !scheduler.isShutdown()){
            scheduler.shutdownNow();
        }
        System.out.println("Work your ass off!");
    }
    public boolean isRunning(){
        return running;
    }
}
