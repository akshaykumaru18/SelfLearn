package com.selfLearn.SELF_LEARN.ListAdapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selfLearn.SELF_LEARN.DataModels.Course;
import com.selfLearn.SELF_LEARN.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
public class CourseListAdapter  extends RecyclerView.Adapter<CourseListAdapter.CourseHolder>{

    private String COURSE_TAG = "CourseListAdapter";
    private  Context context;
    private  List<Course> courses;
    public CourseListAdapter(Context context, List<Course> courses) {
        this.context = context;
        this.courses = courses;
    }

    @NonNull
    @Override
    public CourseListAdapter.CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list_item, parent, false);
        return new CourseListAdapter.CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseListAdapter.CourseHolder holder, int position) {
            Course course = courses.get(position);
            holder.courseTitle.setText(course.getCourseName());
            holder.courseDescription.setText(course.getCourseDescription());
            Picasso.with(context).load(course.getCourseImage()).fit()
                .networkPolicy(NetworkPolicy.NO_CACHE).into(holder.courseImage);

            holder.courseEnrollBtn.setOnClickListener(v -> Log.d(COURSE_TAG,"Clicked Enroll Button"));
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public static class CourseHolder extends RecyclerView.ViewHolder {

        TextView courseTitle;
        TextView courseDescription;
        ImageView courseImage;
        Button courseEnrollBtn;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.c_title);
            courseDescription = itemView.findViewById(R.id.c_disp);
            courseImage = itemView.findViewById(R.id.c_img);
            courseEnrollBtn = itemView.findViewById(R.id.cEnrollBtn);


        }
    }
}
