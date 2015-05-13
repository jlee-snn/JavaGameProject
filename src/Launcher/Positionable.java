/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Launcher;

import java.awt.*;
import java.awt.Rectangle;
/**
 *
 * @author Owr
 */
public interface Positionable {
    public Rectangle getRect();
    public void collisionWith(Positionable object);
    public void draw(Graphics g);
    public void move();
    public boolean isLive();
}
