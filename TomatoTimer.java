package main;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class TomatoTimer implements TimerMode {
    private final int focusTimeMinutes;
    private final int breakTimeMinutes;

    public TomatoTimer(int focusTimeMinutes, int breakTimeMinutes) {
        this.focusTimeMinutes = focusTimeMinutes;
        this.breakTimeMinutes = breakTimeMinutes;
    }

    @Override
    public void startTimer() {
        System.out.println("Tomato Timer Started.");
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

        TimerTask tomatoTask = new TimerTask() {
            int remainingFocusTime = focusTimeMinutes * 60;
            int remainingBreakTime = breakTimeMinutes * 60;

            @Override
            public void run() {
                if (remainingFocusTime == 0 && remainingBreakTime == 0) {
                    System.out.println("Tomato Timer complete!");
                    Toolkit.getDefaultToolkit().beep(); // Sound notification
                    timer.cancel();
                } else {
                    if (remainingFocusTime > 0) {
                        System.out.println("Work time remaining: " + formatTime(remainingFocusTime));
                        remainingFocusTime -= 1;
                    } else if (remainingBreakTime > 0) {
                        if(remainingBreakTime == breakTimeMinutes * 60) {
                            Toolkit.getDefaultToolkit().beep(); // Sound notification
                            Toolkit.getDefaultToolkit().beep(); // Sound notification
                            System.out.println("Focusing complete! Now take a break!");
                        }
                        System.out.println("Break time remaining: " + formatTime(remainingBreakTime));
                        remainingBreakTime -= 1;
                    }
                }
            }
        };

        timer.scheduleAtFixedRate(tomatoTask, 0, 1000); // Update every second
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        DecimalFormat df = new DecimalFormat("00");
        return df.format(minutes) + ":" + df.format(secs);
    }
}
