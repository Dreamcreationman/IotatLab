package com.iotatlab.sigin;

import android.os.Build;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iotatlab.R;
import com.iotatlab.splash.WelcomeActivity;
import com.iotatlab.utils.ActivityManagement;
import com.iotatlab.utils.ViewUtils;

public class SignActivity extends AppCompatActivity implements View.OnClickListener {

    private long firstTime=0;

    private SigninFragment signinFragment;
    private SignupFragment signupFragment;

    private FragmentManager fragmentManager;

    private Button signinBtn,signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.setImmersive(this );
        setContentView(R.layout.activity_sign);
        ActivityManagement.finishSingleActivityByClass(WelcomeActivity.class);
        ActivityManagement.addActivity(this);
        init();
        if (getIntent() != null){
            setSelection(getIntent().getIntExtra("sign",0));
        }else{
            setSelection(0);
        }


    }

    private void init() {

        fragmentManager = getFragmentManager();

        signinBtn = findViewById(R.id.in_btn);
        signupBtn = findViewById(R.id.up_btn);

        signinBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);


    }

    private void setSelection(int index){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            signinBtn.setBackground(getDrawable(R.drawable.signin_bg_page));
            signupBtn.setBackground(getDrawable(R.drawable.signup_bg_page));
        }else{
            signinBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.signin_bg_page));
            signupBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.signup_bg_page));
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.animator_enter , R.anim.animator_exit);

        if (signinFragment != null ){
            fragmentTransaction.hide(signinFragment);
        }
        if (signupFragment != null){
            fragmentTransaction.hide(signupFragment);
        }

        switch (index){
            case 0:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    signinBtn.setBackground(getDrawable(R.drawable.signin_bg_page_chosen));
                    signupBtn.setBackground(getDrawable(R.drawable.signup_bg_page));
                }else{
                    signinBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.signin_bg_page_chosen));
                    signupBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.signup_bg_page));
                }
                if (signinFragment == null){
                    signinFragment = new SigninFragment();
                    fragmentTransaction.add(R.id.fragment_container,signinFragment);
                    Log.e("@@setSelection","Null");
                }else{
                    fragmentTransaction.show(signinFragment);
                    Log.e("@@setSelection","notNull");
                }

                break;
            case 1:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    signupBtn.setBackground(getDrawable(R.drawable.signup_bg_page_chosen));
                    signinBtn.setBackground(getDrawable(R.drawable.signin_bg_page));
                }else{
                    signupBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.signup_bg_page_chosen));
                    signinBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.signin_bg_page));
                }

                if (signupFragment == null){
                    signupFragment = new SignupFragment();
                    fragmentTransaction.add(R.id.fragment_container,signupFragment);
                }else {
                    fragmentTransaction.show(signupFragment);
                }
                break;
        }

        fragmentTransaction.commit();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.in_btn:
                setSelection(0);
                break;
            case R.id.up_btn:
                setSelection(1);
                break;
        }
    }

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
