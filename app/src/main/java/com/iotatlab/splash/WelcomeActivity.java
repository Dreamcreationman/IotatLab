package com.iotatlab.splash;

import android.graphics.Outline;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.iotatlab.R;
import com.iotatlab.utils.ViewUtils;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setImmersive(this);
        setContentView(R.layout.activity_welcome);
        init();

    }

    private void init() {

        if (Build.VERSION.SDK_INT>=21){
            getWindow().setEnterTransition(new Explode().setDuration(500));
            getWindow().setExitTransition(new Explode().setDuration(500));

        }


    }


}
