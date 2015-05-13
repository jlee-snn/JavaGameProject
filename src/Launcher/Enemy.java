/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Launcher;
import java.awt.* ;
import java.awt.event.*;
import java.util.*;
import javax.swing.ImageIcon;
 

public class Enemy extends Character implements Positionable{
	public static final int XSPEED = 2;  //set the speed of the character in x direction  
	public static final int YSPEED = 2;  //set the speed of the character in y direction
	public static final int WIDTH = 21;//34;  //set the width of the character
	public static final int HEIGHT = 30;// 56; //set the height of the character 	
	
	
	private boolean live = true;  //mark whether the character is live
	 
	private int x, y;
	private int oldX, oldY;
        private int speed;
	
        private Direction dir = Direction.STOP;
        private Direction barrelDir = Direction.DOWN;  //this is the direction of the barrel 
        private int step = r.nextInt(25) + 3;  // set the initial length in each random process 
        private int health;
        private CharacterType level;

        private BloodBar bb = new BloodBar();
        
        //image for character
	
       
	
        private int flag_up = 0;
        private int flag_down = 0;
        private int flag_left = 0;
        private int flag_right = 0;
        
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	private boolean good;
	public boolean isGood() {
		return good;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}


	private static Random r = new Random();  // generate a random number
	
	private boolean bL = false, bU = false, bR = false, bD = false;
	
    /**
     *
     * @param x
     * @param y
     * @param good
     */
    public Enemy(int x, int y, boolean good) {
                super(x,y,good);
		this.x = x;
		this.y = y;
		this.oldX = x;  // keep track of the old position of the character
		this.oldY = y;
		this.good = good;
               // enemy = new ImageIcon("images/Enemies/Easy/Level1_EvilClam1.png").getImage();
        

	}
	
    public Enemy(int x, int y, boolean good, Direction dir, int health, int speed, CharacterType level){
		this(x, y, good);
		this.dir = dir;
		this.health = health;
		this.level = level;
                this.speed = speed;
	}       
	
    public enum Direction {
        LEFT, UP, RIGHT, DOWN, STOP
    }
    public enum CharacterType{
    	Enemylevel1, Enemylevel2, Enemylevel3, playerlevel0
    }
    
    // ENEMY AI
    public Enemy.Direction getDirection(){
        return dir;
    }
    // ENEMY AI
    public void setDirection(Enemy.Direction x){
        dir = x;
    }
    
    public void move(){
		this.oldX = x;
		this.oldY = y;
        
    	switch(dir){
    	case LEFT:
    		x -= speed;
    		break;
    	case UP:
    		y -=speed;
    		break;
    	case RIGHT:
    		x += speed;
    		break;
    	case DOWN:
    		y += speed;
    		break;
    	case STOP:
    		break;
    	}
    	if(this.dir != Direction.STOP){   // as long as the character moves, the direction of the barrel is the same as that of character
    		this.barrelDir = this.dir;
    	}
    	
		if(x < 0) {   //set the character can't move out of frame
			x = 0;
		}
		if(y < 70) {
			y = 70;
		}
		if(x + Enemy.WIDTH > Launcher.GAME_WIDTH) {
                    x= oldX;
//x = Launcher.GAME_WIDTH - Character.WIDTH - 10;
		}
		if(y + Enemy.HEIGHT + 30> Launcher.GAME_HEIGHT-60) {
                    y = oldY;
			//y = Launcher.GAME_HEIGHT - Character.HEIGHT - 30;
		}
		
		if(!good){  // set a enemy move in random direction
			Direction[] dirs = Direction.values();
                        
			if(step ==0 ){  //only the step decreases to 0, the new direction will be given
				step = r.nextInt(25) + 3;
				int rn = r.nextInt(dirs.length);
				dir = dirs[rn];
             
			}
                        
			step --; 
                        switch(dir){
                                    case LEFT:
                                        flag_left++;
                                        if (flag_left == 3)
                                        flag_left = 0;
                                        break;  
                                    case RIGHT:
                                        flag_right++;
                                        if (flag_right == 3)
                                        flag_right = 0;
                                        break;
                                    case DOWN:
                                        flag_down++;
                                        if (flag_down == 3)
                                        flag_down = 0;
                                        break;
                                    case UP:
                                        flag_up++;
                                        if (flag_up == 3)
                                        flag_up = 0;
                                        break;
                                }

             
		}
		
    }
    

	public void draw(Graphics g){ 
		//if(!live) return;
		
		Color c = g.getColor();  

		switch(barrelDir){      // paint the barrel of our character
		case LEFT:
                        switch(level){
			case Enemylevel1:
				g.drawImage(ImageHolder.enemy_level1_left[flag_left],x,y,WIDTH,HEIGHT,null); 
				break;
			case Enemylevel2:
				g.drawImage(ImageHolder.enemy_level2_left[flag_left],x,y,WIDTH,HEIGHT,null);
				break;
                        case Enemylevel3:
				g.drawImage(ImageHolder.enemy_level3_left[flag_left],x,y,WIDTH,HEIGHT,null);
                                break;                        
                        }
			break;
		case UP:
			switch(level){
			case Enemylevel1:
				g.drawImage(ImageHolder.enemy_level1_up[flag_up],x,y,WIDTH,HEIGHT,null); 
                                //System.out.println(flag_left);
				break;
			case Enemylevel2:
				g.drawImage(ImageHolder.enemy_level2_up[flag_up],x,y,WIDTH,HEIGHT,null); 
				break;
                        case Enemylevel3:
				g.drawImage(ImageHolder.enemy_level3_up[flag_up],x,y,WIDTH,HEIGHT,null); 
                                break;                        
                        }
			break;
		case RIGHT:
			switch(level){
			case Enemylevel1:
				g.drawImage(ImageHolder.enemy_level1_right[flag_right],x,y,WIDTH,HEIGHT,null); 
				break;
			case Enemylevel2:
				g.drawImage(ImageHolder.enemy_level2_right[flag_right],x,y,WIDTH,HEIGHT,null);
				break;
                        case Enemylevel3:
				g.drawImage(ImageHolder.enemy_level3_right[flag_right],x,y,WIDTH,HEIGHT,null);
                                break;                        
                        }
			break;
		case DOWN:
			switch(level){
			case Enemylevel1:
				g.drawImage(ImageHolder.enemy_level1_down[flag_down],x,y,WIDTH,HEIGHT,null); 
				break;
			case Enemylevel2:
				g.drawImage(ImageHolder.enemy_level2_down[flag_down],x,y,WIDTH,HEIGHT,null); 
				break;
                        case Enemylevel3:
				g.drawImage(ImageHolder.enemy_level3_down[flag_down],x,y,WIDTH,HEIGHT,null); 
                            break;                        
                        }
			break;
		}
                bb.draw(g);
		//move();
	}
	
	private void stay_enemy(){
		x = oldX;
		y = oldY;
	}
	
	

	void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = Direction.LEFT;
		else if(!bL && bU && !bR && !bD) dir = Direction.UP;
		else if(!bL && !bU && bR && !bD) dir = Direction.RIGHT;
		else if(!bL && !bU && !bR && bD) dir = Direction.DOWN;  
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}



	public Rectangle getRect(){
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean collidesWithWall(Wall w){
		if(this.live && this.getRect().intersects(w.getRect()) ){
			this.stay_enemy();  
			return true;
		}
		return false;
		
	}
       
        public void collideWithCharacter(Character object){
            
        
            if(this.live && object.isLive() && this.getRect().intersects(object.getRect())){
                this.stay();
		//object.stay();
		/*if(this.good == true && object.good == false){
                    health--;
		}
		if(this.good == false && object.good == true){
                    health--;
		}
                if(health <= 0){
                    this.live = false;
                }*/
                                            
					//return true;
            }
        }
        
        public void collideWithEnemy(Enemy object){
            
            //we dont need this conditional
            if(this != object){
			if(this.live && object.isLive() && this.getRect().intersects(object.getRect())){
				this.stay_enemy();
				/*object.stay_enemy();
				if(this.good == true && object.good == false){
					health--;
				}
				if(this.good == false && object.good == true){
					health--;
				}
                                if(health <= 0){
                                    this.live = false;
                                }*/
                                            
					//return true;
				}
			}
        }   
        
        
        
        public void collisionWith(Positionable object){
            if(object instanceof Wall) {
                this.collidesWithWall((Wall)object);
                //this.stay();
            }
            if(object instanceof Character){
                this.collideWithCharacter((Character) object);
            }
            if(object instanceof Enemy) {
                this.collideWithEnemy((Enemy) object);
            }
           
        }
        
	
       /* public boolean collidesWithCharacters(java.util.List<Character> characters){
		for(int i=0; i<characters.size(); i++){
			Character t = characters.get(i);
			if(this != t){
				if(this.live && t.isLive() && this.getRect().intersects(t.getRect())){
					this.stay();
					t.stay();
					if(this.good == true && t.good == false){
						health--;
					}
					if(this.good == false && t.good == true){
						health--;
					}
                                        if(health <= 0){
                                            this.live = false;
                                        }
                                            
					return true;
				}
			}
		}   
		return false;
	}*/
        
        private class BloodBar {
		public void draw(Graphics g) {
			//Color c = g.getColor();
			//g.setColor(Color.white);
			//g.drawRect(160, 38, 90, 15);
			//int w = 90 * health/100 ;
			//g.fillRect(160, 38, w, 15);
		//	g.setColor(c);
		}
	}
        
}

