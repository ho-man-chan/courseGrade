package com.example.missfresh.schoolgrade;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import android.widget.ExpandableListView;

public class GradeActivity extends AppCompatActivity {


    /**
     * Activity name
     */
    private final String TITLE = "Grades";

    /**
     * Component List
     */

    public static boolean letterGrade = false;

    /**
     * Adapters
     */
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    /**
     * Courses
     */
    private ArrayList<Course> courses = new ArrayList<Course>();
    private ArrayList<String> courseTitles = new ArrayList<String>();
    private HashMap<String, List<Assignment>> assignments = new HashMap<String, List<Assignment>>();

    /**
     * Initial setup
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        //Get Back button on top bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Generate mock courses and assignments
        generateCourses();
        generateExListView();

        //User interface initialization
        setupUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grade_option, menu);
        return true;
    }

    /**
     * Toggle grade format and refresh view
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemSelected = item.getItemId();

        if(itemSelected==R.id.showLetterMenu) {
            if(letterGrade) {
                letterGrade = false;
            } else {
                letterGrade = true;
            }

        }

        runOnUiThread(new Runnable() {
            public void run() {
                listAdapter.notifyDataSetChanged();
            }
        });

        return super.onOptionsItemSelected(item);
    }

    /**
     * Link component and its listeners
     */
    private void setupUI() {
        expListView = (ExpandableListView) findViewById(R.id.courseEListView);
        listAdapter = new ExpandableListAdapter(this, courseTitles, assignments);
        expListView.setAdapter(listAdapter);
    }

    /**
     * Generate mock courses
     */
    private void generateCourses() {
        Random random = new Random();
        int numberOfCourses = random.nextInt(5);
        for(int i = 0; i < numberOfCourses; i++) {
            courses.add(Course.generateRandomCourse());
        }
    }

    /**
     * Generate assignment hash map
     */
    private void generateExListView() {
        for(int i = 0; i <  courses.size(); i++) {
            assignments.put(courses.get(i).getCourseTitle(),courses.get(i).getAssignments());
            courseTitles.add(courses.get(i).getCourseTitle());
        }
    }


    /**
     * Toggle grade between number and letter
     */
    private void toggleGrades() {

    }


}
