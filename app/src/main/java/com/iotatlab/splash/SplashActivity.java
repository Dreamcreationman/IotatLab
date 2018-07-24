package com.iotatlab.splash;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.iotatlab.R;
import com.iotatlab.siginin.SigninActivity;
import com.iotatlab.utils.ActivityManagement;
import com.iotatlab.utils.ViewUtils;

import static com.iotatlab.utils.Preconditions.checkNotNull;

public class SplashActivity extends AppCompatActivity implements SplashContract.View{

    private SplashContract.Presenter mPresenter;
    protected ImageView logoIv;
    private Animation scaleLogoIn,scaleLogoOut;

    private long firstTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setImmersive(this);
        setContentView(R.layout.activity_splash);
        ActivityManagement.addActivity(this);

        init();

        mPresenter = new SplashPresenter(scaleLogoIn,this,scaleLogoOut,this);
    }

    private void init() {

        logoIv = findViewById(R.id.iv_logo);

        //开始时候的界面logo缩放效果
        scaleLogoIn = new ScaleAnimation(1.5f, 1.0f,1.5f,1.0f,0.5f,0.5f);
        scaleLogoIn.setDuration(1000);
        scaleLogoIn.setFillAfter(true);

        scaleLogoOut = new ScaleAnimation(1.0f,1.5f,1.0f,1.5f,0.5f,0.5f);
        scaleLogoOut.setDuration(800);
        scaleLogoIn.setFillAfter(true);
    }

    @Override
    public void doAnimationIn(Animation animation) {

        logoIv.startAnimation(animation);
    }

    @Override
    public void doAnimationOut(Animation animation) {

        logoIv.startAnimation(animation);

        if (Build.VERSION.SDK_INT>= 21){
            startActivity(new Intent(SplashActivity.this,WelcomeActivity.class), ActivityOptions.makeSceneTransitionAnimation(this,logoIv,"Logo").toBundle());
        }else {
            startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));
        }
    }

    @Override
    public void setPresenter(@NonNull SplashContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                long secondTime=System.currentTimeMillis();
                if(secondTime-firstTime>2000){
                    Toast.makeText(this,"再按一次退出物创",Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                    return true;
                }else{
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

}
