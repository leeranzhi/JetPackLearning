package com.leecode1988.viewmodeltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  MyViewModel myViewModel;
  TextView textView;
  Button button1, button2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

    textView = findViewById(R.id.textView);

    textView.setText(String.valueOf(myViewModel.number));

    button1 = findViewById(R.id.button);
    button2 = findViewById(R.id.button2);
    button1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        myViewModel.number++;
        textView.setText("" + myViewModel.number);
      }
    });

    button2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        myViewModel.number += 2;
        textView.setText("" + myViewModel.number);
      }
    });

  }
}
