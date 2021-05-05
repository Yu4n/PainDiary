package com.example.paindiary.ui.DailyRecord;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DailyRecordViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DailyRecordViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}