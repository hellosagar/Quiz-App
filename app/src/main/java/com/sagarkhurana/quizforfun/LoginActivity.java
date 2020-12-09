package com.sagarkhurana.quizforfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sagarkhurana.quizforfun.data.User;
import com.sagarkhurana.quizforfun.data.UserDatabase;
import com.sagarkhurana.quizforfun.data.UserDatabaseClient;
import com.sagarkhurana.quizforfun.other.SharedPref;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername,etPassword;

    @Override
    protected void onStart() {
        super.onStart();

        SharedPref sharedPref = SharedPref.getInstance();
        if (sharedPref.getUser(this)!=null){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_QuizForFun);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.tietUsername);
        etPassword = findViewById(R.id.tiePassword);
        TextView tvSignUp = findViewById(R.id.tvSignUp);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (!validaInputs(username,password)) return;

                LoginUserTask ut = new LoginUserTask(username,password);
                ut.execute();

            }
        });


        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }

    private boolean validaInputs(String username, String password) {

        if (username.isEmpty()){
            Toast.makeText(this, getString(R.string.username_cannot_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()){
            Toast.makeText(this, getString(R.string.password_cannot_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    class LoginUserTask extends AsyncTask<Void, Void, Void> {

        private final String username;
        private final String password;
        private ArrayList<User> users = new ArrayList<>();

        public LoginUserTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDatabase databaseClient = UserDatabaseClient.getInstance(getApplicationContext());
            users = (ArrayList<User>) databaseClient.userDao().observeAllUser();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            for (User user : users){
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
                    SharedPref sharedPref = SharedPref.getInstance();
                    sharedPref.setUser(LoginActivity.this,user);
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    return;
                }
            }
            Toast.makeText(LoginActivity.this, "User not exist", Toast.LENGTH_SHORT).show();

        }
    }

}


