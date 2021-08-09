package com.selfLearn.SELF_LEARN;

import android.content.Context;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.type.DateTime;
import com.selfLearn.SELF_LEARN.DataModels.Course;
import com.selfLearn.SELF_LEARN.DataModels.CourseMessage;
import com.selfLearn.SELF_LEARN.DataModels.CourseVideo;
import com.selfLearn.SELF_LEARN.ListAdapters.DiscussionListAdapter;
import com.selfLearn.SELF_LEARN.ListAdapters.VideoListAdapter;

import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String courseId;
    RecyclerView discussionMessageList;
    DiscussionListAdapter discussionListAdapter;

    public CourseDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDetailsFragment newInstance(String param1, String param2) {
        CourseDetailsFragment fragment = new CourseDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    String courseDescription;
    List<String> category;
    TextView descriptionView;
    TextView categoryView;
    Button enrollBtn;
    EditText messageField;
    ImageButton sendButton;
    List<CourseMessage> courseMessages;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        courseDescription = getArguments().getString("description");
        courseId = getArguments().getString("courseId");
        descriptionView.setText(courseDescription);
        categoryView.setVisibility(View.INVISIBLE);
        loadDiscussionList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            // Log.d("COURSE DETAIL FRAGMENT",getArguments().getString("description"));

            // category = getArguments().getStringArrayList("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_course_details, container, false);

        discussionMessageList = view.findViewById(R.id.discussionList);
        descriptionView = (TextView) view.findViewById(R.id.courseDescription);
        categoryView = (TextView) view.findViewById(R.id.courseCategory);
        enrollBtn = (Button) view.findViewById(R.id.enrollOrViewBtn);
        messageField = (EditText) view.findViewById(R.id.messageType);
        sendButton = (ImageButton) view.findViewById(R.id.message_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();

                Map<String, Object> msg = new HashMap<>();
                msg.put("messageId", null);
                msg.put("message",messageField.getText().toString());
                msg.put("timestamp", new Date());
                msg.put("sentBy", auth.getCurrentUser().getEmail());
                msg.put("messageType", "TEXT");

                FirebaseFirestore.getInstance()
                        .collection("Courses")
                        .document(courseId)
                        .collection("Discussion")
                        .add(msg).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Map<String, Object> updateMsg = new HashMap<>();
                        updateMsg.put("messageId", documentReference.getId());

                        FirebaseFirestore.getInstance()
                                .collection("Courses")
                                .document(courseId)
                                .collection("Discussion")
                                .document(documentReference.getId()).set(updateMsg, SetOptions.merge());


                    }
                });

                messageField.setText("");
            }
        });
        messageField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    sendButton.setVisibility(View.VISIBLE);
                } else {
                    sendButton.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void loadDiscussionList() {
        Log.d("COURSE ID", "Course ID is : " + courseId);
        discussionMessageList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        discussionMessageList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom = 30;
            }
        });
        courseMessages = new ArrayList<>();
        discussionListAdapter = new DiscussionListAdapter(getContext(), courseMessages,courseId);
        discussionMessageList.setAdapter(discussionListAdapter);
        if(courseId != null){
            FirebaseFirestore.getInstance()
                    .collection("Courses")
                    .document(courseId)
                    .collection("Discussion")
                    .addSnapshotListener((value, error) -> {
                        List<DocumentChange> documentChanges = value.getDocumentChanges();
                        for (DocumentChange doc : documentChanges) {
                            Log.d("MESSSAGE", "ID \t" + doc.getDocument().getId());
                            switch (doc.getType()) {

                                case ADDED:
                                    Log.d("NEW MESSSAGE", "New Message added \t" + doc.getType());
                                    CourseMessage courseMessage = new CourseMessage(doc.getDocument().getId(), doc.getDocument().getString("message"), doc.getDocument().getTimestamp("timestamp"), doc.getDocument().getString("sentBy"), "TEXT");
                                    courseMessages.add(courseMessage);
                                    discussionListAdapter.notifyDataSetChanged();
                                    break;
                                case MODIFIED:

                                    break;
                                case REMOVED:
                                    Log.d("DELETED MESSSAGE", "New Message deleted \t" + doc.getDocument().getId());
                                   for(CourseMessage cm : courseMessages){
                                       if(cm.getMessageId().equals(doc.getDocument().getId())){
                                           courseMessages.remove(cm);
                                       }
                                   }
                                    discussionListAdapter.notifyDataSetChanged();
                                    break;
                            }
                        }
                    });


        }


    }
}