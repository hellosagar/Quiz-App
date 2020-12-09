package com.sagarkhurana.quizforfun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sagarkhurana.quizforfun.other.Constants;

public class QuizOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_option);

        CardView cvMath = findViewById(R.id.cvMath);
        CardView cvGeography = findViewById(R.id.cvGeography);
        CardView cvLiterature = findViewById(R.id.cvLiterature);

        findViewById(R.id.imageViewQuizOption).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cvMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizOptionActivity.this, MathQuizActivity.class);
                intent.putExtra(Constants.SUBJECT,getString(R.string.math));
                startActivity(intent);
            }
        });

        cvGeography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizOptionActivity.this, GeographyOrLiteratureQuizActivity.class);
                intent.putExtra(Constants.SUBJECT,getString(R.string.geography));
                startActivity(intent);
            }
        });

        cvLiterature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizOptionActivity.this, GeographyOrLiteratureQuizActivity.class);
                intent.putExtra(Constants.SUBJECT,getString(R.string.literature));
                startActivity(intent);
            }
        });

    }
}