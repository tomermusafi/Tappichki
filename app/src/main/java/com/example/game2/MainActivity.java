package com.example.game2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    //private Counter count;

    private BannerFragment bannerFragment;
    private HomePageFragment homePageFragment;
    private GameFragment gameFragment;
    private ScorePageFragment scorePageFragment;
    private GameOverFragment gameOverFragment;

    static int count;
    static boolean isHomePage;

    MySharedPreferences msp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        msp = new MySharedPreferences(this);

       // count = new Counter(0);
        count = 0;
        isHomePage = true;

        bannerFragment = new BannerFragment();
        homePageFragment = new HomePageFragment();
        gameFragment = new GameFragment();
        scorePageFragment = new ScorePageFragment(this, msp);
        gameOverFragment = new GameOverFragment(msp);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.maim_FL_banner, bannerFragment);
        transaction.replace(R.id.maim_FL_game, homePageFragment);
        transaction.commit();

        bannerFragment.setCallBack(myCallBack);
        homePageFragment.setCallBack(myCallBack);
        gameFragment.setCallBack(myCallBack);
        scorePageFragment.setCallBack(myCallBack);
        gameOverFragment.setCallBack(myCallBack);

    }


    CallBack_Activity myCallBack = new CallBack_Activity() {


        @Override
        public void goToHomePage() {
            isHomePage = true;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.maim_FL_game, homePageFragment);
            transaction.commit();
        }

        @Override
        public void goToScorePage() {
            isHomePage = false;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.maim_FL_game, scorePageFragment);
            transaction.commit();
        }

        @Override
        public void goToGamePage() {
            isHomePage = false;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.maim_FL_game, gameFragment);
            transaction.commit();
        }

        @Override
        public void goToGameOverPage() {
            isHomePage = false;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.maim_FL_game, gameOverFragment);
            transaction.commit();
        }
    };


    @Override
    public void onBackPressed() {
        Log.d("pttt", "" + isHomePage);
        if(isHomePage){
            super.onBackPressed();
            finish();
        }
        else {
            isHomePage = true;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.maim_FL_game, homePageFragment);
            transaction.commit();
        }
    }
}
