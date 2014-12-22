package us.abusewith.awuchopper;

import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.event.CameraPitchEvent;
import org.osbot.rs07.event.CameraYawEvent;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Condition;
import us.abusewith.awuchopper.utils.Timer;

import java.util.ArrayList;

public class AntiBan {

    private final static AntiBan INSTANCE = new AntiBan();
    private Script script;

    private boolean isRunning, execute;

    private Event pitchEvent, yawEvent;

    public boolean movingCamera() {
        return !(yawEvent == null || pitchEvent == null) && !((yawEvent.hasFinished() && !yawEvent.hasFailed()) || (pitchEvent.hasFinished() && !pitchEvent.hasFailed()));
    }

    private AntiBan() {
    }

    public static AntiBan getInstance() {
        return INSTANCE;
    }

    public void initialize(Script script) {
        INSTANCE.script = script;
        isRunning = true;
        new Thread(new MouseTask()).start();
        new Thread(new CameraTask()).start();
    }

    private class MouseTask implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                if (execute) {
                    if (MethodProvider.random(FatigueHandler.getInstance().getExcitement()) == 1) {
                        try {
                            script.getTabs().skills.hoverSkill(Skill.WOODCUTTING);
                            MethodProvider.sleep(MethodProvider.random(100, 300));
                            if (!script.getTabs().getOpen().equals(Tab.INVENTORY)) {
                                script.getTabs().open(Tab.INVENTORY);
                                Timer.waitFor(new Condition() {
                                    @Override
                                    public boolean evaluate() {
                                        return script.getTabs().getOpen().equals(Tab.INVENTORY);
                                    }
                                }, MethodProvider.random(2000, 3000));
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else if (MethodProvider.random(FatigueHandler.getInstance().getMouseActivity()) == 1) {
                        if (MethodProvider.random(3) == 2) {
                            script.getMouse().move(MethodProvider.random(763), MethodProvider.random(500));
                        } else {
                            if (script.getMouse().isOnScreen()) {
                                script.getMouse().moveOutsideScreen();
                            }
                        }
                    } else if (MethodProvider.random(FatigueHandler.getInstance().getExcitement()) == 1) {
                        getRandomPlayer().hover();
                    }
                }
                try {
                    Thread.sleep(MethodProvider.random(1100, 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private class CameraTask implements Runnable {
        @Override
        public void run() {
            while (isRunning) {
                if (execute) {
                    switch (FatigueHandler.getInstance().getCameraActivity()) {
                        case 1:
                            pitchEvent = script.getBot().getEventExecutor().execute(new CameraPitchEvent(MethodProvider.random(script.getCamera().getLowestPitchAngle(), 67)));
                            yawEvent = script.getBot().getEventExecutor().execute(new CameraYawEvent(MethodProvider.random(0, 360)));
                            break;
                        case 2:
                            yawEvent = script.getBot().getEventExecutor().execute(new CameraYawEvent(MethodProvider.random(0, 360)));
                            break;
                        case 3:
                            pitchEvent = script.getBot().getEventExecutor().execute(new CameraPitchEvent(MethodProvider.random(script.getCamera().getLowestPitchAngle(), 67)));
                            break;
                    }
                }
                try {
                    Thread.sleep(MethodProvider.random(1100, 2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Player getRandomPlayer() {
        ArrayList<Player> players = new ArrayList<>();
        for (Player player : script.getPlayers().getAll()) {
            if (player != null && player.exists() && player.isVisible()) {
                players.add(player);
            }
        }
        return players.get(MethodProvider.random(players.size()));
    }

    public void canExecute(boolean state) {
        this.execute = state;
    }

    public void stop() {
        isRunning = false;
    }

}
