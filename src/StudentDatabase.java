/**
 * Created by John Clayton 07/30/2017.
 * This program manages a student database by being able to 
 * Insert, Delete, Find, and Update records. 
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class StudentDatabase extends JPanel implements ActionListener {

    //Creates the Test Fields 
    private JTextField idFld = new JTextField(10);
    private JTextField nameFld = new JTextField(10);
    private JTextField majorFld = new JTextField(10);

    //Creation of the Dropdown Menu
    private JComboBox<String> optionDpdn;

    //Creation of the Hashmap
    HashMap<Integer, Student> database = new HashMap<Integer, Student>();


    public StudentDatabase() {
        //Setting the properties of the layout.
        this.setLayout(new GridLayout(5,2,10,10));
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        this.setBorder(padding);

        //Creating the Label names to be used.
        JLabel idLbl = new JLabel("ID:");
        JLabel nameLbl = new JLabel("Name:");
        JLabel majorLbl = new JLabel("Major:");
        JLabel selectionLbl = new JLabel("Choose Selection:");

        //Creating the Text names to be used. 
        idFld.setText("");
        nameFld.setText("");
        majorFld.setText("");

        //Creating the dropdown options for the JComboBox constructor.
        String[] options = {"Insert","Delete","Find","Update"};
        optionDpdn = new JComboBox<String>(options);

        //Default to Insert.
        optionDpdn.setSelectedIndex(0);

        //Create a button that triggers the appropriate action.
        JButton processBtn = new JButton("Process Request");
        processBtn.addActionListener(this);

        //Adding Label, Field, Dropdown, and Button elements to the panel.
        add(idLbl);
        add(idFld);
        add(nameLbl);
        add(nameFld);
        add(majorLbl);
        add(majorFld);
        add(selectionLbl);
        add(optionDpdn);
        add(processBtn);
    }

    public static void main(String args[]) {
        
        JFrame frame = new JFrame("Student Database");  //Initializes the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        StudentDatabase school = new StudentDatabase();
        school.setOpaque(true);
        frame.setContentPane(school);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        JFrame jOptFrame = new JFrame();    //Creating a frame for the JOption Frame

        //Check to see if idFld contains an integer.
        try {
            int key = Integer.parseInt(idFld.getText());

            //Initializing variables to be used. 
            String option = optionDpdn.getSelectedItem().toString();
            String name = nameFld.getText();
            String major = majorFld.getText();

            if (option.equals("Insert")) {
                
                if (!name.equals("") && !major.equals("")) {  //Fields cannot be empty.
                    
                    if (database.containsKey(key)) {  //Ensuring the entered ID is not currently in Dbase.
                        
                        invalidId(jOptFrame, option);  //If ID is in Dbase, inform the user.
                    }
                    else {
                        //If ID is not in Dbase, insert the student into the database.
                        database.put(key, new Student(name, major));
                        JOptionPane.showMessageDialog(jOptFrame, "Insertion of Student " + key + ": " + database.get(key).toString() + " successful.");
                        clearFields(jOptFrame, option, name, major);
                    }

                    //Ensures fields are not empty and will prompt user.
                }
                else if (name.equals("") && major.equals("")) {
                    JOptionPane.showMessageDialog(jOptFrame, "Please put a name and major in the appropriate fields.");
                }
                else if (name.equals("")) {
                    JOptionPane.showMessageDialog(jOptFrame, "Please put a name in the appropriate field.");
                }
                else if (major.equals("")) {
                    JOptionPane.showMessageDialog(jOptFrame, "Please put a major in the appropriate field.");
                }
            }
            else if (option.equals("Delete")) {
               
                if (database.containsKey(key)) {
                    //If key is present, inform the user which Student will be removed.
                    JOptionPane.showMessageDialog(jOptFrame, "Student " + key + ": " + database.get(key).toString() + " will be removed.");
                    database.remove(key);
                }
                else {
                    
                    invalidId(jOptFrame, option); //If it isn't present, informs the user.
                }

                clearFields(jOptFrame, option, name, major);
            }
            else if (option.equals("Find")) {
               
                if (database.containsKey(key)) {
                    //If the key is present, return the student's information.
                    JOptionPane.showMessageDialog(jOptFrame, "Student " + key + ": " + database.get(key).toString());
                }
                else {
                    
                    invalidId(jOptFrame, option); //If it isn't present, inform the user.
                }

                clearFields(jOptFrame, option, name, major);
            }
            else if (option.equals("Update")) {
                
                if (database.containsKey(key)) {
                    
                    Object[] gradeChoices = {'A','B','C','D','F'}; //Creates an array to maintain the grade choices.

                    //JOption will display for user to select grade input.
                    char grade = (char)JOptionPane.showInputDialog(null, "Choose grade:", "Grade",
                            JOptionPane.INFORMATION_MESSAGE, null, gradeChoices, gradeChoices[0]);

                    //Creates an array to maintain the credit values.
                    Object[] creditOpt = {3,6};

                    //JOption will display window to select the credit value input.
                    int credits = (int)JOptionPane.showInputDialog(null, "Choose credits:", "Credits",
                            JOptionPane.INFORMATION_MESSAGE, null, creditOpt, creditOpt[0]);

                   
                    database.get(key).courseCompleted(grade, credits);   //Updates student record with given values.

                    //Display the student info with the updated GPA and inform the user that the update occurred.
                    JOptionPane.showMessageDialog(jOptFrame, "Student " + key + ": " + database.get(key).toString() + ". Update complete.");
                
                }else {
                    invalidId(jOptFrame, option);
                }

                clearFields(jOptFrame, option, name, major);
            }
        }

        //Incorrect value entered in for IdField.
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(jOptFrame, "Please put an integer into the ID field.");
        }
    }
    
    public void invalidId(JFrame frame, String option) {
        if (option.equals("Insert")) {
            JOptionPane.showMessageDialog(frame, "Student ID already in use. Please select a different ID.");
        }
        else {
            JOptionPane.showMessageDialog(frame, "Database does not contain the specified student ID.");
        }
    }

    //Clears fields 
    public void clearFields(JFrame frame, String option, String name, String major) {
        nameFld.setText("");
        majorFld.setText("");

        //If an option other than Insert is being used, inform the user that name and major are only used for Insert.
        if (!option.equals("Insert") && (!name.equals("") || !major.equals(""))) {
            JOptionPane.showMessageDialog(frame, "Any input in the name or major fields is irrelevant for requests other than Insert.");
        }
    }
}
