package com.projectakhir.holidaycalendar.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.applikeysolutions.cosmocalendar.selection.SingleSelectionManager;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.projectakhir.holidaycalendar.R;
import com.projectakhir.holidaycalendar.adapter.CalendarEventAdapter;
import com.projectakhir.holidaycalendar.database.AppDatabase;
import com.projectakhir.holidaycalendar.database.CalendarEventModel;
import com.projectakhir.holidaycalendar.view.activity.MainActivity;
import com.projectakhir.holidaycalendar.view.activity.ReadCalendarEventActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;


public class CalendarFragment extends Fragment {
    private CalendarView calendarView;
    private EditText etEvent;
    private Button btnAddEvent, btnShowData;
    private AppDatabase appDatabase;
    private Context context;


    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarView = view.findViewById(R.id.cosmo_calendar);
        etEvent = view.findViewById(R.id.et_event);
        btnAddEvent = view.findViewById(R.id.btn_save);
        btnShowData = view.findViewById(R.id.btn_show_data);
        context = view.getContext();

        calendarView.setCalendarOrientation(0);
        calendarView.setWeekendDays(new HashSet(){{
            add(Calendar.SATURDAY);
            add(Calendar.SUNDAY);
        }});

        if (appDatabase == null) {
            appDatabase = AppDatabase.database(getContext());
        }

        calendarView.setSelectionType(SelectionType.SINGLE);

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (etEvent.getText().toString().equals("")) {
                        Toast.makeText(getContext(), "Please Fill the Form", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (calendarView.getSelectionManager() instanceof SingleSelectionManager) {
                            List<Calendar> calendars = calendarView.getSelectedDates();
                            if (calendars.isEmpty()) {
                                Toast.makeText(getContext(), "Please Select Date", Toast.LENGTH_SHORT).show();
                            } else {
                                Date date = calendars.get(0).getTime();
                                SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH);
                                String formattedDate = sdf.format(date);

                                CalendarEventModel calendarEventModel = new CalendarEventModel();
                                calendarEventModel.setEventName(etEvent.getText().toString());
                                calendarEventModel.setDate(formattedDate);

                                appDatabase.calendarEventDAO().insertCalendarEvent(calendarEventModel);

                                Log.d("CalendarFragment", "Add Event Succeed");
                                Toast.makeText(getContext(), "Add Event Succeed", Toast.LENGTH_SHORT).show();
                                resetForm();
                            }
                        }
                    }
                }catch (Exception ex) {
                    Log.e("CalendarFragment", "Add Event Failed, msg: "+ex.getMessage());
                    Toast.makeText(getContext(), "Add Event Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReadCalendarEventActivity.class);
                context.startActivity(intent);
            }
        });
    }

    private void resetForm() {
        calendarView.clearSelections();
        etEvent.setText("");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
}