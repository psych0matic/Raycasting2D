package org.example;

import java.awt.*;
import java.awt.geom.Line2D;

public class Boundary {
    private Point a,b;
    public Boundary(int x1, int y1, int x2, int y2) {

    }
    
    public void show(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.draw(new Line2D.Double(a.x,a.y,b.x,b.y));
    }
}
