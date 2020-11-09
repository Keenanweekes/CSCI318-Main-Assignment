import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainGui {
    private JFrame jFrame = new JFrame();
    private JPanel mainPanel = new JPanel();
    private JPanel filePanel = new JPanel();
    private JPanel runsPanel = new JPanel();
    private JPanel resultsPanel = new JPanel();

    private JLabel fileLabel;
    private JLabel classLabel;
    private JLabel runsLabel;
    private JLabel valuesLabel;
    private JLabel resultsLabel;

    private JTextField fileField;
    private JTextField classField;
    private JTextField runsField;

    private JButton generateButton;
    private JButton runsButton;

    private JList resultsList;

    private JScrollPane listScroller;
    ArrayList<String> resultsFromTest = new ArrayList<>();

    MainGui()
    {
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setSize(600, 600);
        jFrame.setLocationRelativeTo(null);
        //jFrame.setResizable(false);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));//sets the layout of the main frame to box layout, this is the overarching panel, each new panel added to this will be placed underneat hthe previous panel
        jFrame.add(mainPanel);//adds panel to jframe
 
        //below is for adding everything into the very top panel, file name and class name data can be recieved from here
        filePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 15));
        fileLabel = addToFlowPanel(filePanel, fileLabel, "File Name: ");
        fileField = addToFlowPanel(filePanel, fileField, 10);
        
        classLabel = addToFlowPanel(filePanel, classLabel, "Class Name: ");
        classField = addToFlowPanel(filePanel, classField, 10);
        
        generateButton = addToFlowPanel(filePanel, generateButton, "Generate", 120, 20);
        
        //Below is for when the generate button is pressed.
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = fileField.getText(); // get the users filename and c lass names into variables to be sent into testing.
                String className = classField.getText();
                
                startScanning(fileName);
                createJava(className);
                
            }
        });
        
       
        filePanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE)); //create a border for the jPanel
        mainPanel.add(filePanel);

        //below is adding to the runs panel, here you get the number of runs
        runsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 15));
        runsLabel = addToFlowPanel(runsPanel, runsLabel, "Number of runs: ");
        runsField = addToFlowPanel(runsPanel, runsField, 10);
        
        runsButton = addToFlowPanel(runsPanel, runsButton, "Run", 120, 20);
        runsPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLUE));//create a border for the jPanel
        mainPanel.add(runsPanel);
        
        //button listener for when the run button is pressed.
        resultsPanel.setLayout(new GridLayout(1, 1));//one row, one column for jlist
        
        DefaultListModel<String> listModel;
        resultsList = new JList();
        
        listModel = new DefaultListModel<>();
        resultsList.setModel(listModel);
        listScroller = new JScrollPane(resultsList); //add the jlist to scroll pane
        resultsList.setFixedCellHeight(27); //cell seperation in the jlist
        resultsList.setVisibleRowCount(10);
        
        resultsPanel.add(listScroller); //add scrollpane to jpanel
        mainPanel.add(resultsPanel);//add panel to main panel
        
        //use list model so can easily replace this list with the next list of results.
        //the results of the test need to be added into the list model ot be displayued
        runsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fileName = fileField.getText(); // get the users filename and c lass names into variables to be sent into testing.
                String className = classField.getText();
                int run = Integer.parseInt(runsField.getText()); // get how many runs the user wants into a variable to be passed int otesting.
                
                listModel.clear();//clear all results from earlier test runs
                resultsFromTest.clear();
               
                try {
                    Runtime.getRuntime().exec("javac generateMain.java GenerateClass.java Value.java ART.java Options.java Generate.java " + fileName);
                    Process process = Runtime.getRuntime().exec("java generateMain " + className + " " + run);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        resultsFromTest.add(line);
                    }
                } catch (IOException processException) {
                    // TODO Auto-generated catch block
                    processException.printStackTrace();
                }
                
                for(String r: resultsFromTest) //adds the test results to the list to be displayed
                {
                    listModel.addElement(r);
                }
                jFrame.pack();
            }
        });
        
        jFrame.pack();
    }

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
    
    public void startScanning(String fileName) {
        Scan scan = new Scan();
        try {
            scan.GenCSV(fileName);
        }
        catch(FileNotFoundException e) { System.out.println(e.toString()); }
        catch(IOException e) { System.out.println(e.toString()); }
    }

    public void createJava(String className) {
        CreateConstructor create = new CreateConstructor();
        create.create(className);
    }
}
