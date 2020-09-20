package com.example.onlinefreelaceapp.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class PageViewModelOrderToBeReceived extends ViewModel {

    private MutableLiveData<Integer> mIndex1 = new MutableLiveData<>();
    private LiveData<String> mText1 = Transformations.map(mIndex1, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex1.setValue(index);
    }

    public LiveData<String> getText() {
        return mText1;
    }
}