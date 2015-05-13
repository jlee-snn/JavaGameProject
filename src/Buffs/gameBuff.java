/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Buffs;



/**Class gameBuff implements the GameBuffInterface and serves as a menu for the
 * different buffers such as AttackBuff and SpeedBuff. 
 *
 * @author Joseph
 * @author Richard Liang
 */
public class gameBuff implements GameBuffInterface{
    
   private int GameBuffIdentifier;
   private int ATK;
   private int SPD;
   

    public enum BUFFTYPE{
        ATTACK, SPEED, HEALTH
    }
    
    BUFFTYPE newbuff;
  
    /**
     * Constructor gameBuff initializes the identifiers of the Buffs
     */
    public gameBuff()
    {
       //GameBuffIdentifier=id;
      // generateBuffType();
       
      
  
       
    }
    
    /**
     * generateBuffType creates new buffs and identifies them with a switch 
     * function
     */
    public void generateBuffType(){
        switch(GameBuffIdentifier){
        case 1:newbuff=BUFFTYPE.ATTACK;
            break;
            
        case 2:newbuff=BUFFTYPE.SPEED;
            break;
            
        case 3:newbuff=BUFFTYPE.HEALTH;
            break;
            
        //Throw in error exception TODO
         
    
        
    }
    }

    /**
     * getGameBuffIdentifier returns the number that is used to determine the 
     * buffs
     * @return GameBuffIdentifier
     */
    @Override
    public int getGameBuffIdentifier() {
        
        return GameBuffIdentifier;
        
    }


    /**
     * getAtkBuff returns the ATK of the buff
     * @return ATK
     */
    @Override
    public int getAtkBuff() {
        
        
        return ATK;
       
    }

    /**
     * getSpdBuff returns the SPD of the buff
     * @return SPD
     */
    @Override
    public int getSpdBuff() {
        
        return SPD;
       
    }

    /**
     * setGameBuffIdentifier takes a parameter n and sets the GameBuffIdentifier
     * to it
     * @param n 
     */
    @Override
    public void setGameBuffIdentifier(int n) {
        GameBuffIdentifier=n;
    }

    /**
     * The setAtkBuff sets the ATK of the buffer to parameter n
     * @param n 
     */
    @Override
    public void setAtkBuff(int n) {
        ATK=n;
    }

    /**
     * setSpdBuff takes a parameter n and sets it equal to SPD
     * @param n 
     */
    @Override
    public void setSpdBuff(int n) {
            SPD=n;
    }


    
}