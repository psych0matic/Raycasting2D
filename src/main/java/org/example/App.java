package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Hello world!
 *
 */
public class App extends JFrame implements Runnable
{
    private boolean running = true;
    private int width,height;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics2D g2;
    public static void main( String[] args )
    {
        new App().run();
    }
    public App() {
        createBufferStrategy(2);
        bs = getBufferStrategy();
    }
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        setup();
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            do {
                try{
                    g2 = (Graphics2D) bs.getDrawGraphics();
                    render(g2);
                } finally {
                    g2.dispose();
                }
                bs.show();
            } while (bs.contentsLost());
            //render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }

        }
    }
    private void createCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        getContentPane().setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(canvas);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Main program starts here
     */
    private Boundary b;


    private void setup() {
        createCanvas(400,400);
        b = new Boundary(300,100,300,300);
    }

    private void tick() {

    }

    private void render(Graphics2D g2) {

    }


}
