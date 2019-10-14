package com.leecode1988.score;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.leecode1988.score.databinding.ActivityMainBinding;

/**
 * author:LeeCode
 * <p>
 * create:2019/9/26 0:25
 */
public class MainActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    ActivityMainBinding bindings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        binding.setVariable(BR.data, myViewModel);

        binding.setLifecycleOwner(this);
    }
}
