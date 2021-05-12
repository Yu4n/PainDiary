package com.example.paindiary.ui.reports;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paindiary.R;
import com.example.paindiary.db.AppDatabase;
import com.example.paindiary.db.User;
import com.example.paindiary.db.UserDao;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportsFragment extends Fragment {
    PieDataSet pieDataSet_step;
    ArrayList pieEntries_step;
    PieData pieData_step;
    ArrayList PieEntryLabels;
    PieDataSet pieDataSet_pain;
    ArrayList pieEntries_pain;
    PieData pieData_pain;
    List<UserDao.Count_Pain> Count_Pain;
    User steps;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportsFragment newInstance(String param1, String param2) {
        ReportsFragment fragment = new ReportsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class,"user").allowMainThreadQueries().build();
        Count_Pain = db.userDao().loadLocationsByIds(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        //Log.d("ReportsFragment", Count_Pain.get(0).painLocation + ' ' + Count_Pain.get(0).count);
        steps = db.userDao().getSteps(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_reports, container, false);
        PieChart pieChart_step = root.findViewById(R.id.pieChart_step);
        PieChart pieChart_pain = root.findViewById(R.id.pieChart_pain);
        getEntries_step();
        pieDataSet_step = new PieDataSet(pieEntries_step, "");
        pieData_step = new PieData(pieDataSet_step);
        pieChart_step.setData(pieData_step);
        pieDataSet_step.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet_step.setSliceSpace(2f);
        pieDataSet_step.setValueTextColor(Color.WHITE);
        pieDataSet_step.setValueTextSize(10f);
        //pieChart_pain.setDrawEntryLabels(true);
        //pieDataSet_step.setSliceSpace(5f);
        getEntries_pain();
        pieDataSet_pain = new PieDataSet(pieEntries_pain, "");
        pieData_pain = new PieData(pieDataSet_pain);
        pieChart_pain.setData(pieData_pain);
        pieDataSet_pain.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet_pain.setSliceSpace(2f);
        pieDataSet_pain.setValueTextColor(Color.WHITE);
        pieDataSet_pain.setValueTextSize(10f);
        //pieDataSet_pain.setSliceSpace(5f);
        return root;
    }

    private void getEntries_step() {
        pieEntries_step = new ArrayList<>();
        pieEntries_step.add(new PieEntry(steps.today_steps, "Today steps"));
        pieEntries_step.add(new PieEntry(steps.steps - steps.today_steps, "Remaining steps"));
    }

    private void getEntries_pain() {
        pieEntries_pain = new ArrayList<>();
        for(int i = 0; i < Count_Pain.size(); ++i){
            Log.d("ReportsFragment", Count_Pain.get(i).painLocation + ' ' + Count_Pain.get(i).count);
            pieEntries_pain.add(new PieEntry(Count_Pain.get(i).count, Count_Pain.get(i).painLocation));
        }
    }
}