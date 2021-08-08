package com.selfLearn.SELF_LEARN.ListAdapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.selfLearn.SELF_LEARN.CoursePageActivity;
import com.selfLearn.SELF_LEARN.DataModels.CourseVideo;
import com.selfLearn.SELF_LEARN.DataModels.RecentCourse;
import com.selfLearn.SELF_LEARN.R;
import com.selfLearn.SELF_LEARN.VideoPlayerActivity;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoHolder> {
    private Context context;
    List<CourseVideo> courseVideos;

    public VideoListAdapter(Context context, List<CourseVideo> courseVideos) {
        this.context = context;
        this.courseVideos = courseVideos;
    }

    @NonNull
    @Override
    public VideoListAdapter.VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_video_item, parent, false);
        return new VideoListAdapter.VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListAdapter.VideoHolder holder, int position) {
        CourseVideo cv = courseVideos.get(position);
        holder.cVideoTitleTV.setText(cv.getVideoTitle());
        holder.cVideoDescription.setText(cv.getVideoDescription());

        holder.cPlayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("COURSE VIDEO ITEM", "Playing the video" + position);
                Intent i = new Intent(context, VideoPlayerActivity.class);
                i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("videoId",cv.getYoutubeId());
                context.startActivity(i);
            }
        });



        Picasso.with(context).load(cv.getVideoImage()).fit()
                .networkPolicy(NetworkPolicy.NO_CACHE).into(holder.cImageV);

    }

    @Override
    public int getItemCount() {
        return courseVideos.size();
    }

    public static class VideoHolder extends RecyclerView.ViewHolder {
        TextView  cVideoTitleTV, cVideoDescription;
        ImageButton cPlayButton;
        ImageView cImageV;

        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            cImageV = itemView.findViewById(R.id.cv_image);

            cVideoTitleTV = itemView.findViewById(R.id.cv_video_title);
            cVideoDescription = itemView.findViewById(R.id.cv_video_description);
            cPlayButton = itemView.findViewById(R.id.cv_play);


        }
    }
}
