package com.example.game2;

import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class Anim {


    public static void animateItCode(TextView imv) {
        imv.setScaleX(0.5f);
        imv.setScaleY(0.5f);
        imv.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(350)
                .setInterpolator(new AccelerateInterpolator())
                .start();

    }

}
