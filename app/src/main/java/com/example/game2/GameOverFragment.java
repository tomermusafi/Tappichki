package com.example.game2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameOverFragment extends Fragment {

    private CallBack_Activity callBack_activityList;

    MySharedPreferences msp;

    Gson gson;

    Score score ;

    List<Score> topRecordsArray = new ArrayList<>();

    private View view;
    private TextView GameOver_TXV_score, GameOver_TXV_play_again;



    public GameOverFragment(){

    }

    public GameOverFragment(MySharedPreferences msp) {
        this.msp = msp;
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
            view = inflater.inflate(R.layout.game_over_fragment, container, false);
        }

        findViews(view);

        score = new Score();


        GameOver_TXV_play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        setScore();


        gson = new Gson();
        saveScore();

        return view;
    }

    private void startGame() {
        callBack_activityList.goToGamePage();

    }

    private void findViews(View view) {
        GameOver_TXV_score = view.findViewById(R.id.GameOver_TXV_score);
        GameOver_TXV_play_again = view.findViewById(R.id.GameOver_TXV_play_again);


    }

    public void setScore() {
        Log.d("pttt", "setTitle");
        score.setScore(MainActivity.count);
        GameOver_TXV_score.setText("" +score.getScore());
    }

    private void saveScore(){

        String records = msp.getString("myRecords7", null);
        //record = new Record(score, currentDate, location, gameOverPage_EDT_name.getText().toString());
        if(records == null){
            //Log.d("lll", "rec1: "+ record);
            topRecordsArray.add(score);
            String json = gson.toJson(topRecordsArray);
            //Log.d("oooo", json);
            msp.putString("myRecords7", json);
        }else {
            topRecordsArray = new ArrayList(Arrays.asList(gson.fromJson(records, Score[].class)));
            //record = new Record(score, currentDate, location);
            //Log.d("lll", "rec: "+ record);
            topRecordsArray.add(score);
            //Log.d("lll", "arr: "+ topRecordsArray);
            Collections.sort(topRecordsArray, new Comparator<Score>() {
                @Override
                public int compare(Score a, Score b) {
                    return Integer.compare(b.getScore(), a.getScore());
                }

            });
            if(topRecordsArray.size() > 10){
                for (int i = 0; i < topRecordsArray.size(); i++) {
                    if(i > 9){
                        topRecordsArray.remove(i);
                    }
                }
                String json = gson.toJson(topRecordsArray);
                msp.putString("myRecords7", json);
            }else{
                String json = gson.toJson(topRecordsArray);
                msp.putString("myRecords7", json);
            }
            //Log.d("jjj", records);

        }
    }



}