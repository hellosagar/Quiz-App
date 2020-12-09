package com.sagarkhurana.quizforfun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sagarkhurana.quizforfun.data.User;
import com.sagarkhurana.quizforfun.data.UserDatabase;
import com.sagarkhurana.quizforfun.data.UserDatabaseClient;
import com.sagarkhurana.quizforfun.other.SharedPref;

public class EditPasswordActivity extends AppCompatActivity {

    private EditText etOldPassword,etNewPassword,etConfirmNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        etOldPassword = findViewById(R.id.tietPassword);
        etNewPassword = findViewById(R.id.tietPasswordNewPass);
        etConfirmNewPassword = findViewById(R.id.tietPasswordConfirmNewPass);
        Button btnSavePassword = findViewById(R.id.btnChangePassword);

        findViewById(R.id.imageViewEditPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String oldPassword = etOldPassword.getText().toString();
                String newPassword = etNewPassword.getText().toString();
                String confirmNewPassword = etConfirmNewPassword.getText().toString();

                if (!validateInput(oldPassword,newPassword,confirmNewPassword)) return;

                changePassword(oldPassword, newPassword);
            }
        });


    }

    private void changePassword(String oldPassword,String newPassword) {

        User user = SharedPref.getInstance().getUser(this);
        if (!user.getPassword().equals(oldPassword)){
            Toast.makeText(this, "Please enter the right password", Toast.LENGTH_SHORT).show();
            return;
        }
        user.setPassword(newPassword);
        UpdatePasswordTask updatePasswordTask = new UpdatePasswordTask(user);
        updatePasswordTask.execute();

    }

    private boolean validateInput(String oldPassword, String newPassword, String confirmNewPassword) {

        if (oldPassword.isEmpty()){
            Toast.makeText(this, getString(R.string.old_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (newPassword.isEmpty()){
            Toast.makeText(this, getString(R.string.old_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (confirmNewPassword.isEmpty()){
            Toast.makeText(this, getString(R.string.old_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!confirmNewPassword.equals(newPassword)){
            Toast.makeText(this, getString(R.string.password_must_be_same), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (oldPassword.equals(newPassword)){
            Toast.makeText(this, getString(R.string.new_password_must_be_different), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    class UpdatePasswordTask extends AsyncTask<Void, Void, Void> {

        private final User user;

        public UpdatePasswordTask(User user) {
            this.user = user;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDatabase databaseClient = UserDatabaseClient.getInstance(getApplicationContext());
            databaseClient.userDao().updateUser(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(EditPasswordActivity.this, "Password Updated Successfully!", Toast.LENGTH_SHORT).show();
            SharedPref.getInstance().setUser(EditPasswordActivity.this,user);
            finish();
        }
    }

}