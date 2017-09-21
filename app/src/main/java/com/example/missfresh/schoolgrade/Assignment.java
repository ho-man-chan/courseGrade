package com.example.missfresh.schoolgrade;

import android.util.Log;

import java.util.Random;

/**
 * Created by MissFresh on 2017-09-12.
 */

public class Assignment {

    private static final String TAG = "Assignment.class";

    private static int assID = 0; //static ID increments with every new assignment created
    private String assignmentTitle; //title of assignment
    private int assignmentGrade; //grade of assignment
    private String assignmentLetterGrade; //letter grade of assignment

    //private constructor. Increments ID.
    private Assignment(String title, int grade)
    {
        assignmentTitle = title;
        assignmentGrade = grade;
        assignmentLetterGrade = getLetterGrade(grade);
        assID++;
    }

    public static String getLetterGrade(int grade) {

        if (grade>=90) return "A+";
        else if (grade>=85) return "A";
        else if (grade>=80) return "A-";
        else if (grade>=77) return "B+";
        else if (grade>=73) return "B";
        else if (grade>=70) return "B-";
        else if (grade>=67) return "C+";
        else if (grade>=63) return "C";
        else if (grade>=60) return "C-";
        else if (grade>=57) return "D+";
        else if (grade>=53) return "D";
        else if (grade>=50) return "D-";
        else if (grade>=0) return "F";
        else return "ERR";

    }

    //returns an Assignment instance with random values
    static public Assignment generateRandomAssignment()
    {
        Random rnd = new Random();
        String title = "Assignment " + assID;
        int grade = rnd.nextInt(100) + 1;
        return new Assignment(title, grade);
    }

    //****get methods*****//
    public String getAssignmentTitle() {return assignmentTitle; }
    public int getAssignmentGrade() {return assignmentGrade;}
    public String getAssignmentLetterGrade() {return assignmentLetterGrade;}
}
