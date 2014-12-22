package us.abusewith.awuchopper.nodes;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import us.abusewith.awuchopper.AntiBan;
import us.abusewith.awuchopper.FatigueHandler;
import us.abusewith.awuchopper.NodeController;
import us.abusewith.awuchopper.constants.Draynor;

public class ToBank extends Node {

    public ToBank(Script script) {
        super(script);
    }

    @Override
    public boolean validate() {
        return !script.myPlayer().isMoving() && script.getInventory().isFull() && !Draynor.BANK.contains(script.myPlayer().getPosition());
    }

    @Override
    public void execute() throws InterruptedException {
        AntiBan.getInstance().canExecute(false);
        MethodProvider.sleep(FatigueHandler.getInstance().getReactionTime());
        ((NodeController) script).chop.resetTree();
        if (script.getLocalWalker().walk(Draynor.BANK.getRandomPosition(0))) {
            FatigueHandler.getInstance().clicked();
            waitFor(((NodeController) script).walking, MethodProvider.random(5000, 6000));
        }
    }

}
