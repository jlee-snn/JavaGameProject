package Launcher;
import java.awt.* ;
import java.util.List;
import javax.swing.ImageIcon;

public class Missile implements Positionable{
	public static final int XSPEED = 15;  // set the speed of the missile in x direction 
	public static final int YSPEED = 15;  // set the speed of the missile in y direction
	public static final int WIDTH = 10;  //set the width of the character
	public static final int HEIGHT = 10; //set the height of the character 	
	
	int x, y;
        
      

	
	Character.Direction dir;
        Enemy.Direction dir_enemy;
	private boolean live = true;
	private boolean good;
	public static int attack = 1;
        
	public int getAttack() {
		return attack;
	}

	public void setAttack(int n) {
		attack = n;
	}

	public Missile(int x, int y, Character.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
                
	}

	public Missile(int x, int y, boolean good, Character.Direction dir) {
		
		this.x = x;
		this.y = y;
		this.good = good;
		this.dir = dir;
                
             
	}
        public Missile(int x, int y, Enemy.Direction dir) {
		this.x = x;
		this.y = y;
		this.dir_enemy = dir;
                
                
             
	}

	public Missile(int x, int y, boolean good, Enemy.Direction dir) {
		
		this.x = x;
		this.y = y;
		this.good = good;
		this.dir_enemy = dir;
                
           
	}
	
	
	public void draw(Graphics g) {
		/*if(!live) {
                    //TODO this logic can be moved to the update method, have a standard interface for all?
			//tc.missiles.remove(this);
                        //tc.allObjects.remove(this);
			return;
		}*/
            
               // bullet = new ImageIcon("/src/images/Character/ray.png").getImage();
		//g.drawImage(bullet, WIDTH, HEIGHT, null);
		Color c = g.getColor();
                
		g.setColor(Color.BLACK);   // the color of the missile
		g.fillOval(x, y, WIDTH, HEIGHT);    // the area  of the missile
                
                g.setColor(Color.BLUE);   // the color of the missile
		g.fillOval(x+1, y+1, WIDTH-2, HEIGHT-2);    // the area  of the missile
		g.setColor(c);	
		
		move();
	}
	
	public void move() {
		
		if( good==false){
			switch(dir) {
			case LEFT:
				x -= 50;
			    live = false;
				break;
			case UP:
				y -= 50;
				live = false;
				break;
			case RIGHT:
				x += 50;
				live = false;
				break;
			case DOWN:
				y += 50;
				live = false;
				break;
			}						
		}
		
		switch(dir) {
		case LEFT:
			x -= XSPEED;
			break;
		case UP:
			y -= YSPEED;
			break;
		case RIGHT:
			x += XSPEED;
			break;
		case DOWN:
			y += YSPEED;
			break;

		}
		

		if(x < 0 || y < 0 || x > Launcher.GAME_WIDTH || y > Launcher.GAME_HEIGHT) {
			live = false;
		}
		
	}

	public boolean isLive() {
		return live;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public boolean hitEnemy(Character t) {
		if(this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != t.isGood()) {
			if(t.isGood() == false){
				//tc.Score ++;
                                Launcher.setScore(Launcher.getScore() + 1);
			}
			t.setHealth(t.getHealth()-attack);
			if(t.getHealth() <= 0){
				t.setLive(false);
			}
			this.live = false;  // set the live of the missile to be dead 
			Explode e = new Explode(x, y);
			//.addObject(e);
			return true;
		}
		return false;
	}
	
	/*public boolean hitEnemys(List<Character> characters){
		for(int i=0; i<characters.size(); i++){
			if(hitEnemy(characters.get(i))){
				return true;
			}
		}
		return false;
	}*/
	public boolean hitWall(Wall w){
		if(this.live && this.getRect().intersects(w.getRect()) ){
			this.live = false;
                        if(w.getType() == Wall.WallType.Cracked_Wall){
                            w.setLive(false);
                        }
			return true;
		}
		return false;
		
	}
        
        public void collisionWith(Positionable object){
            if(object instanceof Wall){
                this.hitWall((Wall)object);
            }
            if(object instanceof Character){
                this.hitEnemy((Character)object);
            }
        }
	
}