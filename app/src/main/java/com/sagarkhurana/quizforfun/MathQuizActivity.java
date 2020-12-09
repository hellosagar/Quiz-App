package com.sagarkhurana.quizforfun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sagarkhurana.quizforfun.other.Constants;
import com.sagarkhurana.quizforfun.other.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MathQuizActivity extends AppCompatActivity {

    private int currentQuestionIndex = 0;
    private TextView tvQuestion, tvQuestionNumber;
    private Button btnNext;
    private List<String> questions;
    private int correctQuestion = 0;
    private EditText etAnswer;
    private HashMap<String, String> questionsAnswerMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz);

        Intent getIntent = getIntent();
        questionsAnswerMap = (HashMap<String, String>) Utils.getRandomMathQuestions(Constants.QUESTION_SHOWING);
        questions = new ArrayList<>(questionsAnswerMap.keySet());

        tvQuestion = findViewById(R.id.textView8);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumberMath);
        btnNext = findViewById(R.id.btnNextQuestionMath);
        etAnswer = findViewById(R.id.tietEnterAnswerMath);

        findViewById(R.id.imageViewStartQuiz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String answer = etAnswer.getText().toString();

                if (answer.isEmpty()){
                    Toast.makeText(MathQuizActivity.this, "Answer cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (questionsAnswerMap.get(questions.get(currentQuestionIndex)).equals(answer)) {
                    correctQuestion++;
                }
                currentQuestionIndex++;

                if (btnNext.getText().equals(getString(R.string.next))){
                    displayNextQuestions();
                }else{
                    Intent intentResult = new Intent(MathQuizActivity.this,FinalResultActivity.class);
                    intentResult.putExtra(Constants.SUBJECT,getString(R.string.math));
                    intentResult.putExtra(Constants.CORRECT,correctQuestion);
                    intentResult.putExtra(Constants.INCORRECT,Constants.QUESTION_SHOWING - correctQuestion);
                    intentResult.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentResult);
                    finish();
                }
            }
        });

        displayData();
    }

    private void displayNextQuestions() {
        etAnswer.setText("");
        tvQuestion.setText(questions.get(currentQuestionIndex) + " = ?");
        tvQuestionNumber.setText("Current Question: " + (currentQuestionIndex + 1));

        if (currentQuestionIndex == Constants.QUESTION_SHOWING  - 1){
            btnNext.setText(getText(R.string.finish));
        }
    }

    private void displayData() {
        tvQuestion.setText(questions.get(currentQuestionIndex) + " = ?");
        tvQuestionNumber.setText("Current Question: " + (currentQuestionIndex + 1));
    }

}