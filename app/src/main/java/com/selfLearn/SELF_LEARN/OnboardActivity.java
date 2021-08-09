package com.selfLearn.SELF_LEARN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.SetOptions;
import com.selfLearn.SELF_LEARN.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnboardActivity extends AppCompatActivity {

    public static final String TAG = "OnboardActivity";
    ListView skillsListView;
    Button continueBtn;
    EditText fname, lname,contactNum;

    private final FirebaseFirestore db;

    public OnboardActivity() {
        db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);
        getSupportActionBar().hide();
        skillsListView = (ListView) findViewById(R.id.skillView);
        continueBtn = findViewById(R.id.continueBtn);
        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        contactNum = findViewById(R.id.phNo);



        this.skillsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        this.skillsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG,"Clicked at : \t"+ position);

                CheckedTextView ctv = (CheckedTextView) view;
                boolean currentCheck = ctv.isChecked();
                Skill skill = (Skill) skillsListView.getItemAtPosition(position);
                skill.setSkillChecked(currentCheck);
            }
        });

        this.initSkillsData();
    }

    public void createProfile(View view){
        String email = (String) getIntent().getExtras().get("email");
            String firstName = fname.getText().toString();
            String lastName = lname.getText().toString();
            String phoneNumber = contactNum.getText().toString();

            if(firstName.isEmpty()){
                fname.requestFocus();

            }

            if(lastName.isEmpty()){
                lname.requestFocus();

            }

            if(phoneNumber.isEmpty()){
                contactNum.requestFocus();
            }

            if(phoneNumber.length() == 10){
                List<String> selectedSkills = new ArrayList<String>();
                SparseBooleanArray sp = skillsListView.getCheckedItemPositions();
                for (int i = 0; i< skillsListView.getCheckedItemPositions().size();i++){
                    if(sp.valueAt(i)){
                        // Toast.makeText(getApplicationContext(), skillsListView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                        selectedSkills.add(skillsListView.getItemAtPosition(i).toString());
                    }

                }
                if(selectedSkills.size() == 0){
                    Toast.makeText(getApplicationContext(), "Select atleast 1 Skill to Continue", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,Object> user  = new HashMap<>();
                    user.put("fname",firstName);
                    user.put("email",email);
                    user.put("lname",lastName);
                    user.put("phone-number",phoneNumber);
                    user.put("prefered-skills",selectedSkills);

                    db.collection("Users").document(email).set(user, SetOptions.merge()).addOnSuccessListener(aVoid -> {
                        //Toast.makeText(getApplicationContext(),firstName + "\n" + lastName + "\n" + selectedSkills.toString() + "\n" + phoneNumber, Toast.LENGTH_LONG).show();
                        Intent onboardIntent = new Intent(OnboardActivity.this,HomePageActivity.class);
                        onboardIntent.putExtra("email",email);
                        onboardIntent.putExtra("fname",firstName);
                        startActivity(onboardIntent);
                    }).addOnFailureListener(e -> {
                        Toast.makeText(getApplicationContext(),e.getLocalizedMessage().toLowerCase(), Toast.LENGTH_LONG).show();
                    });


                }
            }



        //Toast.makeText(getApplicationContext(),firstName + "\n" + lastName + "\n" + selectedSkills.toString() + "\n" + phoneNumber, Toast.LENGTH_LONG).show();

    }
    private void initSkillsData()
    {
        Skill nAndroid = new Skill("Native Android", false);
        Skill flutter = new Skill("Flutter", false);
        Skill ios = new Skill("Native IOS", false);
        Skill firebase = new Skill("Firebase", false);
        Skill web = new Skill("Web Development", false);
        Skill react = new Skill("React JS", false);
        Skill machineLearning = new Skill("Machine Learning", false);

        Skill[] skills = new Skill[]{nAndroid,flutter,ios,firebase,web,react,machineLearning};

        ArrayAdapter<Skill> arrayAdapter = new ArrayAdapter<Skill>(this, android.R.layout.simple_list_item_checked,skills);
        this.skillsListView.setAdapter(arrayAdapter);

    }
}