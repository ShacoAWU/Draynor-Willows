package us.abusewith.awuchopper;

import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.script.Script;

import java.util.concurrent.TimeUnit;

public class Statistics {

    private static final Statistics INSTANCE = new Statistics();
    private Script script;
    private final long startTime = System.currentTimeMillis();

    private int birdNests;

    private Statistics() {

    }

    public void initialize(Script script) {
        this.script = script;
        script.getExperienceTracker().start(Skill.WOODCUTTING);
    }

    public static Statistics getInstance() {
        return INSTANCE;
    }

    public void nestDropped() {
        birdNests++;
    }

    public int xpGained() {
        return script.getExperienceTracker().getGainedXP(Skill.WOODCUTTING);
    }

    public int xpPerHour() {
        return script.getExperienceTracker().getGainedXPPerHour(Skill.WOODCUTTING);
    }

    public int getBirdNests() {
        return birdNests;
    }

    public int getCurrentXP() {
        return script.getSkills().getExperience(Skill.WOODCUTTING);
    }

    public int getXPGoal() {
        return script.getSkills().getExperienceForLevel(getCurrentLevel() + 1);
    }

    public int getCurrentLevel() {
        return script.getSkills().getStatic(Skill.WOODCUTTING);
    }

    public int getWillowLogs() {
        return xpGained() / 67;
    }

    public String timeTillNextLvl() {
        long millis = script.getExperienceTracker().getTimeToLevel(Skill.WOODCUTTING);
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public String timeRunning() {
        long millis = System.currentTimeMillis() - startTime;
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

}
