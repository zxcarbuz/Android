package edu.synergy.practice.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

/** Animation moves BufferedImage sprites; a Swing Timer drives repaint at 30 FPS. */
public final class SnowPanel extends JPanel implements ActionListener {
    private static final int FLAKE_COUNT = 38;
    private final List<Flake> flakes = new ArrayList<>();
    private final BufferedImage flakeImage = makeFlakeImage();
    private final Timer timer = new Timer(33, this);
    private final Random random = new Random(2026L);
    private int tickCount;

    public SnowPanel() {
        setBackground(new Color(18, 28, 48));
        setPreferredSize(new Dimension(720, 360));
        for (int i = 0; i < FLAKE_COUNT; i++) {
            flakes.add(new Flake(random.nextInt(720), random.nextInt(360),
                    0.6 + random.nextDouble() * 1.9));
        }
        timer.start();
    }

    /** Separate image creation makes moving an actual image testable. */
    static BufferedImage makeFlakeImage() {
        BufferedImage sprite = new BufferedImage(23, 23, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = sprite.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(236, 247, 255, 215));
        g.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int angle=0; angle<180; angle+=60) {
            double theta = Math.toRadians(angle);
            int dx = (int)(8 * Math.cos(theta));
            int dy = (int)(8 * Math.sin(theta));
            g.drawLine(11-dx,11-dy,11+dx,11+dy);
        }
        g.dispose();
        return sprite;
    }

    public static double nextY(double currentY, double speed, int height) {
        if (height <= 0) return currentY;
        double value = currentY + speed;
        return value > height ? -23 : value;
    }

    @Override public void actionPerformed(ActionEvent e) {
        tickCount++;
        for (Flake f : flakes) {
            f.y = nextY(f.y, f.speed, Math.max(getHeight(), 360));
            f.x = f.x + Math.sin(tickCount * .025 + f.seed) * 0.25;
        }
        repaint();
    }

    @Override protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D)graphics.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint bg = new GradientPaint(0, 0, new Color(18,28,48),0,getHeight(),new Color(43,56,82));
        g.setPaint(bg);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(new Color(255,255,255,40));
        g.fillOval(getWidth()-185,45,125,125);
        for (Flake f : flakes) {
            g.drawImage(flakeImage, (int)f.x, (int)f.y, null);
        }
        g.dispose();
    }

    public void stop() { timer.stop(); }
    private static final class Flake {
        double x; double y; final double speed; final double seed;
        Flake(double x, double y, double speed) {this.x=x;this.y=y;this.speed=speed;this.seed=x * .1;}
    }
}