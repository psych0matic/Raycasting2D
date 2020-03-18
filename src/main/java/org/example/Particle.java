package org.example;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Particle {
    Raycasting2D r2d = new Raycasting2D();
    Vector2D pos;
    Ray[] rays;

    public Particle() {
        pos = new Vector2D(r2d.width/2,r2d.height/2);
        rays = new Ray[360];
        for (int a = 0; a < this.rays.length; a +=1) {
            rays[a] = new Ray(pos,Math.toRadians(a));
        }
    }

    public void update(double x, double y) {
        pos.set(x,y);
    }

    public void look(Graphics2D g2, Boundary[] walls) {
        for (int i = 0; i < rays.length; i++) {
            Ray ray = this.rays[i];
            Vector2D closest = null;
            double record = 500000000;
            for (Boundary wall: walls) {
                Vector2D pt = ray.cast(wall);
                if (pt != null) {
                    double d = pos.distance(pt);
                    if (d < record) {
                        record = d;
                        closest = pt;
                    }
                }
            }
            if (closest != null) {
                g2.setColor(new Color(r2d.random(255),r2d.random(255),r2d.random(255)));
                g2.draw(new Line2D.Double(pos.x,pos.y,closest.x,closest.y));
            }
        }
    }

    public void show(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.draw(new Ellipse2D.Double(pos.x,pos.y,4,4));
        for (Ray ray : rays) {
            ray.show(g2);
        }
    }
}
