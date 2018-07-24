package com.iotatlab.sigin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iotatlab.R;

/**
 * CREATED BY Dream
 * DATE : 2018/7/24
 * MAIL : YANK.TENYOND@GMAIL.COM
 * FUNCTION :
 */
public class SignupFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup,container,false);
        return view;
    }
}
