package main;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class RegularTimer implements TimerMode {
    private final int focusTimeMinutes;

    public RegularTimer(int focusTimeMinutes) {
        this.focusTimeMinutes = focusTimeMinutes;
    }

    @Override
    public void startTimer() {
        System.out.println("Regular Timer Started (" + focusTimeMinutes + " min countdown)");
        Timer timer = new Timer();

        // 3-second countdown
        for (int i = 3; i > 0; i--) {
            System.out.println("Starting in " + i + "...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        TimerTask focusTask = new TimerTask() {
            int remainingTime = focusTimeMinutes * 60;

            @Override
            public void run() {
                if (remainingTime == 0) {
                    System.out.println("Focus complete!");
                    Toolkit.getDefaultToolkit().beep(); // Sound notification
                    timer.cancel();
                } else {
                    printRemainingTime(remainingTime);
                    remainingTime -= 1;
                }
            }
        };

        timer.scheduleAtFixedRate(focusTask, 0, 1000); // Update every second
    }

    private void printRemainingTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        DecimalFormat df = new DecimalFormat("00");
        System.out.println("Time remaining: " + df.format(minutes) + ":" + df.format(secs));
    }
}
