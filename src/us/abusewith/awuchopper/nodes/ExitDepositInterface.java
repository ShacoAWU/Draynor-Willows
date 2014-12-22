package us.abusewith.awuchopper.nodes;

import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Condition;
import us.abusewith.awuchopper.AntiBan;
import us.abusewith.awuchopper.FatigueHandler;

public class ExitDepositInterface extends Node {

    private final Condition depositClosed = new Condition() {
        @Override
        public boolean evaluate() {
            return !script.getDepositBox().isOpen();
        }
    };

    public ExitDepositInterface(Script script) {
        super(script);
    }

    @Override
    public boolean validate() throws InterruptedException {
        return script.getDepositBox().isOpen();
    }

    @Override
    public void execute() throws InterruptedException {
        AntiBan.getInstance().canExecute(false);
        MethodProvider.sleep(FatigueHandler.getInstance().getReactionTime());
        script.getDepositBox().close();
        waitFor(depositClosed, MethodProvider.random(2000, 3000));
    }

}
