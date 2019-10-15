package com.leecode.viewmodelrestore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
  // private MutableLiveData<Integer> number;
  private SavedStateHandle handle;

  public MyViewModel(SavedStateHandle handle) {
    this.handle = handle;
  }

  public MutableLiveData<Integer> getNumber() {
    // if (number == null) {
    //   number = new MutableLiveData<>();
    //   number.setValue(0);
    // }
    // return number;

    if (!handle.contains(MainActivity.KEY_NUMBER)) {
      handle.set(MainActivity.KEY_NUMBER, 0);
    }
    return handle.getLiveData(MainActivity.KEY_NUMBER);
  }

  public void add() {
    // number.setValue(number.getValue() + 1);
    getNumber().setValue(getNumber().getValue() + 1);
  }

}
