package com.selfLearn.SELF_LEARN.ListAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.selfLearn.SELF_LEARN.DataModels.RecentCourse;
import com.selfLearn.SELF_LEARN.HomePageActivity;
import com.selfLearn.SELF_LEARN.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecentCourseListAdapter extends RecyclerView.Adapter<RecentCourseListAdapter.RecentCourseHolder> {

    List<RecentCourse> recentCourses;
    private  Context context;
    public RecentCourseListAdapter(List<RecentCourse> recentCourses,Context context){
        this.recentCourses = recentCourses;
        this.context = context;
    }


    @NonNull
    @Override
    public RecentCourseListAdapter.RecentCourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_cource_list_item,parent,false);
        return new RecentCourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentCourseListAdapter.RecentCourseHolder holder, int position) {
        RecentCourse rc = recentCourses.get(position);
        holder.rcCourceTitleTV.setText(rc.getCourceName());
        holder.rcVideoTitleTV.setText(rc.getCourseID());

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.d("RECENT COURSE ITEM","ITEM CLICKED" + position);
            }
        });


        Picasso.with(context).load("https://m.media-amazon.com/images/I/71+lve0VDlL._SY450_.jpg")
                .networkPolicy(NetworkPolicy.NO_CACHE).into(holder.rcImageV);

    }

    @Override
    public int getItemCount() {
        return recentCourses.size();
    }



    public static class RecentCourseHolder extends  RecyclerView.ViewHolder  implements  View.OnClickListener,View.OnLongClickListener{
        TextView rcCourceTitleTV, rcVideoTitleTV, rcVideoDurationTV;
        ImageButton rcPlayButton;
        ImageView rcImageV;
        public RecentCourseHolder(@NonNull View itemView) {
            super(itemView);
             rcImageV = itemView.findViewById(R.id.rc_image);
             rcCourceTitleTV = itemView.findViewById(R.id.rc_course_title);
             rcVideoTitleTV = itemView.findViewById(R.id.rc_video_title);
             rcVideoDurationTV = itemView.findViewById(R.id.rc_video_duration);
             rcPlayButton = itemView.findViewById(R.id.rc_play);


        }

        @Override
        public void onClick(View v) {
            Log.d("RECENT COURSE ITEM","ITEM CLICKED");
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}


/*
ImageView rcImageV = convertView.findViewById(R.id.rc_image);
       TextView rcCourceTitleTV = convertView.findViewById(R.id.rc_course_title);
        TextView rcVideoTitleTV = convertView.findViewById(R.id.rc_video_title);
        TextView rcVideoDurationTV = convertView.findViewById(R.id.rc_video_duration);
        ImageButton rcPlayButton = convertView.findViewById(R.id.rc_play);

        rcCourceTitleTV.setText(rc.getCourceName());
        rcVideoTitleTV.setText(rc.getCourseID());
 */