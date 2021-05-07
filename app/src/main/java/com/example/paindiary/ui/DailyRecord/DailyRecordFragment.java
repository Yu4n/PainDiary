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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.paindiary.R;
import com.example.paindiary.db.AppDatabase;
import com.example.paindiary.db.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DailyRecordFragment extends Fragment {

    private DailyRecordViewModel DailyRecordViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DailyRecordViewModel =
                new ViewModelProvider(this).get(DailyRecordViewModel.class);
        View root = inflater.inflate(R.layout.fragment_daily_record, container, false);
        //final TextView textView = root.findViewById(R.id.dai);
        DailyRecordViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                // textView.setText(s);
            }
        });
        //RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_daily_record, container,false);
        AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class,"user").allowMainThreadQueries().build();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        List<User> list= db.userDao().loadAllByIds(email);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview_daily_record);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ItemsAdapter(getActivity(), list));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return root;
    }
}