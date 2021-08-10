package com.selfLearn.SELF_LEARN.ListAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.selfLearn.SELF_LEARN.DataModels.CourseMessage;
import com.selfLearn.SELF_LEARN.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DiscussionListAdapter extends RecyclerView.Adapter<DiscussionListAdapter.MessageHolder> {

    private Context context;
    String courseId;
    List<CourseMessage> discussionList;

    public DiscussionListAdapter(Context context, List<CourseMessage> discussionList, String courseId) {
        this.context = context;
        this.discussionList = discussionList;
        this.courseId = courseId;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discussion_message_holder, parent, false);
        return new DiscussionListAdapter.MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        CourseMessage cm = discussionList.get(position);
        holder.msgContentView.setText(cm.getMessage());
        holder.msgSenderView.setText(cm.getSentBy());
        Date date = cm.getMessageTime().toDate();
        String ampm = date.getHours() > 12 ? " PM" : "AM";
        String dateString = date.getHours() + " : " + date.getMinutes() + ampm + " " + date.getDate() + "/" + date.getMonth();

        String pattern = "HH:mm yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        holder.msgTimestampView.setText(simpleDateFormat.format(cm.getMessageTime().toDate()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(cm.getSentBy().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                    FirebaseFirestore.getInstance()
                            .collection("Courses")
                            .document(courseId)
                            .collection("Discussion")
                            .document(cm.getMessageId())
                            .delete()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("COURSE MESSAGE","Message Deleted");
                                }
                            });
                }

                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return discussionList.size();
    }

    public static class MessageHolder extends RecyclerView.ViewHolder {

        TextView msgSenderView;
        TextView msgContentView;
        TextView msgTimestampView;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);
            msgSenderView = itemView.findViewById(R.id.message_senderId);
            msgContentView = itemView.findViewById(R.id.message_content);
            msgTimestampView = itemView.findViewById(R.id.message_timestamp);
        }
    }
}
