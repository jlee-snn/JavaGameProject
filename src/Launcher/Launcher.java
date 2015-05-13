package Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Launcher extends JFrame{
        
    // place global variables here
        public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
        public static int Score = 0; 
        public static int PrizeScore = 0;
        
        public static int final_score;
        
	private static Launcher controller;
	private LauncherView view;
        private GameStart game;

        private GameOverState overstate;

        
        
        //public Grid test;
        
	public Launcher(String t) {
                
		super(t);
                
                //
		view = new LauncherView();	//creates the view, that stuff will be displyed on
		getContentPane().setPreferredSize(view.getPreferredSize());	//makes the window fit around the view
		pack();		
		
		add(view);
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
                //setMaximumSize(new Dimension(GAME_WIDTH, GAME_HEIGHT))
		
		view.getStart().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			handleStart();}});
		//getContentPane.removeAll();
                
               view.instruction().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                instructionActionPerformed();
            }
        });
               
               view.exitGame().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                exitGameActionPerformed();
            }
        });
	}
	
        
	public static void main(String[] args) {
            
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    controller = new Launcher("POWA!");
                    controller.setVisible(true);
                    //System.out.println("test");
                                    
                }
                
            });
	}
	
        private void handleStart() {
	System.out.println("Start");
        remove(view);               //Will this be okay? would we need an option to get back to the main menu
        game = new GameStart();
        add(game);      // begins the game
        revalidate();
        repaint();
        pack();                     // have no idea what this does
        setSize(GAME_WIDTH, GAME_HEIGHT);
        game.getQuit().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
                   killGame();
                    //overGame();
        	}
        });
               
               
	}
        
        
        public static int getScore() {
		return Score;
	}

	public static void setScore(int score) {
		Score = score;
	}
	
        public void addObject(Explode object){
            game.addObject(object);
        }
        
        
        private void instructionActionPerformed(){
            new instruction().setVisible(true);
        }
        private void exitGameActionPerformed(){
            dispose();
        }
       


        
        
    public void killGame() {
        recordScore();
    	resetScore();
        remove(game);
        getContentPane().removeAll();
        revalidate();
        repaint();
        pack();   
	//pack();
   
      //  ReminderLaunch(2);
        
        //view = new LauncherView();	//creates the view, that stuff will be displyed on
  
        overstate=new GameOverState();
        getContentPane().setPreferredSize(overstate.getPreferredSize());	//makes the window fit around the view		
	add(overstate);
	pack();
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
       
     
            
		overstate.getStart().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu();
                        }});
    }

    public void resetScore() {
    	Score = 0;
    	PrizeScore = 0;
    }  
    
    
    
     public void recordScore() {
    	final_score = Score;
        
    	
    }      
       
    public void menu(){
        resetScore();
        remove(overstate);
        getContentPane().removeAll();
        revalidate();
        repaint();
        pack();
        
       
	//pack();
   
      //  ReminderLaunch(2);
        
        view = new LauncherView();	//creates the view, that stuff will be displyed on
  
        //overstate=new GameOverState();
        getContentPane().setPreferredSize(overstate.getPreferredSize());	//makes the window fit around the view		
	add(view);
	pack();
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        view.getStart().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				handleStart();}});
         view.instruction().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                instructionActionPerformed();
            }
        });
               
               view.exitGame().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0){
                exitGameActionPerformed();
            }
        });
    }
   
        
}


 