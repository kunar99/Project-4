/**
 * Created by johnclayton on 8/6/17.
 */
import java.text.*;

public class Student {

    //Declare variables for the different attributes of Student.
    private String name;
    private String major;
    private int totalCredits;
    private int qualityPoints;

    //Initialize these variables based on passed values.
    public Student(String name, String major) {
        totalCredits = 0;
        qualityPoints = 0;
        this.name = name;
        this.major = major;
    }

    //Accepts a grade and credit value for a course and adds the correct amount to the totalCredit and qualityPoint variables.
    public void courseCompleted(char grade, int credits) {
        totalCredits += credits;

        if (grade == 'A') {
            qualityPoints += (4*credits);
        }
        else if (grade == 'B') {
            qualityPoints += (3*credits);
        }
        else if (grade == 'C') {
            qualityPoints += (2*credits);
        }
        else if (grade == 'D') {
            qualityPoints += (1*credits);
        }
        else if (grade == 'F') {
            qualityPoints += (0*credits);
        }
    }

    private double calculateGPA() {
        //If the student has taken courses, calculate GPA.
        if(totalCredits != 0) {
            return (double)qualityPoints/totalCredits;
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