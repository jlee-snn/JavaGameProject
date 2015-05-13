package Launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// This will be the main menu of the game. Not intended to the be the pause menu
@SuppressWarnings("serial")
public class GameOverState extends JPanel {

    private int finalscore;
    private JPanel playerOnePanel;
    private JButton startGame;
   // private JButton menuGame;
    private GridBagConstraints c;
    //private Graphics gameoverscore;

    public GameOverState() {
      
        // general layout stuff
        GridBagLayout generalLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // default spacing
        setLayout(generalLayout);

        // player one
        playerOnePanel = new JPanel();
        playerOnePanel.setBorder(BorderFactory.createTitledBorder("Game Over"));
   
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 2;
        constraints.weighty = 2;
        generalLayout.setConstraints(playerOnePanel, constraints);
        //add(playerOnePanel);   
 
        //LauncherView view2 = new LauncherView();
        
        
        

        c = new GridBagConstraints();// constraints for banner


		// adding top banner to container
        //JLabel banner = new JLabel(new ImageIcon("src/images/Menu/gameover.png"));
        JLabel banner = new JLabel("Game Over!!!!  Final score: "+(Launcher.final_score+(Character.AtkCounter+Character.SpdCounter)));
  
        //gameoverscore.drawString(TOOL_TIP_TEXT_KEY, WIDTH, WIDTH);
		// c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
              
                
		add(banner, c);
        // buttons
        startGame = new JButton("Menu");
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0;
        constraints.weighty = 0;
        generalLayout.setConstraints(startGame, constraints);
        
        //ReminderLaunch(5);
        add(startGame);
        
   

        update();       // only used if we intennd to have dynamic content

    }

    
  //  public void paint(Graphics g){
      //  g.drawString("Game Over!!!", 360  , 300);
      //   g.drawString("Final Score: " + (Launcher.final_score * (Character.AtkCounter+Character.SpdCounter)) ,360,350);
        
   // }
    public void update() {
        //TODO
    }

    public JButton getStart() {
        return startGame;
    }
        
      
 
}
