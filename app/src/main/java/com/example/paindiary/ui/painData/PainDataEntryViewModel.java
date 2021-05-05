package com.example.paindiary.ui.painData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PainDataEntryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PainDataEntryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}