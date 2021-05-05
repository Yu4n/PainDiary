package com.example.paindiary.ui.painData;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paindiary.HomeActivity;
import com.example.paindiary.R;
import com.example.paindiary.ui.home.HomeViewModel;

public class PainDataEntryFragment extends Fragment {

    private PainDataEntryViewModel PainDataEntryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        PainDataEntryViewModel =
                new ViewModelProvider(this).get(PainDataEntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pain_data_entry, container, false);
        final TextView textView = root.findViewById(R.id.nav_pain_data_entry);

        Spinner spinner = (Spinner) root.findViewById(R.id.PDE_spinner);
        Spinner pain_level_spinner = (Spinner) root.findViewById(R.id.PDE_spinner_pain);

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
                Toast.makeText(getContext(), "Your mood is " + mood, Toast.LENGTH_SHORT).show();
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