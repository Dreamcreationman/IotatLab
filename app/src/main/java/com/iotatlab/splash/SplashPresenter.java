package com.iotatlab.splash;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.animation.Animation;


import static com.iotatlab.utils.Preconditions.checkNotNull;

/**
 * CREATED BY Dream
 * DATE : 2018/7/23
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public class SplashPresenter implements SplashContract.Presenter {

    private Animation animationIn, animationOut;
    private SplashContract.View view;
    private Activity activity;

    public SplashPresenter(@NonNull Animation animationIn,
                           @NonNull SplashContract.View view,
                           @NonNull Animation animationOut,
                           @NonNull Activity activity){
        this.animationIn = checkNotNull(animationIn);
        this.view = checkNotNull(view);
        this.animationOut = checkNotNull(animationOut);
        this.activity = activity;
    }

    @Override
    public void subscribe() {
        view.doAnimationIn(animationIn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.doAnimationOut(animationOut);
            }
        }, 1500);
    }

    @Override
    public void unsubscribe() {

    }
}
