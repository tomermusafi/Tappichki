package com.example.game2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class HomePageFragment extends Fragment {

    private CallBack_Activity callBack_activityList;

    private View view;
    private TextView home_TXV_start;

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
            view = inflater.inflate(R.layout.home_page_fragment, container, false);
        }

        findViews(view);

        home_TXV_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        Anim.animateItCode(home_TXV_start);


        return view;
    }

    private void startGame() {
        callBack_activityList.goToGamePage();

    }

    private void findViews(View view) {
        home_TXV_start = view.findViewById(R.id.home_TXV_start);

    }



}