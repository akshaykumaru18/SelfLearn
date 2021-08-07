package com.selfLearn.SELF_LEARN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.selfLearn.SELF_LEARN.DataModels.Course;
import com.selfLearn.SELF_LEARN.DataModels.CourseType;
import com.selfLearn.SELF_LEARN.DataModels.RecentCourse;
import com.selfLearn.SELF_LEARN.ListAdapters.CourseListAdapter;
import com.selfLearn.SELF_LEARN.ListAdapters.RecentCourseListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    RecyclerView recentCourseList;
    RecyclerView courseList;
    RecentCourseListAdapter recentCourseAdapter;
    CourseListAdapter courseListAdapter;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        recentCourseList = findViewById(R.id.recentCourses);
        courseList = findViewById(R.id.courseList);
        progressBar = findViewById(R.id.home_page_loading);
//        ProgressDialog dialog=new ProgressDialog(HomePageActivity.this);
//        dialog.setMessage("Loading Your Activity");
//        dialog.setCancelable(false);
//        dialog.setInverseBackgroundForced(false);
//        dialog.show();
//
//
//
//        dialog.dismiss();
        intializeRecentCourses();
        initializeCourseList();

    }


    private void intializeRecentCourses(){
        //progressBar.setVisibility(View.VISIBLE);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recentCourseList.setLayoutManager(horizontalLayoutManagaer);
        recentCourseList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

                // super.getItemOffsets(outRect, view, parent, state);
                outRect.right = 10;
            }
        });

        List<RecentCourse> recentCourses = new ArrayList<RecentCourse>();

        for (int i = 1;i<5;i++){
            List<String> videos = new ArrayList<>();
            videos.add("Hello");
            videos.add("Hola");

            RecentCourse rc = new RecentCourse("ID : "+ i,"Course : "+ i,videos,"Course", "https://www.tacthub.in/user-panel/img/subject_thumb/data-science-course-840x450.jpg");
            recentCourses.add(rc);
        }
        recentCourseAdapter = new RecentCourseListAdapter(recentCourses,getApplicationContext());
        recentCourseList.setAdapter(recentCourseAdapter);
    }

    public  void initializeCourseList(){
        courseList.setLayoutManager(new LinearLayoutManager(HomePageActivity.this,LinearLayoutManager.VERTICAL,false));

        courseList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {


                outRect.bottom = 30;
            }
        });

        List<Course> courses = new ArrayList<Course>();
        for (int i=1;i<5;i++){
            Course  c = new Course("Course "+ i,String.valueOf(i),"Very good Course on " + i,100, CourseType.Free,"https://www.tacthub.in/user-panel/img/subject_thumb/data-science-course-840x450.jpg",null,null,null);
            courses.add(c);
        }
        courseListAdapter = new CourseListAdapter(getApplicationContext(),courses);
        courseList.setAdapter(courseListAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
}