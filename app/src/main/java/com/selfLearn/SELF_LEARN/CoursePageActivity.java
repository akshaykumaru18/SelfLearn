package com.selfLearn.SELF_LEARN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.selfLearn.SELF_LEARN.DataModels.Course;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CoursePageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    ImageView courseBanner;
    TextView courseTitle;
    ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);


        courseBanner = findViewById(R.id.courseBanner);
        courseTitle = findViewById(R.id.ct);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String courseId = (String) getIntent().getExtras().get("courseID");
        FirebaseFirestore.getInstance()
                .collection("Courses")
                .document(courseId)
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("Hola",documentSnapshot.getString("courseName"));
                getSupportActionBar().setTitle(documentSnapshot.getString("courseName"));

                courseTitle.setText(documentSnapshot.getString("courseName"));
                getSupportActionBar().setSubtitle("Course");


                Picasso.with(getApplicationContext()).load(documentSnapshot.getString("courseImage"))
                        .networkPolicy(NetworkPolicy.NO_CACHE).into(courseBanner);

                CourseDetailsFragment courseDetailsFragment = new CourseDetailsFragment();
                Bundle courseBundle = new Bundle();
                courseBundle.putString("description",documentSnapshot.getString("courseDescription"));
                courseBundle.putLong("price", (Long) documentSnapshot.get("coursePrice"));
                courseBundle.putString("type",  documentSnapshot.getString("courseType"));
                courseBundle.putString("courseId",  documentSnapshot.getString("courseId"));
                courseDetailsFragment.setArguments(courseBundle);
                adapter.addFrag(courseDetailsFragment, "Details");

                CourseVideosFragment courseVideosFragment = new CourseVideosFragment();
                courseVideosFragment.setArguments(courseBundle);
                adapter.addFrag(courseVideosFragment, "Videos");


                viewPager.setAdapter(adapter);
            }
        });
    }
}