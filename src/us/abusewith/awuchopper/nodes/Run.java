package us.abusewith.awuchopper.nodes;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import us.abusewith.awuchopper.AntiBan;
import us.abusewith.awuchopper.FatigueHandler;

public class Run extends Node {

    public Run(Script script) {
        super(script);
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.getSettings().getRunEnergy() == MethodProvider.random(70, 100) && !script.getSettings().isRunning();
    }

    @Override
    public void execute() throws InterruptedException {
        AntiBan.getInstance().canExecute(false);
        MethodProvider.sleep(FatigueHandler.getInstance().getReactionTime());
        script.getSettings().setRunning(true);
    }

}
