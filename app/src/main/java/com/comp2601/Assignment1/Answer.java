package com.comp2601.Assignment1;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {

    private String id;
    private final String answer;

    public Answer (String i, String a){
        id = i;
        answer = a;
    }

    protected Answer(Parcel in) {
        id = in.readString();
        answer = in.readString();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(answer);
    }
}
