package com.example.missfresh.schoolgrade;

import android.content.SharedPreferences;
import android.widget.BaseExpandableListAdapter;

/**
 * Created by MissFresh on 2017-09-18.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import static com.example.missfresh.schoolgrade.GradeActivity.letterGrade;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    /**
     * Initialize data
     */
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Assignment>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> courseTitles,
                                 HashMap<String, List<Assignment>> assignments) {
        this._context = context;
        this._listDataHeader = courseTitles;
        this._listDataChild = assignments;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * Format assignment view items
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Assignment assignment = (Assignment) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.assignment_row, null);
        }

        TextView assignmentTitleTextView = (TextView) convertView
                .findViewById(R.id.assigmentTitleTextView);

        TextView assignmentGradeTextView = (TextView) convertView
                .findViewById(R.id.assigmentGradeTextView);

        assignmentTitleTextView.setText(assignment.getAssignmentTitle());

        if(!GradeActivity.letterGrade) {
            assignmentGradeTextView.setText(Integer.toString(assignment.getAssignmentGrade()));
        } else {
            assignmentGradeTextView.setText(Assignment.getLetterGrade(assignment.getAssignmentGrade()));
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    /**
     * Format course group
     */
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.course_row, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.courseNameTextView);

        TextView courseGradeAverage = (TextView) convertView
                .findViewById(R.id.courseGradeAvgTextView);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        List<Assignment> assignments = _listDataChild.get(headerTitle);
        int averageGrade = getAverageGrade(assignments);

        if(!GradeActivity.letterGrade) {
            courseGradeAverage.setText( Integer.toString(averageGrade)) ;
        } else {
            courseGradeAverage.setText( Assignment.getLetterGrade(averageGrade)) ;
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    /**myListView.invalidateViews();
     * Grade average assignment grade for course
     * @param assignments
     * @return
     */
    public int getAverageGrade(List<Assignment> assignments){
        if(assignments.size()==0) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < assignments.size(); i++) {
            sum+=assignments.get(i).getAssignmentGrade();
        }
        return sum/assignments.size();
    }

}