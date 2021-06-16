package com.projectakhir.holidaycalendar.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectakhir.holidaycalendar.R;
import com.projectakhir.holidaycalendar.adapter.HolidayAdapter;
import com.projectakhir.holidaycalendar.model.HolidaysItem;
import com.projectakhir.holidaycalendar.view.viewmodel.HolidayViewModel;

import java.util.ArrayList;

public class HolidayFragment extends Fragment {

    private HolidayAdapter holidayAdapter;
    private RecyclerView recyclerView;
    private HolidayViewModel holidayViewModel;
    private Context context;

    public HolidayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_holiday, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = view.getContext();

        holidayAdapter = new HolidayAdapter();
        holidayAdapter.notifyDataSetChanged();

        recyclerView = view.findViewById(R.id.rv_fragment_holiday);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        holidayViewModel = new ViewModelProvider(this).get(HolidayViewModel.class);
        holidayViewModel.setHoliday();
        holidayViewModel.getHoliday().observe(getViewLifecycleOwner(), getHoliday);

        recyclerView.setAdapter(holidayAdapter);
    }

    private Observer<ArrayList<HolidaysItem>> getHoliday = new Observer<ArrayList<HolidaysItem>>() {
        @Override
        public void onChanged(ArrayList<HolidaysItem> holidaysItems) {
            if (holidaysItems != null) {
                holidayAdapter.setData(context, holidaysItems);
            }
        }
    };
}