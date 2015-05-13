/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** Package containing all classes, interfaces, and methods that deals 
 *  with game buffs.
 * 
 */



package Buffs;

/**GameBuffInterface is a java interface that contains all related methods
 * for the game buffs/upgrades. 
 *
 * @author Joseph Lee
 * @author Richard Liang
 * 
 * @version 2.0 Build June 6, 2014
 * 
 */

public interface GameBuffInterface {
   
    
    int attackBonus=2;      //Hardcoded attack attribute of buff 
    int movespeedBonus=20;  //Hardcoded speed attribute of buff

 
    
   
    
    /** Returns the GameBuffIdentifier of a buff object. 
     * 
     * @return gameBuffIdentifier
     */
    int getGameBuffIdentifier();
  
    
    
     /** Returns the ATK value of the buff object.
     * 
     * @return ATK
     */
    int getAtkBuff();
  
    
    
     /** Returns the SPD value of the buff object.
     * 
     * @return
     */
    int getSpdBuff();
    
 
    /** Method set the gameBuffIdentifier of the buff object to n.
    * 
    * @param n The integer value passed to transform the gameBuffIdentifier.
    */
    public void setGameBuffIdentifier(int n);
  
    
    
    /** Method sets the ATK of the buff object to n. 
     * 
     * @param n The integer value passed to transform the ATK. 
     */
    public void setAtkBuff(int n);
 
   
    
    
    /** Method sets the SPD of the buff object to n. 
     * 
     * @param n The integer value passed to transform the SPD.
     */
    public void setSpdBuff(int n);
      
    
}
