package com.leecode.viewmodelrestore;

import android.renderscript.ScriptGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.SavedStateVMFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
  MyViewModel myViewModel;
  public final static String KEY_NUMBER = "my_number";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ViewDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    // myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

    myViewModel = ViewModelProviders.of(this, new SavedStateVMFactory(this)).get(MyViewModel.class);

    // if (savedInstanceState != null) {
    //   myViewModel.getNumber().setValue(savedInstanceState.getInt(KEY_NUMBER));
    // }
    viewDataBinding.setVariable(BR.data, myViewModel);
    viewDataBinding.setLifecycleOwner(this);
  }

  // @Override protected void onSaveInstanceState(@NonNull Bundle outState) {
  //   super.onSaveInstanceState(outState);
  //   outState.putInt(KEY_NUMBER, myViewModel.getNumber().getValue());
  // }
}
