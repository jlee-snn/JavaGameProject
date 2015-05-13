package Launcher;

import Launcher.Positionable;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HUDView implements Positionable{
	int x, y, w, h;
        private Image HUDWALL;
        private boolean live = true; 
        private hudType type;
        
        
        
        public static Image HUDslots;
        public static Image oneHUD;
        public static Image twoHUD;
        public static Image threeHUD;
        
        public static enum hudType{
            Permanent_Wall
        }
	public HUDView(int x, int y, int w, int h, boolean live, hudType type) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
                this.live = live;
                this.type = type;
               
              //  HUDWALL= new ImageIcon("images/HUD/HUDBuff.png").getImage();
            
 
              //  HUDslots = new ImageIcon("images/HUD/HUDIce.png").getImage();
                //oneHUD= new ImageIcon("images/HUD/one.png").getImage();
                //twoHUD= new ImageIcon("images/HUD/two.png").getImage();
               // threeHUD= new ImageIcon("images/HUD/three.png").getImage();
                
	}
	@Override
	public void draw(Graphics g){
//                panel = new JPanel();
//                wallpaper = panel.createImage(w,h);
//                Graphics wallgraphics = wallpaper.getGraphics();
//                componentPaint(wallgraphics);
            if (w >= 100){
            for (int i = 0; i < w ; i += 42){        
                g.drawImage(HUDWALL, x+i, y, null);
                }
            }
            else{
            
            }
//              g.setColor(Color.green);
//		g.fillRect(x, y, w, h);
            
            
            
            
            //if Character.AtkCounter=
	}
        
        
        
          public void paintComponent(Graphics g){
		
               // g.drawImage(HUDslots, 0, 490, null);
              
                
           
        
        }
        
        
        
                
        @Override
	public Rectangle getRect(){
		return new Rectangle(x ,y ,w , h);
	}
        @Override
        public void collisionWith(Positionable object){
            
        }
        public void move(){}
        public boolean isLive( ){return true;}
        
}
