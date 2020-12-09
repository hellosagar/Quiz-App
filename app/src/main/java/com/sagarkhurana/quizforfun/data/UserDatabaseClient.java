package com.sagarkhurana.quizforfun.data;

import android.content.Context;

import androidx.room.Room;

public class UserDatabaseClient {

    private static final String DB_NAME = "user_db";
    private static UserDatabase  instance;

    public static synchronized UserDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(), UserDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public UserDatabase getUserDatabase() {
        return instance;
    }

}
