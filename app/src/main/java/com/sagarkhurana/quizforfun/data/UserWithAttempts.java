package com.sagarkhurana.quizforfun.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserWithAttempts implements Parcelable {

    @Embedded
    private User user;
    @Relation(
            parentColumn = "email",
            entityColumn = "email",
            entity = Attempt.class
    )
    private Attempt attempt;

    public UserWithAttempts(User user, Attempt attempt) {
        this.user = user;
        this.attempt = attempt;
    }

    protected UserWithAttempts(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        attempt = in.readParcelable(Attempt.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeParcelable(attempt, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserWithAttempts> CREATOR = new Creator<UserWithAttempts>() {
        @Override
        public UserWithAttempts createFromParcel(Parcel in) {
            return new UserWithAttempts(in);
        }

        @Override
        public UserWithAttempts[] newArray(int size) {
            return new UserWithAttempts[size];
        }
    };

    public User getUser() {
        return user;
    }

    public Attempt getAttempt() {
        return attempt;
    }
}
