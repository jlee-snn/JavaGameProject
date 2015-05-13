package Launcher;
import java.awt.*;


public class Explode {
	int x, y;
	private boolean live = true;  // mark whether the explode live or die.
	int [] diameter = {20};
	int step = 0;
	
	public Explode(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g){
		if(!live){
			//tc.explodes.remove(this);
			return;
		}
		
		if(step == diameter.length){  // if in the last one in the diameter, stop drawing
			live = false;
			step = 0;
			return;
		}
		
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		g.fillOval(x-10, y-10, diameter[step], diameter[step]);
		g.setColor(c);
		step ++;
	}
	
	

}
