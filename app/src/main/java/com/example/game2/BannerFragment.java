package com.example.game2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class BannerFragment extends Fragment {

    private CallBack_Activity callBack_activityList;

    private View view;
    private ImageView banner_IMG_home;
    private ImageView banner_IMG_score;

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
            view = inflater.inflate(R.layout.top_banner_fragment, container, false);
        }

        findViews(view);

        banner_IMG_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePage();
            }
        });
        banner_IMG_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePage();
            }
        });


        return view;
    }

    private void scorePage() {
        callBack_activityList.goToScorePage();

    }
    private void homePage() {
        callBack_activityList.goToHomePage();

    }

    private void findViews(View view) {
        banner_IMG_home = view.findViewById(R.id.banner_IMG_home);
        banner_IMG_score = view.findViewById(R.id.banner_IMG_score);
    }




}