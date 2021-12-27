package com.example.braintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public void gamingLayoutAppears(View view){
        gamingLayout.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        mediaPlayer=MediaPlayer.create(this,R.raw.clock);
        mediaPlayer.start();
        nextQuestion();


        countDownTimer= new   CountDownTimer(30200,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                long seconds=millisUntilFinished/1000;
                timerTextView.setText(Long.toString(seconds)+ " s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
               exitButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                sumTextView.setEnabled(false);
                mediaPlayer.pause();
            }
        }.start();

    }
    public void exit(View view){
        finish();
        System.exit(0);
    }
    public void playAgain(View view){
        playAgain.setVisibility(View.INVISIBLE);
        exitButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        sumTextView.setEnabled(true);
        answersFound=0;
        questionsAsked=0;
        resultOnTop.setText(Integer.toString(answersFound) +"/"+Integer.toString(questionsAsked));
        timerTextView.setText("30s");
        countDownTimer.cancel();
        countDownTimer.start();
        answers.clear();

        nextQuestion();

    }

    public void nextQuestion(){


mediaPlayer.start();
        int a = rand.nextInt(30);
        int b = rand.nextInt(30);
        correctAnswerlocation=rand.nextInt(4);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        int correctAnswer=a+b;
        for (int i=0;i<4;i++){
            if (i==correctAnswerlocation){
                answers.add(correctAnswer);
            }
            else {
                answers.add(rand.nextInt(40));
            }
        }

        button0.setText(Integer.toString (answers.get(0)));
        button1.setText(Integer.toString (answers.get(1)));
        button2.setText(Integer.toString (answers.get(2)));
        button3.setText(Integer.toString (answers.get(3)));

    }

    public void checkAnswer(View view){

        if (view.getTag().equals(Integer.toString(correctAnswerlocation))){
           MediaPlayer mediaPlayerCorrect = MediaPlayer.create(MainActivity.this,R.raw.correct);
           mediaPlayerCorrect.start();
            answersFound++;
            questionsAsked++;


        }
        else {
          //  result.setText("Incorrect");
            MediaPlayer mediaPlayerIncorrect = MediaPlayer.create(MainActivity.this,R.raw.incorrect);
            mediaPlayerIncorrect.start();
            questionsAsked++;
        }

        resultOnTop.setText(Integer.toString(answersFound)+"/"+Integer.toString(questionsAsked) );
        answers.clear();
        nextQuestion();

    }

ConstraintLayout gamingLayout;
TextView sumTextView;
Random rand = new Random();
MediaPlayer mediaPlayer;
ArrayList<Integer> answers=new ArrayList<>();
Button button0;
Button button1;
Button button2;
Button button3;
Button exitButton;
TextView result;
TextView resultOnTop;
TextView textView5;
TextView timerTextView;
Button playAgain;
Button startButton;
int correctAnswerlocation;
    int answersFound=0;
    int questionsAsked = 0;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamingLayout=findViewById(R.id.gamingLayout);
        resultOnTop=findViewById(R.id.correctAnswerTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        exitButton = findViewById(R.id.exitButton);
        startButton=findViewById(R.id.start_button);
        playAgain=findViewById(R.id.playAgain);
        sumTextView=findViewById(R.id.sumTextView);
        timerTextView=findViewById(R.id.timerTextView);



        }
}