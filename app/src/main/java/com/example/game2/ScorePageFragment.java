package com.example.game2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class ScorePageFragment extends Fragment {

    private CallBack_Activity callBack_activityList;

    private View view;
    private Context context;

    private RecyclerView list_LST_scores;
    private Adapter_ScoreModel adapter_scoreModel;
    MySharedPreferences msp;
    Gson gson;

    public ScorePageFragment(){

    }

    public ScorePageFragment(Context context, MySharedPreferences msp) {

        this.context = context;
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
            view = inflater.inflate(R.layout.score_page_fragment, container, false);
        }

        findViews(view);
        gson = new Gson();

        initList();

        return view;
    }

    private void changeTextOfBFragment(String txt) {
        //callBack_activityList.changeText(txt);

    }

    private void findViews(View view) {
        list_LST_scores = view.findViewById(R.id.list_LST_score);

    }

    private void initList() {
        ArrayList<Score> scores = getScore();
        adapter_scoreModel = new Adapter_ScoreModel(context, scores);
        list_LST_scores.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        list_LST_scores.setLayoutManager(layoutManager);
        list_LST_scores.setAdapter(adapter_scoreModel);

        adapter_scoreModel.SetOnItemClickListener(new Adapter_ScoreModel.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Score score) {
                openScore(score);
            }
        });
    }

    private void openScore(Score score) {
        Toast.makeText(context, "" + score.getScore(), Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Score> getScore() {
        ArrayList<Score> scores = new ArrayList<>();
        String records = msp.getString("myRecords7",null);
        if(records != null) {
            scores = new ArrayList(Arrays.asList(gson.fromJson(records, Score[].class)));

        }
        return scores;
    }


}