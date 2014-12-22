package us.abusewith.awuchopper.constants;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.utility.Area;

import java.util.HashMap;

public class Draynor {

    public static final int WILLOW_1 = 11761, WILLOW_2_4 = 11759, WILLOW_3 = 11755, WILLOW_5 = 11763;

    public static final Position WILLOW_11761 = new Position(3088, 3227, 0), WILLOW_11759 = new Position(3087, 3231, 0), WILLOW_11755 = new Position(3088, 3234, 0),
            WILLOW_11759_2 = new Position(3085, 3235, 0), WILLOW_11763 = new Position(3083, 3237, 0);

    public static final int BANK_BOOTH_ID = 11744;

    public static final Area BANK = new Area(3092, 3241, 3095, 3246);

    public static final HashMap<Position, Integer> WILLOW_1_ROTATIONS = new HashMap<>(), WILLOW_2_ROTATIONS = new HashMap<>(),
            WILLOW_3_ROTATIONS = new HashMap<>(), WILLOW_4_ROTATIONS = new HashMap<>(), WILLOW_5_ROTATIONS = new HashMap<>();

    static {
        WILLOW_1_ROTATIONS.put(new Position(3088, 3226, 0), 1129);
        WILLOW_1_ROTATIONS.put(new Position(3089, 3226, 0), 919);
        WILLOW_1_ROTATIONS.put(new Position(3090, 3227, 0), 616);
        WILLOW_1_ROTATIONS.put(new Position(3090, 3228, 0), 407);
        WILLOW_1_ROTATIONS.put(new Position(3089, 3229, 0), 104);
        WILLOW_1_ROTATIONS.put(new Position(3088, 3229, 0), 1944);
        WILLOW_1_ROTATIONS.put(new Position(3087, 3228, 0), 1641);
        WILLOW_1_ROTATIONS.put(new Position(3087, 3227, 0), 1432);

        WILLOW_2_ROTATIONS.put(new Position(3089, 3232, 0), 407);
        WILLOW_2_ROTATIONS.put(new Position(3089, 3231, 0), 616);
        WILLOW_2_ROTATIONS.put(new Position(3088, 3230, 0), 919);
        WILLOW_2_ROTATIONS.put(new Position(3087, 3230, 0), 1129);
        WILLOW_2_ROTATIONS.put(new Position(3086, 3231, 0), 1432);
        WILLOW_2_ROTATIONS.put(new Position(3086, 3232, 0), 1541);
        WILLOW_2_ROTATIONS.put(new Position(3087, 3233, 0), 1944);
        WILLOW_2_ROTATIONS.put(new Position(3088, 3233, 0), 104);

        WILLOW_3_ROTATIONS.put(new Position(3088, 3236, 0), 1944);
        WILLOW_3_ROTATIONS.put(new Position(3089, 3236, 0), 104);
        WILLOW_3_ROTATIONS.put(new Position(3090, 3235, 0), 407);
        WILLOW_3_ROTATIONS.put(new Position(3090, 3234, 0), 616);
        WILLOW_3_ROTATIONS.put(new Position(3089, 3233, 0), 919);
        WILLOW_3_ROTATIONS.put(new Position(3088, 3233, 0), 1129);
        WILLOW_3_ROTATIONS.put(new Position(3087, 3234, 0), 1432);
        WILLOW_3_ROTATIONS.put(new Position(3087, 3235, 0), 1641);

        WILLOW_4_ROTATIONS.put(new Position(3085, 3237, 0), 1944);
        WILLOW_4_ROTATIONS.put(new Position(3086, 3237, 0), 104);
        WILLOW_4_ROTATIONS.put(new Position(3087, 3236, 0), 407);
        WILLOW_4_ROTATIONS.put(new Position(3087, 3235, 0), 616);
        WILLOW_4_ROTATIONS.put(new Position(3086, 3234, 0), 919);
        WILLOW_4_ROTATIONS.put(new Position(3085, 3234, 0), 1129);
        WILLOW_4_ROTATIONS.put(new Position(3084, 3235, 0), 1432);
        WILLOW_4_ROTATIONS.put(new Position(3084, 3236, 0), 1641);

        WILLOW_5_ROTATIONS.put(new Position(3082, 3238, 0), 1641);
        WILLOW_5_ROTATIONS.put(new Position(3082, 3237, 0), 1432);
        WILLOW_5_ROTATIONS.put(new Position(3083, 3239, 0), 1944);
        WILLOW_5_ROTATIONS.put(new Position(3084, 3239, 0), 104);
        WILLOW_5_ROTATIONS.put(new Position(3085, 3238, 0), 407);
        WILLOW_5_ROTATIONS.put(new Position(3085, 3237, 0), 616);
        WILLOW_5_ROTATIONS.put(new Position(3084, 3236, 0), 919);
        WILLOW_5_ROTATIONS.put(new Position(3083, 3236, 0), 1129);
    }

}
