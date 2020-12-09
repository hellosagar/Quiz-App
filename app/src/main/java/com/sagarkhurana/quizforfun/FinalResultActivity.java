package com.sagarkhurana.quizforfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sagarkhurana.quizforfun.data.Attempt;
import com.sagarkhurana.quizforfun.data.UserDatabase;
import com.sagarkhurana.quizforfun.data.UserDatabaseClient;
import com.sagarkhurana.quizforfun.other.Constants;
import com.sagarkhurana.quizforfun.other.SharedPref;
import com.sagarkhurana.quizforfun.other.Utils;

import java.util.Calendar;

public class FinalResultActivity extends AppCompatActivity {

    private TextView tvSubject, tvCorrect, tvIncorrect, tvEarned, tvOverallStatus, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        Intent intent = getIntent();
        int correctAnswer = intent.getIntExtra(Constants.CORRECT, 0);
        int incorrectAnswer = intent.getIntExtra(Constants.INCORRECT, 0);
        String subject = intent.getStringExtra(Constants.SUBJECT);
        String email = SharedPref.getInstance().getUser(this).getEmail();
        int earnedPoints = (correctAnswer * Constants.CORRECT_POINT) - (incorrectAnswer * Constants.INCORRECT_POINT);

        tvSubject = findViewById(R.id.textView16);
        tvCorrect = findViewById(R.id.textView19);
        tvIncorrect = findViewById(R.id.textView27);
        tvEarned = findViewById(R.id.textView28);
        tvOverallStatus = findViewById(R.id.textView29);
        tvDate = findViewById(R.id.textView30);

        findViewById(R.id.imageViewFinalResultQuiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btnFinishQuiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FinalResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Attempt attempt = new Attempt(
                Calendar.getInstance().getTimeInMillis(),
                subject,
                correctAnswer,
                incorrectAnswer,
                earnedPoints,
                email
        );

        getOverAllPoints(attempt);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void getOverAllPoints(Attempt attempt) {
        GetOverallPointsTask getOverallPointsTask = new GetOverallPointsTask(attempt);
        getOverallPointsTask.execute();
    }

    class GetOverallPointsTask extends AsyncTask<Void, Void, Void> {

        private final Attempt attempt;
        private int overallPoints = 0;

        public GetOverallPointsTask(Attempt attempt) {
            this.attempt = attempt;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDatabase databaseClient = UserDatabaseClient.getInstance(getApplicationContext());
            overallPoints = databaseClient.userDao().getOverAllPoints(attempt.getEmail());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            attempt.setOverallPoints(overallPoints + attempt.getEarned());
            displayData(attempt);
            SaveUserAttemptTask saveUserAttemptTask = new SaveUserAttemptTask(attempt);
            saveUserAttemptTask.execute();

            Log.d("OVERALL POINTS", String.valueOf(overallPoints));
        }
    }

    private void displayData(Attempt attempt) {

        tvSubject.setText(attempt.getSubject());
        tvCorrect.setText(String.valueOf(attempt.getCorrect()));
        tvIncorrect.setText(String.valueOf(attempt.getIncorrect()));
        tvEarned.setText(String.valueOf(attempt.getEarned()));
        tvOverallStatus.setText(String.valueOf(attempt.getOverallPoints()));
        tvDate.setText(Utils.formatDate(attempt.getCreatedTime()));

    }

    class SaveUserAttemptTask extends AsyncTask<Void, Void, Void> {

        private Attempt attempt;

        public SaveUserAttemptTask(Attempt attempt) {
            this.attempt = attempt;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            UserDatabase databaseClient = UserDatabaseClient.getInstance(getApplicationContext());
            databaseClient.userDao().insertAttempt(attempt);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("Attempt Saved", attempt.toString());
        }
    }
}