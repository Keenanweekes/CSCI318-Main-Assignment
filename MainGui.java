import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MainGui {
    private JFrame jFrame = new JFrame();//main jframe
    
    private JPanel mainPanel = new JPanel();//main jpanel that all layout panels go inside
    private JPanel userInputPanel = new JPanel();//jpanel for the flowlayout right at the top
    private JPanel testingBoxPanel = new JPanel();//japenl for the gridbag layout that contains the test case info and testing boxes
    private JPanel moreTestsBottomPanel = new JPanel();
    private JPanel competitionPanel = new JPanel();
    
    private JLabel errorRateLabel;//label for error rtate
    private JLabel testCaseLabel;//label for what test case the testing is on
    private JLabel rtLabel;//big RT label
    private JLabel artLabel;//big ART label
    private JLabel moreTestsLabel;//jlabel for how many more compeititons
    private JLabel competitionLabel;//jlabel for the results of the compeitions
    
    private JButton beginTestButton;//button for when the user wants ot begin the test
    private JButton moreTestsButton;//jbutton for more tests, when pressed will ruyn tests depeneing on how many user wants.
    
    private JTextField errorRateTextField;//textfield for the user to put in their error rate of choice
    private JTextField moreTestsField;//jtextfield for how many tests does the user want
    
    private JTextArea rtTestingField;//placeholder text area for rt field
    private JTextArea artTestingField;//placeholder text area for art field
    
    private int currentTestCase = 0; //For the initial testing procedure that will show the graphs
    private double errorRate = 0.00;//errorrate for the black box in the graph
    private int competitionCount = 0;//variable for how many competition roudns the user wants(Found in the bottom part of the GUI)
    
    
    MainGui()
    {
        //Set up the JFrame
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //sets the frame to close if X is pressed
        jFrame.setVisible(true);//sets visible
        jFrame.setSize(500, 300);//sets the size
        jFrame.setLocationRelativeTo(null);//makes the frame appear in the middle of the screen
        jFrame.setResizable(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));//sets the layout of the main frame to box layout, this is the overarching panel, each new panel added to this will be placed underneat hthe previous panel
        jFrame.add(mainPanel);//adds panel to jframe
 
        userInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 10));//sets the userinput panel(the top stuff) as a flow layout, starts elements on the left, with a width gap and then a vertical gap
        errorRateLabel = addToFlowPanel(userInputPanel, errorRateLabel, "Error Rate (Between 0 and 1): ");//add the jlabel to the panel and give it text
        errorRateTextField = addToFlowPanel(userInputPanel, errorRateTextField, 5);//add jtextfield to flowpanel and give it size
        beginTestButton = addToFlowPanel(userInputPanel, beginTestButton, "Begin Test", 95, 20);//add button to dlowpanel, give it text anmd size and it is returned so an action (do something when pressed) listener can be added.
        mainPanel.add(userInputPanel);//add the flowlayout top panel to the main panel
        
        //action listener for the button, so when it is pressed it will take what the user put in for their error rate and hopefully pass that into the test or graphing functions we produce and crate the black box.
        beginTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //this is the error rate that will be used to create the black box error visual on the graph(rememebr it is a double.)
                errorRate = Double.parseDouble(errorRateTextField.getText());  
            }
        }); 
        //create gridbaglayout for the middle area of the GUI with the graphs and headings.
        GridBagConstraints c = createGridBagLayout(1,1,0,0);//create the gridbag layout and its settings.
        testingBoxPanel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.BOTH;//the component fills its "Grid cell" vertically ann horizontally.
        
        //beloe we initialize all the elements that will be placed in the grid back layouts first
        testCaseLabel = new JLabel("Test Case " + String.valueOf(currentTestCase) + ": " +  "Placeholder text"); //need to put whether RT or ART missed or not concatenated on, possibly need to pass this into the testing area functions so the values can be updated easy
        rtLabel = new JLabel("RT");//initialize the RT label
        rtLabel.setFont(new Font("Serif", Font.PLAIN, 34));//sets the font and size so label is large liek the specification
        rtTestingField = new JTextArea(25,40);
        artLabel = new JLabel("ART");//initialize the RT label
        artLabel.setFont(new Font("Serif", Font.PLAIN, 34));//sets the font and size so label is large liek the specification
        artTestingField = new JTextArea(25,40);
        
        //After the initialization of all swing elements, they are placed in the gridbag layout in from top to bottom, so RT column is added and then we go over to the ART column.
        //RT Column
        testingBoxPanel.add(testCaseLabel, c);//adds the test case label to the panel at coordinates x = 0 and y = 0 (top right corner)
        c.gridy++; // makes the next component go underneath (still hugging the left hand side of the window)
        testingBoxPanel.add(rtLabel,c);//adds large RT label to the panel
        c.gridy++;//move the next component underneath(still hugging the left hand side of the window)
        c.insets = new Insets(0,0,0,40);// provide some padding on the right of the text area so it doesnt mesh with the ART text area
        testingBoxPanel.add(rtTestingField, c);//add testing field to gridbag panel.
        
        //ART Column
        c.gridy = 0;//reset the height of next component back ot the top (now we are at the height where it says Test case 0)
        c.gridx++;//now we go right to put in the ART column of elements
        c.gridy++;//go down one so ART label will be in line wit RT label
        testingBoxPanel.add(artLabel, c);//add large ART label
        c.gridy++; //go down so the graph can go underneath the ART heading
        c.insets = new Insets(0,0,0,10); //set spacing so there is a smaller gap on the right side
        testingBoxPanel.add(artTestingField, c);//add to panel
        mainPanel.add(testingBoxPanel);//add the gridbaglayout and everything inside of it to the main panel (Will appear underneath the flowlayout)
        
        //Begin last gridbaglayout for asking the user how many mroe compeitions they want
        GridBagConstraints g = createGridBagLayout(1,1,0,0);
        moreTestsBottomPanel.setLayout(new GridBagLayout());//sets the current panels layout to gridbag
        g.fill = GridBagConstraints.BOTH;//the component fills its "Grid cell" vertically and horizontally

        moreTestsLabel = new JLabel("How many more competitions do you want to run? ");
        moreTestsField = new JTextField(5);
        moreTestsButton = new JButton("Run Tests");
        competitionLabel = new JLabel(competitionCount + " tests have been completed, of which RT wins " + "((HowManyTimesRTWinsVariable times))" + " and ART wins " + "((HowManyTimesARTWinsVariable times))");
        
        //the grid bag layout was stuffing up here so i just decided to have the go underneath each other, still looks good and fulfills its function.
        moreTestsBottomPanel.add(moreTestsLabel, g);//add the mroe tests label to panel
        g.gridy++;//move down
        moreTestsBottomPanel.add(moreTestsField, g);
        g.gridy++;//move down
        moreTestsBottomPanel.add(moreTestsButton, g);
        g.gridy++;//move down
        moreTestsBottomPanel.add(competitionLabel, g);
        mainPanel.add(moreTestsBottomPanel);//add this to the main box layout panel (will appear underneath the graphs.)
        
        //action listener for when the more competition button is pressed. Data from the algorithm and graphs will need to be plugged in so it can displayed the correct winner.
        moreTestsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //try catch block to catch if the user puts in nothing into the text box.
                try
                {
                    competitionCount = Integer.parseInt(moreTestsField.getText());//convert competition rounds to int to be displayed.
                    //////will have to run the testing procedure again here./////
                   //sets the text of the competition jlabel to however many tests the user wants and the wins and losses of the testing procedure
                    competitionLabel.setText(competitionCount + " tests have been completed, of which RT wins " + "((HowManyTimesRTWinsVariable times))" + " and ART wins " + "((HowManyTimesARTWinsVariable times))");
                }
                catch(NumberFormatException exc)
                {
                    JOptionPane.showMessageDialog(mainPanel, "Empty field or strings found in more tests box.");//show error message.
                }
            }
        });
        jFrame.pack();
    }
    
    //thisa function will create a new gridbaglayout with all settings the user wants, i ended up using two grid bag layouts in the program so it saves a few lines of setting up
    private GridBagConstraints createGridBagLayout(int weightX, int weightY, int gridX, int gridY)
    {
        GridBagConstraints newLayout = new GridBagConstraints();
        newLayout.weightx = weightX;
        newLayout.weighty = weightY;
        newLayout.gridx = gridX;//when the x axis is incremented, the next element will be to the right
        newLayout.gridy = gridY;//when y axis is incremented the next element will be underneath
        
        return newLayout;
    }
    
    //This method creates a label, adds it to the current panel and increases the x gridbagconstraints(makes the next component be to the right which is normall a textfield)
    private JLabel createNewLabelMoveRight(GridBagConstraints c, JPanel gridBagPanelName,String contents)
    {
        JLabel newLabel = new JLabel(contents); // create new jlabel
        gridBagPanelName.add(newLabel, c);//add it to the panel with the gridbagconstraints
        c.gridx++;// increase gridbag x constraints so next component will be to the right of this compnenet.
        
        return newLabel;// returns the label , it gets assigned to the JLabel we want it to be, so it may be used or manipulated in the main code block
    }
    
    //function for adding jlabels to flow layouts.
    private JLabel addToFlowPanel(JPanel flowPanel, JLabel labelName, String labelText)
    {
        labelName = new JLabel(labelText);//initializes the label
        flowPanel.add(labelName);//adds it to the panel
        
        return labelName; //retusn the labe lfor further use.
    }
    
    //function for adding jbuttons to flow layouts.
    private JButton addToFlowPanel(JPanel flowPanel, JButton buttonName, String buttonText, int width, int height)
    {
        buttonName = new JButton(buttonText);//initializes the button and give it text
        flowPanel.add(buttonName);//adds the button to the panel
        buttonName.setPreferredSize(new Dimension(width, height));//sets the prefered size to what parameters were passed in
        return buttonName;//returned for further use if needed
    }
    
    //function for adding jtextfields to flow layouts.
    private JTextField addToFlowPanel(JPanel flowPanel, JTextField textField, int size)
    {
        textField = new JTextField(size);//size of text field
        flowPanel.add(textField);//adds to the flowlayout panel
        
        return textField;//retusn for further use
    }
}
