package com.bignerdranch.android.geoquiz;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.geoquiz.databinding.ActivityQuizBinding;
import com.bignerdranch.android.geoquiz.model.Quiz;

public class QuizActivity extends AppCompatActivity {

    private ActivityQuizBinding binding;
    private Quiz quiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        binding.falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNextQuestion();
            }
        });

        quiz = new Quiz();
        updateQuestionView();
        reset();
    }

    private void goToNextQuestion() {
        quiz.nextQuestion();
        updateQuestionView();
        reset();
    }

    private void checkAnswer(boolean userPressedTrue) {
        int answerColor;
        boolean answerIsTrue = quiz.getCurrentQuestion().isAnswerTrue();
        int messageResId;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            answerColor = getResources().getColor(R.color.green);
        } else {
            messageResId = R.string.incorrect_toast;
            answerColor = getResources().getColor(R.color.red);
        }

        binding.resultTextView.setText(messageResId);
        binding.resultTextView.setTextColor(answerColor);
        onQuestionAnswered();
    }

    private void updateQuestionView() {
        binding.questionTextView.setText(quiz.getCurrentQuestion().getTextResId());
    }

    private void reset() {
        binding.resultTextView.setText("");
        binding.trueButton.setEnabled(true);
        binding.falseButton.setEnabled(true);
        binding.nextButton.setEnabled(false);
    }

    private void onQuestionAnswered() {
        binding.trueButton.setEnabled(false);
        binding.falseButton.setEnabled(false);
        binding.nextButton.setEnabled(true);
    }

}
