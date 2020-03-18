package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Hello world!
 *
 */
public class Raycasting2D extends JFrame implements Runnable, MouseMotionListener
{
    private boolean running = true;
    public int width,height;
    private JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setDoubleBuffered(true);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            render(g2d);
        }
    };
    public static void main( String[] args )
    {
        new Raycasting2D().run();
    }
    @Override
    public void run() {
        setup();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            //render();
            //panel.repaint();
            panel.paintImmediately(0,0,width,height);
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }

        }
        stop();
    }
    private void createCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        getContentPane().setPreferredSize(new Dimension(width,height));
        getContentPane().setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        getContentPane().addMouseMotionListener(this);

    }

    /**
     * Main program starts here
     */
    private Boundary[] walls;
    private Ray ray;
    private Particle particle;
    private double mouseX,mouseY;
    private void setup() {
        createCanvas(400,400);
        walls = new Boundary[5+4];
        for (int i = 0; i < walls.length; i++) {
            double x1 = random(width);
            double x2 = random(width);
            double y1 = random(height);
            double y2 = random(height);
            walls[i] = new Boundary(x1,y1,x2,y2);
        }
        walls[walls.length-4] = new Boundary(0,0,width,0);
        walls[walls.length-3] = new Boundary(width,0,width,height);
        walls[walls.length-2] = new Boundary(width,height,0,height);
        walls[walls.length-1] = new Boundary(0,height,0,0);
        particle = new Particle();
    }

    private void tick() {

    }

    private void render(Graphics2D g2d) {
        g2d.setBackground(Color.black);
        g2d.clearRect(0,0,width,height);

        for (Boundary wall : walls) {
            wall.show(g2d);
        }
        particle.update(mouseX,mouseY);
        particle.show(g2d);
        particle.look(g2d,walls);
    }

    private void stop() {

    }

    public int random(int value) {
        return ThreadLocalRandom.current().nextInt(0, value + 1);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
