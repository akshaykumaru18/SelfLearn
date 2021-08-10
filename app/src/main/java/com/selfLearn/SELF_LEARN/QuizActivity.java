package com.selfLearn.SELF_LEARN;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.selfLearn.SELF_LEARN.DataModels.Quizmodel;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTV,questionNumberTV;
    private Button option1Btn,option2Btn,option3Btn,option4Btn;
    private ArrayList<Quizmodel> quizModalArrayList;
    Random random;
    int currentScore=0, questionAttempted=0,currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionTV=findViewById(R.id.idTVQuestion);
        questionNumberTV=findViewById(R.id.idTVQuestionAttempted);
        option1Btn=findViewById(R.id.idBtnOption1);
        option2Btn=findViewById(R.id.idBtnOption2);
        option3Btn=findViewById(R.id.idBtnOption3);
        option4Btn=findViewById(R.id.idBtnOption4);
        quizModalArrayList= new ArrayList<>();
        random=new Random();
        FirebaseFirestore.getInstance()
                .collection("Courses")
                .document(getIntent().getStringExtra("courseId"))
                .collection("Quiz")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if(queryDocumentSnapshots.getDocuments().size() > 0 ){
                            for(DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                                Quizmodel qm = new Quizmodel(doc.getString("question"),doc.getString("option1"),doc.getString("option2"),doc.getString("option3"),doc.getString("option4"),doc.getString("correctChoice"));
                                quizModalArrayList.add(qm);
                            }
                            currentPos=random.nextInt(quizModalArrayList.size());
                            setDataToViews(currentPos);
                        }
                    }
                });

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;

                }
                questionAttempted++;
                currentPos=random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);

            }
        });
        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;

                }
                questionAttempted++;
                currentPos=random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);

            }
        });
        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;

                }
                questionAttempted++;
                currentPos=random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);

            }
        });
        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModalArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;

                }
                questionAttempted++;
                currentPos=random.nextInt(quizModalArrayList.size());
                setDataToViews(currentPos);

            }
        });

    }

    private void setDataToViews(int currentPos) {
        String performance = "";
        if(currentScore > 3 && currentScore < 5){
            performance = "Average";
        }else
        if(currentScore > 5 && currentScore < 7){
            performance = "Good";
        }else
        if(currentPos > 7){
            performance = "Excellent";
        }
        questionNumberTV.setText("Question Attempted :" + questionAttempted +" / " + quizModalArrayList.size() + "\n" + performance);
        if (questionAttempted == quizModalArrayList.size()) {
            new AlertDialog.Builder(QuizActivity.this)
                    .setTitle("Quiz Result")
                    .setMessage("Your correct answers ( " + currentScore + " / "+ quizModalArrayList.size() + " )")

                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.star_on)
                    .show();
        } else {
            questionTV.setText(quizModalArrayList.get(this.currentPos).getQuestion());
            option1Btn.setText(quizModalArrayList.get(this.currentPos).getOption1());
            option2Btn.setText(quizModalArrayList.get(this.currentPos).getOption2());
            option3Btn.setText(quizModalArrayList.get(this.currentPos).getOption3());
            option4Btn.setText(quizModalArrayList.get(this.currentPos).getOption4());

        }
    }

    private void getQuizQuestion() {
        FirebaseFirestore.getInstance()
                .collection("Courses")
                .document(getIntent().getStringExtra("courseId"))
                .collection("Quiz")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if(queryDocumentSnapshots.getDocuments().size() > 0 ){
                                for(DocumentSnapshot doc : queryDocumentSnapshots.getDocuments()){
                                    Quizmodel qm = new Quizmodel(doc.getString("question"),doc.getString("option1"),doc.getString("option2"),doc.getString("option3"),doc.getString("option4"),doc.getString("correctChoice"));
                                    quizModalArrayList.add(qm);
                                }
                            }
                    }
                });


    }
}