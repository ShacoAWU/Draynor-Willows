package us.abusewith.awuchopper.nodes;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.event.CameraEntityEvent;
import org.osbot.rs07.event.Event;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Condition;
import us.abusewith.awuchopper.AntiBan;
import us.abusewith.awuchopper.FatigueHandler;
import us.abusewith.awuchopper.NodeController;
import us.abusewith.awuchopper.constants.General;
import us.abusewith.awuchopper.utils.NullObject;
import us.abusewith.awuchopper.utils.Timer;
import us.abusewith.awuchopper.utils.TreeSelector;

public class Chop extends Node {

    private final TreeSelector treeSelector;

    private final RS2Object nullObject = new NullObject();
    private RS2Object tree = nullObject;

    private Event cameraEvent;

    public Chop(Script script) {
        super(script);
        treeSelector = new TreeSelector(script);
    }

    @Override
    public boolean validate() {
        return (!script.myPlayer().isAnimating() || !tree.exists()) && !script.getInventory().isFull() && !script.myPlayer().isMoving();
    }

    @Override
    public void execute() throws InterruptedException {
        AntiBan.getInstance().canExecute(false);
        if (MethodProvider.random(20) == 0) {
            tree = treeSelector.getRandomTree();
        } else {
            tree = treeSelector.getBestTree();
        }
        if (tree.exists()) {
            if (tree.isVisible()) {
                waitForStableCamera();
                if (tree.interact(General.TREE_CHOP_ACTION)) {
                    FatigueHandler.getInstance().clicked();
                    waitFor(animating, MethodProvider.random(5100, 6000));
                }
            } else {
                if (script.myPlayer().getPosition().distance(tree.getPosition()) > MethodProvider.random(4, 5)) {
                    MethodProvider.sleep(FatigueHandler.getInstance().getReactionTime());
                    if (script.getLocalWalker().walk(tree, true)) {
                        FatigueHandler.getInstance().clicked();
                        waitFor(((NodeController) script).walking, MethodProvider.random(5000, 6000));
                    }
                } else {
                    cameraEvent = script.getBot().getEventExecutor().execute(new CameraEntityEvent(tree));
                }
            }
        }
    }

    private void waitForStableCamera() throws InterruptedException {
        long slept = Timer.waitFor(stableCamera, MethodProvider.random(3000, 4000));
        long reactionTime = FatigueHandler.getInstance().getReactionTime();
        if (slept < reactionTime) {
            MethodProvider.sleep(reactionTime - slept);
        }
    }

    private final Condition animating = new Condition() {
        @Override
        public boolean evaluate() {
            return script.myPlayer().isAnimating() || !tree.exists();
        }
    };
    private final Condition stableCamera = new Condition() {
        @Override
        public boolean evaluate() {
            return !AntiBan.getInstance().movingCamera() && !movingCamera();
        }
    };

    private boolean movingCamera() {
        return cameraEvent != null && !(cameraEvent.hasFinished() && !cameraEvent.hasFailed());
    }

    public RS2Object getCurrentTarget() {
        return tree;
    }

    public void resetTree() {
        tree = nullObject;
    }

}
