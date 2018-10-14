
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
    
    private Human human;
    private ArrayList<Rectangle> rects; //list cot
    private WhatTheHack wth;
    private Font scoreFont;
    public static final Color bg = new Color(0, 158, 158);
    public static final int OBJECT_W = 50, OBJECT_H = 80;
    private Image brick, coin, balloon,plate,trust,shit,candy,stop,rocket,background,paused,finals;

    public GamePanel(WhatTheHack wth, Human human, ArrayList<Rectangle> rects) {
        this.wth = wth;
        this.human = human;
        this.rects = rects;
        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        
        try {
        	brick = ImageIO.read(new File("res/box0.jpg"));
        	coin = ImageIO.read(new File("res/coin.png"));
        	balloon = ImageIO.read(new File("res/balloon.png"));
        	plate = ImageIO.read(new File("res/plate.png"));
        	trust = ImageIO.read(new File("res/trust.png"));
        	shit = ImageIO.read(new File("res/shit.png"));
        	candy = ImageIO.read(new File("res/candy.png"));
        	stop = ImageIO.read(new File("res/stop.png"));
        	rocket = ImageIO.read(new File("res/rocket.png"));
        	background = ImageIO.read(new File("res/background.png"));
        	paused = ImageIO.read(new File("res/paused.png"));
        	finals = ImageIO.read(new File("res/final.png"));


        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
  
    public void paintComponent(Graphics g) {
    	g.drawImage(background, 0,0,WhatTheHack.WIDTH,WhatTheHack.HEIGHT,null);
        human.update(g);
        for(Rectangle r : rects) {
        	
	        Graphics2D g2d = (Graphics2D) g;
	        AffineTransform old = g2d.getTransform();
	        g2d.translate(r.x+OBJECT_W/2, r.y+OBJECT_H/2);
	        
	        if(r == rects.get(0)) {
	            g2d.drawImage(brick, -OBJECT_W/2, -OBJECT_H/2, GamePanel.OBJECT_W, GamePanel.OBJECT_H, null);
	            g2d.setTransform(old);
	            
	        }
        	else if(r == rects.get(1)) {
                
               
                g2d.drawImage(brick, -OBJECT_W/2, -OBJECT_H/2+10, GamePanel.OBJECT_W, 110, null);
                g2d.setTransform(old);
            }
        	else if(r == rects.get(2)) {
               
                g2d.drawImage(coin, -OBJECT_W/2, -OBJECT_H/2, 50, 50, null);
                g2d.setTransform(old);
            }
        	else if(r == rects.get(3)) {
               
                g2d.drawImage(balloon, -OBJECT_W/2, -OBJECT_H/2, 75, 100, null);
                g2d.setTransform(old);
            }
        	else if(r == rects.get(4)||r == rects.get(5)) {
               
                g2d.drawImage(plate, -OBJECT_W/2, -OBJECT_H/2, 150, 30, null);
                g2d.setTransform(old);
            }
        	else if(r == rects.get(6)||r == rects.get(7)) {
                
                g2d.drawImage(trust, -OBJECT_W/2, -OBJECT_H/2, 150, 30, null);
                g2d.setTransform(old);
            }
        	else if(r == rects.get(8)) {
                
                g2d.drawImage(shit, -OBJECT_W/2, -OBJECT_H/2, 150, 120, null);
                g2d.drawImage(candy, -OBJECT_W/2, -OBJECT_H/2-250, 150, 250, null);
                g2d.setTransform(old);
            }
        	else if(r == rects.get(9)) {
                
                g2d.drawImage(stop, -OBJECT_W/2, -OBJECT_H/2, 40, 40, null);
                g2d.setTransform(old);
            }
        	else if (r == rects.get(10)) {
        		g2d.drawImage(rocket, -OBJECT_W/2, -OBJECT_H/2, 100, 60, null);
                g2d.setTransform(old);
        	}
			else if (r == rects.get(11)) {
        		g2d.drawImage(rocket, -OBJECT_W/2, -OBJECT_H/2, 100, 60, null);
                g2d.setTransform(old);
        	}
        	else if (r == rects.get(12)) {
        		g2d.drawImage(finals, -OBJECT_W/2, -OBJECT_H/2, 300, 130, null);
                g2d.setTransform(old);
        	}
        }
        g.setFont(scoreFont);  
        g.setColor(Color.BLACK);
        g.drawString("Score: "+wth.getScore(), 10, 20);
        
        if(wth.paused()) {
            g.drawImage(paused, 0,0,WhatTheHack.WIDTH,WhatTheHack.HEIGHT,null);
        }
    }
}
