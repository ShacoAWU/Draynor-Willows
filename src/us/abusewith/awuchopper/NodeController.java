package us.abusewith.awuchopper;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.util.GraphicUtilities;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import org.osbot.rs07.utility.Condition;
import us.abusewith.awuchopper.constants.General;
import us.abusewith.awuchopper.nodes.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

@ScriptManifest(name = "AWU Draynor Willows", info = "Start at Draynor bank with an axe.", author = "Shaco", version = 1.00, logo = "")
public class NodeController extends Script {

    private final ArrayList<Node> nodes = new ArrayList<>();

    public final Chop chop = new Chop(this);
    private final TakeNest takeNest = new TakeNest(this);

    public final Condition walking = new Condition() {
        @Override
        public boolean evaluate() {
            return myPlayer().isMoving();
        }
    };

    @Override
    public void onStart() throws InterruptedException {
        antiBan.unregisterAllBehaviors();
        Collections.addAll(nodes, new ExitDepositInterface(this), new EscapeCombat(this), new Run(this), takeNest, chop, new ToBank(this), new Bank(this));
        FatigueHandler.getInstance().initialize();
        Statistics.getInstance().initialize(this);
        AntiBan.getInstance().initialize(this);
    }

    @Override
    public int onLoop() throws InterruptedException {
        for (Node node : nodes) {
            if (node.validate()) {
                node.execute();
            }
        }
        AntiBan.getInstance().canExecute(true);
        return random(100, 200);
    }

    @Override
    public void onMessage(Message message) throws InterruptedException {
        if (message.getMessage().equals(General.BIRD_NEST_MESSAGE) && message.getType().equals(Message.MessageType.GAME)) {
            takeNest.nestDropped();
        }
    }

    @Override
    public void onPaint(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font("Arial", Font.PLAIN, 14));

        us.abusewith.awuchopper.utils.Paint.drawChatBackground(g);
        us.abusewith.awuchopper.utils.Paint.drawText("AWU Draynor Willows by Shaco", 16, 363, Color.GREEN, g);
        us.abusewith.awuchopper.utils.Paint.drawText("Time running: " + Statistics.getInstance().timeRunning(), 16, 363 + 15, Color.RED, g);

        if (getClient().isLoggedIn()) {
            us.abusewith.awuchopper.utils.Paint.drawText("XP/Hour: " + Statistics.getInstance().xpPerHour(), 16, 363 + 30, Color.RED, g);
            us.abusewith.awuchopper.utils.Paint.drawText("XP Gained: " + Statistics.getInstance().xpGained(), 16, 363 + 45, Color.RED, g);
            us.abusewith.awuchopper.utils.Paint.drawText("Bird Nests: " + Statistics.getInstance().getBirdNests(), 16, 363 + 60, Color.RED, g);
            us.abusewith.awuchopper.utils.Paint.drawText("Willow Logs: " + Statistics.getInstance().getWillowLogs(), 16, 363 + 75, Color.RED, g);
            
            g.setColor(Color.CYAN);
            RS2Object tree = chop.getCurrentTarget();
            if (tree.exists()) {
                g.draw(GraphicUtilities.getModelArea(bot, tree.getGridX(), tree.getGridY(), tree.getZ(), tree.getModel()));
            }
        }
    }

    @Override
    public void onExit() throws InterruptedException {
        AntiBan.getInstance().stop();
    }

}
