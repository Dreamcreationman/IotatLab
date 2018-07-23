package com.iotatlab.splash;

import android.content.Context;
import android.view.animation.Animation;

import com.iotatlab.base.BasePresenter;
import com.iotatlab.base.BaseView;

/**
 * CREATED BY Dream
 * DATE : 2018/7/23
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public interface SplashContract {

    interface View extends BaseView<Presenter>{
        void doAnimationIn(Animation animation);
        void doAnimationOut(Animation animation);
    }

    interface Presenter extends BasePresenter{


    }

}
