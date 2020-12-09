package com.sagarkhurana.quizforfun.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "attempt")
public class Attempt implements Parcelable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "createdTimeAttempt")
    private final long createdTime;
    @ColumnInfo(name = "subject")
    private final String subject;
    @ColumnInfo(name = "correct")
    private final int correct;
    @ColumnInfo(name = "incorrect")
    private final int incorrect;
    @ColumnInfo(name = "earned")
    private final int earned;
    @ColumnInfo(name = "email")
    private final String email;
    @ColumnInfo(name = "overallPoints")
    private int overallPoints;

    public Attempt(long createdTime, String subject, int correct, int incorrect, int earned, String email) {
        this.createdTime = createdTime;
        this.subject = subject;
        this.correct = correct;
        this.incorrect = incorrect;
        this.earned = earned;
        this.email = email;
    }

    public void setOverallPoints(int overallPoints) {
        this.overallPoints = overallPoints;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public String getSubject() {
        return subject;
    }

    public int getCorrect() {
        return correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public int getEarned() {
        return earned;
    }

    public String getEmail() {
        return email;
    }

    public int getOverallPoints() {
        return overallPoints;
    }

    public static Creator<Attempt> getCREATOR() {
        return CREATOR;
    }

    protected Attempt(Parcel in) {
        createdTime = in.readLong();
        subject = in.readString();
        correct = in.readInt();
        incorrect = in.readInt();
        earned = in.readInt();
        email = in.readString();
        overallPoints = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(createdTime);
        dest.writeString(subject);
        dest.writeInt(correct);
        dest.writeInt(incorrect);
        dest.writeInt(earned);
        dest.writeString(email);
        dest.writeInt(overallPoints);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Attempt> CREATOR = new Creator<Attempt>() {
        @Override
        public Attempt createFromParcel(Parcel in) {
            return new Attempt(in);
        }

        @Override
        public Attempt[] newArray(int size) {
            return new Attempt[size];
        }
    };
}
