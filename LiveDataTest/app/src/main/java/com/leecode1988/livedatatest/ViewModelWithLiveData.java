package com.leecode1988.livedatatest;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * author:LeeCode
 * create:2019/9/3 1:07
 */
public class ViewModelWithLiveData extends ViewModel {
    private MutableLiveData<Integer> likeNumber;

    public ViewModelWithLiveData() {
        likeNumber = new MutableLiveData<>();
        likeNumber.setValue(0);
    }

    public MutableLiveData<Integer> getLikeNumber() {
        return likeNumber;
    }

    public void addLikeNumber(int n) {
        likeNumber.setValue(likeNumber.getValue() + n);
    }

}
