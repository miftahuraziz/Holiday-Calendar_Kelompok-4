package com.projectakhir.holidaycalendar.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.projectakhir.holidaycalendar.R;
import com.projectakhir.holidaycalendar.adapter.CalendarEventAdapter;
import com.projectakhir.holidaycalendar.database.AppDatabase;
import com.projectakhir.holidaycalendar.database.CalendarEventModel;

import java.util.ArrayList;

public class ReadCalendarEventActivity extends AppCompatActivity {
    private Button btnDelete;
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private CalendarEventAdapter calendarEventAdapter;
    private ArrayList<CalendarEventModel> listCalendarEvent = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_calendar_event);

        btnDelete = findViewById(R.id.btn_delete_event);

        recyclerView = findViewById(R.id.rv_fragment_calendar);
        calendarEventAdapter = new CalendarEventAdapter(getApplicationContext());
        calendarEventAdapter.notifyDataSetChanged();

        if (appDatabase == null) {
            appDatabase = AppDatabase.database(getApplicationContext());
        }
        listCalendarEvent.addAll(appDatabase.calendarEventDAO().getCalendarEvent());
        calendarEventAdapter.setData(listCalendarEvent);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(calendarEventAdapter);

        setTitle("Event Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}