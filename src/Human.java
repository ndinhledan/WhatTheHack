

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Human {
    public static float x, y, vx, vy;
    public static final int RAD = 50;
    private Image img,floor;
    public Human() {
        x = 100;		
        y = 350;
        try {
            img = ImageIO.read(new File("res/human.png"));  
        	floor = ImageIO.read(new File("res/floor.png"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void gravity() {
        x+=vx;
        if(y>=350 && vy>0 ) {
        	y = 350;
        }
        else {
        	y+=vy;
            vy+=0.5f;
        }
    }
    public void update(Graphics g) {
    	g.drawImage(floor, 0, 400,640,200,null);
    	g.setColor(Color.WHITE);
        g.drawImage(img, Math.round(x-RAD),Math.round(y-RAD),2*RAD,2*RAD, null); 

    }
    public void jump() {
        vy = -8;
     }
    
    public void moveLeft() {
    	vx = -3;
    }
    
    public void moveRight() {
    	vx = 3;
    }
    public void stayStill() {
    	vx = 0;
    }
    
    public void reset() {
        x = 100;
        y = 350;
        vx = vy = 0;
    }
}
