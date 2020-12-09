package com.sagarkhurana.quizforfun.other;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.sagarkhurana.quizforfun.data.User;

public class SharedPref {

    private static SharedPref instance = null;

    private static final String sharedPreferencesName = "kevinSharedPref";

    private SharedPref() {
    }

    public static SharedPref getInstance() {
        if (instance == null) {
            instance = new SharedPref();
        }
        return instance;
    }

    public void setUser(Context context, User user){
        SharedPreferences pref = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Constants.USER,new Gson().toJson(user));
        editor.apply();
    }

    public User getUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
        return new Gson().fromJson(pref.getString(Constants.USER,""),User.class);
    }

    public void clearSharedPref(@NonNull Context context) {
        SharedPreferences pref = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }

}
