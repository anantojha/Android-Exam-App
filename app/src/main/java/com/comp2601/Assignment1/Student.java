package com.comp2601.Assignment1;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    //XML tags the define Student properties
    public static final String XML_EMAIL = "email";
    public static final String XML_NAME = "name";
    public static final String XML_ID = "id";

    private String email;
    private int score;
    private String[] myAnswers;
    private String name;
    private String id;

    public Student(String e, String n, String i){
        email = e;
        score = 0;
        myAnswers = new String[10];
        name = n;
        id = i;
    }

    protected Student(Parcel in) {
        email = in.readString();
        score = in.readInt();
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getMyAnswers() {
        return myAnswers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeInt(score);
        dest.writeString(name);
        dest.writeString(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
