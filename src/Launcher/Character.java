package Launcher;
import Buffs.AttackBuff;
import Buffs.SpeedBuff;
import java.awt.* ;
import java.awt.event.*;
import java.util.*;
import javax.swing.ImageIcon;
import java.awt.image.*;
 

public class Character implements Positionable{
    
	public static final int XSPEED = 3;  //set the speed of the character in x direction  
	public static final int YSPEED = 3;  //set the speed of the character in y direction
	public static final int WIDTH = 35;  //set the width of the character
	public static final int HEIGHT = 35; //set the height of the character 	
	 
       
        // Buffs
        private int TotalUpgradeCounter=0;         //Count total number of buffs
        public static int SpdCounter=0;           //Count player's speed buffs
        public static int AtkCounter=0;           //Count player's attack buffs 
       
	private boolean live = true;  //mark whether the character is live
	 
	private int x, y;
	private int oldX, oldY;
        private int speed;
	
        private Direction dir = Direction.STOP;
        private Direction barrelDir = Direction.DOWN;  //this is the direction of the barrel 
        private int step = r.nextInt(25) + 3;  // set the initial length in each random process 
        private int health;
        private CharacterType level;
        
            
           
        Timer timerATK;    //Timer Object
        Timer timerSPD;  

        
        
        
        

        private BloodBar bb = new BloodBar();  //Health bar
       
        
        //flag
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
	
	public boolean good;
        
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
	
	public Character(int x, int y, boolean good) {
		this.x = x;
		this.y = y;
		this.oldX = x;  // keep track of the old position of the character
		this.oldY = y;
		this.good = good;
    
	}
	
	public Character(int x, int y, boolean good, Direction dir, int health, int speed, CharacterType level){
		this(x, y, good);
		this.dir = dir;
		this.health = health;
		this.level = level;
                this.speed = speed;
                
                SpdCounter=0;           //Count player's speed buffs
                AtkCounter=0; 
	}       
	
    public enum Direction {
        LEFT, UP, RIGHT, DOWN, STOP
    }
    public enum CharacterType{
    	Enemylevel0, Enemylevel1, Enemylevel2, playerlevel0
    }
    
    
       // @Override
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
		if(x + Character.WIDTH > Launcher.GAME_WIDTH) {
			x = oldX;// Launcher.GAME_WIDTH - Character.WIDTH - 10;
		}
		if(y + Character.HEIGHT + 30 > Launcher.GAME_HEIGHT-65) {
			y = oldY;//Launcher.GAME_HEIGHT - Character.HEIGHT - 30;
                        x= oldX;    //x = Launcher.GAME_WIDTH - Character.WIDTH - 10;
		}
		if(y + Character.HEIGHT + 30> Launcher.GAME_HEIGHT-65) {
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
			
			/*if(r.nextInt(3) > 1){
				this.fire();   // the enemy can fire
			}*/
	     

             
		}
		
    }
    

	public void draw(Graphics g){ 
		//if(!live) return;
		
                int offset = 10;
               // System.out.println(flag_left);
		Color c = g.getColor();  
                
		switch(level){
			case playerlevel0:
				g.setColor(Color.white);
				break;
			case Enemylevel0:
				g.setColor(Color.red);
				break;
			case Enemylevel1:
				g.setColor(Color.blue);
				break;
                        case Enemylevel2:
                            g.setColor(Color.YELLOW);
                            break;
		}
		
		


		switch(barrelDir){      // paint the barrel of our character
		case LEFT:
			g.drawImage(ImageHolder.penguin_left[flag_left],x,y,WIDTH + offset,HEIGHT + offset,null);
                      
			break;
		case UP:
			g.drawImage(ImageHolder.penguin_up[flag_up],x,y,WIDTH + offset,HEIGHT + offset,null);
                   
			break;
		case RIGHT:
			g.drawImage(ImageHolder.penguin_right[flag_right],x,y,WIDTH + offset,HEIGHT + offset,null);
                      
			break;
		case DOWN:
			g.drawImage(ImageHolder.penguin_down[flag_down],x,y,WIDTH + offset,HEIGHT + offset,null);
                    
                        //g.drawImage(penguin_down_1,x,y,null);
           
			break;
                default:
                    g.drawImage(ImageHolder.penguin_left[flag_left],x,y,WIDTH + offset,HEIGHT + offset,null);

                    
                    
		}
                bb.draw(g);
		//move();
                
                
                
	}
	
	void stay(){
		x = oldX;
		y = oldY;
	}
	
	
	public void keyPressed(int key){
		//int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT:
			bL = true;
                        flag_left++;
                        if (flag_left == 3)
                            flag_left = 0;
			break;
		case KeyEvent.VK_UP:
			bU = true;
                        flag_up++;
                        if (flag_up == 3)
                            flag_up = 0;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
                        flag_right++;
                        if (flag_right == 3)
                            flag_right = 0;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
                        flag_down++;
                        if (flag_down == 3)
                            flag_down = 0;
			break;
		}
		locateDirection();
	}
	void locateDirection() {
		if(bL && !bU && !bR && !bD) dir = Direction.LEFT;
		else if(!bL && bU && !bR && !bD) dir = Direction.UP;
		else if(!bL && !bU && bR && !bD) dir = Direction.RIGHT;
		else if(!bL && !bU && !bR && bD) dir = Direction.DOWN;  
		else if(!bL && !bU && !bR && !bD) dir = Direction.STOP;
	}

	public void keyReleased(int key) {
		//int key = e.getKeyCode();
		switch(key){
		//case KeyEvent.VK_SPACE:   //every time the "space" is released, one missile will be fired; 
		//	fire();
		  //  break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false ;
			break;
		}
		locateDirection();
	}
        
        // for simplicity sake, consider moving call to this method to main class to avoid passing entire class
	public Missile fire(){
		if(!live) return null; 
		int x1 = this.x + Character.WIDTH/2-Missile.WIDTH/2;   //set the missile fired from the center of our Character
		int y1 = this.y + Character.HEIGHT/2-Missile.HEIGHT/2;
                
               switch(barrelDir){      // paint the barrel of our character
		case LEFT:
                    
                    x1=x1-12;
			
                      
			break;
		case UP:
	            x1=x1-5;
                   
			break;
		case RIGHT:
                     x1=x1+22;
			
                      
			break;
		case DOWN:
		     x1=x1+12;
                    
                    
           
			break;
                default:
                     x1=x1-12;

                      
		}
             
		Missile m = new Missile(x1, y1, good, barrelDir); // pass the position  and the direction of the barrel to the missile 
		//tc.missiles.add(m);
                //tc.allObjects.add(m);
		return m;  
	}
        
        
        
        //@Override
	public Rectangle getRect(){
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean collidesWithWall(Wall w){
		if(this.live && this.getRect().intersects(w.getRect()) ){
			this.stay();  
			return true;
		}
		return false;
		
	}
        
        public void collideWithCharacter(Character object){
            
            if(this != object){
				if(this.live && object.isLive() && this.getRect().intersects(object.getRect())){
					this.stay();
					object.stay();
					if(this.good == true && object.good == false){
						health--;
					}
					if(this.good == false && object.good == true){
						health--;
					}
                                        if(health <= 0){
                                            this.live = false;
                                        }
                                            
					//return true;
				}
			}
        }
        
        //@Override
        public void collisionWith(Positionable object){
            if(object instanceof Wall) {
                this.collidesWithWall((Wall)object);
                //this.stay();
            }
            if(object instanceof Character){
                this.collideWithCharacter((Character) object);
            }
            if(object instanceof AttackBuff){
                this.eat((AttackBuff) object);
                
            }
            
               if(object instanceof SpeedBuff){
                this.eat((SpeedBuff) object);
            }
        }
        
	
        public boolean collidesWithCharacters(java.util.List<Character> characters){
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
	}
        
        private class BloodBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
                        g.setColor(Color.BLACK);
			g.drawRect(159, 37, 451, 16);
                        g.setColor(Color.BLUE);
			int w = 90 * health/100 ;
			g.fillRect(160, 38, w, 15);
			g.setColor(c);
		}
	}
        
        
        
          // NEW METHODS FOR COUNTING BUFFS
        
         public boolean eat(AttackBuff b) {
            if(this.good == true){
		if(this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			//tc.PrizeScore++; TODO
                    if(AtkCounter<=2 ){
                        AtkCounter++;
                        SpdCounter=SpdCounter+0;
                        //Insert Code Here: 
                        
                        
                        
                        System.out.println("SPEED:"+SpdCounter);
                        System.out.println("ATTACK: "+AtkCounter);
                    } else{
                        System.out.println("Slot Full");
                                }
                    
                                
                      
                
                        
			b.setLive(false);
			return true;
		}
            }
		return false;
	}
        
           public boolean eat(SpeedBuff b) {
            if(this.good == true){
		if(this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			 
                     if(SpdCounter<=2){
                          SpdCounter++;
                          AtkCounter=AtkCounter+0;
                        //Insert Code Here: 
                        
                        
                        System.out.println("SPEED:"+SpdCounter);
                        System.out.println("ATTACK: "+AtkCounter);
                    } else{
                        System.out.println("Slot Full");
                                } ;
                        
			b.setLive(false);
			return true;
		}
            
		
	}
            return false;
           }
           
           
           
           public int ActivateAttackBuff(){
                
               if(AtkCounter<=3 && AtkCounter >= 1){
                        --AtkCounter;
                        
                   
                        Missile.attack=2;
                        
                        this.Reminder1(2);
                    
               }
               if(AtkCounter<=0){
                   AtkCounter=0;
               }
               else{
                        System.out.println("None Left");
                   
                        
               }
               return 0;
           }
           
           
          // public int getAtkCounter(){
          //     return AtkCounter;
           //}
           
          // public int getSpdCounter(){
           //    return SpdCounter;
          // }
           
           
           
           public int ActivateSpeedBuff(){
               
               if(SpdCounter<=3 && SpdCounter >= 1){
                        --SpdCounter;
                        speed=SpeedBuff.movespeedBonus;
                        System.out.println(SpdCounter);
                        this.Reminder2(2);
                      
               }
               
                 if(AtkCounter<=0){
                   AtkCounter=0;
               }
               else{
                        System.out.println("None Left");
                   
                        
               }
               return 0;
           }
           
      
           
           
        public void Reminder1(int seconds) {
        timerATK = new Timer();
        timerATK.schedule(new RemindTaskAtk(), seconds*1000);
        
        
	}
        public void Reminder2(int seconds) {
        timerSPD = new Timer();
        timerSPD.schedule (new RemindTaskSpd(), seconds*1000);
	}

       class RemindTaskAtk extends TimerTask {
     
        //@Override
        public void run() {
            
       
            Missile.attack=1;
            
            
            System.out.format("Upgrade over!%n");
            timerATK.cancel(); //Terminate the timer thread
        }
       }
        
        
       class RemindTaskSpd extends TimerTask {
     
        @Override
        public void run() {
            
            speed=6;
            //Missile.attack=1;
            
            
            System.out.format("Upgrade over!%n");
            timerSPD.cancel(); //Terminate the timer thread
        }
    }
       
       
        
}

