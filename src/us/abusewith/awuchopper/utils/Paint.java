package us.abusewith.awuchopper.utils;

import java.awt.*;

public class Paint {

    public static void drawText(String text, int x, int y, Color color, Graphics g) {
        g.setColor(Color.BLACK);
        g.drawString(text, x + 1, y + 1);
        g.setColor(color);
        g.drawString(text, x, y);
    }

    public static void drawChatBackground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setComposite(AlphaComposite.SrcOver.derive(0.77f));
        g.drawRect(1, 338, 516, 141);
        g.fillRect(1, 338, 516, 141);
        g.setComposite(AlphaComposite.SrcOver);
    }

    public static void drawXPBar(int x, int y, int totalXp, int currentXp, String timeTillNext, int currentLevel, Graphics2D g) {
        double finished = (((double) totalXp - (double) currentXp) / (double) totalXp) * 100d;
        int finishedLength = (int) ((finished / 100d) * 487d);
        g.setColor(Color.RED);
        g.drawRect(x, y, 487, 20);
        g.setComposite(AlphaComposite.SrcOver.derive(0.6f));
        g.fillRect(x + 1 + finishedLength - 1, y + 1, 487 - finishedLength, 19);
        g.setColor(Color.GREEN);
        g.fillRect(x + 1, y + 1, finishedLength - 1, 19);
        g.setComposite(AlphaComposite.SrcOver);
        g.setColor(Color.BLACK);
        if (currentLevel == 99) {
            g.drawString("Time till 200M Xp: " + timeTillNext, x + 150, y + 15);
        } else {
            g.drawString("Time till level " + (currentLevel + 1) + ": " + timeTillNext, x + 150, y + 15);
        }
    }


}
