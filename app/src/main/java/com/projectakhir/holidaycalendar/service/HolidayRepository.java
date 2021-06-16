package com.projectakhir.holidaycalendar.service;

import com.projectakhir.holidaycalendar.model.HolidayResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HolidayRepository {
    @GET("holidays?pretty&key=b82bd941-dada-48d6-893f-924ed149ff69&country=ID&year=2020")
    Call<HolidayResponse> getHoliday();
}
