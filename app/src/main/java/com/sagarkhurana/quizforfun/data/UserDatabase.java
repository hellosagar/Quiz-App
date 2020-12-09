package com.sagarkhurana.quizforfun.data;

import android.app.Person;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sagarkhurana.quizforfun.other.Convertor;

@Database(
entities = {User.class,Attempt.class},
        exportSchema = false,
        version = 1
)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}