package us.abusewith.awuchopper.utils;

import org.osbot.rs07.Bot;
import org.osbot.rs07.accessor.XObjectDefinition;
import org.osbot.rs07.api.Client;
import org.osbot.rs07.api.def.ObjectDefinition;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Model;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.utility.Area;

public class NullObject implements RS2Object {

    @Override
    public int getOrientation() {
        return 0;
    }

    @Override
    public int getConfig() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public void setPrioritized(boolean b) {

    }

    @Override
    public int getLocalX() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getGridX() {
        return 0;
    }

    @Override
    public ObjectDefinition getDefinition() {
        return new ObjectDefinition(new XObjectDefinition() {
            @Override
            public int getSizeX() {
                return 0;
            }

            @Override
            public short[] getOriginalModelColors() {
                return new short[0];
            }

            @Override
            public int[] getTransformIds() {
                return new int[0];
            }

            @Override
            public int getId() {
                return 0;
            }

            @Override
            public boolean getClipping1() {
                return false;
            }

            @Override
            public String[] getActions() {
                return new String[0];
            }

            @Override
            public int getClipping2() {
                return 0;
            }

            @Override
            public boolean getClipping3() {
                return false;
            }

            @Override
            public int getSizeY() {
                return 0;
            }

            @Override
            public int getWalkToData() {
                return 0;
            }

            @Override
            public short[] getModifiedModelColors() {
                return new short[0];
            }

            @Override
            public String getName() {
                return "null";
            }

            @Override
            public int[] getModelIds() {
                return new int[0];
            }

            @Override
            public Bot getBot() {
                return null;
            }
        });
    }

    @Override
    public Bot getBot() {
        return null;
    }

    @Override
    public int getGridY() {
        return 0;
    }

    @Override
    public int getSizeY() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getZ() {
        return 0;
    }

    @Override
    public Area getArea(int i) {
        return new Area();
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public int getSizeX() {
        return 0;
    }

    @Override
    public boolean examine() {
        return false;
    }

    @Override
    public Client getClient() {
        return null;
    }

    @Override
    public Position getPosition() {
        return new Position(0, 0, 0);
    }

    @Override
    public boolean isPrioritized() {
        return false;
    }

    @Override
    public Model getModel() {
        return new Model();
    }

    @Override
    public int getLocalY() {
        return 0;
    }

    @Override
    public String[] getActions() {
        return new String[0];
    }

    @Override
    public boolean hasAction(String... strings) {
        return false;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int[] getModelIds() {
        return new int[0];
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public int getUID() {
        return 0;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public boolean hover() {
        return false;
    }

    @Override
    public boolean interact(String... strings) {
        return false;
    }

}
