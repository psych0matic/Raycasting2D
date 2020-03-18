package org.example;

import java.awt.*;
import java.awt.geom.Line2D;

public class Boundary {
    public Vector2D a,b;
    public Boundary(double x1, double y1, double x2, double y2) {
        this.a = new Vector2D(x1,y1);
        this.b = new Vector2D(x2,y2);
    }
    
    public void show(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.draw(new Line2D.Double(a.x,a.y,b.x,b.y));
    }
}
