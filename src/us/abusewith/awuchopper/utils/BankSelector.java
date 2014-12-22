package us.abusewith.awuchopper.utils;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import us.abusewith.awuchopper.constants.Draynor;

import java.util.ArrayList;

public class BankSelector {

    private Script script;

    public BankSelector(Script script) {
        this.script = script;
    }

    public RS2Object getRandomBank() {
        ArrayList<RS2Object> banks = new ArrayList<>();
        for (RS2Object object : script.getObjects().getAll()) {
            if (object.getId() == Draynor.BANK_BOOTH_ID) {
                banks.add(object);
            }
        }
        return banks.get(MethodProvider.random(banks.size()));
    }

    public RS2Object getClosest() {
        return script.getObjects().closest(Draynor.BANK_BOOTH_ID);
    }

}
