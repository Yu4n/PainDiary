package com.example.paindiary.ui.DailyRecord;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiary.R;

public class DailyRecordFragment extends Fragment {

    private DailyRecordViewModel DailyRecordViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DailyRecordViewModel =
                new ViewModelProvider(this).get(DailyRecordViewModel.class);
        View root = inflater.inflate(R.layout.fragment_daily_record, container, false);
        final TextView textView = root.findViewById(R.id.daily_record_editText);
        DailyRecordViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}