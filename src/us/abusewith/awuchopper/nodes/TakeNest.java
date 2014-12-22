package us.abusewith.awuchopper.nodes;


import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import us.abusewith.awuchopper.AntiBan;
import us.abusewith.awuchopper.FatigueHandler;
import us.abusewith.awuchopper.NodeController;
import us.abusewith.awuchopper.Statistics;
import us.abusewith.awuchopper.constants.General;

public class TakeNest extends Node {

    private boolean nestDropped;

    public TakeNest(Script script) {
        super(script);
    }

    @Override
    public boolean validate() throws InterruptedException {
        GroundItem birdNest = script.getGroundItems().closest(General.BIRD_NESTS);
        return nestDropped && birdNest != null && birdNest.exists() && !script.getInventory().isFull() && !script.myPlayer().isMoving();
    }

    @Override
    public void execute() throws InterruptedException {
        AntiBan.getInstance().canExecute(false);
        MethodProvider.sleep(FatigueHandler.getInstance().getReactionTime() + (FatigueHandler.getInstance().getReactionTime() / 3));
        GroundItem birdNest = script.getGroundItems().closest(General.BIRD_NESTS);
        if (birdNest.exists()) {
            if (birdNest.isVisible()) {
                if (birdNest.interact(General.TAKE_ACTION)) {
                    Statistics.getInstance().nestDropped();
                    FatigueHandler.getInstance().clicked();
                    nestDropped = false;
                    waitFor(((NodeController) script).walking, MethodProvider.random(5000, 6000));
                }
            } else {
                script.getCamera().toEntity(birdNest);
            }
        }
    }

    public void nestDropped() {
        nestDropped = true;
    }

}
