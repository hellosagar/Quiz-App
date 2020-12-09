package com.sagarkhurana.quizforfun.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "user")
public class User implements Parcelable {

    @ColumnInfo(name = "username")
    private final String username;
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "email")
    private final String email;
    @ColumnInfo(name = "password")
    private String password;

    public User(String username, @NonNull String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    protected User(Parcel in) {
        username = in.readString();
        email = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
