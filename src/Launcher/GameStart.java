package Launcher;
import Buffs.AttackBuff;
import Buffs.SpeedBuff;
import Music.JavaAudioSFX;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
//import javax.swing.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GameStart extends JPanel implements Runnable{
	
        //the main thread becomes the game loop
        private Thread gameloop;
        
        public Thread getgameloop(){
            return gameloop;
        }
        //Thread.sleep(1000);
        
        
        
        //Music
        
        private JavaAudioSFX sound = new JavaAudioSFX();
        
        
        private int enemyCounter;
        private boolean characterCounter;

        
    
        //public HUDView hudview=new HUDView( 700, 100, 40, 400,true,HUDView.hudType.Permanent_Wall);
        
        private final int DELAY = 25;
        private Image offscreenImage = null;
        private boolean pause = false;
        private boolean level2ready = true;
        private boolean level3ready = true;
        
        private JButton quitGame;
        
        
        private int timer;
        
        private Quadtree quad = new Quadtree(0, new Wall(0,0,Launcher.GAME_WIDTH,Launcher.GAME_HEIGHT, true, Wall.WallType.Permanent_Wall));
	public List<Positionable> allObjects = new ArrayList<Positionable>();
        private List<Positionable> returnObjects = new ArrayList<Positionable>();
        
        public Character player = new Character(400, 300, true, Character.Direction.STOP, 500, 6,  Character.CharacterType.playerlevel0);  // the initial position of the player
	
  
        
        public AttackBuff ab= new AttackBuff(700,100);
        public AttackBuff ab2= new AttackBuff(700,400);
        public SpeedBuff sb= new SpeedBuff(100,100);
        public SpeedBuff sb2=new SpeedBuff(100,400);
                
        public AttackBuff ab3= new AttackBuff(600,100);
        public AttackBuff ab4= new AttackBuff(600,400);
        public SpeedBuff sb3= new SpeedBuff(130,120);
        public SpeedBuff sb4=new SpeedBuff(120,400);
                
                
        public AttackBuff ab5= new AttackBuff(700,100);
        public AttackBuff ab6= new AttackBuff(700,400);
        public SpeedBuff sb5= new SpeedBuff(100,100);
        public SpeedBuff sb6=new SpeedBuff(100,400)        
                
                ;
        boolean b[] = {true, true, true};
        
        public Grid enemyAI;
        
        //private JFrame pauseMenu;
        //private JPanel menu;
        private JButton pauseGame;
        
        
        
        //add the image for the screen
        private Image icetilefloor;
        
        //private Image HUDslots;
        private Image oneHUD;
        private Image twoHUD;
        private Image threeHUD;
        
	public GameStart() {
            GridBagLayout generalLayout = new GridBagLayout();
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(5, 5, 5, 5); // default spacing
            setLayout(generalLayout);
            pauseGame = new JButton("Pause");
            constraints.gridx = 0;
            constraints.gridy = 1;
            constraints.gridwidth = 2;
            constraints.gridheight = 1;
            constraints.fill = GridBagConstraints.NONE;
            constraints.weightx = 0;
            constraints.weighty = 0;
            generalLayout.setConstraints(pauseGame, constraints);
            //add(pauseGame);
            
            
            
            
            icetilefloor = new ImageIcon("src/images/Map/IceMapV3.png").getImage();
            
            
 
               // HUDslots = new ImageIcon("src/images/HUD/HUDIce.png").getImage();
                oneHUD= new ImageIcon("images/HUD/one.png").getImage();
                twoHUD= new ImageIcon("images/HUD/two.png").getImage();
                threeHUD= new ImageIcon("images/HUD/three.png").getImage();
            
            
       
            
         
   
                       
            init();
            
	}
        
        
        
        
        //default paint method           
        public void paintComponent(Graphics g){
		g.drawImage(icetilefloor,0, -7, null);
               // g.drawImage(HUDslots, 0, 470, null);
            
                
                //painted Border
                
                g.setColor(Color.BLACK);
                g.drawRect(0,0, Launcher.GAME_WIDTH-1, Launcher.GAME_HEIGHT-10);
                
                g.setColor(Color.YELLOW);
                
                HUDLayoutSpeed(g);
                HUDLayoutAttack(g);
                
                
             
                
                        
        }
        
        
        
        
        
        
        
        public void HUDLayoutSpeed(Graphics g){
       
             int countercase=player.SpdCounter;
            
            switch (countercase){
                
                case 0: g.drawString("0", 280, 530);
                    break;
                    
                case 1: g.drawString("1", 280, 530);
                    break;
                case 2: g.drawString("2", 280, 530);
                    break;
                    
                case 3: g.drawString("3", 280, 530);
                    break;
                    default: g.drawString("0", 280, 530);
                    
                
            }
                
                     
                 
            
                     
                 
        }
        
             public void HUDLayoutAttack(Graphics g){
       
             int countercase=player.AtkCounter;
            
            switch (countercase){
                
                case 0: g.drawString("0", 166, 530);
                    break;
                    
                case 1: g.drawString("1", 166, 530);
                    break;
                case 2: g.drawString("2", 166, 530);
                    break;
                    
                case 3: g.drawString("3", 166, 530);
                    break;
                default: g.drawString("0", 166, 530);
                      
              
                    
                
            }
            }
                
        

	//@Override
	public void init() {
            setBindings();
            setSize(Launcher.GAME_WIDTH, Launcher.GAME_HEIGHT);
           
            setBackground(Color.BLACK);
            setPreferredSize(new Dimension(Launcher.GAME_WIDTH, Launcher.GAME_HEIGHT));
            
            timer = 0;
            enemyAI = new Grid();
            quitGame = new JButton();
            
            enemyAI = new Grid();
           
            allObjects.add(player);
            allObjects.add(ab);
            allObjects.add(ab2);
            allObjects.add(sb);
            allObjects.add(sb2);
            
	    //init the enemy
            for(int i=0; i<5; i++){
		allObjects.add(new Enemy(25 + 40 * (i+1), 50, false, Enemy.Direction.DOWN, 1,3, Enemy.CharacterType.Enemylevel1)); // create the enemy 
              
            }
            //initalize walls
            for(int i=0; i<5; i++){
		allObjects.add(new Wall(600, 50 + 42 * (i+1), 37, 42,true, Wall.WallType.Cracked_Wall)); 
                allObjects.add(new Wall(50+42*i, 428, 37, 42,true, Wall.WallType.Cracked_Wall));
               // allObjects.add(new Wall(210+42*i, 386, 42, 42,true, Wall.WallType.Cracked_Wall));
               // allObjects.add(new Wall(420+42*i, 428, 42, 42,true, Wall.WallType.Cracked_Wall));
               // allObjects.add(new Wall(420+42*i, 386, 42, 42,true, Wall.WallType.Cracked_Wall));
                //allObjects.add(new Wall(442, 50 + 42 * (i+1), 42, 42,true, Wall.WallType.Cracked_Wall));
                 // allObjects.add(new Wall(414, 50 + 42 * (i+1), 42, 42,true, Wall.WallType.Cracked_Wall));                
                allObjects.add(new Wall(700, 50 + 37 * (i+2), 42, 42,true, Wall.WallType.Permanent_Wall));
                allObjects.add(new Wall(736, 50 + 37 * (i+1), 42, 42,true, Wall.WallType.Cracked_Wall));
                //allObjects.add(new Wall(610, 50 + 42 * (i+1), 42, 42,true, Wall.WallType.Cracked_Wall));
                //allObjects.add(new Wall(652, 50 + 42 * (i+1), 42, 42,true, Wall.WallType.Cracked_Wall));
            }
            
            allObjects.add(new Wall(100, 200, 37, 42, true, Wall.WallType.Cracked_Wall));
            allObjects.add(new Wall(100, 250, 37, 42,true, Wall.WallType.Permanent_Wall));
            allObjects.add(new Wall(100, 300, 37, 42, true, Wall.WallType.Permanent_Wall));
            allObjects.add(new Wall(100, 350, 37, 42, true, Wall.WallType.Permanent_Wall));
            
            //initialize grid
            
            for(Positionable object : allObjects) {
                if(object instanceof Wall) {
                    enemyAI.addWall(object.getRect());
                }
            }
            enemyAI.addWall(new Rectangle(0,0,Launcher.GAME_WIDTH, 55));
          
            gameUpdate();
            setDoubleBuffered(true);
            
	}
        
        @Override
        public void addNotify() {
            super.addNotify();
            gameloop = new Thread(this);
            gameloop.start();
        }

	//@Override
	public void update() throws InterruptedException  {
            //System.out.println("test");
            
            if(!pause && level2ready && level3ready){
            gameUpdate();
            }
            else {
              //pause menu
                timer = timer +1;
                if(timer > 150){
                    level2ready = true;
                    level3ready = true;
                    timer = 0;
                }
            }
	}
        
        @Override
        public void paint(Graphics g) {
            super.paint(g);
                this.HUDLayoutAttack(g);
                this.HUDLayoutSpeed(g);

            //setBackground(Color.BLACK);
            if(level2ready && level3ready){
                    if(offscreenImage == null){
                                 offscreenImage  = this.createImage(Launcher.GAME_WIDTH, Launcher.GAME_HEIGHT);
                         }
        //            System.out.println(Launcher.GAME_WIDTH);
        //            System.out.println(Launcher.GAME_HEIGHT);
                    Graphics goffScreen = offscreenImage.getGraphics();
                    paintComponent(goffScreen);
                    Color c = goffScreen.getColor();
        //            goffScreen.setColor(Color.BLACK);
        //            goffScreen.fillRect(0, 0, Launcher.GAME_WIDTH, Launcher.GAME_HEIGHT);
        //            goffScreen.setColor(c);
                    g.drawImage(offscreenImage, 0, 0, null);
                    
                   
                   
                    g.setColor(Color.WHITE);
                    
                   
                    g.drawString("Score: " + Launcher.Score , Launcher.GAME_WIDTH - 200, 540);
                    g.drawString("Health: " + player.getHealth() , Launcher.GAME_WIDTH - 720, 50);
                    g.drawString("Bonus: " + (player.AtkCounter+player.SpdCounter), Launcher.GAME_WIDTH - 200, 560);

                    for(Positionable element : allObjects) {

                        element.draw(g);
                    }
            }
            else if(!level2ready){
        
            g.drawImage(offscreenImage, 0, 0, null);
           // g.drawString("Score: " + Launcher.Score , Launcher.GAME_WIDTH - 150, 60);
                g.drawImage(ImageHolder.Level_2, 0, 0, 800, 600, null); 
               
            }
            else if(!level3ready){
                            g.drawImage(ImageHolder.Level_3, 0, 0, 800, 600, null); 
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }

        public void run() {
            
            long beforeTime, timeDiff, sleep;

            beforeTime = System.currentTimeMillis();

            while (true) {

                try {
                    update();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameStart.class.getName()).log(Level.SEVERE, null, ex);
                }
                repaint();

                timeDiff = System.currentTimeMillis() - beforeTime;
                sleep = DELAY - timeDiff;

                if (sleep < 0) {
                    sleep = 2;
                }

                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + e.getMessage());
                }

                beforeTime = System.currentTimeMillis();
            }
        }
        
       
        private void gameUpdate() {
            Enemy object;
   
            
            if(enemyCounter == 5 && b[0] == true) {                
                level2ready=false;
                
                 for(int i=0; i<5; i++){
			         allObjects.add(new Enemy(50 + 40 * (i+1), 100, false, Enemy.Direction.DOWN, 2, 4, Enemy.CharacterType.Enemylevel2)); // create the enemy
                        //allObjects.add(characters.get(5+i));          
                }
                b[0] = false;  
                
                
       
                
                allObjects.add(sb3);
                allObjects.add(sb4);
            }
            
            if(enemyCounter == 10 && b[1] == true){
                level3ready = false;
                    for(int i=0; i<5; i++){
                        allObjects.add(new Enemy(50 + 40 * (i+1), 100, false, Enemy.Direction.DOWN, 3, 5, Enemy.CharacterType.Enemylevel3)); // create the enemy
                        //allObjects.add(characters.get(10+i));          
                    }
                    b[1] = false;     
                    
                    
                    
                allObjects.add(ab3);
                allObjects.add(ab4);
          
            }
            
            
            
            if(enemyCounter == 15 && b[2] == true){
                     
                b[2] = false;  
                quitGame();                 
            }
            
            if(characterCounter == true){
                  characterCounter=false;
                  quitGame();
             
            }
            
            //initialize grid
            enemyAI.clearMapWalls();
            for(Positionable ob : allObjects) {
                if(ob instanceof Wall) {
                    enemyAI.addWall(ob.getRect());
                }
            }
            enemyAI.addWall(new Rectangle(0,0,Launcher.GAME_WIDTH, 55));
            
            enemyAI.clearCostMap();
            enemyAI.determineCostMap(enemyAI.determineGridCell(player)[0] , enemyAI.determineGridCell(player)[1] , Grid.COMPASS.East, 0); //arbritrary initial direction
            
            for(int ii = 0; ii < allObjects.size(); ii ++) {
                if(allObjects.get(ii) instanceof Enemy){
                   object = (Enemy) allObjects.get(ii);
                   object.setDirection(enemyAI.determineDirection(object));
                }
                allObjects.get(ii).move();
                if(!allObjects.get(ii).isLive()) {
                    
                        if(allObjects.get(ii) instanceof Enemy){
                        enemyCounter++;
                        }
                        
                        
                           if(player.isLive()==false){
                            
                       
                        
                        
                           quitGame();
                        
                        }
                       
                allObjects.remove(ii);
                    
                
                }
                
         
            }

            // quad tree implementation for collision detection
            quad.clear();
            for (int i = 0; i < allObjects.size(); i++) {
                quad.insert(allObjects.get(i));
            }
            for (int i = 0; i < allObjects.size(); i++) {
                returnObjects.clear();
                quad.retrieve(returnObjects, allObjects.get(i));
                for (int x = 0; x < (returnObjects.size()); x++) {
                    // Run collision detection algorithm between objects
                    for(int y = 0; y < (returnObjects.size()); y++){
                        if(x != y){
                          returnObjects.get(x).collisionWith(returnObjects.get(y));
                          //add deletion code here?
                        }
                    }
                }
            }
            
        }
        
        // These are Key Bindings, they replace Key Lisetners as a more reliable method of key inputs
        // 
        private void setBindings() {
            int condition = WHEN_IN_FOCUSED_WINDOW;
            InputMap inputMap = getInputMap(condition);
            ActionMap actionMap = getActionMap();

            // array of keys to binded to a desired action
            final KeyStroke[] keyStrokes = {
                  KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
                  KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
                  KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
                  KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0),
                  KeyStroke.getKeyStroke(KeyEvent.VK_P, 0)
            };
            for (final KeyStroke keyStroke : keyStrokes) {
               inputMap.put(keyStroke, keyStroke.toString());
               actionMap.put(keyStroke.toString(), new AbstractAction() {
                  @Override
                  public void actionPerformed(ActionEvent evt) {
                     player.keyPressed(keyStroke.getKeyCode());
                     if ( keyStroke.getKeyCode() == KeyEvent.VK_P){
                         pause = true;
                     }
                  }
               });
            }
            final KeyStroke[] keyStrokes2 = {
                  KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true),
                  KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true),
                  KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true),
                  KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true),
                  KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true),
                  KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, true),
                  KeyStroke.getKeyStroke(KeyEvent.VK_1, 0,true),
                  KeyStroke.getKeyStroke(KeyEvent.VK_2, 0,true)   
            };
            
            for (final KeyStroke keyStroke : keyStrokes2) {
               inputMap.put(keyStroke, keyStroke.toString());
               actionMap.put(keyStroke.toString(), new AbstractAction() {
                  @Override
                  public void actionPerformed(ActionEvent evt) {
                     player.keyReleased(keyStroke.getKeyCode());
                     int key = keyStroke.getKeyCode();
                     switch(key){
                         case KeyEvent.VK_SPACE:
                             allObjects.add(player.fire());
                     try {
                         sound.JavaAudioSFX();
                     } catch (Exception ex) {
                         Logger.getLogger(GameStart.class.getName()).log(Level.SEVERE, null, ex);
                     }
                             break;
                         case KeyEvent.VK_P:
                             pause = false;
                             
                         case KeyEvent.VK_1:
                             player.ActivateAttackBuff();
                     
                     
                         case KeyEvent.VK_2:
                             player.ActivateSpeedBuff();
                     }
                     
                  }
               });
            }
         }
        
        
        public void addObject(Explode object){
            //TODO  
            //allObjects.add(object);
        }
        
        
        public void quitGame(){
            quitGame.doClick();
        }
        public JButton getQuit(){
            return quitGame;
        }
        
        Timer timer2;
        
         public void ReminderLevel(int seconds) {
             timer2 = new Timer();
            timer2.schedule (new RemindL(), seconds*1000);
	     }

       class RemindL extends TimerTask {
     
        @Override
        public void run() {
            
            
       
     
            
            pause=false;
            timer2.cancel(); //Terminate the timer thread
        }
       }
}
