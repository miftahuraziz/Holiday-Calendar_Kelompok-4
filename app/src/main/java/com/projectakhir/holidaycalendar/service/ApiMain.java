package com.projectakhir.holidaycalendar.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMain {
    private Retrofit retrofit;

    public HolidayRepository getApiHoliday() {
        String BASE_URL = "https://holidayapi.com/v1/";

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(HolidayRepository.class);
    }
}
