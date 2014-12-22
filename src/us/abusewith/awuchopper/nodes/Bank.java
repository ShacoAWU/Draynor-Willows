package us.abusewith.awuchopper.nodes;

import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.utility.Condition;
import us.abusewith.awuchopper.AntiBan;
import us.abusewith.awuchopper.FatigueHandler;
import us.abusewith.awuchopper.NodeController;
import us.abusewith.awuchopper.constants.Draynor;
import us.abusewith.awuchopper.constants.General;
import us.abusewith.awuchopper.utils.BankSelector;

public class Bank extends Node {

    private final BankSelector bankSelector;

    public Bank(Script script) {
        super(script);
        bankSelector = new BankSelector(script);
    }

    @Override
    public boolean validate() {
        RS2Object bank = script.getObjects().closest(Draynor.BANK_BOOTH_ID);
        return script.getInventory().isFull() && (Draynor.BANK.contains(script.myPlayer().getPosition()) || bank.isVisible());
    }

    @Override
    public void execute() throws InterruptedException {
        AntiBan.getInstance().canExecute(false);
        MethodProvider.sleep(FatigueHandler.getInstance().getReactionTime());
        if (!script.getBank().isOpen()) {
            RS2Object bank;
            if (MethodProvider.random(10) == 0) {
                bank = bankSelector.getRandomBank();
            } else {
                bank = bankSelector.getClosest();
            }
            if (bank.isVisible()) {
                if (bank.interact(General.BANK_ACTION)) {
                    FatigueHandler.getInstance().clicked();
                    waitFor(bankOpen, MethodProvider.random(10000, 12000));
                }
            } else {
                script.getCamera().toEntity(bank, true);
            }
        } else {
            script.getBank().depositAllExcept(getAxe());
        }
    }

    private final Condition bankOpen = new Condition() {
        @Override
        public boolean evaluate() {
            return script.getBank().isOpen();
        }
    };


    private int getAxe() {
        for (Item item : script.getInventory().getItems()) {
            if (item.getName().endsWith(" axe")) {
                return item.getId();
            }
        }
        return 0;
    }

}
