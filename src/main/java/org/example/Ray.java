package org.example;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Ray {
    private Vector2D dir;
    private Vector2D pos;

    public Ray(Vector2D _pos, double angle) {
        this.pos = _pos;
        this.dir = new Vector2D(Math.cos(angle),Math.sin(angle));
    }

    public void lookAt(double x, double y) {
        this.dir.x = x - this.pos.x;
        this.dir.y = y - this.pos.y;
        this.dir.normalize();
    }

    public void show(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.translate(pos.x,pos.y);
        g2.draw(new Line2D.Double(0,0,dir.x * 10,dir.y * 10));
        g2.translate(-pos.x,-pos.y);
    }

    public Vector2D cast(Boundary wall) {
        final double x1 = wall.a.x;
        final double y1 = wall.a.y;
        final double x2 = wall.b.x;
        final double y2 = wall.b.y;

        final double x3 = pos.x;
        final double y3 = pos.y;
        final double x4 = pos.x + dir.x;
        final double y4 = pos.y + dir.y;

        final double den = (x1-x2) * (y3-y4) - (y1-y2) * (x3-x4);
        if (den == 0) {
            return null;
        }

        final double t = ((x1-x3) * (y3-y4) -(y1-y3) * (x3-x4)) /den;
        final double u = -((x1-x2) * (y1-y3) -(y1-y2) * (x1-x3)) /den;
        if (t > 0 && t < 1 && u >0) {
            Vector2D pt = new Vector2D();
            pt.x = x1 + t * (x2-x1);
            pt.y = y1 + t * (y2-y1);
            return pt;
        } else {
            return null;
        }
    }
}
