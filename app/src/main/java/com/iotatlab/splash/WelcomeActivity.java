package com.iotatlab.splash;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.iotatlab.R;
import com.iotatlab.sigin.SignActivity;
import com.iotatlab.siginin.SigninActivity;
import com.iotatlab.siginup.SignupActivity;
import com.iotatlab.utils.ActivityManagement;
import com.iotatlab.utils.ViewUtils;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button signinBtn,signupBtn;
    private ImageView logoIv;

    private long firstTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setImmersive(this);
        ActivityManagement.addActivity(this);
        setContentView(R.layout.activity_welcome);

        init();

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityManagement.finishSingleActivityByClass(SplashActivity.class);
            }
        }, 500);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    private void init() {

        if (Build.VERSION.SDK_INT>=21){
            getWindow().setEnterTransition(new Explode().setDuration(500));
        }

        signinBtn = findViewById(R.id.btn_signin);
        signupBtn = findViewById(R.id.btn_signup);
        logoIv = findViewById(R.id.iv_logocenter);

        signupBtn.setOnClickListener(this);
        signinBtn.setOnClickListener(this);
        logoIv.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signin:
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                Intent intent1 = new Intent(WelcomeActivity.this,SignActivity.class);
                intent1.putExtra("sign",1);
                if (Build.VERSION.SDK_INT>= 21){
                    startActivity(intent1, compat.toBundle());
                }else {
                    startActivity(intent1);
                }
                break;
            case R.id.btn_signup:
                ActivityOptionsCompat compat2 = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
                Intent intent2 = new Intent(WelcomeActivity.this,SignActivity.class);
                intent2.putExtra("sign",0);
                if (Build.VERSION.SDK_INT>= 21){
                    startActivity(intent2, compat2.toBundle());
                }else {
                    startActivity(intent2);
                }
                break;
            case R.id.iv_logocenter:
                Toast.makeText(WelcomeActivity.this,"物联网应用技术创新实践班",Toast.LENGTH_SHORT).show();
                break;
        }
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
