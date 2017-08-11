/**
 * Created by John Clayton 07/30/2017.
 * This program manages a student database by being able to 
 * Insert, Delete, Find, and Update records. 
 */
import java.text.*;

public class Student {

    //Declare variables to be used.
    private String name;
    private String major;
    private int credit;
    private int points;

    //Initialize these variables based on passed values.
    public Student(String name, String major) {
        credit = 0;
        points = 0;
        this.name = name;
        this.major = major;
    }

    //Accepts a grade and credit value for a course and adds the correct amount to the totalCredit and qualityPoint variables.
    public void courseCompleted(char grade, int credits) {
        credit += credits;

        if (grade == 'A') {
            points += (4*credits);
        }
        else if (grade == 'B') {
            points += (3*credits);
        }
        else if (grade == 'C') {
            points += (2*credits);
        }
        else if (grade == 'D') {
            points += (1*credits);
        }
        else if (grade == 'F') {
            points += (0*credits);
        }
    }

    private double calculateGPA() {
        //If the student has taken courses, calculate GPA.
        if(credit != 0) {
            return (double) points / credit;
        }
        //Otherwise, default the GPA to 4.0.
        else {
            return 4.0;
        }
    }

    //Returns the name, major and formatted GPA.
    public String toString() {
        DecimalFormat numberFormat = new DecimalFormat("#.0");
        double GPA = calculateGPA();
        return "Name: " + name + ", Major: " + major + ", GPA: " + numberFormat.format(GPA);
    }
}
