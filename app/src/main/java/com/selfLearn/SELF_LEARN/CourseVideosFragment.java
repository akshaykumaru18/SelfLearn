package com.selfLearn.SELF_LEARN;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.selfLearn.SELF_LEARN.DataModels.Course;
import com.selfLearn.SELF_LEARN.DataModels.CourseVideo;
import com.selfLearn.SELF_LEARN.ListAdapters.CourseListAdapter;
import com.selfLearn.SELF_LEARN.ListAdapters.VideoListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseVideosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseVideosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView videosList;
    VideoListAdapter videoListAdapter;
    String courseId;

    public CourseVideosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseVideosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseVideosFragment newInstance(String param1, String param2) {
        CourseVideosFragment fragment = new CourseVideosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            courseId = getArguments().getString("courseId");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_videos, container, false);
        videosList = view.findViewById(R.id.courseVideosList);
        videosList.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        videosList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom = 30;
            }
        });

        List<CourseVideo> courseVideos = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .collection("Courses")
                .document(courseId)
                .collection("Videos")
                .orderBy("timestamp")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots.getDocuments().size() > 0) {
                        for (DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()) {

                            CourseVideo courseVideo = new CourseVideo(
                                    doc.getString("videoTitle"),
                                    doc.getString("videoImage"),
                                    doc.getString("videoDescription"),
                                    doc.getString("youtubeId")
                            );

                            Log.d("COURSE VIDEOS", courseVideo.getVideoTitle() + "\n" + courseVideo.getVideoDescription() + "\n" + courseVideo.getVideoImage() + "\n" + courseVideo.getYoutubeId());
                            courseVideos.add(courseVideo);

                        }
                    }
                    videoListAdapter = new VideoListAdapter(view.getContext(), courseVideos);
                    videosList.setAdapter(videoListAdapter);
                });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }
}