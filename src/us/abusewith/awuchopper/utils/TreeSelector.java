package us.abusewith.awuchopper.utils;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;
import org.osbot.rs07.script.Script;
import us.abusewith.awuchopper.constants.Draynor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TreeSelector {

    private Script script;

    public TreeSelector(Script script) {
        this.script = script;
    }

    public RS2Object getBestTree() {
        RS2Object tree = null;
        int players = 2000;
        for (RS2Object object : script.getObjects().getAll()) {
            if (isValidTree(object)) {
                int amount = 0;
                for (Player player : script.getPlayers().getAll()) {
                    if (!player.getName().equals(script.myPlayer().getName())) {
                        for (Map.Entry<Position, Integer> entry : getRotations(object).entrySet()) {
                            Position position = entry.getKey();
                            Integer rotation = entry.getValue();
                            if (player.getPosition().equals(position) && player.getRotation() == rotation) {
                                amount++;
                            }
                        }
                    }
                }
                if (amount < players) {
                    players = amount;
                    tree = object;
                } else if (amount == players) {
                    if (script.myPlayer().getPosition().distance(object.getPosition()) < script.myPlayer().getPosition().distance(tree.getPosition())) {
                        players = amount;
                        tree = object;
                    }
                }
            }
        }
        return tree;
    }

    public RS2Object getRandomTree() {
        ArrayList<RS2Object> trees = new ArrayList<>();
        for (RS2Object object : script.getObjects().getAll()) {
            if (isValidTree(object)) {
                trees.add(object);
            }
        }
        return trees.get(MethodProvider.random(trees.size()));
    }

    private HashMap<Position, Integer> getRotations(RS2Object object) {
        switch (object.getId()) {
            case Draynor.WILLOW_1:
                return Draynor.WILLOW_1_ROTATIONS;
            case Draynor.WILLOW_2_4:
                if (object.getPosition().equals(Draynor.WILLOW_11759)) {
                    return Draynor.WILLOW_2_ROTATIONS;
                } else {
                    return Draynor.WILLOW_4_ROTATIONS;
                }
            case Draynor.WILLOW_3:
                return Draynor.WILLOW_3_ROTATIONS;
            case Draynor.WILLOW_5:
                return Draynor.WILLOW_5_ROTATIONS;
            default:
                return null;
        }
    }

    private boolean isValidTree(RS2Object object) {
        switch (object.getId()) {
            case Draynor.WILLOW_1:
                if (object.getPosition().equals(Draynor.WILLOW_11761)) {
                    return true;
                }
                break;
            case Draynor.WILLOW_2_4:
                if (object.getPosition().equals(Draynor.WILLOW_11759) || object.getPosition().equals(Draynor.WILLOW_11759_2)) {
                    return true;
                }
                break;
            case Draynor.WILLOW_3:
                if (object.getPosition().equals(Draynor.WILLOW_11755)) {
                    return true;
                }
                break;
            case Draynor.WILLOW_5:
                if (object.getPosition().equals(Draynor.WILLOW_11763)) {
                    return true;
                }
                break;
        }
        return false;
    }

}
