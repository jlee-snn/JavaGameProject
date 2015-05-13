package Launcher;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// This will be the main menu of the game. Not intended to the be the pause menu
@SuppressWarnings("serial")
public class LauncherView extends JPanel {

    
    private JPanel playerOnePanel;
    private JButton startGame;
    private JButton exitGame;
    private JButton instruction;
    private GridBagConstraints c;

    public LauncherView() {
      
        // general layout stuff
        GridBagLayout generalLayout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // default spacing
        setLayout(generalLayout);

        // player one
        playerOnePanel = new JPanel();
        playerOnePanel.setBorder(BorderFactory.createTitledBorder("Menu Test"));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 2;
        constraints.weighty = 2;
        generalLayout.setConstraints(playerOnePanel, constraints);
        //add(playerOnePanel);   
        

        c = new GridBagConstraints();// constraints for banner


		// adding top banner to container
        JLabel banner = new JLabel(new ImageIcon("src/images/Menu/MenuTitle.png"));
        
        
		// c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
                c.gridheight=1;
                c.weightx=0;
                c.weighty=0.5;        
                c.fill= GridBagConstraints.NONE;
              
                
		add(banner, c);
        // buttons
        startGame = new JButton("START");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = .5;
        constraints.weighty = 0;
        generalLayout.setConstraints(startGame, constraints);
        add(startGame);
        
        instruction = new JButton("INSTRUCTION");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.5;
        constraints.weighty = 0;
        generalLayout.setConstraints(instruction, constraints);
        add(instruction);
        
                
        
        
        exitGame = new JButton("EXIT");
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 0.5;
        constraints.weighty = 0;
        generalLayout.setConstraints(exitGame, constraints);
        add(exitGame);
                

        update();       // only used if we intennd to have dynamic content

    }

    public void update() {
        //TODO
    }

    public JButton getStart() {
        return startGame;
    }
    public JButton instruction(){
        return instruction;
    }

    public JButton exitGame(){
        return exitGame;
    }
 
}
