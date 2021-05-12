package com.example.paindiary.ui.painData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.paindiary.db.AppDatabase;
import com.example.paindiary.R;
import com.example.paindiary.db.User;
import com.example.paindiary.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PainDataEntryFragment extends Fragment {
    private FirebaseAuth mAuth;
    private PainDataEntryViewModel PainDataEntryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        PainDataEntryViewModel =
                new ViewModelProvider(this).get(PainDataEntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pain_data_entry, container, false);
        final TextView textView = root.findViewById(R.id.nav_pain_data_entry);
        Button saveBtn = (Button) root.findViewById(R.id.save_button);
        Spinner spinner = (Spinner) root.findViewById(R.id.PDE_spinner);
        Spinner pain_level_spinner = (Spinner) root.findViewById(R.id.PDE_spinner_pain);
        Button editBtn = (Button) root.findViewById(R.id.edit_button);
        EditText editSteps = root.findViewById(R.id.steps_edit);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //当item被选择后调用此方法
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //获取我们所选中的内容
                String s = parent.getItemAtPosition(position).toString();
                //弹一个吐司提示我们所选中的内容
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
            //只有当patent中的资源没有时，调用此方法
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pain_level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //当item被选择后调用此方法
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //获取我们所选中的内容
                String s = parent.getItemAtPosition(position).toString();
                //弹一个吐司提示我们所选中的内容
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
            //只有当patent中的资源没有时，调用此方法
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        RatingBar mRatingBar = (RatingBar) root.findViewById(R.id.ratingBar_PDE);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rating = (float) Math.ceil(rating);
                String mood = "medium";
                switch ((int) rating){
                    case 1:
                        mood = "very low";
                        break;
                    case 2:
                        mood = "low";
                        break;
                    case 4:
                        mood = "good";
                        break;
                    case 5:
                        mood = "very good";
                        break;
                    default:
                        mood = "medium";
                }
                //Log.d("PainDataEntryFragment","mood");
                Toast.makeText(getContext(), "Your mood is " + mood, Toast.LENGTH_SHORT).show();
            }
        });
        // save to room database
        saveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("PainDataEntryFragment","user init");
                mAuth = FirebaseAuth.getInstance();
                String email = mAuth.getCurrentUser().getEmail();
                String painLocation = spinner.getSelectedItem().toString();
                String painLevel = pain_level_spinner.getSelectedItem().toString();
                int mood = (int)Math.ceil(mRatingBar.getRating());
                String[] stepsArray = editSteps.getText().toString().split("\\s*,\\s*");
                User user = new User();
                user.email = email;
                user.painLevel = painLevel;
                user.painLocation = painLocation;
                user.mood = mood;
                user.steps = Integer.parseInt(stepsArray[0]);
                user.today_steps = Integer.parseInt(stepsArray[1]);
                user.temp = HomeFragment.temp - (float)273.15;
                user.humidity = HomeFragment.humidity;
                user.pressure = HomeFragment.pressure;
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                user.date = format.format(date);
                Log.d("PainDataEntryFragment","user created");
                //Toast.makeText(getActivity().getApplicationContext(),painLevel,Toast.LENGTH_SHORT).show();
                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                        AppDatabase.class,"user").allowMainThreadQueries().build();
                //db.userDao().insert(user);
                if (db.userDao().getDate(user.email).equals(user.date)){
                    db.userDao().updateUsers(user.email, painLocation, painLevel, mood, user.date, user.steps, user.today_steps);
                    saveBtn.setEnabled(false);
//                    spinner.setEnabled(false);
//                    pain_level_spinner.setEnabled(false);
//                    editSteps.setEnabled(false);
//                    mRatingBar.setEnabled(false);
                    Toast.makeText(getActivity().getApplicationContext(),"save success!",Toast.LENGTH_SHORT).show();
                } else {
                    db.userDao().insert(user);
                    saveBtn.setEnabled(false);
                    Toast.makeText(getActivity().getApplicationContext(),"save success!",Toast.LENGTH_SHORT).show();
                }
                Log.d("PainDataEntryFragment", db.userDao().getDate(user.email));
                //Log.d("PainDataEntryFragment", String.valueOf(user.temp) + ' ' + user.humidity + ' ' + user.pressure);
//                saveBtn.setEnabled(false);
//                spinner.setEnabled(false);
//                pain_level_spinner.setEnabled(false);
//                editSteps.setEnabled(false);
//                mRatingBar.setEnabled(false);
                //else {
//                    db.userDao().insert(user);
//                    //Toast.makeText(getActivity().getApplicationContext(),user.uid,Toast.LENGTH_SHORT).show();
//                    Log.d("PainDataEntryFragment", user.date);
//                }
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBtn.setEnabled(true);
                spinner.setEnabled(true);
                pain_level_spinner.setEnabled(true);
                editSteps.setEnabled(true);
                mRatingBar.setEnabled(true);
            }
        });
        PainDataEntryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}