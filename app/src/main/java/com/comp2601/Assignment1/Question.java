package com.comp2601.Assignment1;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Question implements Parcelable {

    //XML tags the define Question properties
    public static final String XML_QUESTION = "question";
    public static final String XML_QUESTION_TEXT = "question_text";
    public static final String XML_ANSWER = "answer";
    public static final String XML_ATTR_Id = "Id";

    private String mQuestionString; //id of string resource representing the question
    private String[] mAnswer; //boolean representing the question answer
    private final int id;

    public Question(String aQuestion, String[] anAnswer, int i){
        mQuestionString = aQuestion;
        mAnswer = anAnswer;
        id = i;
        mAnswer = new String[5];
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public Question(Parcel in){
        mQuestionString = in.readString();
        mAnswer = in.createStringArray();
        id = in.readInt();
    }


    public String getQuestionString(){return mQuestionString;}
    public String[] getAnswer(){return mAnswer;}

    public void setQuestionString(String q){ mQuestionString = q; }

    @Override
    public String toString(){
        String toReturn = "";
        toReturn += mQuestionString;
        return toReturn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }


    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }
}
