package com.example.game2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {

    private CallBack_Activity callBack_activityList;

    private View view;


    private Button main_BTN_left, main_BTN_right;

    private TextView main_TXV_timer, main_TXV_counter, main_TXV_press;

    private int timeLeft;

    private final int START_TIME = 10;
    private final int SECOND = 1000;

    private boolean isPressed;

    static Handler handler1 = new Handler();
    static Runnable myRun1;


    public GameFragment(){

    }

    public void setCallBack(CallBack_Activity _callBack_activityList) {
        this.callBack_activityList = _callBack_activityList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);
        Log.d("pttt", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("pttt", "onCreateView");
        if(view==null){
            view = inflater.inflate(R.layout.game_fragment, container, false);
        }

        findViews(view);

        isPressed = false;
        MainActivity.count = 0;
        main_TXV_counter.setText("");
        main_TXV_press.setVisibility(View.VISIBLE);

        //counter = 0;
        timeLeft = START_TIME;

        main_TXV_timer.setText("" + timeLeft);

        main_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPressed){

                    setCounter(++ MainActivity.count);
                    isPressed = false;
                }
            }
        });

        main_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPressed){
                    if(MainActivity.count == 0){
                        main_TXV_press.setVisibility(View.INVISIBLE);
                        startTimer();
                    }

                    setCounter(++ MainActivity.count);
                    isPressed = true;

                }
            }
        });


        return view;
    }

    private void gameOver() {
        callBack_activityList.goToGameOverPage();

    }

    private void findViews(View view) {
        main_BTN_left = view.findViewById(R.id.main_BTN_left);
        main_BTN_right = view.findViewById(R.id.main_BTN_right);
        main_TXV_timer = view.findViewById(R.id.main_TXV_timer);
        main_TXV_counter = view.findViewById(R.id.main_TXV_counter);
        main_TXV_press = view.findViewById(R.id.main_TXV_press);

    }

    private void setCounter(int counter){
        main_TXV_counter.setText("" + counter);
    }

    private void startTimer(){

        handler1 = new Handler();
        myRun1 = new Runnable() {
            @Override
            public void run() {
                countTime();
                startTimer();
                if(timeLeft == 0){
                    handler1.removeCallbacks(myRun1);
                    gameOver();
                }

            }
        };


        handler1.postDelayed(myRun1, SECOND);

    }

    private void countTime(){
        timeLeft --;
        main_TXV_timer.setText("" + timeLeft);

    }

    @Override
    public void onPause() {
        super.onPause();
        handler1.removeCallbacks(myRun1);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!(MainActivity.count == 0)){
            startTimer();
        }
    }
}