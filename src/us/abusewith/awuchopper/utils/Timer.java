package us.abusewith.awuchopper.utils;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.utility.Condition;

public class Timer {

    private final long startTime;
    private final long endTime;

    public Timer(long period) {
        startTime = System.currentTimeMillis();
        endTime = startTime + period;
    }

    public boolean isRunning() {
        return System.currentTimeMillis() < endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public static long waitFor(Condition condition, long timeout) throws InterruptedException {
        Timer timer = new Timer(timeout);
        while (timer.isRunning() && !condition.evaluate()) {
            MethodProvider.sleep(MethodProvider.random(110, 220));
        }
        return System.currentTimeMillis() - timer.getStartTime();
    }

}
