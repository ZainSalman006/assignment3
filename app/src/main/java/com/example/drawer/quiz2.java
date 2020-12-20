package com.example.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class quiz2 extends AppCompatActivity {

    TextView textView;
    Button next,finish;
    RadioGroup rg;
    RadioButton rb1,rb2,rb3,rb4;
    public static int iterator,score;
    TextView timer;

    private static final long START_TIME_IN_MILLIS = 15000;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                nextMCQS();
            }
        }.start();
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timer.setText(timeLeftFormatted);
    }

    String questions[] = {
            "The term ‘Computer’ is derived from__________?",
            "Who is the father of Computer?",
            "The basic operations performed by a computer are__________?",
            "Who is the father of Internet ?",
            "If a computer has more than one processor then it is known as__________?",
            "A light sensitive device that converts drawing, printed text or other images into digital form is___________?",
            "WWW stands for___________?",
            "A collection of system programs that controls and co-ordinates the overall operations of a computer system is called____________?",
            "What type of operating system MS-DOS is?",
            "Which technology is used in compact disks?"
    };
    String answers[] = {"Latin","Charles Babbage","All the above","Vint Cerf",
            "Multiprocessor","Scanner","World Wide Web","Operating system","Command Line Interface","Laser"};
    String opt[] = {
            "Latin","German","French","Arabic",
            "Allen Turing","Charles Babbage","Simur Cray","Augusta Adaming",
            "Arithmetic operation","Logical operation","Storage and relative","All the above",
            "Chares Babbage","Vint Cerf","Denis Riche","Martin Cooper",
            "Uni-process","Multiprocessor","Multi-threaded","Multi-programming",
            "Keyboard","Scanner","OMR","None of these",
            "World Whole Web","Wide World Web","Web World Wide","World Wide Web",
            "System software","Operating system","Utility program","Device driver",
            "Command Line Interface","Graphical User Interface","Multitasking","Menu Driven Interface",
            "Mechanical","Electrical","Electro Magnetic","Laser"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        timer = findViewById(R.id.timer);
        next=(Button)findViewById(R.id.next);
        finish=(Button)findViewById(R.id.finish);
        textView=(TextView) findViewById(R.id.textView);

        rg=(RadioGroup)findViewById(R.id.rg);
        rb1=(RadioButton)findViewById(R.id.rb1);
        rb2=(RadioButton)findViewById(R.id.rb2);
        rb3=(RadioButton)findViewById(R.id.rb3);
        rb4=(RadioButton)findViewById(R.id.rb4);
        textView.setText(questions[iterator]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);

        startTimer();
        score=0;
        iterator=0;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.cancel();
                mTimeLeftInMillis = START_TIME_IN_MILLIS;
                if(rg.getCheckedRadioButtonId()==-1)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please select a choice", Toast.LENGTH_LONG);

                    toast.show();
                    return;
                }
                RadioButton selected_ans = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                String answer = selected_ans.getText().toString();
                if(answer.equals(answers[iterator])) {
                    score++;
                    Log.i("score",score+"");
                }
                startTimer();
                iterator++;

                if(iterator<questions.length)
                {
                    textView.setText(questions[iterator]);
                    rb1.setText(opt[iterator*4]);
                    rb2.setText(opt[iterator*4 +1]);
                    rb3.setText(opt[iterator*4 +2]);
                    rb4.setText(opt[iterator*4 +3]);
                }
                else
                {
                    dotask();
                }
                rg.clearCheck();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotask();
            }
        });

    }
    public void  dotask()
    {
        Intent in = new Intent(getApplicationContext(),quiz1.class);
        in.putExtra("score",score+"");
        startActivity(in);
    }

    public void nextMCQS(){
        mCountDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        iterator++;
        textView.setText(questions[iterator]);
        rb1.setText(opt[iterator*4]);
        rb2.setText(opt[iterator*4 +1]);
        rb3.setText(opt[iterator*4 +2]);
        rb4.setText(opt[iterator*4 +3]);
        rg.clearCheck();
        startTimer();
    }

}