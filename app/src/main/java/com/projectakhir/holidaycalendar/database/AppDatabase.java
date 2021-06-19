package com.projectakhir.holidaycalendar.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

@Database(entities = {CalendarEventModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CalendarEventDAO calendarEventDAO();
    private static AppDatabase appDatabase;

    public static  AppDatabase database(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    "dbEvent"
            ).allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
