package com.comp2601.Assignment1;

/**
 * (c) 2020 L.D. Nel
 */
import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =  MainActivity.class.getSimpleName();

    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;
    private Button mEButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mSubmitButton;

    private TextView mAnswerATextView;
    private TextView mAnswerBTextView;
    private TextView mAnswerCTextView;
    private TextView mAnswerDTextView;
    private TextView mAnswerETextView;

    private TextView mQuestionTextView;
    private TextView mStudentTextView;
    private ArrayList<Question> questions;
    private Student student;

    private int questionIndex;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("student", student);
        outState.putParcelableArrayList("questions", questions);
        outState.putInt("questionIndex", questionIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //set and inflate UI to manage

        mAButton = (Button) findViewById(R.id.A_button);
        mBButton = (Button) findViewById(R.id.B_button);
        mCButton = (Button) findViewById(R.id.C_button);
        mDButton = (Button) findViewById(R.id.D_button);
        mEButton = (Button) findViewById(R.id.E_button);
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mSubmitButton = (Button) findViewById(R.id.submit_button);

        mAnswerATextView = (TextView) findViewById(R.id.answerA_text_view);
        mAnswerBTextView = (TextView) findViewById(R.id.answerB_text_view);
        mAnswerCTextView = (TextView) findViewById(R.id.answerC_text_view);
        mAnswerDTextView = (TextView) findViewById(R.id.answerD_text_view);
        mAnswerETextView = (TextView) findViewById(R.id.answerE_text_view);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setTextColor(Color.BLUE);

        mStudentTextView = (TextView) findViewById(R.id.student_text_view);
        mStudentTextView.setTextColor(Color.RED);

        mAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "True Button Clicked"); //print to console for debugging

                student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[0];
                mAnswerATextView.setBackgroundColor(Color.parseColor("#cde1f3"));
                mAnswerBTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerCTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerDTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerETextView.setBackgroundColor(Color.parseColor("#FAFAFA"));

            }

        });
        mBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[1];
                mAnswerBTextView.setBackgroundColor(Color.parseColor("#cde1f3"));
                mAnswerATextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerCTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerDTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerETextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
            }

        });
        mCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[2];
                mAnswerCTextView.setBackgroundColor(Color.parseColor("#cde1f3"));
                mAnswerATextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerBTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerDTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerETextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
            }

        });
        mDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[3];
                mAnswerDTextView.setBackgroundColor(Color.parseColor("#cde1f3"));
                mAnswerATextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerBTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerCTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerETextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
            }

        });
        mEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student.getMyAnswers()[questionIndex] = questions.get(questionIndex).getAnswer()[4];
                mAnswerETextView.setBackgroundColor(Color.parseColor("#cde1f3"));
                mAnswerATextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerBTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerCTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
                mAnswerDTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
            }

        });

        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                refreshView();
                if(questionIndex > 0) questionIndex--;
                setQuizActivityView();
                setSavedAnswer();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                refreshView();
                if(questionIndex < questions.size()-1) questionIndex++;
                setQuizActivityView();
                setSavedAnswer();
            }
        });

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                intent.putExtra("answers", student.getMyAnswers());
                String[] qs = new String[questions.size()];
                for (int i=0; i<qs.length; i++){
                    qs[i] = questions.get(i).getQuestionString();
                }
                intent.putExtra("questions", qs);
                intent.putExtra("name", student.getName());
                intent.putExtra("id", student.getId());
                intent.putExtra("email", student.getEmail());
                startActivityForResult(intent, 0);
            }
        });

        questions = null;
        ArrayList<Question> parsedModel = null;

        if (savedInstanceState != null) {
            System.out.println("SAVED FROM STATE");
            student = savedInstanceState.getParcelable("student");
            questions = savedInstanceState.getParcelableArrayList("questions");
            questionIndex = savedInstanceState.getInt("questionIndex");
        } else {
            System.out.println("SAVED FROM FILE");
            questionIndex = 0;
            student = new Student("", "","");
            try {
                InputStream iStream = getResources().openRawResource(R.raw.exam);
                BufferedReader bReader = new BufferedReader(new InputStreamReader(iStream));
                parsedModel = Exam.pullParseFrom(bReader, student);
                bReader.close();
            }
            catch (java.io.IOException e){
                e.printStackTrace();
            }

            if(parsedModel == null || parsedModel.isEmpty())
                Log.i(TAG, "ERROR: Questions Not Parsed");

            questions = parsedModel;
        }

        // Display question to view
        setQuizActivityView();
        setSavedAnswer();
    }

    public void setQuizActivityView(){
        if(questions != null && questions.size() > 0) {
            mQuestionTextView.setText("" + (questionIndex + 1) + ") " + questions.get(questionIndex).toString());
            mAnswerATextView.setText(questions.get(questionIndex).getAnswer()[0]);
            mAnswerBTextView.setText(questions.get(questionIndex).getAnswer()[1]);
            mAnswerCTextView.setText(questions.get(questionIndex).getAnswer()[2]);
            mAnswerDTextView.setText(questions.get(questionIndex).getAnswer()[3]);
            mAnswerETextView.setText(questions.get(questionIndex).getAnswer()[4]);
        }

        mStudentTextView.setText(student.getName());
    }

    public void setSavedAnswer(){
        if(student.getMyAnswers()[questionIndex] != null){
            if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[0])){
                mAnswerATextView.setBackgroundColor(Color.parseColor("#cde1f3"));
            } else if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[1])) {
                mAnswerBTextView.setBackgroundColor(Color.parseColor("#cde1f3"));
            } else if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[2])) {
                mAnswerCTextView.setBackgroundColor(Color.parseColor("#cde1f3"));
            } else if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[3])) {
                mAnswerDTextView.setBackgroundColor(Color.parseColor("#cde1f3"));
            } else if(student.getMyAnswers()[questionIndex].equals(questions.get(questionIndex).getAnswer()[4])) {
                mAnswerETextView.setBackgroundColor(Color.parseColor("#cde1f3"));
            }
        }
    }

    public void refreshView(){
        mAnswerATextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
        mAnswerBTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
        mAnswerCTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
        mAnswerDTextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
        mAnswerETextView.setBackgroundColor(Color.parseColor("#FAFAFA"));
    }
}
