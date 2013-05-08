import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * 
 * @author derek and mick
 * 11/29/12
 * 
 * Creates the GUI for the PokerDice version 2. This class also checks for winning hands. 
 *
 */
public class PokerDiceGUI extends JPanel implements ItemListener {
    /**
	 * Creates a new JFrame if the user rolls a winning hand
	 */
	JFrame winnerFrame = new JFrame("Winner");
	
	private PokerDie[] dice = {new PokerDie(),new PokerDie()
		,new PokerDie(),new PokerDie(),new PokerDie()};
	private boolean[] toRoll = {true,true,true,true,true};
	private static final long serialVersionUID = 1L;

	/**
	 * Declare all the check boxes, radio buttons, menu items in the menubar, and roll and finish buttons
	 */
	JCheckBox dice1box;
    JCheckBox dice2box;
    JCheckBox dice3box;
    JCheckBox dice4box;
    JCheckBox dice5box;
    
	private JButton roll_button;
	private int num_rolls = 0;
	private JButton finish_button;
 
    JLabel pictureLabel;
    JLabel pictureLabe2;
    JLabel pictureLabe3;
    JLabel pictureLabe4;
    JLabel pictureLabe5;
    
    JLabel dice1;
    
    static JMenuBar mb = new JMenuBar();
    static JMenu menu = new JMenu();

 
    public PokerDiceGUI() {
    	
        super(new BorderLayout());
        /**
         * Register the menu and add the menubar to the menu
         */
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        mb.add(menu);
        
        /**
         * These are the action listeners for the menubar radio buttons. The user can either play a 1 player or 2 player game
         */
        ActionListener actionListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
   //make 1 player game
            }
          };
          
          ActionListener actionListener2 = new ActionListener() {
              public void actionPerformed(ActionEvent actionEvent) {
     //make 2 player game
            	  
              }
            };  
        ButtonGroup group = new ButtonGroup();
        /**
         * Add the radio buttons to the menu bar. This is for the 1 player option
         */
        JRadioButtonMenuItem Players1 = new JRadioButtonMenuItem("1 Player");
        Players1.setSelected(true);
        Players1.setMnemonic(KeyEvent.VK_R);
       // Players1.setActionCommand("Empty");
        group.add(Players1);
        Players1.addActionListener(actionListener1);
        menu.add(Players1);
        
        
        /**
         * Add the radio buttons to the menu bar. This is the 2 player option
         */
        JRadioButtonMenuItem Players2 = new JRadioButtonMenuItem("2 Player");
        Players2.setSelected(false);
        Players2.setMnemonic(KeyEvent.VK_Z);
        //Players2.setActionCommand("Empty");
        group.add(Players2);
        Players2.addActionListener(actionListener2);
        menu.add(Players2);
       
        /**
         * Create the bottom buttons Roll and Finish
         */
		roll_button = new JButton("Roll!");
		finish_button = new JButton("Finish!");
        
		/**
		 * Create the check boxes
		 */
        dice1box = new JCheckBox("1Keep?");
        dice1box.setMnemonic(KeyEvent.VK_C);
        dice1box.setSelected(false);
        
        /**
         * Set up the picture labels for each dice
         */
        dice1 = new JLabel();
        dice1.setFont(dice1.getFont().deriveFont(Font.ITALIC));
 
        dice2box = new JCheckBox("2Keep?");
        dice2box.setMnemonic(KeyEvent.VK_G);
        dice2box.setSelected(false);
 
        dice3box = new JCheckBox("3Keep?");
        dice3box.setMnemonic(KeyEvent.VK_H);
        dice3box.setSelected(false);
 
        dice4box = new JCheckBox("4Keep?");
        dice4box.setMnemonic(KeyEvent.VK_T);
        dice4box.setSelected(false);
        
        dice5box = new JCheckBox("5Keep?");
        dice5box.setMnemonic(KeyEvent.VK_T);
        dice5box.setSelected(false);
 
        /**
         * Registers a listener for the check boxes
         */
        dice1box.addItemListener(this);
        dice2box.addItemListener(this);
        dice3box.addItemListener(this);
        dice4box.addItemListener(this);
        dice5box.addItemListener(this);
 
        /**
         * Creates the picture label
         */
        pictureLabel = new JLabel();
        pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
        pictureLabel.setIcon(createImageIcon("10.png"));
        pictureLabe2 = new JLabel();
        pictureLabe2.setFont(pictureLabe2.getFont().deriveFont(Font.ITALIC));
        pictureLabe2.setIcon(createImageIcon("J.png"));
        pictureLabe3 = new JLabel();
        pictureLabe3.setFont(pictureLabe3.getFont().deriveFont(Font.ITALIC));
        pictureLabe3.setIcon(createImageIcon("Q.png"));
        pictureLabe4 = new JLabel();
        pictureLabe4.setFont(pictureLabe4.getFont().deriveFont(Font.ITALIC));
        pictureLabe4.setIcon(createImageIcon("K.png"));
        pictureLabe5 = new JLabel();
        pictureLabe5.setFont(pictureLabe5.getFont().deriveFont(Font.ITALIC));
        pictureLabe5.setIcon(createImageIcon("A.png"));
 
        /**
         * Put the check boxes in a column in a panel
         */
        JPanel dicePanel = new JPanel(new GridLayout(0, 5));
        dice1box.setVisible(false);
        dice2box.setVisible(false);
        dice3box.setVisible(false);
        dice4box.setVisible(false);
        dice5box.setVisible(false);
        dicePanel.add(dice1box);
        dicePanel.add(dice2box);
        dicePanel.add(dice3box);
        dicePanel.add(dice4box);
        dicePanel.add(dice5box);
        
        
        /**
         * Create the button button panel. There is 2 buttons, roll and finish
         */
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        buttonPanel.add(roll_button);
        finish_button.setVisible(false);
        buttonPanel.add(finish_button);
        
        /**
         * Create the middle gif panel where the card faces appear.
         */
        JPanel gifPanel = new JPanel(new GridLayout(0,5));
        gifPanel.add(pictureLabel, BorderLayout.CENTER);
        gifPanel.add(pictureLabe2, BorderLayout.CENTER);
        gifPanel.add(pictureLabe3, BorderLayout.CENTER);
        gifPanel.add(pictureLabe4, BorderLayout.CENTER);
        gifPanel.add(pictureLabe5, BorderLayout.CENTER);
        

        /**
         * This is where the the roll button and action listeners are registered. This counts how many rolls the user has and makes sure the user only gets a total of 2 rolls per hand.
         * This is also where the radio buttons are for the dice. If a radio button for a dice is selected, it will be rolled if it is not the end 
         * of the game.
         */
        
  		roll_button.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  				finish_button.setVisible(true);
  				dice1box.setVisible(true);
  		        dice2box.setVisible(true);
  		        dice3box.setVisible(true);
  		        dice4box.setVisible(true);
  		        dice5box.setVisible(true);
  				winnerFrame.setVisible(false);
  				changeToGIF();
  				for(int i = 0; i < dice.length; i++){
  					if(toRoll[i] == true){
  						dice[i].roll();
  					}
  				}
  				//currentHand();
  				changeToDie();
  				Toolkit.getDefaultToolkit().beep();//make beep noise
  				++num_rolls;
  				if(num_rolls >= 2){
  					dice1box.setSelected(false);
  					dice2box.setSelected(false);
  					dice3box.setSelected(false);
  					dice4box.setSelected(false);
  					dice5box.setSelected(false);
  					toRoll = new boolean[5];
  			        toRoll[0] = true;
  			        toRoll[1] = true;
  			        toRoll[2] = true;
  			        toRoll[3] = true;
  			        toRoll[4] = true;
  					win();
  				}
  				roll_button.setText("Roll(" + num_rolls + ")" ); 
  				setBackground(Color.white);
  				repaint();
  			}
  		});
  		
  		/**
  		 * This is the action performed when the user clicks finish. It will compute their hand and turn the background Red
  		 */
		finish_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dice1box.setSelected(false);
				dice2box.setSelected(false);
				dice3box.setSelected(false);
				dice4box.setSelected(false);
				dice5box.setSelected(false);
				toRoll = new boolean[5];
			    toRoll[0] = true;
			    toRoll[1] = true;
			    toRoll[2] = true;
			    toRoll[3] = true;
			    toRoll[4] = true;
				win();
				setBackground(Color.red);
				repaint();
			}
		});
		
		/**
		 * Add our buttons and menubars to the JPanel and windows
		 */
		mb.add(Players1);
		mb.add(Players2);
		add(gifPanel, BorderLayout.NORTH);
        add(dicePanel, BorderLayout.LINE_START);
        add(buttonPanel, BorderLayout.SOUTH);
        add(dice1, BorderLayout.LINE_END);
        
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
    
    /**
     * @param void
     *If the user rolls a winning hand, a new windows appears with fireworks congratulating the user
     */
    private void win() {
    	finish_button.setVisible(false);
    	
        dice1box.setVisible(false);
        dice2box.setVisible(false);
        dice3box.setVisible(false);
        dice4box.setVisible(false);
        dice5box.setVisible(false);
		num_rolls = 0;
		winnerFrame = new JFrame("Winner");
        //winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JTextArea winner = new JTextArea("Congratz you got " + currentHand());
        JLabel fireworks = new JLabel();
        fireworks.setFont(fireworks.getFont().deriveFont(Font.ITALIC));
        fireworks.setIcon(createImageIcon("fireworks.gif"));
        
        winnerFrame.getContentPane().add(fireworks, BorderLayout.CENTER);
        winnerFrame.getContentPane().add(winner, BorderLayout.SOUTH);
 
        //Display the window.
        winnerFrame.pack();
        //frame.setSize(475, 260);//set frame window size (x,y)
        winnerFrame.setVisible(true);    
		
	}
 
    /** Listens to the check boxes. */
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        System.out.println(toRoll[0]);
        if (source == dice1box) {
			toRoll[0] = !toRoll[0];
			System.out.println("box1 " + toRoll[0]);
        } else if (source == dice2box) {
        	toRoll[1] = !toRoll[1];
        } else if (source == dice3box) {
        	toRoll[2] = !toRoll[2];
        } else if (source == dice4box) {
        	toRoll[3] = !toRoll[3];
        } else if (source == dice5box) {
        	toRoll[4] = !toRoll[4];
        }
       
        
    }
    
    /**
     * Ensures the images are in the JPanel, if images are not found, prints an error
     * Also makes sure the image path is correct
     */
 
    protected void changeToGIF() {    	
        //Get the icon corresponding to the image.
        ImageIcon icon = createImageIcon("dice.gif");
        pictureLabel.setIcon(icon);
        pictureLabe2.setIcon(icon);
        pictureLabe3.setIcon(icon);
        pictureLabe4.setIcon(icon);
        pictureLabe5.setIcon(icon);
        if (icon == null) {
            pictureLabel.setText("Missing Image");
            pictureLabe2.setText("Missing Image");
            pictureLabe3.setText("Missing Image");
            pictureLabe4.setText("Missing Image");
            pictureLabe5.setText("Missing Image");
        } else {
            pictureLabel.setText(null);
            pictureLabe2.setText(null);
            pictureLabe3.setText(null);
            pictureLabe4.setText(null);
            pictureLabe5.setText(null);
        }
    }
    
    /**
     * Currently a copy of the above but might take something to change the picture to the roll.
     * Renders the image icons the user will see. Each dice represents one card
     */
    protected void changeToDie(){
    	
        ImageIcon icon1 = createImageIcon(dice[0].topFace + ".png");
        ImageIcon icon2 = createImageIcon(dice[1].topFace + ".png");
        ImageIcon icon3 = createImageIcon(dice[2].topFace + ".png");
        ImageIcon icon4 = createImageIcon(dice[3].topFace + ".png");
        ImageIcon icon5 = createImageIcon(dice[4].topFace + ".png");
        
        pictureLabel.setIcon(icon1);
		pictureLabe2.setIcon(icon2);	        
		pictureLabe3.setIcon(icon3);  
		pictureLabe4.setIcon(icon4);
        pictureLabe5.setIcon(icon5);
        
    }
 
    
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = PokerDiceGUI.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
 
    /**
     * @param void
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("PokerDice");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setJMenuBar(mb);
        mb.add(menu);
        //mb.add(Players1);
        
        //Create and set up the content pane.
        JComponent newContentPane = new PokerDiceGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        //frame.setSize(475, 260);//set frame window size (x,y)
        frame.setVisible(true);
    }
    
    /**
     * @return String
     * @param void
     * finds what the current hand is
     */
    public String currentHand(){
    	
    	for(int i = 0; i < dice.length; i++){
    		for(int j = i + 1; j < dice.length; j++){
    			if(dice[j].value < dice[i].value){
    				PokerDie temp = dice[i];
    				dice[i] = dice[j];
    				dice[j] = temp;
    			}
    		}
    	}
    	
    	String highest = dice[dice.length - 1].topFace;
    	String hand = highest;
    	
    	if(five()){
    		hand = "5 of a kind " + highest;
    	}
    	else if(four() > 0){
    		hand = "4 of a kind " + dice[four()].topFace();
    	}
    	else if(fullHouse() > 0){
    		hand = "Full house " + dice[fullHouse()].topFace();
    	}
    	else if(three() > 0){
    		hand = "3 of a kind " + dice[three()].topFace();
    	}
    	else if(twoPair() > 0){
    		hand = "2 pair " + dice[twoPair()].topFace();
    	}
    	else if(pair() > 0){
    		hand = "2 of a kind " + dice[pair()].topFace();
    	}
    	return hand + " High";
    }

/**
 * 
 * @return boolean
 * @param void 
 * handles 5 of a kind. If player receives 5 of a kind, returns true
 */
	private boolean five() {
		int die1 = dice[0].value;
		int die2 = dice[1].value;
		int die3 = dice[2].value;
		int die4 = dice[3].value;
		int die5 = dice[4].value;
		
		if(die1 == die2 && die2 == die3 && die3 == die4 && die4 == die5){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return integer
	 * @param void
	 * if the player rolls 4 of a kind, it will return a positive integer
	 */
	private int four() {
		int die1 = dice[0].value;
		int die2 = dice[1].value;
		int die3 = dice[2].value;
		int die4 = dice[3].value;
		int die5 = dice[4].value;
		
		if(die1 == die2 && die2 == die3 && die3 == die4){
			return 1;
		}
		if(die2 == die3 && die3 == die4 && die4 == die5){
			return 4;
		}
		return -1;
	}
	/**
	 * 
	 * @return integer
	 * @param void
	 * handles full house
	 */
	
	private int fullHouse() {
		int die1 = dice[0].value;
		int die2 = dice[1].value;
		int die3 = dice[2].value;
		int die4 = dice[3].value;
		int die5 = dice[4].value;
		
		if(die1 == die2 && die3 == die4 && die4 == die5){
			return 4;
		}
		if(die1 == die2 && die2 == die3 && die4 == die5){
			return 1;
		}
		return -1;
	}
	/**
	 * 
	 * @return integer
	 * @param void
	 * handles 3 of a kind
	 */
	private int three() {
		int die1 = dice[0].value;
		int die2 = dice[1].value;
		int die3 = dice[2].value;
		int die4 = dice[3].value;
		int die5 = dice[4].value;
		
		if(die1 == die2 && die2 == die3){
			return 1;
		}
		if(die2 == die3 && die3 == die4){
			return 2;
		}
		if(die3 == die4 && die4 == die5){
			return 3;
		}
		return -1;
	}
	/**
	 * 
	 * @return integer
	 * @param void
	 * takes care of 2 pair by comparing dice values
	 */
	private int twoPair() {
		int die1 = dice[0].value;
		int die2 = dice[1].value;
		int die3 = dice[2].value;
		int die4 = dice[3].value;
		int die5 = dice[4].value;
		
		if(die1 == die2){
			if(die3 == die4){
				if(die3 > die1){
					return 2;
				}
				return 1;
			}
			if(die3 == die5){
				if(die3 > die1){
					return 2;
				}
				return 1;
			}
			if(die4 == die5){
				if(die4 > die1){
					return 3;
				}
				return 1;
			}
		}
		if(die1 == die3){
			if(die4 == die5){
				if(die4 > die1){
					return 3;
				}
				return 2;
			}
		}
		if(die2 == die3){
			if(die4 == die5){
				if(die4 > die2){
					return 3;
				}
				return 1;
			}
		}
		return -1;
	}
	/**
	 * 
	 * @return integer
	 * @param void
	 * handles if the player receives a pair
	 */
	private int pair(){
		int die1 = dice[0].value;
		int die2 = dice[1].value;
		int die3 = dice[2].value;
		int die4 = dice[3].value;
		int die5 = dice[4].value;
		if(die1 == die2){
			return 1;
		}
		if(die2 == die3){
			return 1;
		}
		if(die3 == die4){
			return 2;
		}
		if(die4 == die5){
			return 3;
		}
		return -1;
	}
	/**
	 * 
	 * @param args
	 * @return null
	 * This is the main method which first calls createAndShowGUI()
	 * Schedule a job for the event-dispatching thread.
	 * 
	 */

	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
