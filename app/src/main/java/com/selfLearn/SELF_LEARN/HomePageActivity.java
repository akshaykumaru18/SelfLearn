package com.selfLearn.SELF_LEARN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.selfLearn.SELF_LEARN.DataModels.RecentCourse;
import com.selfLearn.SELF_LEARN.ListAdapters.RecentCourseListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    RecyclerView recentCourseList;
    RecentCourseListAdapter recentCourseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();
        recentCourseList = findViewById(R.id.recentCourses);

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
        List<RecentCourse> recentCourses = new ArrayList<>();
        for (int i = 0;i<5;i++){
            List<String> videos = new ArrayList<>();
            videos.add("Hello");
            videos.add("Hola");

            RecentCourse rc = new RecentCourse("ID : "+ i,"Course : "+ i,videos,"Course", "Namasthe");
            recentCourses.add(rc);
        }
        recentCourseAdapter = new RecentCourseListAdapter(recentCourses,getApplicationContext());

        recentCourseList.setClickable(true);


        recentCourseList.setAdapter(recentCourseAdapter);
    }
}