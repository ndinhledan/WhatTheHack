

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WhatTheHack implements ActionListener, KeyListener {
    
    public static final int FPS = 100, WIDTH = 640 , HEIGHT = 480;
    
    private Human human;
    private JFrame frame;
    private JPanel panel;
    private ArrayList<Rectangle> rects;
    private int time, scroll;
    private Timer t;
    private KeyEvent k; 
    private boolean paused;
    
    public void go() {
        frame = new JFrame("Trust me!");
        human = new Human();
        rects = new ArrayList<Rectangle>();
        panel = new GamePanel(this, human, rects);
        frame.add(panel);
        
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);
        
        paused = true;
        
        t = new Timer(1000/FPS, this); 
        t.start();  
    }
    public static void main(String[] args) {
    	new WhatTheHack().go();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {   
        panel.repaint();
        if(!paused) {
            human.gravity();
            if(scroll == 50) {
            	int h = 80;
            	Rectangle r1 = new Rectangle(WIDTH, HEIGHT - 120, GamePanel.OBJECT_W, h);
                rects.add(r1);
            }
            else if(scroll == 300) {
            	int h = 110;
                Rectangle r2 = new Rectangle(WIDTH, HEIGHT - 165, GamePanel.OBJECT_W, h);
                rects.add(r2);
            }
            else if(scroll == 550) {
            	int h = 50;
            	Rectangle r3 = new Rectangle(WIDTH, HEIGHT - 250, GamePanel.OBJECT_W, h);
                rects.add(r3);
                
            }
            else if(scroll == 800) {
            	int h = 100;
            	Rectangle r4 = new Rectangle(WIDTH, HEIGHT - 350, 75, h);
                rects.add(r4);
                
            }
            else if(scroll == 1050) {
            	int h = 30;
            	Rectangle r5 = new Rectangle(WIDTH, HEIGHT - 400, 150, h);
            	Rectangle r6 = new Rectangle(WIDTH, HEIGHT - 200, 150, h);
                rects.add(r5);
                rects.add(r6);
                
            }
            else if(scroll == 1300) {
            	int h = 30;
            	Rectangle r7 = new Rectangle(WIDTH, HEIGHT - 400, 150, h);
            	Rectangle r8 = new Rectangle(WIDTH, HEIGHT - 150, 150, h);
                rects.add(r7);
                rects.add(r8);
                
            }
            else if(scroll == 1550) {
            	int h = 120;
            	Rectangle r9 = new Rectangle(WIDTH, HEIGHT - 200, 150, h);
                rects.add(r9);
                
            }
            else if(scroll == 1800) {
            	int h = 40;
            	Rectangle r10 = new Rectangle(WIDTH, HEIGHT - 120, 40, h);
                rects.add(r10);
            }
            else if (scroll == 2050) {
            	int h = 60;
            	Rectangle r11 = new Rectangle(WIDTH, HEIGHT - 350, 100,h );
            	rects.add(r11);
            }
			else if (scroll == 2400){
				int h = 60;
            	Rectangle r12 = new Rectangle( -10, 340, 100 , h );
            	rects.add(r12);
			}
            else if (scroll == 2800) {
            	int h = 130;
            	Rectangle r13 = new Rectangle(WIDTH, HEIGHT - 400, 300,h );
            	rects.add(r13);
            }
            
            
            boolean game = true;
            if (scroll == 3200) {
            	JOptionPane.showMessageDialog(frame, "You do NOT have enough Faith in Me!!!\n"+"Your score was: "+"0, Sorry."); // lay time score
                game = false;
            }
            for(Rectangle r : rects) { 
                r.x-=3;
                
                if(rects.size() == 13){
                  	if (r.contains(human.x+25, human.y-25)){
                  		JOptionPane.showMessageDialog(frame, "You win!\n"+"Your score was: "+time+"."); // lay time score
                        game = false;
                  	}
               }
                else if((r == rects.get(0)&&r.contains(human.x , human.y))||((r.contains(human.x + 25, human.y - 25)||r.contains(human.x+10, human.y+20)) && r!= rects.get(0) ))  { //dam vao 1 trong 2 cot tren/duoi
                    JOptionPane.showMessageDialog(frame, "You lose!\n"+"Your score was: "+time+"."); // lay time lam score
                    game = false;
                }
                
              
                if(r == rects.get(0)) {
                	if (Math.abs(r.x - human.x) < 30 ) {
                		r.y -= 15 ;   
                	}
                }
                else if(rects.size()==4) {
                	if (Math.abs(r.x - human.x) < 50 ) {
                		r.y += 15 ; 
                	}                	
                }
                else if(rects.size()== 5 || rects.size() == 6) {
                	if (Math.abs(r.x - human.x) < 50){
                		r.y += 5;
                	}
                }
               
               else if(rects.size() == 8){
                	if (Math.abs(r.x - human.x) < 50){
                		rects.get(6).y += 3;
                		rects.get(7).y -= 3;
                	}
                }
               else if(rects.size() == 10){
               	if (Math.abs(r.x - human.x) < 650 && Math.abs(r.x - human.x)>100){
               		r.y -= 2;
               	}
               	else if (Math.abs(r.x - human.x) <= 100) {
               		r.y += 7;
               	}
                }
               else if(rects.size() == 11){
                  	if (Math.abs(r.x - human.x) < 550 && r.y<340){
                  		r.y += 3;
                  	}
                  	else if (r.y>=340) {
                  		r.y = 340;
                  		r.x -= 10;
                  	}
                   
                }
				else if (rects.size() == 12 ){
					if (scroll < 2460) {r.x += 8;}
					else if (scroll >= 2460 ){r.y -= 5;}
				}
               
            }
  
            time++;
            scroll++;

            if(human.y+human.RAD < 0|| human.x - human.RAD > WIDTH || human.x + human.RAD < 0) { 
                game = false;
            }

            if(!game) {
                rects.clear(); 
                human.reset();
                time = 0;
                scroll = 0;
                paused = true;
            }
        }
        else {
            
        }
    }
    
    public int getScore() {
        return time;
    }
    
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP) {
            human.jump();
        }
        else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
            paused = false;    
        }
        else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
        	human.moveLeft();
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
        	human.moveRight();
        }        
    }
    public void keyReleased(KeyEvent e) {   
        	human.stayStill();
        
    }
    public void keyTyped(KeyEvent e) {		
        
    }
    
    public boolean paused() {
        return paused;
    }
}
