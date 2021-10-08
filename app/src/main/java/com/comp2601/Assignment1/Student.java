package com.comp2601.Assignment1;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    //XML tags the define Student properties
    public static final String XML_EMAIL = "email";

    private String email;
    private final int score;
    private String[] myAnswers;

    public Student(String e) {
        email = e;
        score = 0;
        myAnswers = new String[10];
    }

    protected Student(Parcel in) {
        email = in.readString();
        score = in.readInt();
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
    }
}
