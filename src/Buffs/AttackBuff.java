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
import java.awt.Image;
import java.awt.Rectangle;


/** Class AttackBuff creates a gameBuff object that contains methods targeting 
 *  the ATK value 
 *
 * @author Joseph
 * @author Richard Liang
 * @author Jian Liu
 * 
 * @version 1.0 Build 6/1/2014
 * @version 2.0 Build 6/6/2014
 * 
 */
public class AttackBuff extends gameBuff implements Positionable {
    
   
 
        
        int x, y, w, h;
	//public OfficeClient tc; 
        
        private final int WIDTH=22;
        private int HEIGHT=22;

     
        
	int step = 0;
	private boolean live = true;

	private int[][] pos = {
			          {0, 0}
					  };

        
        /** AttackBuff constructor passes parameters n and m as x and y values 
         *  and creates a gameBuff object on the JFrame while using the 
         *  specified x and y values as coordinates. 
         * 
         * @param n x-axis position.
         * @param m y-axis position. 
         */
	public AttackBuff(int n,int m) {
                
                pos[0][0]=n;
                pos[0][1]=m;  
		x = pos[0][0];
		y = pos[0][1];
		w = h = 15;
         
	}
        
        /** setPosition method passes parameters x and y and sets the positions
         * of the gameBuff object on the JFrame
         * 
         * @param x x axis
         * @param y y axis
         */
        public void setPosition(int x, int y){
            int[][] pos = {
			          {x, y}
					  };
            
            }
        
        /** the draw method passes parameter g and sets the image of the gameBuff
         * on the JFrame
         * 
         * @param g 
         */
	public void draw(Graphics g) {
		if(!live) return;

		//Color c = g.getColor();
		//g.setColor(Color.BLUE);
		//g.fillRect(x, y, w, h);
		//g.setColor(c);

		///move();
                
                
                g.drawImage(ImageHolder.bullet,x,y,WIDTH,HEIGHT,null);
	}

        /** The move method passes no parameters and increments step and sets the
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

        /** The getRect method of the Rectangle class passes no parameters and 
         * only has a return statement
         * 
         * @return a new Rectangle class with x and y coordinates and w and h 
         * width and height
         */
	public Rectangle getRect() {
		return new Rectangle(x, y, w , h);
	}

        /**The isLive method determines if the gameBuff is on the JFrame
         * 
         * @return boolean if the gameBuff is live
         */
	public boolean isLive() {
		return live;
	}

        /**setLive passes a boolean parameter that sets the gameBuff on the 
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
         * 
         * @param object 
         */
        public void collisionWith(Positionable object){
            
        }

}
