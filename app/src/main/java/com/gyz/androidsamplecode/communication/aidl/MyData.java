package com.gyz.androidsamplecode.communication.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyData implements Parcelable {
    public int number;
    public String text;

    public MyData(int number, String text) {
        this.number = number;
        this.text = text;
    }

    protected MyData(Parcel in) {
        // 需要跟写入顺序保持一致
        number = in.readInt();
        text = in.readString();
    }

    public static final Parcelable.Creator<MyData> CREATOR = new Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel in) {
            return new MyData(in);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(text);
    }

    // 需要手动实现这个方法，否则报错
    public void readFromParcel(Parcel in){
        // 需要跟写入顺序保持一致
        number = in.readInt();
        text = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Getters and setters omitted for brevity
}