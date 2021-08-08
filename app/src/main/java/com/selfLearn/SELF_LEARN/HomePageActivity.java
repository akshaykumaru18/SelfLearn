package com.selfLearn.SELF_LEARN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import  androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.selfLearn.SELF_LEARN.DataModels.Course;
import com.selfLearn.SELF_LEARN.DataModels.CourseType;
import com.selfLearn.SELF_LEARN.DataModels.RecentCourse;
import com.selfLearn.SELF_LEARN.ListAdapters.CourseListAdapter;
import com.selfLearn.SELF_LEARN.ListAdapters.RecentCourseListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity {

    RecyclerView recentCourseList;
    RecyclerView courseList;
    RecentCourseListAdapter recentCourseAdapter;
    CourseListAdapter courseListAdapter;
    ProgressDialog dialog;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        getSupportActionBar().setTitle("Self Learn");
        getSupportActionBar().setSubtitle("Online Guru");



        recentCourseList = findViewById(R.id.recentCourses);
        courseList = findViewById(R.id.courseList);
        progressBar = findViewById(R.id.home_page_loading);
        dialog = new ProgressDialog(HomePageActivity.this);

        loadContents();


    }

    private void loadContents() {
        dialog.setMessage("Loading Courses");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();
        intializeRecentCourses();
        initializeCourseList();
    }

    private void intializeRecentCourses() {
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

        for (int i = 1; i < 5; i++) {
            List<String> videos = new ArrayList<>();
            videos.add("Hello");
            videos.add("Hola");

            RecentCourse rc = new RecentCourse("ID : " + i, "Course : " + i, videos, "Course", "https://www.tacthub.in/user-panel/img/subject_thumb/data-science-course-840x450.jpg");
            recentCourses.add(rc);
        }
        recentCourseAdapter = new RecentCourseListAdapter(recentCourses, getApplicationContext());
        recentCourseList.setAdapter(recentCourseAdapter);

    }

    public void initializeCourseList() {
        courseList.setLayoutManager(new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.VERTICAL, false));

        courseList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom = 30;
            }
        });

        List<Course> courses = new ArrayList<>();


        FirebaseFirestore.getInstance()
                .collection("Courses")
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
            Log.d("HomePageActivity", String.valueOf(queryDocumentSnapshots.getDocuments().size()));
            if (queryDocumentSnapshots.getDocuments().size() > 0) {
                for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {
                    Map<String, ?> data = doc.getData();
                    Course c = new Course(
                            data.get("courseName").toString(),
                            doc.getId(), data.get("courseDescription").toString(),
                            Integer.parseInt(data.get("coursePrice").toString()),
                            data.get("coursePrice").toString().equals("Free") ? CourseType.Free : CourseType.Paid,
                            data.get("courseImage").toString(),
                            null,
                            null,
                            null
                    );
                    courses.add(c);
                }
                courseListAdapter = new CourseListAdapter(getApplicationContext(), courses);
                courseList.setAdapter(courseListAdapter);

                dialog.dismiss();
            }
        }).addOnFailureListener(e -> {
            Log.d("FAILED FETCHING", e.getLocalizedMessage());
            dialog.dismiss();
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.logout){


             FirebaseAuth mAuth = FirebaseAuth.getInstance();
             mAuth.signOut();
            Intent logoutIntent = new Intent(HomePageActivity.this,MainActivity.class);

            startActivity(logoutIntent);
            finish();

        }
        return super.onOptionsItemSelected(item);

    }
}