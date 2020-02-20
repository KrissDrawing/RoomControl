package com.kriss.roomcontrol;

import android.os.Parcel;
import android.os.Parcelable;


public class Subtask implements Parcelable {

    public final String name;

    public Subtask(String name){
        this.name = name;
    }

    protected Subtask(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Subtask> CREATOR = new Creator<Subtask>() {
        @Override
        public Subtask createFromParcel(Parcel in) {
            return new Subtask(in);
        }

        @Override
        public Subtask[] newArray(int size) {
            return new Subtask[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
