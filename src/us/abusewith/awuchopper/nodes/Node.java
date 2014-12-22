package us.abusewith.awuchopper.nodes;


import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Condition;
import us.abusewith.awuchopper.utils.Timer;

public abstract class Node {

    public Script script;

    public Node(Script script) {
        this.script = script;
    }

    public abstract boolean validate() throws InterruptedException;

    public abstract void execute() throws InterruptedException;

    public boolean waitFor(Condition condition, long timeout) throws InterruptedException {
        Timer timer = new Timer(timeout);
        while (timer.isRunning() && !condition.evaluate()) {
            MethodProvider.sleep(MethodProvider.random(110, 220));
        }
        return condition.evaluate();
    }

}
