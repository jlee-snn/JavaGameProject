    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Launcher;

import java.awt.*;
/**
 *
 * @author Owner
 */
public class Grid {
    private int[][] horizontalWalls;
    private int[][] verticalWalls;
    private int[][] costMap;
    private static int rows = 50;
    private static int cols = 67;
    private static int block_size = 12;

    public enum COMPASS {
    	North, South, East, West
    }

    public Grid() {
    	horizontalWalls = new int[rows + 1][cols];
    	verticalWalls = new int[rows][cols + 1];
    	costMap = new int[rows][cols];

    	//costMap initializations
    	for(int i = 0; i < rows; i++) {
    		for(int j = 0; j < cols; j++) {
    			costMap[i][j] = 0;
    		}
    	}
    }

    // check if a neighbor grid cell is blocked by a wall
    // return of -1 indicates indices are out of bounds, otherwise return cost
    public int isNeighborBlocked(int row, int col, COMPASS dir) {
    	int holder = -1;
        if(((row < 0 || row > (rows-1) || col < 0 || col > cols) && (dir == COMPASS.West || dir == COMPASS.East) ) || ((row < 0 || row > rows || col < 0 || col > (cols-1)) && (dir == COMPASS.North || dir == COMPASS.South))) {
    		return -1;
    	}
    	switch (dir) {
    		case North:
    			holder = horizontalWalls[row][col];
                        break;
    		case South:
    			holder = horizontalWalls[row+1][col];
                    break;
    		case West:
    			holder = verticalWalls[row][col];
                    break;
    		case East:
    			holder = verticalWalls[row][col+1];
                    break;
    	}
        return holder;
    }

    public void clearMapWalls()
      {
        int i, j;
        for(i = 0; i < rows; i++)
        {
          for(j = 0; j < cols+1; j++)
          {
            verticalWalls[i][j] = 0;
          }
        }
        for(i = 0; i < rows+1; i++)
        {
          for(j = 0; j < cols; j++)
          {
            horizontalWalls[i][j] = 0;
          }
        }
      }
    //return -1 if index are out of bounds, return -2 if wall index is unreasonable, otherwise return zero
    public int setWall(int row, int col, int isBlocked, COMPASS dir) {
        if(((row < 0 || row > (rows-1) || col < 0 || col > cols) && (dir == COMPASS.West || dir == COMPASS.East)) || ((col < 0 || col > (cols-1) || row < 0 || row > rows) && (dir == COMPASS.North || dir == COMPASS.South)))  {
            return -1;
        }
        if(isBlocked > 1) {
            return -2;
        }
        switch(dir) {
            case North:
                 horizontalWalls[row][col] = isBlocked;
             break;
            case South:
                 horizontalWalls[row][col] = isBlocked;
             break;
            case West:
             verticalWalls[row][col] = isBlocked;
             break;
            case East:
             verticalWalls[row][col+1] = isBlocked;
             break;
        }
        return 0;
      }
    
    // parse a rectangle object and create walls on grid to represent rectangle
    public void addWall(Rectangle rect) {
        int subdivisions;
        int increment;
        int yLoc;
        int xLoc;
        int cell[];
        
        int diffx;
        int diffy;
        
        int margin = 22;
        
        //horizontal walls
        
        //subdivisions = (int) (rect.getWidth() + margin)/block_size;
        //subdivisions = subdivisions + 10; // 2 is arbritary
        //increment = (int) (rect.getWidth() + margin)/subdivisions;
        yLoc = (int) (rect.getY() - margin/2);// + (rect.getHeight() + margin)/2);
        xLoc = (int) (rect.getX() - margin/2);//(rect.getWidth() + margin)/2);
        
        diffx = (int) rect.getWidth() + margin;
        
        //subdivisions++;
        //top wall first
        for(int ii = 0; ii <= diffx; ii++) {
           cell = determineGridCell(xLoc, yLoc);
           //cell[0]= cell[0] - 1;
           setWall(cell[0], cell[1], 1,COMPASS.North); 
           xLoc = xLoc + 1;
        }
        
        yLoc = (int) (rect.getY() + rect.getHeight() + margin);//(rect.getHeight() + margin)/2);
        xLoc = (int) (rect.getX() - margin/2);//(rect.getWidth() + margin)/2);
        for(int ii = 0; ii <= diffx; ii++) {
           cell = determineGridCell(xLoc, yLoc);
           //cell[0]= cell[0] + 1;
           setWall(cell[0], cell[1], 1,COMPASS.South); 
           xLoc = xLoc + 1;//increment;
        }
        
        //vertical walls
        //subdivisions = (int) (rect.getHeight() + margin)/block_size;
        //subdivisions = subdivisions + 10;// + 1; // 2 is arbritary
        //increment = (int) (rect.getHeight() + margin)/subdivisions;// + 1;
        yLoc = (int) (rect.getY() - margin/2);
        xLoc = (int) (rect.getX() - margin/2);//(rect.getWidth() + margin)/2);
        
        diffy = (int) rect.getHeight() + margin;
        
        for(int ii = 0; ii <= diffy; ii++) {
           cell = determineGridCell(xLoc, yLoc);
           //cell[1]= cell[1] - 1;
           setWall(cell[0], cell[1], 1,COMPASS.West); 
           yLoc = yLoc + 1;
        }
        
        
        yLoc = (int) (rect.getY() - margin/2);
        xLoc = (int) (rect.getX() + rect.getWidth() +  margin/2);
        for(int ii = 0; ii <= diffy; ii++) {
           cell = determineGridCell(xLoc, yLoc);
           //cell[1]= cell[1] - 1;
           setWall(cell[0], cell[1], 1,COMPASS.East); 
           yLoc = yLoc + 1;
        }
        
    }
    
    // set the cost for the indicated grid cell
    // return -1 if indices are out of bounds, otherwise return 0
    public int setCost(int row, int col, int val) {
    	if(row < 0 || row > (rows-1) || col < 0 || col > (cols-1)) {
    		return -1;
    	}
    	else {
    		costMap[row][col] = val;
    		return 0;
    	}
    }

    // get the cost of the indicated cell, otherwise return the -1 if out of bounds
    public int getCost(int row, int col) {
    	if(row < 0 || row > (rows-1) || col < 0 || col > (cols-1)) {
    		return -1;
    	}
    	else {
    		return costMap[row][col];
    		
    	}
    }
    
    int getNeighborCost(int row, int col, COMPASS dir)
      {
        if(row < 0 || row > rows || col < 0 || col > cols)
        {
          return -1;
        }
        int cellValue = 0;
        switch(dir)
        {
            case North:
             if(row == 0) { cellValue = 1000; }
             else { cellValue = costMap[row-1][col]; }
             break;
            case South:
             if(row == (rows-1)) { cellValue = 1000; }
               else { cellValue = costMap[row+1][col]; }
             break;
            case West:
             if(col == 0) { cellValue = 1000; }
             else { cellValue = costMap[row][col-1]; }
             break;
            case East:
             if(col == (cols-1)) { cellValue = 1000; }
             else { cellValue = costMap[row][col+1]; }
             break;
        }
        return cellValue;
      }

    public void clearCostMap() {
        int i, j;
        for(i = 0; i < rows; i++)
        {
          for(j = 0; j < cols; j++)
          {
            costMap[i][j] = 0;
          }
        }
    }

    public void printCostMap() {
    	int i, j;
    	System.out.println("Cost Map: ");
    	 for(i = 0; i < rows; i++) {
          for(j = 0; j < cols; j++) {
            System.out.print(costMap[i][j] + " ");
          }
          System.out.println();
        } 
    }
    
    public void printObstacleMap() {
        int i, j;
        System.out.print("Obstacle Map:\r\n");
        for(i = 0; i < rows; i++)
        {
          for(j = 0; j < cols; j++)
          {
            if(horizontalWalls[i][j] == 0)
            {
               System.out.print("    ");
            }
            else
            {
               System.out.print(" ---");
            }
          }
          System.out.print("\r\n");
          for(j = 0; j < cols; j++)
          {
            if(verticalWalls[i][j] == 0)
            {
              System.out.print("  O ");
            }
            else
            {
              System.out.print("| O ");
            }

          }
          System.out.print("|\r\n");
        }
        for(j = 0; j < cols; j++)
        {
          if(horizontalWalls[8][j] == 0)
          {
            System.out.print("    ");
          }
          else
          {
            System.out.print( " ---");
          }
        }
        System.out.print("\r\n");
      }

    public void determineCostMap(int row, int col, COMPASS dir, int cost){

        //System.out.println(cost);
        //set cost code
        //row = row-1;
        //col = col - 1;
        cost++;
        int curr_cost = getCost(row, col);

        if((curr_cost > cost) || (curr_cost ==0)){
            setCost(row,col,cost);
                
            if((row >= 0 && row < rows) && (col >= 0 && col < cols)){

                //do recursion
                    if(isNeighborBlocked(row, col, COMPASS.West) == 0){
                        determineCostMap(row, (col-1), COMPASS.West, cost);
                    }
                    if(isNeighborBlocked(row, col, COMPASS.North) == 0){
                        determineCostMap((row-1), col, COMPASS.North, cost);
                    }
                    if(isNeighborBlocked(row, col, COMPASS.East) == 0){
                        determineCostMap(row, (col+1), COMPASS.East, cost);
                    }
                    if(isNeighborBlocked(row, col, COMPASS.South) == 0){
                        determineCostMap((row+1), col, COMPASS.South, cost);
                    }
                
            }
        }
    }
    
    public int[] determineGridCell(Positionable object) {
        int cell[] = new int[2];
        //May not be the most accurate method
        cell[1] = (int) (object.getRect().getCenterX()*cols/Launcher.GAME_WIDTH - 1);//*cols;
        cell[0] = (int) ((object.getRect().getCenterY()*rows/Launcher.GAME_HEIGHT) - 1);//*rows;
        
        //cell = yCell*cols + xCell;
        
        return cell;
    }
    
    public int[] determineGridCell(int x, int y) {
        int cell[] = new int[2];
        
        cell[1] = (int) (x*cols/Launcher.GAME_WIDTH - 1);
        cell[0] = (int) (y*rows/(Launcher.GAME_HEIGHT) - 1);
        
        //cell = yCell*cols + xCell;
        
        return cell;
    }
    
    public Enemy.Direction determineDirection(Positionable object) {
        Enemy.Direction dir;
        int row;
        int col;
        int lowestCost;
        int currCost;
        
        Enemy obj = (Enemy) object;
        lowestCost = 1000;
        row = determineGridCell(object)[0] ;
        col = determineGridCell(object)[1] ;
        
        if(obj.getDirection() == Enemy.Direction.STOP){
            dir = Enemy.Direction.STOP;
        }
        else {
            dir = Enemy.Direction.RIGHT; // put here to avoid compilation errors
            //if(obj.getDirection() == Enemy.Direction.UP){
            currCost = getNeighborCost(row, col, COMPASS.North);
            if ( (currCost != 0) && (currCost < lowestCost) ){
                lowestCost = currCost;
                dir = Enemy.Direction.UP;
            }
            //}
            currCost = getNeighborCost(row, col, COMPASS.West);
            if ( (currCost != 0) && (currCost < lowestCost) ){
                lowestCost = currCost;
                dir = Enemy.Direction.LEFT;
            }
            currCost = getNeighborCost(row, col, COMPASS.South);
            //System.out.println("neighbor return: " + getNeighborCost(row, col, COMPASS.South));
            //System.out.println("curr: "+currCost+"   low: " + lowestCost);
            if ( (currCost != 0) && (currCost < lowestCost) ){
                lowestCost = currCost;
                dir = Enemy.Direction.DOWN;
            }
            currCost = getNeighborCost(row, col, COMPASS.East);
            if ( (currCost != 0) && (currCost < lowestCost) ){
                lowestCost = currCost;
                dir = Enemy.Direction.RIGHT;
            }
            
            //dir = Enemy.Direction.STOP;
        }
        
        return dir;
    }

}
