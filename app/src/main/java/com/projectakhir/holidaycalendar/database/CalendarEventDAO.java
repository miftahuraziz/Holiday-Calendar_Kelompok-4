package com.projectakhir.holidaycalendar.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CalendarEventDAO {
    @Insert
    long insertCalendarEvent(CalendarEventModel calendarEventModel);

    @Delete
    int deleteCalendarEvent(CalendarEventModel calendarEventModel);

    @Query("SELECT * FROM calendar_event_table")
    List<CalendarEventModel> getCalendarEvent();
}
