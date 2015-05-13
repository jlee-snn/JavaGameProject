/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Buffs;


import Launcher.ImageHolder;
import Launcher.Positionable;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**Class AttackBuff creates a gameBuff object that contains methods targeting 
 * the SPD value
 * 
 * 
 * @author Joseph
 * @author Richard Liang
 */
public class SpeedBuff extends gameBuff implements Positionable {
    
   
        private final int WIDTH=22;
        private final int HEIGHT=22;
        
        int x, y, w, h;
	//public OfficeClient tc; 

        
      
        
	int step = 0;
	private boolean live = true;

	private int[][] pos = {
			          {350, 300}
					  };

        /**
         * SpeedBuff constructor passes parameter n and m as x and y values 
         * and creates a gameBuff object on the JFrame while using the 
         * specified x and y values as coordinates. 
         * 
         * @param n x axis
         * @param m y axis
         */
	public SpeedBuff(int n,int m) {
            
                pos[0][0]=n;
                pos[0][1]=m;
		x = pos[0][0];
		y = pos[0][1];
		w = h = 15;
	}

        /**
         * draw method passes a parameter g and sets the image of the gameBuff
         * on the JFrame
         * 
         * @param g 
         */
        @Override
	public void draw(Graphics g) {
		if(!live) return;
                
                  g.drawImage(ImageHolder.coffee,x,y,WIDTH,HEIGHT,null);

		//Color c = g.getColor();
		//g.setColor(Color.RED);
		//g.fillRect(x, y, w, h);
		//g.setColor(c);

		///move();
	}

        /**move is a method that passes no parameters and increments step and sets the
         * x and y coordinates of the gameBuff in the position
         * 
         */
	public void move() {
		step ++;
		if(step == pos.length){
			step = 0;
		}
		x = pos[step][0];
		y = pos[step][1];
	}

        /**
         * The getRect method of the Rectangle class passes no parameters and 
         * only has a return statement
         * @return a new Rectangle class with x and y coordinates and w and h 
         * width and height
         */
	public Rectangle getRect() {
		return new Rectangle(x, y, w , h);
	}

        /**
         * The isLive method determines if the gameBuff is on the JFrame
         * 
         * @return boolean if the gameBuff is live
         */
	public boolean isLive() {
		return live;
	}

        /**
         * setLive passes a boolean parameter that sets the gameBuff on the 
         * JFrame
         * 
         * @param live sets boolean to live or not
         */
	public void setLive(boolean live) {
		this.live = live;
	}
        
        /**
         * collisionWith passes a parameter object that determines when gameBuff
         * collides with character
         * @param object 
         */
        public void collisionWith(Positionable object){
            
        }

}
