/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Launcher;

//import launcher.
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class Quadtree {
 
  private int MAX_OBJECTS = 10;
  private int MAX_LEVELS = 5;
 
  private int level;
  private List<Positionable> objects;
  private Positionable bounds;
  private Quadtree[] nodes;
 
 /*
  * Constructor
  */
  public Quadtree(int pLevel, Positionable pBounds) {
   level = pLevel;
   objects = new ArrayList<Positionable>();
   bounds = pBounds;
   nodes = new Quadtree[4];
  }
  
  /*
  * Clears the quadtree
  */
  public void clear() {
   objects.clear();
 
   for (int i = 0; i < nodes.length; i++) {
     if (nodes[i] != null) {
       nodes[i].clear();
       nodes[i] = null;
     }
   }
  }
  
  private void split() {
   int subWidth = (int) (bounds.getRect().getWidth() / 2);
   int subHeight = (int) (bounds.getRect().getHeight() / 2);
   int x = (int) bounds.getRect().getX();
   int y = (int) bounds.getRect().getY();
 
   nodes[0] = new Quadtree(level+1, new Wall(x + subWidth, y, subWidth, subHeight, true, Wall.WallType.Permanent_Wall));
   nodes[1] = new Quadtree(level+1, new Wall(x, y, subWidth, subHeight, true,Wall.WallType.Permanent_Wall));
   nodes[2] = new Quadtree(level+1, new Wall(x, y + subHeight, subWidth, subHeight,true, Wall.WallType.Permanent_Wall));
   nodes[3] = new Quadtree(level+1, new Wall(x + subWidth, y + subHeight, subWidth, subHeight,true, Wall.WallType.Permanent_Wall));
 }
  
  /*
  * Determine which node the object belongs to. -1 means
  * object cannot completely fit within a child node and is part
  * of the parent node
  */
  private int getIndex(Positionable pRect) {
   int index = -1;
   double verticalMidpoint = bounds.getRect().getX() + (bounds.getRect().getWidth() / 2);
   double horizontalMidpoint = bounds.getRect().getY() + (bounds.getRect().getHeight() / 2);
 
   // Object can completely fit within the top quadrants
   boolean topQuadrant = (pRect.getRect().getY() < horizontalMidpoint && pRect.getRect().getY() + pRect.getRect().getHeight() < horizontalMidpoint);
   // Object can completely fit within the bottom quadrants
   boolean bottomQuadrant = (pRect.getRect().getY() > horizontalMidpoint);
 
   // Object can completely fit within the left quadrants
   if (pRect.getRect().getX() < verticalMidpoint && pRect.getRect().getX() + pRect.getRect().getWidth() < verticalMidpoint) {
      if (topQuadrant) {
        index = 1;
      }
      else if (bottomQuadrant) {
        index = 2;
      }
    }
    // Object can completely fit within the right quadrants
    else if (pRect.getRect().getX() > verticalMidpoint) {
     if (topQuadrant) {
       index = 0;
     }
     else if (bottomQuadrant) {
       index = 3;
     }
   }
 
   return index;
 }
  
  /*
 * Insert the object into the quadtree. If the node
 * exceeds the capacity, it will split and add all
 * objects to their corresponding nodes.
 */
 public void insert(Positionable pRect) {
   if (nodes[0] != null) {
     int index = getIndex(pRect);
 
     if (index != -1) {
       nodes[index].insert(pRect);
 
       return;
     }
   }
 
   objects.add(pRect);
 
   if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
      if (nodes[0] == null) { 
         split(); 
      }
 
     int i = 0;
     while (i < objects.size()) {
       int index = getIndex(objects.get(i));
       if (index != -1) {
         nodes[index].insert(objects.remove(i));
       }
       else {
         i++;
       }
     }
   }
 }
 
 /*
 * Return all objects that could collide with the given object
 */
 public List retrieve(List returnObjects, Positionable pRect) {
   int index = getIndex(pRect);
   if (index != -1 && nodes[0] != null) {
     nodes[index].retrieve(returnObjects, pRect);
   }
 
   returnObjects.addAll(objects);
 
   return returnObjects;
 }
  
}