package com.example.colorclick;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameView7 extends AppCompatActivity {
    //buttons
    ImageButton btn_00, btn_01, btn_02, btn_03, btn_04, btn_05, btn_06,
            btn_07, btn_08, btn_09, btn_10, btn_11;
    ImageView matchTheColor;
    boolean rightColor = false;

    //Timer Variables
    TextView timer;
    public long counter = 5;
    public long counterRemaining;
    CountDownTimer count;
    ImageButton pauseButton;
    public long currentTime = 5000;
    public long goDownTime = 1000;
    public long temp = 0;
    Dialog pause;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game6);

        //CREATED ON GAME BOARD ON LOAD
        //DEFAULT IMAGE BUTTONS
        btn_00 = findViewById(R.id.button_00);
        btn_01 = findViewById(R.id.button_01);
        btn_02 = findViewById(R.id.button_02);
        btn_03 = findViewById(R.id.button_03);
        btn_04 = findViewById(R.id.button_04);
        btn_05 = findViewById(R.id.button_05);
        btn_06 = findViewById(R.id.button_06);
        btn_07 = findViewById(R.id.button_07);
        btn_08 = findViewById(R.id.button_08);
        btn_09 = findViewById(R.id.button_09);
        btn_10 = findViewById(R.id.button_10);
        btn_11 = findViewById(R.id.button_11);

        //DEFAULT IMAGE BUTTON REPRESENTING THE COLOR TO BE MATCHED
        matchTheColor = findViewById(R.id.imageView_color);
        matchTheColor.setClickable(false);


        btn_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_10.setVisibility(View. INVISIBLE);
                checkvisi();

            }
        });

        btn_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_05.setVisibility(View. INVISIBLE);
                checkvisi();
            }

        });



        //INITIALIZE THE PAUSE VARIABLE
        pause = new Dialog(this);
        pauseButton = (ImageButton) findViewById(R.id.button_pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPENS PAUSE MENU DIALOG
                pauseMenu(v);

                //CANCELS THE COUNT DOWN TIMER WHILE THE PAUSE MENU IS OPEN
                count.cancel();
            }
        });

        //CREATES AREA FOR TIMER ON GAME BOARD ON LOAD
        timer = (TextView) findViewById(R.id.timerView);

        //THE INITIAL COUNTDOWNTIMER IS CREATED UPON PLAYING THE GAME
        count = new CountDownTimer(currentTime, goDownTime) {
            public void onTick(long millisUntilFinished) {

                // Sets the timer area with the counter
                timer.setText(String.valueOf(counter));

                // Counter is deducted by 1 every second
                counter--;

                // Keeps track of the initial counter
                counterRemaining = counter;

                // Keeps track of the initial time left
                temp = millisUntilFinished;
            }

            //CALLS GAMEOVER PAGE ONCE TIMER RUNS OUT
            public void onFinish() {
                timer.setText("0");
                openGameOver(v);
            }
            //START THE TIMER
        }.start();
        //******************************************************//
    }//END OF ON-CREATE

    //PAUSE MENU METHOD
    public void pauseMenu(View v) {
        TextView closebutton;
        TextView resume;

        // Brings up the dialog when the pause button is clicked
        pause.setContentView(R.layout.pause_menu);

        // Recreates a CountDownTimer with accurate time every time the 'X' button is clicked
        closebutton = (TextView) pause.findViewById(R.id.closebutton);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Makes sure the counter is accurate upon resuming game
                counterRemaining++;

                // A new CountDownTimer is created upon closing the pause menu
                count = new CountDownTimer(temp, goDownTime) {
                    public void onTick(long millisUntilFinished) {

                        // Sets the timer area with the counter
                        timer.setText(String.valueOf((counterRemaining)));

                        // Tracks the time left
                        temp = millisUntilFinished;

                        // Counter is deducted by 1 every second
                        counterRemaining--;
                    }

                    // Calls GameOver page once time runs out
                    public void onFinish() {
                        timer.setText("0");
                        openGameOver(v);
                    }
                    // Starts the timer
                }.start();
                // Closes pause dialog
                pause.dismiss();
            }
        });

        // Recreates a CountDownTimer with accurate time every time the resume button is clicked
        resume = (Button) pause.findViewById(R.id.resumeButton);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Makes sure the counter is accurate upon resuming game
                counterRemaining++;

                // A new CountDownTimer is created upon closing the pause menu
                count = new CountDownTimer(temp, goDownTime) {
                    public void onTick(long millisUntilFinished) {

                        // Sets the timer area with the counter
                        timer.setText(String.valueOf((counterRemaining)));

                        // Tracks the time left
                        temp = millisUntilFinished;

                        // Counter is deducted by 1 every second
                        counterRemaining--;
                    }

                    // Calls GameOver page once time runs out
                    public void onFinish() {
                        timer.setText("0");
                        openGameOver(v);
                    }
                    // Starts the timer
                }.start();
                // Closes pause dialog
                pause.dismiss();
            }
        });
        //shows the popup menu
        pause.show();
    }//END OF PAUSE MENU

    // GAMEOVER SCREEN METHOD
    public void openGameOver(View v) {
        Intent intent = new Intent(this, GameOver.class);
        count.cancel();
        startActivity(intent);
    }//END OF GAMEOVER

    public void openGameView() {
        Intent intent = new Intent(this, GameView.class);
        count.cancel();
        startActivity(intent);
    }

    public void checkvisi(){
        if(btn_10.getVisibility() == View.INVISIBLE && btn_05.getVisibility() == View.INVISIBLE){
            count.cancel();
            openGameView();
        }
    }
}//END OF CLASS


