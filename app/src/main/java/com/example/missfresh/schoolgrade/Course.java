package com.example.missfresh.schoolgrade;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by MissFresh on 2017-09-12.
 */

public class Course {

        private static final String TAG = "Course.class";

        private static int courseID = 0; //static ID increments with every new Course created
        private String courseTitle; //cou
        private ArrayList<Assignment> assignments;

        private Course(String title, ArrayList<Assignment> assns)
        {
            courseTitle = title;
            assignments = assns;
            courseID++;
        }

        //returns a Course instant with random assignment values
        static public Course generateRandomCourse()
        {
            Random rnd = new Random();
            int assignmentNo = rnd.nextInt(5);
            ArrayList<Assignment> assignments = new ArrayList<Assignment>();
            for(int i=0; i < assignmentNo; i++)
                assignments.add(Assignment.generateRandomAssignment());
            return new Course("Course " + courseID, assignments);
        }

        //****get methods*****//
        public String getCourseTitle() {return courseTitle;}
        public ArrayList<Assignment> getAssignments() {return assignments;}
}
