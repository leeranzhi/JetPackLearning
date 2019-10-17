package com.leecode1988.livedatatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.leecode1988.livedatatest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ViewModelWithLiveData viewModelWithLiveData;
//    TextView textView;
//    Button button1, button2;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


//        textView = findViewById(R.id.textView);
//        button1 = findViewById(R.id.button);
//        button2 = findViewById(R.id.button2);

        viewModelWithLiveData = ViewModelProviders.of(this).get(ViewModelWithLiveData.class);
        binding.setData(viewModelWithLiveData);
        //LiveData的自我监听
        binding.setLifecycleOwner(this);


//        //LiveData的观察
//        viewModelWithLiveData.getLikeNumber().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                binding.textView.setText(String.valueOf(integer));
//            }
//        });
//
//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewModelWithLiveData.addLikeNumber(1);
//            }
//        });
//        binding.button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewModelWithLiveData.addLikeNumber(2);
//            }
//        });
    }
}
