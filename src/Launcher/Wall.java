package Launcher;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Wall implements Positionable{
	int x, y, w, h;
        private Image permanent_ice,wallpaper,crackedwall;
        private boolean live = true; 
        private WallType type;
        
        public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
        
        public WallType getType(){
            return type;
        }
        
        
        public static enum WallType{
            Permanent_Wall, Cracked_Wall
        }
	public Wall(int x, int y, int w, int h, boolean live, WallType type) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
                this.live = live;
                this.type = type;
               
                permanent_ice = new ImageIcon("src/images/Walls/Permanent_Ice_Wall2.png").getImage();
                crackedwall = new ImageIcon("src/images/Walls/Cracked_Ice_Wall2.png").getImage();
	}
	@Override
	public void draw(Graphics g){
//                panel = new JPanel();
//                wallpaper = panel.createImage(w,h);
//                Graphics wallgraphics = wallpaper.getGraphics();
//                componentPaint(wallgraphics);
            
            if (w >= 50){
            for (int i = 0; i < w ; i += 42){        
                g.drawImage(permanent_ice, x+i, y, null);
                }
            }/*
            else{
            for (int j = 0; j < h ; j += 42){  
                g.drawImage(crackedwall, x, y+j, null);
        
            }
            }
            */
//              g.setColor(Color.green);
//		g.fillRect(x, y, w, h);
            if(type == Wall.WallType.Cracked_Wall){   
               g.drawImage(crackedwall, x, y, null);  
            }
            else{
               g.drawImage(permanent_ice, x, y, null);  
            }
            
            
	}
        @Override
	public Rectangle getRect(){
		return new Rectangle(x ,y ,w , h);
	}
        @Override
        public void collisionWith(Positionable object){
            
        }
        public void move(){}
        
}
